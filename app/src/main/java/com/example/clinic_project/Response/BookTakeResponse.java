package com.example.clinic_project.Response;

import com.google.gson.annotations.SerializedName;

public class BookTakeResponse {

    @SerializedName("is_success")
    public boolean isSuccess;

    @SerializedName("message")
    public String message;
}
