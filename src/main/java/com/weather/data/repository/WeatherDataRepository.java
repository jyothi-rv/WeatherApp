package com.weather.data.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.weather.data.model.WeatherData;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;

/*
 * @EnableScan public interface WeatherDataRepository extends
 * CrudRepository<WeatherData, String>{
 * 
 * }
 */
@Repository
public class WeatherDataRepository {

    @Autowired
    private DynamoDbClient dynamoDbClient;

    public void save(WeatherData weatherData) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s(weatherData.getId()).build());
        item.put("name", AttributeValue.builder().s(weatherData.getName()).build());
        //item.put("data", AttributeValue.builder().s(weatherData.getData()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName("WeatherData")
                .item(item)
                .build();

        dynamoDbClient.putItem(request);
    }
}