package com.api.base;

import org.testng.annotations.BeforeClass;

import com.api.utils.ConfigReader;

import io.restassured.RestAssured;

public class BaseTest {

	
	@BeforeClass
	public void setup() {
		
		ConfigReader.loadProperties();
		RestAssured.baseURI = ConfigReader.getBaseUrl();
		
		
	}
	public static String getBaseUrl() {
	
	return ConfigReader.getBaseUrl();
	}
	
}
