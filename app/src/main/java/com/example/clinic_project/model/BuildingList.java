package com.example.clinic_project.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BuildingList {

    @SerializedName("data")
    public ArrayList<Building> data;

    @SerializedName("next_page_url")
    public String nextPageUrl;

    @SerializedName("previous_page_url")
    public String previousPageUrl;

    @SerializedName("first_page_url")
    public String firstPageUrl;

    @SerializedName("last_page_url ")
    public String lastPageUrl;

}
