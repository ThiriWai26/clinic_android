package com.example.clinic_project.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Building {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("town_name")
    public String townName;

    @SerializedName("type_id")
    public int typeId;

    @SerializedName("type_name")
    public String typeName;

    @SerializedName("feature_photo")
    public String image;

    public Building(){

    }
}
