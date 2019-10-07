package com.example.clinic_project.model;

import com.google.gson.annotations.SerializedName;

public class HospitalSchedule {

    @SerializedName("id")
    public int id;

    @SerializedName("start_time")
    public String startTime;

    @SerializedName("end_time")
    public String endTime;

}
