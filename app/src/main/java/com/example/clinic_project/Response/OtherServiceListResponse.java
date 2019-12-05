package com.example.clinic_project.Response;

import com.example.clinic_project.model.OtherServiceList;
import com.google.gson.annotations.SerializedName;

public class OtherServiceListResponse {

    @SerializedName("is_success")
    public boolean isSuccess;

    @SerializedName("Other Services")
    public OtherServiceList otherServiceList;


}
