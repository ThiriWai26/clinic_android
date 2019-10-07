package com.example.clinic_project.model;

import com.google.gson.annotations.SerializedName;

public class BookingOfHospital {

    @SerializedName("name")
    public String hospitalName;

    @SerializedName("latitude")
    public double latitude;

    @SerializedName("longitude")
    public double longitude;

    @SerializedName("town_name")
    public String townName;


}
