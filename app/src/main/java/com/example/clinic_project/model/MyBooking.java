package com.example.clinic_project.model;

import com.google.gson.annotations.SerializedName;

public class MyBooking {

    @SerializedName("doctor_logo")
    public String image;

    @SerializedName("doctor_name")
    public String doctorName;

    @SerializedName("hospital_name")
    public String hospitalName;

    @SerializedName("data")
    public String data;

    @SerializedName("time_table")
    public String timeTable;

    @SerializedName("latitude")
    public int latitude;

    @SerializedName("longitude")
    public int longitude;
}
