package com.api.tests;

import org.testng.annotations.Test;

import com.api.base.BaseTest;

public class ConfigTest extends BaseTest {
	
	@Test
	public void verifyBaseUrl() {
		
		System.out.println("Base URL : "+io.restassured.RestAssured.baseURI);
		
	}
	
	
	
	

}
