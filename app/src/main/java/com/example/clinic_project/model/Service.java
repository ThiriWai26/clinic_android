package com.example.clinic_project.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Service {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("type")
    public String type;

    @SerializedName("address")
    public String address;

    @SerializedName("about")
    public String about;

    @SerializedName("logo")
    public String profile;

    @SerializedName("feature_photo")
    public String featurePhoto;

    @SerializedName("start_time")
    public int startTime;

    @SerializedName("end_time")
    public int endTime;

    @SerializedName("rating")
    public int rating;

    @SerializedName("town_id")
    public int townId;

    @SerializedName("created_at")
    public String createdAt;

    @SerializedName("updated_at")
    public String updatedAt;

    @SerializedName("phones")
    public List<String> phoneNumber;

}
