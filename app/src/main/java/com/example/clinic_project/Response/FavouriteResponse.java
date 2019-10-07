package com.example.clinic_project.Response;

import com.google.gson.annotations.SerializedName;

public class FavouriteResponse {

    @SerializedName("is_success")
    public boolean isSuccess;

    @SerializedName("error_message")
    public String error_message;

}
