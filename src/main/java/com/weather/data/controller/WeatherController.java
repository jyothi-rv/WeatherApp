package com.weather.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.data.model.WeatherData;
import com.weather.data.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {
	
	@Autowired
	    private  WeatherService weatherService;
	    
	    @GetMapping("/{city}")
	    public ResponseEntity<WeatherData> getWeather(@PathVariable String city) {
	    	WeatherData weatherData = weatherService.getWeather(city);
	        return ResponseEntity.ok(weatherData);
	    }
	    @PostMapping("/add")
	    public ResponseEntity<WeatherData> getWeathertwo(@RequestBody String city) {
	    	WeatherData weatherData = weatherService.getWeather(city);
	        return ResponseEntity.ok(weatherData);
	    }

	   
	   

}
