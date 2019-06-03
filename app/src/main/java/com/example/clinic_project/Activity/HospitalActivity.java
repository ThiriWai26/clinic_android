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
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.BuildingListResponse;
import com.example.clinic_project.adapter.BuildingAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.BuildingHolder;
import com.example.clinic_project.model.Building;
import com.example.clinic_project.model.TownList;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HospitalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BuildingHolder.OnBuildingClickListener, Spinner.OnItemSelectedListener {

    private Intent intent;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private RetrofitService service;
    private Spinner spinnerType, spinnerLocation;


    private int typeId = 2;
    private int townId = 0;

    List<String> locations = new ArrayList<>();
    List<String> type = new ArrayList<>();
    List<TownList> townLists = new ArrayList<>();

    private String nextPage, previousPage, firstPage, lastPage;
    private int page=1;
    private BuildingAdapter adapter;
    private ArrayAdapter<String> dataAdapter;

    List<Building> building = new ArrayList<>();
    List<Building> newBuildings = new ArrayList<>();

    private String token = null;
    private LinearLayoutManager linearLayoutManager;
    private int lastVisibleItemPosition=0;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        initBuildingList();


        searchViewModify();
        searchViewFilter();
        getBuildingList(2, 0);


        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = linearLayoutManager.getItemCount();

                int currentFirstVisible = linearLayoutManager.findFirstVisibleItemPosition();
                lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                Log.i("currentfirstVisible", String.valueOf(currentFirstVisible));
                Log.i("lastfirstVisible", String.valueOf(lastVisibleItemPosition));
                Log.i("totalItemCount",String.valueOf(totalItemCount));

                if(nextPage!=null && lastVisibleItemPosition==19 ){

                }


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
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //initial work of activity
    private void initBuildingList() {


        searchView = findViewById(R.id.sv);
        recyclerView = findViewById(R.id.recyclerView);
        service = new RetrofitService();
        adapter = new BuildingAdapter(this);
        spinnerType = findViewById(R.id.spinner_type);
        spinnerLocation = findViewById(R.id.spinner_location);

        token= Token.MyToken.getToken();

        Log.e("HospitalActivityToken", token);


        type.add("Hospital");
        type.add("Clinic");
        type.add("Lab");
        type.add("All");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getBaseContext(), R.layout.spinner_item, type);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        locations.add(0, "All");
        dataAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.spinner_item, locations);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocation.setAdapter(dataAdapter);
        spinnerLocation.setOnItemSelectedListener(this);

        spinnerType.setAdapter(dataAdapter1);
        spinnerType.setOnItemSelectedListener(this);

        getLocationList(token);

    }

    private void getLocationList(String token) {

    }

    private void getBuildingList(int typeId, int locationId) {
        Log.e("buildingList", "success");

        Api buildingApi = service.getRetrofitService().create(Api.class);

        buildingApi.getBuildingList(token, typeId, locationId).enqueue(new Callback<BuildingListResponse>() {
            @Override
            public void onResponse(Call<BuildingListResponse> call, Response<BuildingListResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().isSuccess) {
                        building = response.body().buildingList.data;
                        adapter.addItem(building);
                        Log.e("Hospital_buildingSize", String.valueOf(building.size()));
                    }
                }

            }

            @Override
            public void onFailure(Call<BuildingListResponse> call, Throwable t) {

            }

        });

    }


    private void searchViewFilter() {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                s = s.toLowerCase(Locale.getDefault());
                if (s.length() != 0) {
                    newBuildings.clear();
                    for (Building building : building) {
                        if (building.name.toLowerCase(Locale.getDefault()).contains(s)) {

                            newBuildings.add(building);
                        }
                    }
                    adapter.addItem(newBuildings);
                } else {
                    adapter.addItem(building);
                }
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                s = s.toLowerCase(Locale.getDefault());
                if (s.length() != 0) {
                    newBuildings.clear();
                    for (Building building : building) {
                        if (building.name.toLowerCase(Locale.getDefault()).contains(s)) {

                            newBuildings.add(building);
                        }
                    }
                    adapter.addItem(newBuildings);
                } else {
                    adapter.addItem(building);
                }

                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    //search view modify

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void searchViewModify() {

        searchView.setIconified(false);
        searchView.setIconifiedByDefault(false);

        SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setHint("Search Doctors");
        searchAutoComplete.setHintTextColor(Color.WHITE);
        searchAutoComplete.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);

        ImageView searchIcon = searchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
        searchIcon.focusSearch(View.FOCUS_RIGHT);


    }

    @Override
    public void onBuildingClick(int id) {

        Intent intent = new Intent(this, HospitalDetailActivity.class);
        intent.putExtra("buildingId", id);
        intent.putExtra("typeId",2);
        Log.e("doctor_id", String.valueOf(id));
        startActivity(intent);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String typeName = spinnerType.getSelectedItem().toString();
        String locationName = spinnerLocation.getSelectedItem().toString();
        Log.e("typeName", typeName);

        if (locationName.equals("All")) {
            townId = 0;
        }

        for (TownList townList : townLists) {
            if (townList.name.equals(locationName)) {
                townId = townList.id;
                int e = Log.e(" name:locationId", townList.name + String.valueOf(townId));
                break;
            }
        }

        switch (typeName) {
            case "Clinic":

                typeId = 1;
                break;

            case "Hospital":
                typeId = 2;
                break;

            case "Lab":
                typeId = 3;
                break;

            case "All":
                typeId = 0;
                break;
        }

        getBuildingList(typeId, townId);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}