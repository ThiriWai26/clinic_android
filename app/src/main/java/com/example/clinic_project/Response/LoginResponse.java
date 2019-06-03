package com.example.clinic_project.Response;

import com.google.gson.annotations.SerializedName;


    public class LoginResponse {
        @SerializedName("is_success")
        public boolean isSuccess;

        @SerializedName("error_message")
        public String errorMessage;

        @SerializedName("token")
        public String token;

    }








