package com.weather.data.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.data.model.WeatherApiResponse;
import com.weather.data.model.WeatherData;

@Service
public class WeatherService {
	
	 private static final String API_KEY = "07c192d3724b4f557b2eda61a31e6bc5";
	 private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
	/*
	 * private static final String API_KEY = "07c192d3724b4f557b2eda61a31e6bc5";
	 * private static final String BASE_URL =
	 * "http://api.openweathermap.org/data/2.5/weather";
	 * 
	 * public String getWeather(String city) { RestTemplate restTemplate = new
	 * RestTemplate(); String url = String.format("%s?q=%s&appid=%s", BASE_URL,
	 * city, API_KEY); return restTemplate.getForObject(url, String.class); }
	 */
	private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public WeatherService(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper, DynamoDBMapper dynamoDBMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public WeatherData getWeather(String city) {
    	 String url = String.format("%s?q=%s&appid=%s", BASE_URL, city, API_KEY);
        //String url = "https://api.example.com/weather/" + city;
        
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println(response);
        try {
            WeatherApiResponse apiResponse = objectMapper.readValue(response.getBody(), WeatherApiResponse.class);
            String jsonString = objectMapper.writeValueAsString(apiResponse);
            System.out.println(jsonString);
            WeatherData weatherData = mapToWeatherData(apiResponse,city);
            dynamoDBMapper.save(weatherData);
            return weatherData;
        } catch (IOException e) {
            throw new RuntimeException("Error parsing weather API response", e);
        }
    }

    private WeatherData mapToWeatherData(WeatherApiResponse apiResponse,String city) {
    	String url = String.format("%s?q=%s&appid=%s", BASE_URL, city, API_KEY);
        WeatherData weatherData = new WeatherData();
        weatherData.setId(apiResponse.getId());
        weatherData.setName(apiResponse.getName());
        weatherData.setData(restTemplate.getForObject(url, String.class));
        // Additional mapping logic
        return weatherData;
    }

   
	
}
