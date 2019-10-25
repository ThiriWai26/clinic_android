package com.example.clinic_project.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.service.RetrofitService;


public class RegistrationDetailActivity extends AppCompatActivity {

    private RetrofitService service;
    private ImageView featurephoto, profile, imgback;
    private TextView tvname,tvtown,tvaddress,tvabout,tvtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_detail);

        initActivity();
    }

    @SuppressLint("WrongViewCast")
    private void initActivity() {

        service = new RetrofitService();
        featurephoto = findViewById(R.id.featuredphoto);
        profile = findViewById(R.id.profile);
        imgback = findViewById(R.id.imgback);
        tvname = findViewById(R.id.tvName);
        tvtown = findViewById(R.id.tvTown);
        tvaddress = findViewById(R.id.tvAddress);
        tvabout = findViewById(R.id.tvabout);
        tvtime = findViewById(R.id.tvTime);

        getRegistrationDetail();
    }

    private void getRegistrationDetail() {
        Log.e("getRegistrationDetail","success");
        Api registrationDetailApi = service.getRetrofitService().create(Api.class);
    }


}
