package com.example.clinic_project.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.adapter.MyFavouriteDoctorAdapter;
import com.example.clinic_project.holder.MyFavouriteDoctorHolder;
import com.example.clinic_project.service.RetrofitService;

public class MyFavouriteDoctorActivity extends AppCompatActivity implements MyFavouriteDoctorHolder.OnItemClickListener  {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private RetrofitService service;
    private TextView txtdoctor;
    private ImageView imgsetting;
    private MyFavouriteDoctorAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite_doctor);

        initActivity();
    }

    private void initActivity() {

        txtdoctor = findViewById(R.id.textdoctor);
        imgsetting = findViewById(R.id.imgsetting);
//        searchView = findViewById(R.id.sv);
        recyclerView = findViewById(R.id.recyclerView);
        service = new RetrofitService();

        adapter = new MyFavouriteDoctorAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}






