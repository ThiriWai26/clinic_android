package com.example.clinic_project.Response;


import com.example.clinic_project.model.HospitalSchedule;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HospitalScheduleResponse {

    @SerializedName("is_success")
    public boolean isSuccess;

    @SerializedName("schedules")
    public List<HospitalSchedule> hospitalSchedules;
}
