package com.example.clinic_project.Response;

import com.example.clinic_project.model.OtherServiceDetail;
import com.google.gson.annotations.SerializedName;

public class OtherServiceDetailResponse {

    @SerializedName("is_success")
    public boolean isSuccess;

    @SerializedName("Other Services")
    public OtherServiceDetail otherServiceDetail;
}
