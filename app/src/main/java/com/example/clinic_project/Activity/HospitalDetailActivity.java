package com.example.clinic_project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.BuildingDetailResponse;
import com.example.clinic_project.Response.DoctorListResponse;
import com.example.clinic_project.adapter.DoctorAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.DoctorHolder;
import com.example.clinic_project.model.BuildingDetail;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalDetailActivity extends AppCompatActivity implements DoctorHolder.OnDoctorClickListener {

    private ImageView imageView,imgSpecial,image1,image2,image3;
    private TextView seeAll,buildingName,town,address,phone;
    private RecyclerView recyclerView;
    private DoctorAdapter adapter;
    private String token;
    private RetrofitService service;
    private int buildingId;
    private int typeId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detail);

        initActivity();
        getBuildingDetail();
    }

    private void getBuildingDetail() {

        Api buildingApi = service.getRetrofitService().create(Api.class);
        buildingApi.getBuildingDetail(token,buildingId,typeId).enqueue(new Callback<BuildingDetailResponse>() {
            @Override
            public void onResponse(Call<BuildingDetailResponse> call, Response<BuildingDetailResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isScuccess){

                        BuildingDetail buildingDetail = response.body().buildingDetails.get(0);
                        buildingName.setText(buildingDetail.name);
                        town.setText(buildingDetail.townName);
                        address.setText(buildingDetail.address);
                        String ph = buildingDetail.phoneNumber.get(0);
                        for(int i=1;i<buildingDetail.phoneNumber.size();i++){
                            ph += ","+buildingDetail.phoneNumber.get(i);
                        }
                        phone.setText(ph);

                        Picasso.get().load("http://128.199.180.50/api/get_image/"+buildingDetail.freaturedPhoto).resize(500,500).centerInside().into(imageView);
                        Picasso.get().load("http://128.199.180.50/api/get_image/"+buildingDetail.freaturedPhoto).resize(500,500).centerInside().into(image1);
                        Picasso.get().load("http://128.199.180.50/api/get_image/"+buildingDetail.freaturedPhoto).resize(500,500).centerInside().into(image2);
                        Picasso.get().load("http://128.199.180.50/api/get_image/"+buildingDetail.freaturedPhoto).resize(500,500).centerInside().into(image3);


                        getDoctorList();


                    }
                }
            }

            @Override
            public void onFailure(Call<BuildingDetailResponse> call, Throwable t) {

            }
        });


    }

    private void getDoctorList() {

        Api doctorListApi = service.getRetrofitService().create(Api.class);
        doctorListApi.getDoctorList(token).enqueue(new Callback<DoctorListResponse>() {
            @Override
            public void onResponse(Call<DoctorListResponse> call, Response<DoctorListResponse> response) {

                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        adapter.addDoctors(response.body().doctorLists.doctors);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<DoctorListResponse> call, Throwable t) {

            }
        });


    }

    private void initActivity() {

        imageView = findViewById(R.id.imageView);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        imgSpecial = findViewById(R.id.imgSpecial);
        seeAll = findViewById(R.id.seeAll);
        buildingName = findViewById(R.id.buildindName);
        town = findViewById(R.id.town);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);

        recyclerView = findViewById(R.id.recyclerView);

        token = Token.MyToken.getToken();
        service = new RetrofitService();

        adapter = new DoctorAdapter(this);

        Bundle bundle = getIntent().getExtras();
        buildingId = bundle.getInt("buildingId");
        typeId = bundle.getInt("typeId");

        imgSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SpecializationListActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onDoctorClick(int id) {

    }
}
