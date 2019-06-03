package com.example.clinic_project.Response;

import com.example.clinic_project.model.TownList;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TownListResponse {

    @SerializedName("is_success")
    public boolean isSuccess;

    @SerializedName("towns")
    public List<TownList> towns;
}
