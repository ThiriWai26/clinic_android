package com.example.clinic_project.Response;

import com.example.clinic_project.model.BuildingDetail;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BuildingDetailResponse {

    @SerializedName("is_success")
    public boolean isScuccess;

    @SerializedName("building")
    public List<BuildingDetail> buildingDetails;
}
