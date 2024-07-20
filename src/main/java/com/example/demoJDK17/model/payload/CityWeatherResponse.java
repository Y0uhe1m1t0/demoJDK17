package com.example.demoJDK17.model.payload;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString

public class CityWeatherResponse implements Serializable {
	private String mensaje;
	private Object object;
}
