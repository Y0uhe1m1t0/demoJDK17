package com.example.demoJDK17.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class CityWeatherDTO {
	private Integer id;
	private String cityName;
	private String weatherResponse;
	private LocalDateTime lastRequest;
}
