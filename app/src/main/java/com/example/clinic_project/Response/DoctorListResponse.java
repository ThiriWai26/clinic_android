package com.example.clinic_project.Response;

import com.example.clinic_project.model.DoctorList;
import com.google.gson.annotations.SerializedName;

public class DoctorListResponse {

    @SerializedName("is_success")
    public boolean isSuccess;

    @SerializedName("doctors")
    public DoctorList doctorLists;
}
