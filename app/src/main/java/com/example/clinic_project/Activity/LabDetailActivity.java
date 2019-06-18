package com.example.clinic_project.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class LabDetailActivity extends AppCompatActivity {

    private RetrofitService service;
    private String token;
    private ImageView imageView;
    private TextView txtname,txtlocation,txtphoneno,txttown;
    private int townId = -1;
    private int typeId = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_detail);

        initActivity();
    }

    private void initActivity() {

        imageView = findViewById(R.id.profile);
        txtname = findViewById(R.id.tvName);
        txtlocation = findViewById(R.id.tvLocation);
        txtphoneno = findViewById(R.id.tvphoneNo);
        txttown = findViewById(R.id.tvAddress);
        token = Token.MyToken.getToken();
        service = new RetrofitService();

        Bundle bundle = getIntent().getExtras();
        townId = bundle.getInt("townId");
        Log.e("townId",String.valueOf(townId));
        getBuildingDetail();

    }

    private void getBuildingDetail() {

        Log.e("Building_detail","successs");

        final Api buildingDetailApi = service.getRetrofitService().create(Api.class);
        buildingDetailApi.getBuildingDetail(token, townId, typeId).enqueue(new Callback<BuildingDetailResponse>() {
            @Override
            public void onResponse(Call<BuildingDetailResponse> call, Response<BuildingDetailResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isScuccess){

                        Log.e("name",response.body().buildingDetails.get(0).phoneNumber.get(0));
                        Log.e("address",response.body().buildingDetails.get(0).address);
                        Picasso.get()
                                .load("http://128.199.180.50/api/get_image/" + response.body().buildingDetails.get(0).photos.get(0))
                                .resize(40, 40)
                                .onlyScaleDown()
                                .centerCrop()
                                .into(imageView);

                        txtname.setText(response.body().buildingDetails.get(0).name);
                        txtlocation.setText(response.body().buildingDetails.get(0).address);
                        txtphoneno.setText(response.body().buildingDetails.get(0).phoneNumber.get(0));
                    }
                }
            }

            @Override
            public void onFailure(Call<BuildingDetailResponse> call, Throwable t) {

            }
        });

    }
}
