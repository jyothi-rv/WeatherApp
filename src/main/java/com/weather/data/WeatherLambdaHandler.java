package com.weather.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.boot.SpringApplication;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

/*
 * public class WeatherLambdaHandler implements RequestStreamHandler<Map<String,
 * String>, APIGatewayProxyResponseEvent> { private final WeatherService
 * weatherService = new WeatherService(); private final AmazonDynamoDB dynamoDB
 * = AmazonDynamoDBClientBuilder.defaultClient(); private final DynamoDBMapper
 * mapper = new DynamoDBMapper(dynamoDB);
 * 
 * @Override public APIGatewayProxyResponseEvent handleRequest(Map<String,
 * String> event, Context context) { APIGatewayProxyResponseEvent response = new
 * APIGatewayProxyResponseEvent(); try { String city = event.get("city"); if
 * (city == null || city.isEmpty()) { response.setStatusCode(400);
 * response.setBody("City parameter is missing"); return response; }
 * 
 * String weatherData = weatherService.getWeather(city); WeatherData data = new
 * WeatherData(); String id = UUID.randomUUID().toString(); data.setCity(id);
 * data.setCity(city); data.setData(weatherData); mapper.save(data);
 * 
 * response.setStatusCode(200); response.setBody(weatherData); } catch
 * (Exception e) { response.setStatusCode(500);
 * response.setBody("An error occurred: " + e.getMessage()); } return response;
 * } }
 */
public class WeatherLambdaHandler implements RequestStreamHandler{

	private static final SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    static {
        try {
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(DataApplication.class);
        } catch (ContainerInitializationException e) {
            throw new RuntimeException("Could not initialize Spring Boot application", e);
        }
    }

    
	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		// TODO Auto-generated method stub
		 handler.proxyStream(input, output, context);
	}
	
}