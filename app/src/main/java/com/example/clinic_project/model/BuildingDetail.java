package com.example.clinic_project.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BuildingDetail {

    public BuildingDetail(){

    }

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("town_name")
    public String townName;

    @SerializedName("address")
    public String address;

    @SerializedName("photos")
    public List<String> photos;

    @SerializedName("featured_photo")
    public String freaturedPhoto;

    @SerializedName("phone_number")
    public List<String> phoneNumber;

    @SerializedName("google_map_location")
    public String map;



}

