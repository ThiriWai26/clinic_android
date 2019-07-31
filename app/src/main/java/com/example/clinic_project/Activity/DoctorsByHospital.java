package com.example.clinic_project.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.DoctorByHospitalResponse;
import com.example.clinic_project.adapter.DoctorAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.DoctorHolder;
import com.example.clinic_project.model.Doctor;
import com.example.clinic_project.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorsByHospital extends AppCompatActivity implements DoctorHolder.OnDoctorClickListener {

    private RecyclerView recyclerView;
    private RetrofitService service;
    private TextView textname, texttype;
    private ImageView imgback;
    DoctorAdapter adapter;

    List<Doctor> doctors = new ArrayList<>();
    private String token = null;
    private int hospital_id = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_by_hospital);

        initActivity();
    }

    private void initActivity() {

        recyclerView = findViewById(R.id.recyclerView);
        service = new RetrofitService();
        textname = findViewById(R.id.tvName);
        texttype = findViewById(R.id.tvType);
        imgback = findViewById(R.id.imgback);

        adapter = new DoctorAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getDoctorByHospital();

    }

    private void getDoctorByHospital() {

        Log.e("doctorbyhospital","success");
        Api doctorbyhospitalApi = service.getRetrofitService().create(Api.class);
        doctorbyhospitalApi.getDoctorsByHospital(token,hospital_id).enqueue(new Callback<DoctorByHospitalResponse>() {
            @Override
            public void onResponse(Call<DoctorByHospitalResponse> call, Response<DoctorByHospitalResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){

                        doctors = response.body().doctorByHospital.doctors;
                        adapter.addDoctors(doctors);
                        Log.e("DoctorsByHospital", String.valueOf(doctors.size()));

                    }
                }
            }

            @Override
            public void onFailure(Call<DoctorByHospitalResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void onDoctorClick(int id) {

        Intent intent = new Intent(getApplicationContext(), DoctorDetailActivity.class);
        intent.putExtra("doctorId", id);
        intent.putExtra("Token", token);
        Log.e("doctor_id", String.valueOf(id));
        startActivity(intent);

    }
}
