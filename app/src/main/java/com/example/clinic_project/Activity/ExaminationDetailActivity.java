package com.example.clinic_project.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.BuildingDetailResponse;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.fragment.FragmentExamination;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;
import com.google.android.gms.maps.GoogleMap;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ExaminationDetailActivity extends AppCompatActivity {

    private RetrofitService service;
    private ImageView featurephoto,profile,imgback;
    private TextView tvname,tvtown,tvaddress,tvabout,tvtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination_detail);

        initActivity();

    }

    @SuppressLint("WrongViewCast")
    private void initActivity() {

        service = new RetrofitService();
        featurephoto = findViewById(R.id.featuredphoto);
        profile = findViewById(R.id.profile);
        tvname = findViewById(R.id.tvName);
        tvtown = findViewById(R.id.tvTown);
        tvaddress = findViewById(R.id.tvAddress);
        tvabout = findViewById(R.id.tvabout);
        tvtime = findViewById(R.id.tvTime);

        getExaminationDetail();

    }

    private void getExaminationDetail() {
        Log.e("getExaminationDetail","success");
        Api examinationDetailApi = service.getRetrofitService().create(Api.class);
    }


}
