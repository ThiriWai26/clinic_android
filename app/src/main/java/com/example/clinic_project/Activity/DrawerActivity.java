package com.example.clinic_project.Activity;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.DoctorListResponse;
import com.example.clinic_project.Response.SpecializationListResponse;
import com.example.clinic_project.adapter.DoctorAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.DoctorHolder;
import com.example.clinic_project.model.Building;
import com.example.clinic_project.model.Doctor;
import com.example.clinic_project.model.SpecializationList;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DoctorHolder.OnDoctorClickListener {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private RetrofitService service;
    private TextView txtdoctor;
    private ImageView imgsetting;
    DoctorAdapter adapter;
    ArrayAdapter<String> dataAdapter;
    List<SpecializationList> specializationLists = new ArrayList<>();
    List<String> categories = new ArrayList<>();

    List<Doctor> doctors = new ArrayList<>();
    List<Doctor> newDoctors = new ArrayList<>();
    private String token = null;
    private int specializationId = -1;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initDoctorList();
        getDoctorsList();
        searchViewFilter();
        searchViewModify();

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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.

//        getMenuInflater().inflate(R.menu.search_view_menu, menu);
//
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView =
//                (SearchView) MenuItemCompat.getActionView(searchItem);
//
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));
//
//        return super.onCreateOptionsMenu(menu);


    @SuppressLint("ResourceType")
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        getMenuInflater().inflate(R.id.action_search, (Menu) menuItem);
        getMenuInflater().inflate(R.id.action_settings, (Menu) menuItem);

       MenuItem searchItem  = ((Menu) menuItem).findItem(R.id.action_search);
       MenuItem searchItem1 = ((Menu) menuItem).findItem(R.id.action_settings);

       SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
       SearchView searchView1 = (SearchView) MenuItemCompat.getActionView(searchItem1);

       SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
       searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
       searchView1.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
       return super.onOptionsItemSelected(menuItem);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

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


    //initial work of activity
    private void initDoctorList() {


        txtdoctor = findViewById(R.id.textdoctor);
        imgsetting = findViewById(R.id.imgsetting);
        searchView = findViewById(R.id.sv);
        recyclerView = findViewById(R.id.recyclerView);
        service = new RetrofitService();
        adapter = new DoctorAdapter(this);
//        spinner = findViewById(R.id.spinner);

        token = Token.MyToken.getToken();

        Log.e("DrawerActivityToken", token);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        spinner.setOnItemSelectedListener(this);

//        dataAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.spinner_item, categories);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(dataAdapter);
        getSpecializationList(token);

        Bundle bundle = getIntent().getExtras();
        specializationId = bundle.getInt("specializationId");

        if (specializationId != -1) {
            getDoctorsBySpecialization(specializationId);
            Log.e("specializationId", String.valueOf(specializationId));
        }
    }

    private void getSpecializationList(String token) {

        Api specializationListApi = service.getRetrofitService().create(Api.class);

        specializationListApi.getSpecializationList(token).enqueue(new Callback<SpecializationListResponse>() {
            @Override
            public void onResponse(Call<SpecializationListResponse> call, Response<SpecializationListResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess) {

                        specializationLists = response.body().specializations;
                        Log.e("Special size", String.valueOf(response.body().specializations.size()));


                        for (SpecializationList special : specializationLists) {
                            categories.add(special.name);
                            Log.e("name", special.name);
                        }
                        categories.add(0, "All");
//                        dataAdapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "Successful!", Toast.LENGTH_SHORT).show();

                    }
                }

            }

            @Override
            public void onFailure(Call<SpecializationListResponse> call, Throwable t) {

            }

        });


    }

    private void getDoctorsList() {

        Api doctorListApi = service.getRetrofitService().create(Api.class);
        doctorListApi.getDoctorList(token).enqueue(new Callback<DoctorListResponse>() {
            @Override
            public void onResponse(Call<DoctorListResponse> call, Response<DoctorListResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().isSuccess) {
                        doctors = response.body().doctorLists.doctors;
                        adapter.addDoctors(doctors);

                        Log.e("DoctorLists", String.valueOf(doctors.size()));
                    }
                }

            }

            @Override
            public void onFailure(Call<DoctorListResponse> call, Throwable t) {

            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void searchViewModify() {

        SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(R.id.search_src_text);
        searchAutoComplete.setTextColor(Color.BLACK);
        searchAutoComplete.setHint("Search Doctor");
        searchAutoComplete.setHintTextColor(Color.BLACK);
        searchAutoComplete.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);

    }

    private void searchViewFilter() {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                s = s.toLowerCase(Locale.getDefault());
                if(s.length() != 0){
                    newDoctors.clear();
                    for (Doctor doctors : doctors){
                        if (doctors.name.toLowerCase(Locale.getDefault()).contains(s)) {
                            newDoctors.add(doctors);
                        }
                    }
                    adapter.addDoctors(newDoctors);
                }else{
                    adapter.addDoctors(doctors);
                }
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                s = s.toLowerCase(Locale.getDefault());
                if (s.length() != 0){
                    newDoctors.clear();
                    for (Doctor doctors : doctors) {
                        if (doctors.name.toLowerCase(Locale.getDefault()).contains(s)) {

                            newDoctors.add(doctors);
                        }
                    }
                    adapter.addDoctors(newDoctors);
                }else {
                    adapter.addDoctors(doctors);
                }

                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                return false;
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


    private void getDoctorsBySpecialization(int special_id) {
        Api doctorsBySpecialApi = service.getRetrofitService().create(Api.class);
        doctorsBySpecialApi.getDoctorList(token, special_id).enqueue(new Callback<DoctorListResponse>() {
            @Override
            public void onResponse(Call<DoctorListResponse> call, Response<DoctorListResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess) {
                        adapter.addDoctors(response.body().doctorLists.doctors);

                    }
                }
            }

            @Override
            public void onFailure(Call<DoctorListResponse> call, Throwable t) {

            }
        });
    }


}
