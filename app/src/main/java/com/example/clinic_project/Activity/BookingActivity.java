package com.example.clinic_project.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.clinic_project.R;
import com.example.clinic_project.adapter.BookAdapter;
import com.example.clinic_project.service.RetrofitService;

public class BookingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RetrofitService service;
    private BookAdapter bookAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

    }
}
