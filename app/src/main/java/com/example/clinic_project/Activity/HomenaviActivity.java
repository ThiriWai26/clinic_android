package com.example.clinic_project.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.clinic_project.R;
import com.example.clinic_project.fragment.FragmentHostipal;
import com.example.clinic_project.fragment.HomeFragment;

public class HomenaviActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_navi);

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

//        btnDoctor = findViewById(R.id.btn_doctor);
//        btnHospital = findViewById(R.id.btn_hospital);
//        btnLab = findViewById(R.id.btn_lab);
//        btnClinc = findViewById(R.id.btn_clinic);
//        mybooking = findViewById(R.id.mybooking);
//        myfavdoctor = findViewById(R.id.myfavdoctor);
//        myfavhospital = findViewById(R.id.myfabhospital);
//
//        btnDoctor.setOnClickListener(this);
//        btnHospital.setOnClickListener(this);
//        btnLab.setOnClickListener(this);
//        btnClinc.setOnClickListener(this);
//
//        b = getIntent().getExtras();
//
//        token = b.getString("Token");
//        Log.e("HomeActivityToken", token);
//
//        mybooking.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MyBookingActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        myfavdoctor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MyFavouriteDoctorActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        myfavhospital.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MyFavouriteHospitalActivity.class);
//                startActivity(intent);
//            }
//        });
        loadFragment(new HomeFragment());

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

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

//    @Override
//    public void onClick(View v) {
//
//        if (v == btnDoctor) {
//
//
//            intent = new Intent(HomenaviActivity.this, DrawerActivity.class);
//
//            startActivity(intent);
//
//        }
//
//        if (v == btnHospital) {
//
//            intent = new Intent(HomenaviActivity.this, HospitalActivity.class);
//
//            startNextActivity(intent);
//
//        }
//
//        if (v == btnLab) {
//
//            intent = new Intent(HomenaviActivity.this, LabActivity.class);
//
//            startNextActivity(intent);
//
//        }
//
//        if (v == btnClinc) {
//
//            intent = new Intent(HomenaviActivity.this, ClinicActivity.class);
//
//            startNextActivity(intent);
//
//        }
//
//    }

    private void loadFragment(Fragment fragment){

        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();

    }

}
