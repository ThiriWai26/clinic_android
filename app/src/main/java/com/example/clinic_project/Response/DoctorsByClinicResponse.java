package com.example.clinic_project.Response;

import com.example.clinic_project.model.DoctorByClinic;
import com.example.clinic_project.model.DoctorDetail;
import com.example.clinic_project.model.DoctorList;
import com.google.gson.annotations.SerializedName;

public class DoctorsByClinicResponse {

    @SerializedName("is_success")
    public Boolean isSuccess;

    @SerializedName("doctors")
    public DoctorList doctorByClinic;
}
