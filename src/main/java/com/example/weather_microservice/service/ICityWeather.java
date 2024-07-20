package com.example.weather_microservice.service;

import com.example.weather_microservice.model.dto.CityWeatherDTO;

public interface ICityWeather {
	CityWeatherDTO save(CityWeatherDTO cityWeather);
	String findByCityName(String cityName);
}
