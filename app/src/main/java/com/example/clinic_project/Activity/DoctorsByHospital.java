package com.example.clinic_project.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.DoctorByHospitalResponse;
import com.example.clinic_project.adapter.DoctorAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.DoctorHolder;
import com.example.clinic_project.model.Doctor;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorsByHospital extends AppCompatActivity implements DoctorHolder.OnDoctorClickListener {

    private RecyclerView recyclerView;
    private RetrofitService service;
    private ImageView imgback;
    DoctorAdapter adapter;

    List<Doctor> doctor = new ArrayList<>();
    private String token = null;
    private int hospital_id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_by_hospital);

        initActivity();
    }

    private void initActivity() {

        recyclerView = findViewById(R.id.recyclerView);
        service = new RetrofitService();
        imgback = findViewById(R.id.imageback);

        token = Token.MyToken.getToken();
//        Log.e("DrawerActivityToken", token);

        adapter = new DoctorAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getDoctorByHospital();

    }

    private void getDoctorByHospital() {

        Log.e("doctorbyhospital","success");
        Api doctorByHospitalApi = service.getRetrofitService().create(Api.class);
        doctorByHospitalApi.getDoctorsByHospital(token,hospital_id).enqueue(new Callback<DoctorByHospitalResponse>() {
            @Override
            public void onResponse(Call<DoctorByHospitalResponse> call, Response<DoctorByHospitalResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        Log.e("response.body","success");
                        doctor = response.body().doctorByHospital.doctors;
                        adapter.addDoctors(doctor);
                        Log.e("doctorbyhospital_size",String.valueOf(doctor.size()));
                    }
                    else {
                        Log.e("response.body","fail");
                    }
                }else{
                    Log.e("response","fail");
                }
            }

            @Override
            public void onFailure(Call<DoctorByHospitalResponse> call, Throwable throwable) {

            }
        });
    }


    @Override
    public void onDoctorClick(int id) {

        Intent intent = new Intent(getApplicationContext(), CalenderViewActivity.class);
        intent.putExtra("doctorId", id);
        intent.putExtra("Token", token);
        Log.e("doctor_id", String.valueOf(id));
        startActivity(intent);

    }

    public void onBackClick(View view) {
        finish();
    }
}
