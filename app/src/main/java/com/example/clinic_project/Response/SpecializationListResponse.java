package com.example.clinic_project.Response;

import com.example.clinic_project.model.SpecializationList;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpecializationListResponse extends SpecializationList {

    @SerializedName("is_success")
    public Boolean isSuccess;

    @SerializedName("specialists")
    public List<SpecializationList> specializations;
}
