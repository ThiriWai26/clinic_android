package com.example.clinic_project.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.BuildingDetailResponse;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HospitalDetailActivity extends AppCompatActivity {

    private RetrofitService service;
    private String token;
    private ImageView imageView,imgback,imgSpecial;
    private TextView address,txtname,txtlocation,textabout;
    private Button button;
    private int buildingId = -1;
    private int typeId = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detail);

        initActivity();
    }

    private void initActivity() {

        imageView = findViewById(R.id.imageView);
        imgSpecial = findViewById(R.id.imgSpecial);
        address = findViewById(R.id.address);
        txtname = findViewById(R.id.tvName);
        imgback = findViewById(R.id.imgback);
        txtlocation = findViewById(R.id.textLocation);
        textabout = findViewById(R.id.textabout);
//        button = findViewById(R.id.btnHospital);
        token = Token.MyToken.getToken();
        service = new RetrofitService();

        Bundle bundle = getIntent().getExtras();
        buildingId = bundle.getInt("buildingId");
        Log.e("buildingId",String.valueOf(buildingId));
        getBuildingDetail();

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HospitalListActivity.class);
//                startActivity(intent);
                finish();
            }
        });

    }

    private void getBuildingDetail() {

        Log.e("Building_detail","successs");

        final Api buildingDetailApi = service.getRetrofitService().create(Api.class);
        buildingDetailApi.getBuildingDetail(token, buildingId, typeId).enqueue(new Callback<BuildingDetailResponse>() {
            @Override
            public void onResponse(Call<BuildingDetailResponse> call, Response<BuildingDetailResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isScuccess){

                        Log.e("name",response.body().buildingDetails.get(0).phoneNumber.get(0));
                        Log.e("address",response.body().buildingDetails.get(0).address);
                        Log.e("photo",response.body().buildingDetails.get(0).freaturedPhoto);
                        Picasso.get()
                                .load("http://128.199.180.50/api/get_image/" + response.body().buildingDetails.get(0).freaturedPhoto)
                                .resize(40, 40)
                                .onlyScaleDown()
                                .centerCrop()
                                .into(imageView);

                        txtname.setText(response.body().buildingDetails.get(0).name);
                        address.setText(response.body().buildingDetails.get(0).address);

                        txtlocation.setText(response.body().buildingDetails.get(0).townName);
//                        txtphoneno.setText(response.body().buildingDetails.get(0).phoneNumber.get(0));
//                        txttown.setText(response.body().buildingDetails.get(0).address);
                        textabout.setText(response.body().buildingDetails.get(0).phoneNumber.get(0));
                    }
                }
            }

            @Override
            public void onFailure(Call<BuildingDetailResponse> call, Throwable t) {

            }
        });

    }
}
