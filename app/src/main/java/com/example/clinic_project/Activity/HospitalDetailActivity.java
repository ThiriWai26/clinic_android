package com.example.clinic_project.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.clinic_project.MapsActivity;
import com.example.clinic_project.R;
import com.example.clinic_project.Response.BuildingDetailResponse;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;
import com.google.android.gms.maps.GoogleMap;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.graphics.Color.RED;


public class HospitalDetailActivity extends AppCompatActivity {

    private RetrofitService service;
    private String token;
    private ImageView imageView,imgback,imgphone,imgSpecial,imgmap,imgfav,imgservice;
    private TextView address,txtname,txtlocation,textabout,textviewmap,textservice;
    private RelativeLayout hservice,department;
    private Button btn;
    private CardView cardservice,carddepartment;
    private GoogleMap googleMap;
    private int buildingId = -1;
    private int typeId = 2;

    private boolean isFavourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detail);

        initActivity();

    }

    @SuppressLint("WrongViewCast")
    private void initActivity() {

        imageView = findViewById(R.id.imageView);
        imgSpecial = findViewById(R.id.imgsetting);
        btn = findViewById(R.id.btn);
        address = findViewById(R.id.address);
        txtname = findViewById(R.id.tvName);
        imgback = findViewById(R.id.imgback);
        txtlocation = findViewById(R.id.textLocation);
        textabout = findViewById(R.id.textabout);
        imgphone = findViewById(R.id.phone);
//        imgmap = findViewById(R.id.map);
        imgfav = findViewById(R.id.imgfav);
        imgservice = findViewById(R.id.imageservice);
        textservice = findViewById(R.id.txservice);
        token = Token.MyToken.getToken();
        service = new RetrofitService();
        hservice= findViewById(R.id.relativeservice);
        department = findViewById(R.id.relativedepartment);


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

//        imgmap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

        imgphone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PhoneActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DoctorsByClinic.class);
                startActivity(intent);
            }
        });

       imgfav.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if (isFavourite){
                   imgfav.setBackgroundResource(R.drawable.favouritewhite);
                   isFavourite=false;
               }
               else {
                   imgfav.setBackgroundResource(R.drawable.favouritered);
                   isFavourite=true;
               }
           }
       });

        hservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ServiceActivity.class);
                startActivity(intent);
            }
        });

        department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DepartmentActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getBuildingDetail() {

        Log.e("Building_detail","successs");

        final Api buildingDetailApi = service.getRetrofitService().create(Api.class);
        buildingDetailApi.getBuildingDetail(token, typeId, buildingId).enqueue(new Callback<BuildingDetailResponse>() {
            @Override
            public void onResponse(Call<BuildingDetailResponse> call, Response<BuildingDetailResponse> response) {

                if(response.isSuccessful()){
                    if(response.body().isScuccess){

                        Log.e("name",response.body().buildingDetails.get(0).phoneNumber.get(0));
                        Log.e("address",response.body().buildingDetails.get(0).address);
                        Log.e("photo",response.body().buildingDetails.get(0).freaturedPhoto);
                        Picasso.get()
                                .load("http://128.199.180.50/api/get_image/" + response.body().buildingDetails.get(0).freaturedPhoto)
                                .into(imageView);

                        txtname.setText(response.body().buildingDetails.get(0).name);
                        address.setText(response.body().buildingDetails.get(0).townName);

                        txtlocation.setText(response.body().buildingDetails.get(0).address);
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
