package com.example.clinic_project.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.BuildingDetailResponse;
import com.example.clinic_project.adapter.LabContactNumberAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.ServiceByClinicHolder;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClinicDetailActivity extends AppCompatActivity implements ServiceByClinicHolder.OnItemClickListener {

    private ImageView imgfeature, imgback, imgprofile, imgfav;
    private TextView tvname, tvlocation, tvaddress, tvabout, tvcancel, tvok;
    private RatingBar ratingBar;
    private RelativeLayout service, department;
    private Button btnappointment;
    private RetrofitService retrofitService;
    private LabContactNumberAdapter adapter;
    private RecyclerView recyclerView;
    private String token = null;
    private int buildingId = -1;
    private String type = "clinics";
    private boolean isFavourite;
    private int favouriteableId = -1;
    private String favouriteableType = "clinics";
    private int rateableId = -1;
    private String rateableType = "clinics";
    private int value = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_detail);

        initActivity();
    }


    @SuppressLint("WrongViewCast")
    private void initActivity() {

        imgfeature = findViewById(R.id.imgfeaturedphoto);
        imgback = findViewById(R.id.imgback);
        imgprofile = findViewById(R.id.profile);
        imgfav = findViewById(R.id.imgfav);
        tvname = findViewById(R.id.tvName);
        tvlocation = findViewById(R.id.location);
        tvaddress = findViewById(R.id.address);
        tvabout = findViewById(R.id.about);
        ratingBar = findViewById(R.id.rating);
        service = findViewById(R.id.relativeservice);
        department = findViewById(R.id.relativedepartment);
        btnappointment = findViewById(R.id.btnbook);
        retrofitService = new RetrofitService();
        token = Token.MyToken.getToken();

        Bundle bundle = getIntent().getExtras();
//        buildingId = bundle.getInt("buildingId");
        Log.e("buildingId",String.valueOf(buildingId));

//        getClinicDetail();

        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ServiceByClinicActivity.class);
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

//    private void getClinicDetail() {
//
//        Log.e("Building_detail","successs");
//
//        Api buildingDetailApi = service.getRetrofitService().create(Api.class);
//        buildingDetailApi.getBuildingDetail(token, type, buildingId).enqueue(new Callback<BuildingDetailResponse>() {
//            @Override
//            public void onResponse(Call<BuildingDetailResponse> call, Response<BuildingDetailResponse> response) {
//                if(response.isSuccessful()){
//                    if(response.body().isSuccess){
//                        Picasso.get()
//                                .load("http://192.168.100.201:8000/api/download_image/" + response.body().buildingDetails.featurePhoto)
//                                .into(imageView);
//
//                        txtname.setText(response.body().buildingDetails.name);
//                        txtlocation.setText(response.body().buildingDetails.townName);
////                        txtphoneno.setText(response.body().buildingDetails.get(0).phoneNumber.get(0));
////                        txttown.setText(response.body().buildingDetails.address);
//                        textabout.setText(response.body().buildingDetails.phoneNumber.get(0));
//
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<BuildingDetailResponse> call, Throwable t) {
//
//            }
//        });
//    }


}
