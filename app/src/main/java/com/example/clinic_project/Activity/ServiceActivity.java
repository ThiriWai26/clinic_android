package com.example.clinic_project.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.clinic_project.R;
import com.example.clinic_project.service.RetrofitService;

public class ServiceActivity extends AppCompatActivity {

    private SearchView searchView;
    private RetrofitService service;
    private ImageView imglab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        initActivity();
    }

    private void initActivity() {

//        searchView = findViewById(R.id.sv);
//        service = new RetrofitService();
//        imglab = findViewById(R.id.imglab);

    }
}
