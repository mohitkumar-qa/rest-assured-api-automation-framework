package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.endpoints.BookingEndpoints;
import com.api.pojo.Booking;
import com.api.requests.RequestSpec;
import com.api.requests.ResponseSpec;
import com.api.utils.AuthManager;
import com.api.utils.BookingData;

import io.restassured.response.Response;

public class PatchBookingTest extends BaseTest {

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

        System.out.println("Created Booking ID: " + bookingId);
    }

    @Test(dependsOnMethods = "createBooking")
    public void patchBooking() {

        String patchBody =
                """
        		{
        			"firstname":"Rahul"
        			}
        		""";

        Response response =
                given()
                    .spec(RequestSpec.getRequestSpec())
                    .cookie("token", AuthManager.getToken())
                    .body(patchBody)
                .when()
                    .patch(BookingEndpoints.PATCH_BOOKING, bookingId);

        response.then()
                .log().all()
                .spec(ResponseSpec.getResponseSpec())
                .statusCode(200);

        Booking updatedBooking = response.as(Booking.class);

        Assert.assertEquals(updatedBooking.getFirstname(), "Rahul");
        Assert.assertEquals(updatedBooking.getLastname(), "Kumar");
        Assert.assertEquals(updatedBooking.getTotalprice(), 150);
        Assert.assertTrue(updatedBooking.isDepositpaid());

        Assert.assertEquals(
                updatedBooking.getBookingdates().getCheckin(),
                "2026-08-10");

        Assert.assertEquals(
                updatedBooking.getBookingdates().getCheckout(),
                "2026-09-20");
    }
}