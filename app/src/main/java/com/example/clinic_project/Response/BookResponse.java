package com.example.clinic_project.Response;

import com.example.clinic_project.model.Booking;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookResponse {

    @SerializedName("is_success")
    public boolean isSuccess;

    @SerializedName("book_lists")
    public List<Booking> bookLists;
}
