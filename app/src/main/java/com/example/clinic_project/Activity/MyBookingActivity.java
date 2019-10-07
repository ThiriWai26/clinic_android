package com.example.clinic_project.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.MyBookingResponse;
import com.example.clinic_project.adapter.MyBookingAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.MyBookingHolder;
import com.example.clinic_project.model.Bookings;
import com.example.clinic_project.service.RetrofitService;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBookingActivity extends AppCompatActivity implements MyBookingHolder.OnItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private MyBookingAdapter adapter;
    private RetrofitService service;
    private CircleImageView imgdoctor;
    private Button btncancel;
    private TextView txtdoctorname,txthospitalname,txtdate,txtmap;
    private String token = null;

    List<Bookings> bookings = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);
        initBookingList();

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

    private void initBookingList() {

        txtdoctorname = findViewById(R.id.tvName);
        txthospitalname = findViewById(R.id.tvhospitalname);
        txtdate = findViewById(R.id.tvtime);
        txtmap = findViewById(R.id.tvMap);
        imgdoctor = findViewById(R.id.profile);
        btncancel = findViewById(R.id.btncancel);
        service = new RetrofitService();

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MyBookingAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getMyBooking();

    }

    private void getMyBooking() {

        Log.e("mybooking","success");
        Api myBookingApi = service.getRetrofitService().create(Api.class);
        myBookingApi.getMyBooking(token).enqueue(new Callback<MyBookingResponse>() {
            @Override
            public void onResponse(Call<MyBookingResponse> call, Response<MyBookingResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        Log.e("response.body","success");
                        adapter.addItem(response.body().upcomingBooking.bookings);
                        Log.e("Hospital_buildingSize",String.valueOf(bookings.size()));
                        adapter.notifyDataSetChanged();

                    }
                    else {
                        Log.e("response.body","fail");
                    }
                }else {
                    Log.e("response","fail");
                }
            }

            @Override
            public void onFailure(Call<MyBookingResponse> call, Throwable t) {

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

    @Override
    public void onItemClick(int parseInt) {

    }
}
