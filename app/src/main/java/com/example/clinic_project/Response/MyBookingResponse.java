package com.example.clinic_project.Response;

import com.example.clinic_project.model.UpcomingBooking;
import com.google.gson.annotations.SerializedName;

public class MyBookingResponse {

    @SerializedName("is_success")
    public boolean isSuccess;

    @SerializedName("bookings")
    public UpcomingBooking upcomingBooking;
}
