package com.example.clinic_project.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.MyFavouriteDoctorResponse;
import com.example.clinic_project.Response.SpecializationListResponse;
import com.example.clinic_project.adapter.MyFavouriteDoctorAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.MyFavouriteDoctorHolder;
import com.example.clinic_project.model.Doctor;
import com.example.clinic_project.model.SpecializationList;
import com.example.clinic_project.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFavouriteDoctorActivity extends AppCompatActivity implements MyFavouriteDoctorHolder.OnItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private SearchView searchView;
    private RetrofitService service;
    private TextView txtdoctor;
    private ImageView imgsetting;
    private MyFavouriteDoctorAdapter adapter;

    String token = null;
    List<Doctor> doctors = new ArrayList<>();
    List<Doctor> newDoctors = new ArrayList<>();
    List<SpecializationList> specializationLists = new ArrayList<>();
    List<String> categories = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite_doctor);

        initActivity();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initActivity() {

        txtdoctor = findViewById(R.id.textdoctor);
        imgsetting = findViewById(R.id.imgsetting);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.sv);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_logout) {

            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);// 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}





