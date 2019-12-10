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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.adapter.CustomExpandableListAdapter;
import com.example.clinic_project.datasource.ExpandableListDataSource;
import com.example.clinic_project.fragment.FragmentClinic;
import com.example.clinic_project.fragment.FragmentDoctor;
import com.example.clinic_project.fragment.FragmentHostipal;
import com.example.clinic_project.fragment.FragmentLab;
import com.example.clinic_project.fragment.FragmentMyBooking;
import com.example.clinic_project.fragment.FragmentMyFavouriteDoctor;
import com.example.clinic_project.fragment.FragmentMyFavouriteHospital;
import com.example.clinic_project.fragment.HomeFragment;
import com.example.clinic_project.fragment.NavigationManager;
import com.example.clinic_project.service.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomenaviActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tbTitle;
    private Button btnDoctor, btnHospital, btnLab, btnClinc;
    private CardView mybooking,myfavdoctor,myfavhospital;
    private String token;
    private DrawerLayout mDrawerLayout;

    private NavigationManager mNavigationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_navi);

        initActivity();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    private void initActivity() {

        tbTitle=findViewById(R.id.tvTitle);
        tbTitle.setText("Clinic Management");

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


    private void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();

    }
}
