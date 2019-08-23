package com.example.clinic_project.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class HomenaviActivity extends AppCompatActivity implements  View.OnClickListener {

    private TextView tbTitle;
    private Button btnDoctor, btnHospital, btnLab, btnClinc;
    private CardView mybooking,myfavdoctor,myfavhospital;
    private String token;

    private String mActivityTitle;
    private String[] items;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ExpandableListView mExpandableListView;
    private ExpandableListAdapter mExpandableListAdapter;
    private List<String> mExpandableListTitle;
    private Map<String, List<String>> mExpandableListData;

    private NavigationManager mNavigationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_navi);

        initActivity();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);

//        toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
        mActivityTitle = getTitle().toString();
        mExpandableListView = findViewById(R.id.navList);

        initItems();

        LayoutInflater inflater = getLayoutInflater();

        View listHeaderView = inflater.inflate(R.layout.nav_header_drawer2, null, false);
        mExpandableListView.addHeaderView(listHeaderView);

        mExpandableListData = ExpandableListDataSource.getData(this);
        mExpandableListTitle = new ArrayList(mExpandableListData.keySet());

        addDrawerItems();
        setupDrawer();

        if (savedInstanceState == null) {
            selectFirstItemAsDefault();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    private void setupDrawer() {

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.Home_Navi);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
//        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    private void addDrawerItems() {

        mExpandableListAdapter = new CustomExpandableListAdapter(this, mExpandableListTitle, mExpandableListData);
        mExpandableListView.setAdapter(mExpandableListAdapter);
        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                getSupportActionBar().setTitle(mExpandableListTitle.get(groupPosition).toString());
            }
        });

        mExpandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                getSupportActionBar().setTitle(R.string.Home_Navi);
            }
        });

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                String selectedItem = ((List) (mExpandableListData.get(mExpandableListTitle.get(groupPosition))))
                        .get(childPosition).toString();
                getSupportActionBar().setTitle(selectedItem);

                if (items[1].equals(mExpandableListTitle.get(groupPosition))) {
                    mNavigationManager.showFragmentAction(selectedItem);
                } else if (items[2].equals(mExpandableListTitle.get(groupPosition))) {
                    mNavigationManager.showFragmentComedy(selectedItem);
                }  else {
                    throw new IllegalArgumentException("Not supported fragment type");
                }

                return false;
            }
        });
    }

    private void selectFirstItemAsDefault() {

        if (mNavigationManager != null) {
            String firstActionMovie = getResources().getStringArray(R.array.medicines)[0];
            mNavigationManager.showFragmentAction(firstActionMovie);
            getSupportActionBar().setTitle(firstActionMovie);
        }
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

    private void initItems() {

        items = getResources().getStringArray(R.array.HomeNavigation);
    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//
//        }
//    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        } else if (id == R.id.nav_logout) {
//
//            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);// 0 - for private mode
//            SharedPreferences.Editor editor = pref.edit();
//            editor.clear();
//            editor.commit();
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
//            finish();
//        }
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }


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
