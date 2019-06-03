package com.example.clinic_project.Response;

import com.example.clinic_project.model.DoctorDetail;
import com.google.gson.annotations.SerializedName;

public class DoctorDetailResponse {

    @SerializedName("is_success")
    public Boolean isSuccess;

    @SerializedName("doctor")
    public DoctorDetail doctorDetail;
}
