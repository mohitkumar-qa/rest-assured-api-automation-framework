package com.api.tests;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import com.api.base.BaseTest;
import com.api.endpoints.BookingEndpoints;
import com.api.pojo.Booking;
import com.api.pojo.BookingDates;
import com.api.requests.RequestSpec;
import com.api.requests.ResponseSpec;
import com.api.utils.BookingData;

import io.restassured.response.Response;

public class NegativeBookingTest extends BaseTest {
	
	
	@Test
	public void createBookingWithInvalidData() {

	    String invalidBody =
	            """
	            {
	                "firstname": "Mohit",
	                "lastname": "Kumar",
	                "totalprice": "invalid",
	                "depositpaid": true,
	                "bookingdates": {
	                    "checkin": "2026-08-10",
	                    "checkout": "2026-09-20"
	                }
	            }
	            """;

	    Response response =
	            given()
	                .spec(RequestSpec.getRequestSpec())
	                .body(invalidBody)
	            .when()
	                .post(BookingEndpoints.CREATE_BOOKING);

	    response.then()
	            .log().all()
	            .statusCode(200)
	            .body("bookingid",notNullValue())
	            .body("booking.totalprice", nullValue());
	}
	
	
	@Test
	public void updateBookingWithInvalidToken() {
		
		//Step 1. Create a valid booking
		Booking booking = BookingData.createDefaultBooking();
		
		Response createResponse = 
					given()
						.spec(RequestSpec.getRequestSpec())
						.body(booking)
				.when()
					.post(BookingEndpoints.CREATE_BOOKING);
		
			createResponse.then()
						.statusCode(200);
		
		//Step 2. Extract dynamic booking ID
		    int bookingId = createResponse.jsonPath().getInt("bookingid");
			
		    System.out.println("Created Booking Id : "+bookingId);
		
		//Step 3. Prepare complete update Body    
		    Booking updateBooking = new Booking();
		    
		    updateBooking.setFirstname("Rahul");
		    updateBooking.setLastname("Sharm");
		    updateBooking.setTotalprice(500);
		    updateBooking.setDepositpaid(false);
		    
		    BookingDates date = new BookingDates();
		    date.setCheckin("2026-10-01");
		    date.setCheckout("2026-10-10");
		    
		    updateBooking.setBookingdates(date);
		    
		//Step 4. Send PUT with Invalid token    
		    
		    
		    
		
		    Response response = 
		    		given()
						.spec(RequestSpec.getRequestSpec())
						.cookie("token","invalid-Token")
						.body(updateBooking)
					.when()
						.put(BookingEndpoints.UPDATE_BOOKING,bookingId);
		
		//Step 5. Verify authentication failure
		
		response.then()
				.log().all()
				.spec(ResponseSpec.getResponseSpec())
				.statusCode(403);
		
		
		
	}

}
