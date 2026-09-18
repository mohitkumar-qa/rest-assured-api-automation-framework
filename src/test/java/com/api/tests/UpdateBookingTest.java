package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.endpoints.BookingEndpoints;
import com.api.pojo.Booking;
import com.api.pojo.BookingDates;
import com.api.requests.RequestSpec;
import com.api.requests.ResponseSpec;
import com.api.utils.AuthManager;
import com.api.utils.BookingData;

import io.restassured.response.Response;

public class UpdateBookingTest extends BaseTest {

	int bookingId;
		
		@Test
		public void createBooking() {
				
				Booking booking = BookingData.createDefaultBooking();
				
				Response response =
						given()
							.spec(RequestSpec.getRequestSpec())
						//	.log().all()
							.body(booking)
						.when()	
							.post(BookingEndpoints.CREATE_BOOKING);
						
				response.then()
						//	.log().all()
							.spec(ResponseSpec.getResponseSpec())
							.statusCode(200);
				
				bookingId = response.jsonPath().getInt("bookingid");
				System.out.println("Created Booking ID: "+bookingId);
						
			}
			
			
			@Test(dependsOnMethods  ="createBooking")
			public void updatedBooking() {
				
				Booking booking = new Booking();
				
				booking.setFirstname("Rahul");
				booking.setLastname("Sharma");
		        booking.setTotalprice(500);
		        booking.setDepositpaid(false);

		        BookingDates dates = new BookingDates();
		        dates.setCheckin("2026-10-01");
		        dates.setCheckout("2026-10-10");

		        booking.setBookingdates(dates);

				Response response =
						given()
							.spec(RequestSpec.getRequestSpec())
							.cookie("token",AuthManager.getToken())
							.body(booking)
						.when()
							.put(BookingEndpoints.UPDATE_BOOKING,bookingId);
							
				response.then()
					.spec(ResponseSpec.getResponseSpec())
					.log().all()
					.statusCode(200);
				
				//Deserializtion
				Booking updatedBooking = response.as(Booking.class);
				
				Assert.assertEquals(updatedBooking.getFirstname(), "Rahul");
				
				Assert.assertEquals(
						updatedBooking.getBookingdates().getCheckin(), 
						"2026-10-01");
				
				
			}
		
		
		
		
		
		
		
	}
	
	
	

