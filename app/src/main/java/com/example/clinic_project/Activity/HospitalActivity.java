package com.example.clinic_project.Activity;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.support.v7.widget.SearchView;
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
import com.example.clinic_project.service.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        BuildingHolder.OnBuildingClickListener {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private RetrofitService service;
    private TextView txthospital;
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

        service = new RetrofitService();

        initBuildingList();

//        searchViewFilter();
//        searchViewModify();

    }

    private void getBuildingList(int typeId, int townId) {

        Log.e("buildingList","success");

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

    private void initBuildingList() {

        searchView = findViewById(R.id.sv);
        recyclerView = findViewById(R.id.recyclerView);
        txthospital = findViewById(R.id.txthospital);
        imgsetting = findViewById(R.id.imgsetting);
        adapter = new BuildingAdapter(this);

        Bundle b = getIntent().getExtras();
        token = b.getString("Token");
        Log.e("HospitalActivityToken", token);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getLocationList(token);
        getBuildingList(typeId,townId);

//        Bundle bundle = getIntent().getExtras();
//        townListId = bundle.getInt("specializationId");
//
//        if (townListId != -1) {
//            getLocationList(String.valueOf(townListId));
//            Log.e("townListId", String.valueOf(townListId));
//        }

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
    public void onBuildingClick(int id) {

        Intent intent = new Intent(this, HospitalDetailActivity.class);
        intent.putExtra("buildingId", id);
        intent.putExtra("typeId",2);
        Log.e("building_id",String.valueOf(id));
        startActivity(intent);

    }


    @SuppressLint("ResourceType")
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        getMenuInflater().inflate(R.id.action_search, (Menu) menuItem);
        getMenuInflater().inflate(R.id.action_settings, (Menu) menuItem);

        MenuItem searchItem  = ((Menu) menuItem).findItem(R.id.action_search);
        MenuItem searchItem1 = ((Menu) menuItem).findItem(R.id.action_settings);

        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(searchItem);
        android.support.v7.widget.SearchView searchView1 = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(searchItem1);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView1.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return super.onOptionsItemSelected(menuItem);
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
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //search view modify

//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
//    private void searchViewModify() {
//
//        searchView.setIconified(false);
//        searchView.setIconifiedByDefault(false);
//
//        android.support.v7.widget.SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
//        searchAutoComplete.setHint("Search Doctors");
//        searchAutoComplete.setHintTextColor(Color.WHITE);
//        searchAutoComplete.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
//
//        ImageView searchIcon = searchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
//        searchIcon.focusSearch(View.FOCUS_RIGHT);

//    }

//    private void searchViewFilter() {
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                query = query.toLowerCase(Locale.getDefault());
//                if(query.length() != 0){
//                    newBuildings.clear();
//                    for (Building building : building) {
//                        if (building.name.toLowerCase(Locale.getDefault()).contains(query)) {
//
//                            newBuildings.add(building);
//                        }
//                    }
//                    adapter.addItem(newBuildings);
//                } else {
//                    adapter.addItem(building);
//                }
//                Toast.makeText(getApplicationContext(), query, Toast.LENGTH_LONG).show();
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                newText = newText.toLowerCase(Locale.getDefault());
//                if(newText.length() != 0){
//                    newBuildings.clear();
//                    for (Building building : building) {
//                        if (building.name.toLowerCase(Locale.getDefault()).contains(newText)) {
//
//                            newBuildings.add(building);
//                        }
//                    }
//                    adapter.addItem(newBuildings);
//                } else {
//                    adapter.addItem(building);
//                }
//
//                Toast.makeText(getApplicationContext(), newText, Toast.LENGTH_LONG).show();
//                return false;
//            }
//
//
//            });
//    }


}
