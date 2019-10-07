package com.example.clinic_project.model;

import com.google.gson.annotations.SerializedName;


public class Favourite {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("town_id")
    public int townId;

    @SerializedName("town_name")
    public String townName;

}
