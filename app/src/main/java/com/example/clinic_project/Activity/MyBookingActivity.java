package com.example.clinic_project.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.clinic_project.R;
import com.example.clinic_project.adapter.MyBookingAdapter;
import com.example.clinic_project.holder.MyBookingHolder;

public class MyBookingActivity extends AppCompatActivity implements MyBookingHolder.OnItemClickListener {

    private RecyclerView recyclerView;
    private MyBookingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);

        initBookingList();
    }

    private void initBookingList() {

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MyBookingAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onItemClick(String date, int timeId) {

    }
}
