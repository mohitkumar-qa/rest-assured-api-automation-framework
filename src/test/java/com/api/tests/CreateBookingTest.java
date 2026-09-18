package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.endpoints.BookingEndpoints;
import com.api.pojo.Booking;
import com.api.pojo.BookingDates;
import com.api.requests.RequestSpec;
import com.api.requests.ResponseSpec;

import io.restassured.response.Response;

public class CreateBookingTest extends BaseTest {

	@Test(dataProvider = "bookingData",dataProviderClass = BookingDataProvider.class)
	public void createBooking(String firstname,
								String lastname,
								int totalprice,
								boolean depositpaid,
								String checkin,
								String checkout) {
		
		Booking booking = new Booking();

		booking.setFirstname(firstname);
		booking.setLastname(lastname);
		booking.setTotalprice(totalprice);
		booking.setDepositpaid(depositpaid);
		
		BookingDates dates = new BookingDates();
        dates.setCheckin(checkin);
        dates.setCheckout(checkout);

        booking.setBookingdates(dates);
		
		Response response =
				given()
					.spec(RequestSpec.getRequestSpec())
					.body(booking)
				.when()	
					.post(BookingEndpoints.CREATE_BOOKING);
				
		response.then()
					.spec(ResponseSpec.getResponseSpec())
					.statusCode(200)
					.body(matchesJsonSchemaInClasspath(
							"schemas/booking-create-response-scheme.json"))
					;
		
		int bookingId = response.jsonPath().getInt("bookingid");
		System.out.println("Created Booking ID :"+bookingId);
				
	}
	
	
	
}
