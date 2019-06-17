package com.example.clinic_project.Response;

import com.example.clinic_project.model.ClinicList;
import com.example.clinic_project.model.DoctorList;
import com.google.gson.annotations.SerializedName;

public class ClinicListResponse {

    @SerializedName("is_success")
    public boolean isSuccess;

    @SerializedName("next_page_url")
    public String nextPage;

    @SerializedName("previou_page_url")
    public String previousPage;

    @SerializedName("first_page_url")
    public String firstPage;

    @SerializedName("last_page_url")
    public String lastPage;

    @SerializedName("clinics")
    public ClinicList clinicLists;
}

