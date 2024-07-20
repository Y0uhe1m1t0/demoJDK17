package com.example.weather_microservice.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.weather_microservice.model.CityWeather;
import com.example.weather_microservice.model.dto.CityWeatherDTO;
import com.example.weather_microservice.repository.CityWeatherRepository;
import com.example.weather_microservice.service.ICityWeather;

import reactor.core.publisher.Mono;

@Service
public class CityWeatherImpl implements ICityWeather {
	
	@Autowired
	private CityWeatherRepository cityWeatherRepository;
	
	@Value("${openweather.url}")
	private String openWeatherUrl;
	
	@Value("${openweather.appid}")
	private String openWeatherAppId;

	@Transactional
	@Override
	public CityWeatherDTO save(CityWeatherDTO cityWeather) {
		CityWeather savedCityWeather = cityWeatherRepository.save(CityWeather.builder()
				.cityName(cityWeather.getCityName())
				.weatherResponse(cityWeather.getWeatherResponse())
				.lastRequest(cityWeather.getLastRequest())
				.build());
		return CityWeatherDTO.builder()
				.cityName(savedCityWeather.getCityName())
				.weatherResponse(savedCityWeather.getWeatherResponse())
				.lastRequest(savedCityWeather.getLastRequest())
				.build();
	}
	
	public String findByCityName(String cityName) {
		WebClient clientOW = WebClient.builder().baseUrl(openWeatherUrl).build();
		String owResponse = clientOW.get().uri(uriBuilder -> uriBuilder.path("/weather")
				.queryParam("q", cityName)
				.queryParam("appId", openWeatherAppId)
				.build())
		.retrieve()
		.bodyToMono(String.class)
		.onErrorResume(e -> Mono.justOrEmpty(cityWeatherRepository.findByCityName(cityName).orElse(new CityWeather()).getWeatherResponse()))
		.block();
		
		if(owResponse != null) {
			CityWeatherDTO cityResponse = CityWeatherDTO.builder().cityName(cityName).weatherResponse(owResponse)
					.lastRequest(LocalDateTime.now())
					.build();
			
			save(cityResponse);
		}
		return owResponse;
	}
	
	public List<CityWeatherDTO> getAllCityWeathers(Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
		
		Page<CityWeather> pagedResult = cityWeatherRepository.findAll(paging);
		
		if(pagedResult.hasContent()) {
			return pagedResult.map(
					entity -> { 
						return CityWeatherDTO
								.builder()
								.id(entity.getId())
								.cityName(entity.getCityName())
								.weatherResponse(entity.getWeatherResponse())
								.lastRequest(entity.getLastRequest())
								.build();
					}).getContent();
		}
		else {
			return new ArrayList<CityWeatherDTO>();
		}
	}
}
