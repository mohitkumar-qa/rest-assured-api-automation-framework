package com.api.tests;

import org.testng.annotations.DataProvider;

public class BookingDataProvider {

	@DataProvider(name = "bookingData")
	public Object[][] getBookingData() {

		return new Object[][] {
		    {"Mohit", "Kumar", 150, true, "2026-Aug-10", "2026-Sep-20"},
		    {"Rahul", "Sharma", 500, false, "2026-Oct-01", "2026-Oct-10"},
		    {"Amit", "Singh", 300, true, "2026-Nov-01", "2026-Nov-10"}
		};
	}
	
	
}
