package com.example.clinic_project.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.support.v7.widget.SearchView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.BuildingListResponse;
import com.example.clinic_project.Response.ClinicListResponse;
import com.example.clinic_project.Response.TownListResponse;
import com.example.clinic_project.adapter.BuildingAdapter;
import com.example.clinic_project.adapter.ClinicAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.BuildingHolder;
import com.example.clinic_project.holder.ClinicHolder;
import com.example.clinic_project.model.Building;
import com.example.clinic_project.model.Clinic;
import com.example.clinic_project.model.ClinicDetail;
import com.example.clinic_project.model.TownList;
import com.example.clinic_project.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClinicActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ClinicHolder.OnClinicClickListener {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private RetrofitService service;
    private TextView txtclinic;
    private ImageView imgsetting;
    ClinicAdapter adapter;
    private int doctorId = -1;


    List<Clinic> clinic = new ArrayList<>();
    List<Clinic> newClinics = new ArrayList<>();
    private String token;
    ArrayAdapter<String> dataAdapter;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initClinic();
        getClinicList(token,doctorId);
        searchViewModify();
        searchViewFilter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void initClinic() {


        searchView = findViewById(R.id.sv);
        recyclerView = findViewById(R.id.recyclerView);
        txtclinic = findViewById(R.id.txtclinic);
        imgsetting = findViewById(R.id.imgsetting);
        service = new RetrofitService();
        adapter = new ClinicAdapter(this);

        Bundle b = getIntent().getExtras();
        token = b.getString("Token");

        Log.e("ClinicActivityToken", token);
    }

    private void getClinicList(String token, int doctorId) {

        Log.e("ClinicActivity","success");
        Api clinicListApi = service.getRetrofitService().create(Api.class);
        clinicListApi.getClinicList(token,doctorId).enqueue(new Callback<ClinicListResponse>() {
            @Override
            public void onResponse(Call<ClinicListResponse> call, Response<ClinicListResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){

                        clinic = response.body().clinicLists.clinics;
                        adapter.addItem(clinic);

                        Log.e("ClinicLists", String.valueOf(clinic.size()));


                    }

                }
            }

            @Override
            public void onFailure(Call<ClinicListResponse> call, Throwable t) {

            }
        });


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    private void searchViewFilter() {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                s = s.toLowerCase(Locale.getDefault());
                if(s.length() != 0){
                    newClinics.clear();
                    for (Clinic clinic : clinic){
                        if (clinic.name.toLowerCase(Locale.getDefault()).contains(s)) {
                            newClinics.add(clinic);
                        }
                    }
                    adapter.addItem(newClinics);
                }else{
                    adapter.addItem(clinic);
                }
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                s = s.toLowerCase(Locale.getDefault());
                if (s.length() != 0){
                    newClinics.clear();
                    for (Clinic clinic : clinic) {
                        if (clinic.name.toLowerCase(Locale.getDefault()).contains(s)) {

                            newClinics.add(clinic);
                        }
                    }
                    adapter.addItem(newClinics);
                }else {
                    adapter.addItem(clinic);
                }

                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void searchViewModify() {

        searchView.setIconified(false);
        searchView.setIconifiedByDefault(false);
        android.support.v7.widget.SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setHint("Search Doctors");
        searchAutoComplete.setHintTextColor(Color.WHITE);
        searchAutoComplete.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;    }


    @Override
    public void onClinicClick(int id) {

        Intent intent = new Intent(getApplicationContext(), ClinicDetailActivity.class);
        intent.putExtra("clinicId", id);
        intent.putExtra("Token", token);
        Log.e("clinic_id", String.valueOf(id));
        startActivity(intent);
    }
}
