package com.example.clinic_project.Response;

import com.example.clinic_project.model.Building;
import com.example.clinic_project.model.BuildingList;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BuildingListResponse {

    @SerializedName("is_success")
    public boolean isSuccess;

    @SerializedName("next_page_url")
    public String nextPage;

    @SerializedName("previous_page_url")
    public String previousPage;

    @SerializedName("first_page_url")
    public String firstPage;

    @SerializedName("last_page_url")
    public String lastPage;

    @SerializedName("Buildings")
    public BuildingList buildingList;

}
