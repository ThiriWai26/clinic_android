package com.example.clinic_project.Response;

import com.google.gson.annotations.SerializedName;

public class RatingResponse {

    @SerializedName("is_success")
    public boolean isSuccess;

    @SerializedName("error_message")
    public String errorMessage;
}
