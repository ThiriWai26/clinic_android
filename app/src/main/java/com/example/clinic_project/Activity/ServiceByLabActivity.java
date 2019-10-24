package com.example.clinic_project.Activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.clinic_project.R;
import com.example.clinic_project.adapter.ServiceByLabAdapter;
import com.example.clinic_project.holder.ServiceByLabHolder;
import com.example.clinic_project.service.RetrofitService;

public class ServiceByLabActivity extends AppCompatActivity implements ServiceByLabHolder.OnItemClickListener {

    private RecyclerView recyclerView;
    private RetrofitService service;
    private ServiceByLabAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_by_lab);

        initActivity();
    }

    private void initActivity() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.back1); // your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LabDetailActivity.class);
                startActivity(intent);            }
        });

        recyclerView= findViewById(R.id.recycler_lab_service);
        service = new RetrofitService();
        adapter = new ServiceByLabAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
