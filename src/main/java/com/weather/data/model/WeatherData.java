package com.weather.data.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "WeatherData")
public class WeatherData {
		@DynamoDBHashKey(attributeName = "id")
    	private String id;
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		@DynamoDBAttribute(attributeName = "name")
	    private String name;

	    
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		@DynamoDBAttribute(attributeName = "data")
	    private String data;

		
   
}
