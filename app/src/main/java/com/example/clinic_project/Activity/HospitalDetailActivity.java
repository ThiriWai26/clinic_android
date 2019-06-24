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


public class HospitalDetailActivity extends AppCompatActivity {

    private RetrofitService service;
    private String token;
    private ImageView imageView,imgSpecial;
    private TextView txtname,txtlocation,txtphoneno,txttown,tvabout,textabout;
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
        txtname = findViewById(R.id.tvName);
        txtlocation = findViewById(R.id.tvLocation);
        textabout = findViewById(R.id.textabout);
        tvabout= findViewById(R.id.tvabout);
        txtphoneno = findViewById(R.id.tvphoneNo);
        txttown = findViewById(R.id.town);
        token = Token.MyToken.getToken();
        service = new RetrofitService();

        Bundle bundle = getIntent().getExtras();
        buildingId = bundle.getInt("buildingId");
        Log.e("buildingId",String.valueOf(buildingId));
        getBuildingDetail();

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
                        txtlocation.setText(response.body().buildingDetails.get(0).townName);

//                        txtphoneno.setText(response.body().buildingDetails.get(0).phoneNumber.get(0));
//                        txttown.setText(response.body().buildingDetails.get(0).address);

                        String About =response.body().buildingDetails.get(0).address;
                        textabout.setText(response.body().buildingDetails.get(0).name);
                    }
                }
            }

            @Override
            public void onFailure(Call<BuildingDetailResponse> call, Throwable t) {

            }
        });

    }
}
