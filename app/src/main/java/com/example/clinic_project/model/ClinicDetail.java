package com.example.clinic_project.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClinicDetail {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("photo")
    public String photo;

    @SerializedName("about")
    public String about;

    @SerializedName("towns")
    public List<String > towns;

    @SerializedName("specialists")
    public List<String> specialists;

    @SerializedName("clinics")
    public List<String> clinics;
}
