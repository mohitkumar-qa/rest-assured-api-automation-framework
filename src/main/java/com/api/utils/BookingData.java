package com.api.utils;

import com.api.pojo.Booking;
import com.api.pojo.BookingDates;

public class BookingData {

    public static Booking createDefaultBooking() {

        Booking booking = new Booking();

        booking.setFirstname("Mohit");
        booking.setLastname("Kumar");
        booking.setTotalprice(150);
        booking.setDepositpaid(true);

        BookingDates dates = new BookingDates();

        dates.setCheckin("2026-Aug-10");
        dates.setCheckout("2026-Sep-20");

        booking.setBookingdates(dates);

        return booking;
    }
}