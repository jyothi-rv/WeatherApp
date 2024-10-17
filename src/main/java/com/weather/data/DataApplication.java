package com.weather.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.weather.data.service.WeatherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class DataApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(DataApplication.class, args);
	}
	

}
