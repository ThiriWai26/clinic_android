package com.example.clinic_project.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.BookTakeResponse;
import com.example.clinic_project.Response.HospitalScheduleResponse;
import com.example.clinic_project.adapter.HospitalScheduleAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.HospitalScheduleHolder;
import com.example.clinic_project.model.HospitalSchedule;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;

import java.time.DayOfWeek;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalenderViewActivity extends AppCompatActivity implements HospitalScheduleHolder.OnItemClickListener {

    private CalendarView calendarView;
    private ImageView imgback;
    private TextView tvbook;
    private RecyclerView recyclerView;
    private RetrofitService service;
    private HospitalScheduleAdapter adapter;
    private List<HospitalSchedule> hospitalSchedules = new ArrayList<>();

    private String token = null;
    private int doctorId = -1;
    private int hospitalId = 1;
    private int day;

    private String date = "2019-10-05";
    private int scheduleId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_view);

        initActivity();
    }

    private void initActivity() {

        calendarView = findViewById(R.id.calendarView);
        imgback = findViewById(R.id.imgback);
        tvbook = findViewById(R.id.tvbooking);
        recyclerView = findViewById(R.id.recyclerView);
        service = new RetrofitService();
        token= Token.MyToken.getToken();

        Bundle bundle = getIntent().getExtras();
        token = Token.MyToken.getToken();

        doctorId = bundle.getInt("doctorId");
        Log.e("doctorId",String.valueOf(doctorId));

        adapter = new HospitalScheduleAdapter(this);
        adapter.addItem(hospitalSchedules);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        calendarView = findViewById(R.id.calendarView);
        imgback = findViewById(R.id.imgback);
        tvbook = findViewById(R.id.tvbooking);
        recyclerView = findViewById(R.id.recyclerView);
        service = new RetrofitService();
        token  = Token.MyToken.getToken();

        Bundle bundle1 = getIntent().getExtras();
        doctorId = bundle1.getInt("doctorId");
        Log.e("doctorId",String.valueOf(doctorId));

//        Bundle bundle2 = getIntent().getExtras();
//        hospitalId = bundle2.getInt("buildingId");
        Log.e("hospitalId",String.valueOf(hospitalId));

        final Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        final int days = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        date = String.valueOf(days) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(year);
        Log.e("Date format",date);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                date = String.valueOf(dayOfMonth) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(year);
                calendar.set(year,month,dayOfMonth);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

                 if((dayOfWeek-1)==0){
                     day = 8;
                 }
                 else {
                     day = dayOfWeek -1;
                 }

                 Log.e("day",String.valueOf(day));

                getHospitalSchedule();
            }
        });

            Log.e("ifcondition","success");
            tvbook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if((date != null) && (scheduleId != -1)) {
                        Log.e("tvbookOnClick", "success");
                        getBookingList();
                    }
                }
            });

    }

    private void getBookingList() {
        Log.e("getBookingList","success");
            if((date != null) && (scheduleId != -1)) {
                final Api bookingapi = service.getRetrofitService().create(Api.class);
                bookingapi.getBookTake(token, date, scheduleId).enqueue(new Callback<BookTakeResponse>() {
                    @Override
                    public void onResponse(Call<BookTakeResponse> call, Response<BookTakeResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().isSuccess) {
                                Log.e("response.body", "success");
                                Toast.makeText(getApplicationContext(), response.body().message, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), response.body().errorMessage, Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Log.e("response.body","fail");
                        }
                    }
                    @Override
                    public void onFailure(Call<BookTakeResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Server failure", Toast.LENGTH_LONG).show();
                    }
                });
            }else {
                Toast.makeText(getApplicationContext(), "Choose Booking", Toast.LENGTH_LONG).show();
            }
    }

    private void getHospitalSchedule() {
        Log.e("getHospitalSchedule","success");
        Api hospitalscheduleApi = service.getRetrofitService().create(Api.class);
        hospitalscheduleApi.getHospitalSchedule(token,hospitalId,doctorId,day).enqueue(new Callback<HospitalScheduleResponse>() {
            @Override
            public void onResponse(Call<HospitalScheduleResponse> call, Response<HospitalScheduleResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        Log.e("response.body","success");
                        adapter.addItem(response.body().hospitalSchedules);
                    }
                    else {
                      adapter.clearItem();
                    }
                }
                else{
                    Log.e("server response","fail");
                }
            }

            @Override
            public void onFailure(Call<HospitalScheduleResponse> call, Throwable t) {
                Log.e("onfailure",t.toString());
            }
        });

    }


    @Override
    public void onItemClick(String date, int scheduleId) {

        this.date = date;
        this.scheduleId = scheduleId;

        Log.e("dayId,scheduleId", date + "," + String.valueOf(scheduleId));
    }




}
