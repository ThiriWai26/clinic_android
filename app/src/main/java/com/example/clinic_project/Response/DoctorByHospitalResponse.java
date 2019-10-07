package com.example.clinic_project.Response;

import com.example.clinic_project.model.DoctorList;
import com.google.gson.annotations.SerializedName;

public class DoctorByHospitalResponse {

    @SerializedName("is_success")
    public Boolean isSuccess;

    @SerializedName("doctors")
    public DoctorList doctorByHospital;

    @SerializedName("next_page_url")
    public String nextPageUrl;

    @SerializedName("previous_page_url")
    public String previousPageUrl;

    @SerializedName("first_page_url")
    public String firstPageUrl;

    @SerializedName("last_page_url ")
    public String lastPageUrl;
}
