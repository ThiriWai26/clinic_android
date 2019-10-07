package com.example.clinic_project.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BuildingDetail {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("address")
    public String address;

    @SerializedName("about")
    public String about;

    @SerializedName("latitude")
    public String latitude;

    @SerializedName("longitude")
    public String longitude;

    @SerializedName("logo")
    public String logo;

    @SerializedName("feature_photo")
    public String featurePhoto;

    @SerializedName("photos")
    public String photos;

    @SerializedName("phone_numbers")
    public List<String> phoneNumber;

    @SerializedName("rating")
    public float rating;

    @SerializedName("is_public")
    public int ispublic;

    @SerializedName("town_id")
    public int townId;

    @SerializedName("town_name")
    public String townName;

    public BuildingDetail(){

    }


}

