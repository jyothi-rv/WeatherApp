package com.weather.data.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamoDBConfig {
	/*
	 * @Value("${aws.access.key}") private String awsAccessKey;
	 * 
	 * @Value("${aws.access.secret-key}") private String awsSecretKey;
	 */

    @Value("${amazon.dynamodb.endpoint:}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.aws.region}")
    private String awsRegion;
	/*
	 * @Value("${aws.region}") private String awsRegion;
	 */
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
    	  AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard()
                  .withCredentials(new InstanceProfileCredentialsProvider(false))
                  .withRegion(awsRegion);
        if (!amazonDynamoDBEndpoint.isEmpty()) {
            builder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, awsRegion));
        }
        return builder.build();
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
        return new DynamoDBMapper(amazonDynamoDB);
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
