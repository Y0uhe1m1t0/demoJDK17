package com.example.weather_microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.weather_microservice.model.dto.CityWeatherDTO;
import com.example.weather_microservice.model.payload.CityWeatherResponse;
import com.example.weather_microservice.service.impl.CityWeatherImpl;

@RestController
@RequestMapping("/api/v2")
public class CityWeatherController {
	@Autowired
	private CityWeatherImpl cityWeatherService;
	
	@GetMapping("cityWeather/{name}")
    public ResponseEntity<?> showByName(@PathVariable String name){
		String weatherResponse = null;
		try {
			weatherResponse = cityWeatherService.findByCityName(name);
			return new ResponseEntity(CityWeatherResponse.builder()
					.mensaje(weatherResponse != null && weatherResponse != ""?"Información obtenida":"No se pudo obtener información. Intente mas tarde.")
					.object(weatherResponse)
					.build(), weatherResponse != null && weatherResponse != ""?HttpStatus.OK:HttpStatus.NOT_FOUND);
		}catch(DataAccessException dAE) {
			return new ResponseEntity(CityWeatherResponse.builder()
					.mensaje(dAE.getMessage())
					.object(null)
					.build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	@GetMapping("getHistory")
    public ResponseEntity<?> getCitiesHistory(@RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "lastRequest") String sortBy){
		List<CityWeatherDTO> history = null;
		try {
			history = cityWeatherService.getAllCityWeathers(pageNo, pageSize, sortBy);
        /*return new ResponseEntity<List<CityWeatherDTO>>(history, new HttpHeaders(), HttpStatus.OK);*/
			return new ResponseEntity(CityWeatherResponse.builder()
					.mensaje("Últimas 10 consultas")
					.object(history)
					.build(), HttpStatus.OK);
		}catch (DataAccessException dAE) {
			return new ResponseEntity(CityWeatherResponse.builder()
					.mensaje(dAE.getMessage())
					.object(null)
					.build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	@PostMapping("cityWeather")
    public ResponseEntity<?> create(@RequestBody CityWeatherDTO cityWeather){
		CityWeatherDTO cityWeatherDto = null;
		try {
			cityWeatherDto = cityWeatherService.save(cityWeather);
			//return cityWeatherService.save(cityWeather);
			return new ResponseEntity(CityWeatherResponse.builder()
					.mensaje("Nuevo registro almacenado")
					.object(cityWeatherDto)
					.build(), HttpStatus.CREATED);
		}catch (DataAccessException dAE) {
			return new ResponseEntity(CityWeatherResponse.builder()
					.mensaje(dAE.getMessage())
					.object(null)
					.build(), HttpStatus.METHOD_NOT_ALLOWED);
		}
    }
}
