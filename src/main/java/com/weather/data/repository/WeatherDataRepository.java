package com.weather.data.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.weather.data.model.WeatherData;

@EnableScan
public interface WeatherDataRepository extends CrudRepository<WeatherData, String>{

}
