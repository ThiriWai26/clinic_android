package com.example.clinic_project.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.MyFavouriteDoctorResponse;
import com.example.clinic_project.Response.SpecializationListResponse;
import com.example.clinic_project.adapter.MyFavouriteDoctorAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.MyFavouriteDoctorHolder;
import com.example.clinic_project.model.SpecializationList;
import com.example.clinic_project.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFavouriteDoctorActivity extends AppCompatActivity implements MyFavouriteDoctorHolder.OnItemClickListener  {

    private RecyclerView recyclerView;
    private RetrofitService service;
    private TextView txtdoctor;
    private ImageView imgsetting;
    private MyFavouriteDoctorAdapter adapter;

    String token = null;
    List<SpecializationList> specializationLists = new ArrayList<>();
    List<String> categories = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite_doctor);

        initActivity();
    }

    private void initActivity() {

        txtdoctor = findViewById(R.id.textdoctor);
        imgsetting = findViewById(R.id.imgsetting);
        recyclerView = findViewById(R.id.recyclerView);
        service = new RetrofitService();

        adapter = new MyFavouriteDoctorAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getMyFavDoctor();
        getSpecializationList(token);
    }

    private void getSpecializationList(String token) {

        Log.e("spicializationlist","success");
        Api getSpeciallizationListApi = service.getRetrofitService().create(Api.class);
        getSpeciallizationListApi.getSpecializationList(token).enqueue(new Callback<SpecializationListResponse>() {
            @Override
            public void onResponse(Call<SpecializationListResponse> call, Response<SpecializationListResponse> response) {
                if (response.isSuccessful()){
                    if(response.body().isSuccess){

                        specializationLists = response.body().specializations;
                        Log.e("Special Size", String.valueOf(response.body().specializations.size()));

                        for(SpecializationList special : specializationLists) {
                            categories.add(special.name);
                            Log.e("name",special.name);
                        }

                        categories.add(0,"All");
                        Toast.makeText(getApplicationContext(), "Successful!", Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<SpecializationListResponse> call, Throwable t) {

            }
        });

    }

    private void getMyFavDoctor() {

        Log.e("myfavDoctor", "success");
        Api myfavdoctorApi = service.getRetrofitService().create(Api.class);
        myfavdoctorApi.getMyFavouriteDoctor(token).enqueue(new Callback<MyFavouriteDoctorResponse>() {
            @Override
            public void onResponse(Call<MyFavouriteDoctorResponse> call, Response<MyFavouriteDoctorResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){

                    }
                }
            }

            @Override
            public void onFailure(Call<MyFavouriteDoctorResponse> call, Throwable t) {

            }
        });



    }
}






