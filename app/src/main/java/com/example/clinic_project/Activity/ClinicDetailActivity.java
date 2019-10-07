package com.example.clinic_project.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.BuildingDetailResponse;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClinicDetailActivity extends AppCompatActivity {

    private RetrofitService service;
    private String token;
    private ImageView imageView,imgback;
    private TextView txtname, txtlocation,textabout;
    private Button btndoctor;
    private int buildingId = -1;
    private String type = "clinics";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_detail);

        initActivity();
    }


    @SuppressLint("WrongViewCast")
    private void initActivity() {

        imageView = findViewById(R.id.imageView);
        imgback = findViewById(R.id.imgback);
        txtname = findViewById(R.id.tvName);
        txtlocation = findViewById(R.id.tvLocation);
        textabout = findViewById(R.id.textabout);
        btndoctor = findViewById(R.id.btnDoctor);
        token = Token.MyToken.getToken();
        service = new RetrofitService();


        Bundle bundle = getIntent().getExtras();
        buildingId = bundle.getInt("buildingId");
        Log.e("buildingId",String.valueOf(buildingId));
        getBuildingDetail();

//        imgback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getApplicationContext(), ClinicActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
    }

    private void getBuildingDetail() {

        Log.e("Building_detail","successs");

        Api buildingDetailApi = service.getRetrofitService().create(Api.class);
        buildingDetailApi.getBuildingDetail(token, type, buildingId).enqueue(new Callback<BuildingDetailResponse>() {
            @Override
            public void onResponse(Call<BuildingDetailResponse> call, Response<BuildingDetailResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        Picasso.get()
                                .load("http://192.168.100.201:8000/api/download_image/" + response.body().buildingDetails.featurePhoto)
                                .into(imageView);

                        txtname.setText(response.body().buildingDetails.name);
                        txtlocation.setText(response.body().buildingDetails.townName);
//                        txtphoneno.setText(response.body().buildingDetails.get(0).phoneNumber.get(0));
//                        txttown.setText(response.body().buildingDetails.address);
                        textabout.setText(response.body().buildingDetails.phoneNumber.get(0));

                    }
                }
            }

            @Override
            public void onFailure(Call<BuildingDetailResponse> call, Throwable t) {

            }
        });

    }
}
