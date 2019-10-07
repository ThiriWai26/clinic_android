package com.example.clinic_project.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Bookings {

    @SerializedName("date")
    public String date;

    @SerializedName("timetable")
    public BookingOfTimetable timetable;

    @SerializedName("doctor")
    public BookingOfDoctor doctor;

    @SerializedName("hospital")
    public BookingOfHospital hospital;

}
