package com.example.clinic_project.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.adapter.HospitalScheduleAdapter;
import com.example.clinic_project.model.HospitalSchedule;
import com.example.clinic_project.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

public class BookingLabActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private ImageView imgback;
    private TextView done;
    private RecyclerView recyclerView;
    private RelativeLayout bookItem;
    private RetrofitService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_lab);

        initActivity();
    }

    private void initActivity() {

        calendarView = findViewById(R.id.calendarView);
        imgback = findViewById(R.id.imgback);
        done = findViewById(R.id.tvdone);
        recyclerView = findViewById(R.id.labbook_recyclerview);

    }
}
