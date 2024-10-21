package com.weather.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.weather.data.model.WeatherData;
import com.weather.data.repository.WeatherDataRepository;
import com.weather.data.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {
	
	@Autowired
	private  WeatherService weatherService;
	
	 @Autowired
	 private WeatherDataRepository weatherDataRepository;
	    
		/*
		 * @GetMapping("/{city}") public ResponseEntity<WeatherData>
		 * getWeather(@PathVariable String city) { WeatherData weatherData =
		 * weatherService.getWeather(city); return ResponseEntity.ok(weatherData); }
		 * 
		 * @PostMapping("/add") public ResponseEntity<WeatherData>
		 * getWeathertwo(@RequestBody String city) { WeatherData weatherData =
		 * weatherService.getWeather(city); return ResponseEntity.ok(weatherData); }
		 */
	 @GetMapping("/{city}")
	    public WeatherData getWeather(@PathVariable String city) throws JsonMappingException, JsonProcessingException {
	        WeatherData weatherData = weatherService.fetchWeatherData(city);
	        System.out.println("weather_data"+weatherData);
	        weatherDataRepository.save(weatherData);
	        return weatherData;
	    }

	   
	   

}
