package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import com.api.base.BaseTest;
import com.api.endpoints.BookingEndpoints;
import com.api.pojo.Booking;
import com.api.requests.RequestSpec;
import com.api.requests.ResponseSpec;
import com.api.utils.BookingData;

import io.restassured.response.Response;

public class GetBookingTest extends BaseTest {
	
	
	int bookingId;

	@Test
public void createBooking() {
		
		Booking booking = BookingData.createDefaultBooking();		
		Response response =
				given()
					.spec(RequestSpec.getRequestSpec())
					.body(booking)
				.when()	
					.post(BookingEndpoints.CREATE_BOOKING);
				
		response.then()
					.spec(ResponseSpec.getResponseSpec())
					.statusCode(200);
					
		bookingId = response.jsonPath().getInt("bookingid");
				
	}
	
	
	@Test(dependsOnMethods  ="createBooking")
	public void getBooking() {
		
		
		Response response = 
				given()
					.spec(RequestSpec.getRequestSpec())
				.when()
					.get(BookingEndpoints.GET_BOOKING,bookingId);
		
		response.then()
					.spec(ResponseSpec.getResponseSpec())
					.statusCode(200)
					.body(matchesJsonSchemaInClasspath(
							"schemas/booking-schema.json"));
		
		
		Booking b = response.as(Booking.class);
		
		Assert.assertEquals(b.getFirstname(), "Mohit");
		Assert.assertEquals(b.getLastname(), "Kumar");
		Assert.assertEquals(b.getTotalprice(), 150);
		Assert.assertTrue(b.isDepositpaid());
		
		Assert.assertEquals(b.getBookingdates().getCheckin(), "2026-08-10");
		Assert.assertEquals(b.getBookingdates().getCheckout(), "2026-09-20");
	}
	
	

}
