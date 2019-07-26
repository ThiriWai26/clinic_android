package com.example.clinic_project.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.clinic_project.MapsActivity;
import com.example.clinic_project.R;
import com.example.clinic_project.Response.BookTakeResponse;
import com.example.clinic_project.adapter.MyBookingAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.MyBookingHolder;
import com.example.clinic_project.model.Booking;
import com.example.clinic_project.service.RetrofitService;

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

//        Log.e("mybookingActivity", "oncreate");
//        Bundle b = getIntent().getExtras();
//        token = b.getString("Token");

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MyBookingAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    @Override
    public void onItemClick(String date, int timeId) {

    }
}
