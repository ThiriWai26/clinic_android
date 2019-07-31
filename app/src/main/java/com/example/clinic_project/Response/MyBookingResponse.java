package com.example.clinic_project.Response;

import com.example.clinic_project.model.MyBooking;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyBookingResponse {

    @SerializedName("is_success")
    public boolean isSuccess;

    @SerializedName("book_lists")
    public List<MyBooking> bookLists;
}
