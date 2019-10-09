package com.example.clinic_project.Response;

import com.example.clinic_project.model.BuildingDetail;
import com.google.gson.annotations.SerializedName;

public class BuildingDetailResponse {

    @SerializedName("is_success")
    public boolean isSuccess;

    @SerializedName("building")
    public BuildingDetail buildingDetails;
}
