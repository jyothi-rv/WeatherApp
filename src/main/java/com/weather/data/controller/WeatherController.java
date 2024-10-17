package com.weather.data.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.data.model.WeatherData;
import com.weather.data.repository.WeatherDataRepository;
import com.weather.data.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {
	
	@Autowired
	  private WeatherDataRepository weatherDataRepository;
	
	
	    private final WeatherService weatherService;
	    @Autowired
	    public WeatherController(WeatherService weatherService) {
	        this.weatherService = weatherService;
	    }

	    @GetMapping("/{city}")
	    public ResponseEntity<WeatherData> getWeather(@PathVariable String city) {
	    	WeatherData weatherData = weatherService.getWeather(city);
	        return ResponseEntity.ok(weatherData);
	    }

	   
	   

}
