package com.example.clinic_project.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.clinic_project.R;
import com.example.clinic_project.adapter.BookAdapter;
import com.example.clinic_project.holder.BookHolder;
import com.example.clinic_project.service.RetrofitService;

import java.util.Calendar;
import java.util.TimeZone;

public class CalenderViewActivity extends AppCompatActivity implements BookHolder.OnItemClickListener {

    private CalendarView calendarView;
    private ImageView imgback;
    private RecyclerView recyclerView;
    private RetrofitService retrofitService;
    private BookAdapter adapter;
    private String date = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_view);

        initActivity();
    }

    private void initActivity() {

        calendarView = findViewById(R.id.calendarView);
        imgback = findViewById(R.id.imgback);
        recyclerView = findViewById(R.id.recyclerView);
        retrofitService = new RetrofitService();

        Log.e("bookActivity", "oncreate");

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        date = String.valueOf(day) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(year);
        Log.e("Date format",date);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                date = String.valueOf(dayOfMonth) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(year);
                Log.e("date", date);

            }
        });

//        imgback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getApplicationContext(), HospitalDetailActivity.class);
//                startActivity(intent);
//            }
//        });

        adapter = new BookAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClick(String date, int timeId) {

        this.date = date;
        Log.e("dateId", date + "," + String.valueOf(date));

    }
}
