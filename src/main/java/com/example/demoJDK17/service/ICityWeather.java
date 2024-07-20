package com.example.demoJDK17.service;

import com.example.demoJDK17.model.dto.CityWeatherDTO;

public interface ICityWeather {
	CityWeatherDTO save(CityWeatherDTO cityWeather);
	String findByCityName(String cityName);
}
