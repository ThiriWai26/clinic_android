package com.example.clinic_project.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Building {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("feature_photo")
    public String image;

    @SerializedName("rating")
    public float rating;

    @SerializedName("town_id")
    public int townId;

    @SerializedName("town_name")
    public String townName;

}
