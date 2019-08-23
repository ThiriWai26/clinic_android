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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic_project.R;
import com.example.clinic_project.fragment.FragmentClinic;
import com.example.clinic_project.fragment.FragmentDoctor;
import com.example.clinic_project.fragment.FragmentHostipal;
import com.example.clinic_project.fragment.FragmentLab;
import com.example.clinic_project.fragment.FragmentMyBooking;
import com.example.clinic_project.fragment.FragmentMyFavouriteDoctor;
import com.example.clinic_project.fragment.FragmentMyFavouriteHospital;
import com.example.clinic_project.fragment.HomeFragment;
import com.example.clinic_project.service.Token;

public class HomenaviActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private TextView tbTitle;
    private Button btnDoctor, btnHospital, btnLab, btnClinc;
    private CardView mybooking,myfavdoctor,myfavhospital;
    private String token;

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

        tbTitle=findViewById(R.id.tvTitle);
        tbTitle.setText("Clinic Management");

        btnDoctor = findViewById(R.id.btn_doctor);
        btnHospital = findViewById(R.id.btn_hospital);
        btnLab = findViewById(R.id.btn_lab);
        btnClinc = findViewById(R.id.btn_clinic);

        mybooking = findViewById(R.id.mybooking);
        myfavdoctor = findViewById(R.id.myfavdoctor);
        myfavhospital = findViewById(R.id.myfavhospital);

        btnDoctor.setOnClickListener(this);
        btnHospital.setOnClickListener(this);
        btnLab.setOnClickListener(this);
        btnClinc.setOnClickListener(this);

        mybooking.setOnClickListener(this);
        myfavdoctor.setOnClickListener(this);
        myfavhospital.setOnClickListener(this);

        token= Token.MyToken.getToken();
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


    private void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();

    }

    @Override
    public void onClick(View v) {
        if (v == btnDoctor) {
            tbTitle.setText("Doctors");
            loadFragment(new FragmentDoctor());
        }

        if (v == btnHospital) {

            tbTitle.setText("Hospitals");
            loadFragment(new FragmentHostipal());
        }

        if (v == btnLab) {

            tbTitle.setText("Labs");
            loadFragment(new FragmentLab());
        }

        if (v == btnClinc) {

            tbTitle.setText("Clinics");
            loadFragment(new FragmentClinic());
        }

        if (v == mybooking){

            tbTitle.setText("My Booking");
            loadFragment(new FragmentMyBooking());
        }

        if(v == myfavdoctor){
            tbTitle.setText("My Favourite Doctors");
            loadFragment(new FragmentMyFavouriteDoctor());
        }

        if(v == myfavhospital){

            tbTitle.setText("My Favourite Hospital");
            loadFragment(new FragmentMyFavouriteHospital());
        }


    }
}
