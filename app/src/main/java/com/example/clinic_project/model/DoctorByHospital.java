package com.example.clinic_project.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DoctorByHospital {
    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("photo")
    public String photo;

    @SerializedName("specialists")
    public List<String> specialists;

    @SerializedName("about")
    public String about;

}
