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
import android.support.v7.widget.SearchView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.BuildingListResponse;
import com.example.clinic_project.Response.TownListResponse;
import com.example.clinic_project.adapter.BuildingAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.BuildingHolder;
import com.example.clinic_project.model.Building;
import com.example.clinic_project.model.TownList;
import com.example.clinic_project.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClinicActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BuildingHolder.OnBuildingClickListener {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private RetrofitService service;
    private TextView txtclinic;
    private ImageView imgsetting;
    private int typeId = 2;
    private int townId = 0;

    private BuildingAdapter adapter;
    List<String> locations = new ArrayList<>();
    List<TownList> townLists = new ArrayList<>();

    List<Building> building = new ArrayList<>();
    List<Building> newBuildings = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private String token = null;
    private int townListId = -1;


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
        searchViewModify();
//        searchViewFilter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void initClinic() {

        searchView = findViewById(R.id.sv);
        recyclerView = findViewById(R.id.recyclerView);
        txtclinic = findViewById(R.id.txtclinic);
        imgsetting = findViewById(R.id.imgsetting);
        service = new RetrofitService();
        adapter = new BuildingAdapter(this);

        Bundle b = getIntent().getExtras();
        token = b.getString("Token");

        Log.e("ClinicActivityToken", token);

        getLocationList(token);
        getClinicList(typeId,townId);

    }

    private void getClinicList(int typeId, int townId) {

        Log.e("clinicList","success");

        Api buildingListApi = service.getRetrofitService().create(Api.class);
        buildingListApi.getBuildingList(token,typeId,townId).enqueue(new Callback<BuildingListResponse>() {
            @Override
            public void onResponse(Call<BuildingListResponse> call, Response<BuildingListResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){

                        building = response.body().buildingList.data;
                        adapter.addItem(building);
                        Log.e("Hospital_buildingSize",String.valueOf(building.size()));

                    }

                }
            }

            @Override
            public void onFailure(Call<BuildingListResponse> call, Throwable t) {

            }
        });

    }

    private void getLocationList(String token) {

        Log.e("LocationList","success");
        Api townListApi = service.getRetrofitService().create(Api.class);
        townListApi.getTownList(token).enqueue(new Callback<TownListResponse>() {

            @Override
            public void onResponse(Call<TownListResponse> call, Response<TownListResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        townLists = response.body().towns;
                        for (TownList townList : townLists) {
                            locations.add(townList.name);
                            Log.e("locations", townList.name);
                        }
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "Successful!", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<TownListResponse> call, Throwable t) {

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



//    private void searchViewFilter() {
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//
//                s = s.toLowerCase(Locale.getDefault());
//                if(s.length() != 0){
//                    newClinics.clear();
//                    for (Clinic clinic : clinic){
//                        if (clinic.name.toLowerCase(Locale.getDefault()).contains(s)) {
//                            newClinics.add(clinic);
//                        }
//                    }
//                    adapter.addItem(newClinics);
//                }else{
//                    adapter.addItem(clinic);
//                }
//                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//
//                s = s.toLowerCase(Locale.getDefault());
//                if (s.length() != 0){
//                    newClinics.clear();
//                    for (Clinic clinic : clinic) {
//                        if (clinic.name.toLowerCase(Locale.getDefault()).contains(s)) {
//
//                            newClinics.add(clinic);
//                        }
//                    }
//                    adapter.addItem(newClinics);
//                }else {
//                    adapter.addItem(clinic);
//                }
//
//                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
//                return false;
//            }
//        });
//
//    }


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
        return true;
    }



    @Override
    public void onBuildingClick(int id) {

        Intent intent = new Intent(this, HospitalDetailActivity.class);
        intent.putExtra("buildingId", id);
        intent.putExtra("typeId",2);
        Log.e("building_id",String.valueOf(id));
        startActivity(intent);
    }
}
