package com.example.clinic_project.model;

import com.google.gson.annotations.SerializedName;

public class Booking {

    @SerializedName("date")
    public String date;

    @SerializedName("start_time")
    public String startTime;

    @SerializedName("end_time")
    public String endTime;

    @SerializedName("building_name")
    public String clinicName;

    @SerializedName("town_name")
    public String townName;

    @SerializedName("time_id")
    public int timeId;

    @SerializedName("book_status")
    public int bookStatus;

}
