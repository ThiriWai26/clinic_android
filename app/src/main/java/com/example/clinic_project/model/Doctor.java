package com.example.clinic_project.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Doctor {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("photo")
    public String photo;

    @SerializedName("about")
    public String about;

    @SerializedName("specialists")
    public List<String> specialists;


}


