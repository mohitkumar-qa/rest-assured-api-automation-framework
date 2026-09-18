package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import com.api.base.BaseTest;
import com.api.endpoints.CommonEndpoints;
import com.api.requests.RequestSpec;
import com.api.requests.ResponseSpec;

public class PingTest extends BaseTest {

	@Test
	public void verifyHealthCheck() {
		
		given()
			.spec(RequestSpec.getRequestSpec())
		.when()
			.get(CommonEndpoints.HEALTH_CHECK)
		.then()
			.log().all()
			.spec(ResponseSpec.getResponseSpec())
			.statusCode(201);
	}
	
	
}
