package com.example.weather_microservice.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.weather_microservice.model.CityWeather;

@Repository
public interface CityWeatherRepository extends CrudRepository<CityWeather, Integer>, PagingAndSortingRepository<CityWeather, Integer> {
	public Optional<CityWeather> findByCityName(String name);
}
