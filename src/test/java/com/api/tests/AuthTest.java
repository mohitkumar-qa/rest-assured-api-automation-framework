package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import com.api.base.BaseTest;
import com.api.endpoints.AuthEndpoints;
import com.api.requests.RequestSpec;

import io.restassured.response.Response;

public class AuthTest extends BaseTest{

	@Test
	public static String getToken() {
		
		String requestBody=
				"""
				{
					"username":"admin",
					"password":"password123"
				
				}
				
				
				""";
		
		Response response = 
				given()
					.spec(RequestSpec.getRequestSpec())
					.body(requestBody)
				.when()
					.post(AuthEndpoints.CREATE_TOKEN);
		
		response.then()
					.statusCode(200);
		
		return response.jsonPath().getString("token");
					
		
	}
	
	
}
	
	
	
	
	
	
