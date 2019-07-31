package com.example.clinic_project.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.MyBookingResponse;
import com.example.clinic_project.adapter.MyBookingAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.MyBookingHolder;
import com.example.clinic_project.model.MyBooking;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBookingActivity extends AppCompatActivity implements MyBookingHolder.OnItemClickListener {

    private RecyclerView recyclerView;
    private MyBookingAdapter adapter;
    private RetrofitService service;
    private CircleImageView imgdoctor;
    private Button btncancel;
    private TextView txtdoctorname,txthospitalname,txtdate,txtmap;
    private String token = null;

    List<MyBooking> booking = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);
        initBookingList();

    }

    private void initBookingList() {

        txtdoctorname = findViewById(R.id.tvName);
        txthospitalname = findViewById(R.id.tvhospitalname);
        txtdate = findViewById(R.id.tvtime);
        txtmap = findViewById(R.id.tvMap);
        imgdoctor = findViewById(R.id.profile);
        btncancel = findViewById(R.id.btncancel);
        service = new RetrofitService();

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MyBookingAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getMyBooking();

    }

    private void getMyBooking() {

        Log.e("mybooking","success");
        Api myBookingApi = service.getRetrofitService().create(Api.class);
        myBookingApi.getMyBooking(token).enqueue(new Callback<MyBookingResponse>() {
            @Override
            public void onResponse(Call<MyBookingResponse> call, Response<MyBookingResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){


                    }
                }
            }

            @Override
            public void onFailure(Call<MyBookingResponse> call, Throwable t) {

            }
        });


    }

    @Override
    public void onItemClick(String date, int timeId) {

    }
}
