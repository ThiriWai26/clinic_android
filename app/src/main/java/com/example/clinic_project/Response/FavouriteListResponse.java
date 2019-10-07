package com.example.clinic_project.Response;

import com.example.clinic_project.model.DoctorList;
import com.example.clinic_project.model.FavouriteList;
import com.google.gson.annotations.SerializedName;

public class FavouriteListResponse {

    @SerializedName("is_success")
    public Boolean isSuccess;

    @SerializedName("favourite_list")
    public FavouriteList favouriteList;
}
