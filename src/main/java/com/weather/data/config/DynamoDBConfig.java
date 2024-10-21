package com.weather.data.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDBConfig {
	/*
	 * @Value("${aws.access.key}") private String awsAccessKey;
	 * 
	 * @Value("${aws.access.secret-key}") private String awsSecretKey;
	 */

	@Value("${aws-endpoint}")
	private String amazonDynamoDBEndpoint;
	/*
	 * @Value("${aws.region}") private String awsRegion;
	 */
    @Bean
	public DynamoDbClient dynamoDbClient() {
		AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard()
				.withCredentials(new InstanceProfileCredentialsProvider(false));

		if (!amazonDynamoDBEndpoint.isEmpty()) {
			builder.withEndpointConfiguration(
					new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, "us-east-1"));
		}

		return DynamoDbClient.builder().endpointOverride(URI.create(amazonDynamoDBEndpoint)).region(Region.US_EAST_1)
				.build();
	}
	/*
	 * @Bean public AWSCredentials amazonAWSCredentials(){ return new
	 * BasicAWSCredentials(awsAccessKey, awsSecretKey); }
	 * 
	 * 
	 * @Bean public AWSCredentialsProvider amazonAWSCredentialsProvider(){ return
	 * new AWSStaticCredentialsProvider(amazonAWSCredentials()); }
	 * 
	 * @Bean public AmazonDynamoDB amazonDynamoDB(){ return
	 * AmazonDynamoDBClientBuilder.standard() .withEndpointConfiguration(new
	 * AwsClientBuilder.EndpointConfiguration(awsDynamoDBEndPoint, awsRegion))
	 * .withCredentials(amazonAWSCredentialsProvider()) .build(); }
	 */

   
}
