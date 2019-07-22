package com.example.clinic_project.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.DoctorsByClinicResponse;
import com.example.clinic_project.adapter.DoctorAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.DoctorHolder;
import com.example.clinic_project.model.Doctor;
import com.example.clinic_project.model.SpecializationList;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorsByClinic extends AppCompatActivity implements DoctorHolder.OnDoctorClickListener {

    private RecyclerView recyclerView;
    private RetrofitService retrofitService;
    private TextView textname,texttype;
    private ImageView imageback;
    DoctorAdapter adapter;
    ArrayAdapter<String> dataAdapter;

    List<Doctor> doctors = new ArrayList<>();
    private String token = null;
    private int clinic_id = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_by_clinic);

        initActivity();

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HospitalDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initActivity() {

        textname = findViewById(R.id.tvname);
        texttype = findViewById(R.id.tvType);
        imageback = findViewById(R.id.imageback);
        recyclerView = findViewById(R.id.recyclerView);
        retrofitService = new RetrofitService();
        adapter = new DoctorAdapter(this);

        token = Token.MyToken.getToken();
        Log.e("DrawerActivityToken", token);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getDoctorByClinic();

//

    }

    private void getDoctorByClinic() {

        Log.e("doctorsbyClinic", "success");

        final Api doctorsByClinic = retrofitService.getRetrofitService().create(Api.class);
        doctorsByClinic.getDoctorByClinic(token,clinic_id).enqueue(new Callback<DoctorsByClinicResponse>() {
            @Override
            public void onResponse(Call<DoctorsByClinicResponse> call, Response<DoctorsByClinicResponse> response) {

                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        doctors = response.body().doctorByClinic.doctors;
                        adapter.addDoctors(doctors);

                        Log.e("DoctorsByClinic", String.valueOf(doctors.size()));

                    }


                }
            }

            @Override
            public void onFailure(Call<DoctorsByClinicResponse> call, Throwable t) {

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
