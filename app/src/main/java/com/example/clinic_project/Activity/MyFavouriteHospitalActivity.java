package com.example.clinic_project.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.adapter.MyFavouriteHospitalAdapter;
import com.example.clinic_project.holder.MyFavouriteHospitalHolder;
import com.example.clinic_project.service.RetrofitService;

public class MyFavouriteHospitalActivity extends AppCompatActivity implements MyFavouriteHospitalHolder.OnItemClickListener  {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private RetrofitService service;
    private TextView txthospital;
    private ImageView imgsetting;

    private MyFavouriteHospitalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favourite_hospital);
        initActivity();
    }

    private void initActivity() {

        searchView = findViewById(R.id.sv);
        recyclerView = findViewById(R.id.recyclerView);
        txthospital = findViewById(R.id.txthospital);
        imgsetting = findViewById(R.id.imgsetting);
        adapter = new MyFavouriteHospitalAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
