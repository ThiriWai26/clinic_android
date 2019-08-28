package com.example.clinic_project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.clinic_project.R;
import com.example.clinic_project.fragment.FragmentClinic;
import com.example.clinic_project.fragment.FragmentDoctor;
import com.example.clinic_project.fragment.FragmentHostipal;
import com.example.clinic_project.fragment.FragmentLab;
import com.example.clinic_project.fragment.FragmentMyBooking;
import com.example.clinic_project.fragment.FragmentMyFavouriteDoctor;
import com.example.clinic_project.fragment.FragmentMyFavouriteHospital;

public class AnotherHomenaviActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String fragmentName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_homenavi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        init();
    }

    private void init() {

        Intent intent = getIntent();
        fragmentName = intent.getStringExtra("fragment");

        Log.e("fragmentName",fragmentName);

        if(fragmentName.equals("Doctor")){
            loadFragment(new FragmentDoctor());
        }
        if(fragmentName.equals("Hospital")){
            loadFragment(new FragmentHostipal());
        }
        if(fragmentName.equals("Lab")){
            loadFragment(new FragmentLab());
        }
        if(fragmentName.equals("Clinic")){
            loadFragment(new FragmentClinic());
        }
        if(fragmentName.equals("Booking")){
            loadFragment(new FragmentMyBooking());
        }
        if(fragmentName.equals("Favdoctor")){
            loadFragment(new FragmentMyFavouriteDoctor());
        }
        if(fragmentName.equals("Favhospital")){
            loadFragment(new FragmentMyFavouriteHospital());
        }
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.another_frame_layout,fragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.another_homenavi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
