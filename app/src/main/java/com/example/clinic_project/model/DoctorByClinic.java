package com.example.clinic_project.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DoctorByClinic {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("photo")
    public String photo;

    @SerializedName("specialists")
    public List<String> specialists;

    @SerializedName("clinics")
    public List<String> clinics;

    @SerializedName("towns")
    public List<String> towns;

    @SerializedName("about")
    public String about;
}
