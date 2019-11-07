package com.example.clinic_project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.adapter.CustomExpandableListAdapter;
import com.example.clinic_project.datasource.ExpandableListDataSource;
import com.example.clinic_project.fragment.FragmentAssessment;
import com.example.clinic_project.fragment.FragmentClinic;
import com.example.clinic_project.fragment.FragmentDoctor;
import com.example.clinic_project.fragment.FragmentDrag;
import com.example.clinic_project.fragment.FragmentExamination;
import com.example.clinic_project.fragment.FragmentHostipal;
import com.example.clinic_project.fragment.FragmentLab;
import com.example.clinic_project.fragment.FragmentMyBooking;
import com.example.clinic_project.fragment.FragmentMyFavouriteDoctor;
import com.example.clinic_project.fragment.FragmentMyFavouriteHospital;
import com.example.clinic_project.fragment.FragmentRegistration;
import com.example.clinic_project.fragment.NavigationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnotherHomenaviActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private String fragmentName;
    private TextView txttitle;

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
        setContentView(R.layout.activity_another_homenavi);

        txttitle = findViewById(R.id.tvTitle);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        mExpandableListView = findViewById(R.id.navList);

        init();
        initItems();

        LayoutInflater inflater = getLayoutInflater();
        View listHeaderView = inflater.inflate(R.layout.nav_header_drawer2, null, false);
        mExpandableListView.addHeaderView(listHeaderView);
        mExpandableListData = ExpandableListDataSource.getData(this);
        mExpandableListTitle = new ArrayList(mExpandableListData.keySet());

        addDrawerItems();
        setupDrawer();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            selectFirstItemAsDefault();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);


    }

    private void selectFirstItemAsDefault() {

        if (mNavigationManager != null) {
            String firstActionMovie = getResources().getStringArray(R.array.medicines)[0];

            mNavigationManager.showFragmentHome(firstActionMovie);
            getSupportActionBar().setTitle(firstActionMovie);
        }
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

                if (items[0].equals(mExpandableListTitle.get(groupPosition))) {
                    mNavigationManager.showFragmentHome(selectedItem);
                } else if (items[1].equals(mExpandableListTitle.get(groupPosition))) {
                    mNavigationManager.showFragmentMedicine(selectedItem);
                } else if (items[2].equals(mExpandableListTitle.get(groupPosition))) {
                    mNavigationManager.showFragmentFavourite(selectedItem);
                } else if (items[3].equals(mExpandableListTitle.get(groupPosition))) {
                    mNavigationManager.showFragmentProfile(selectedItem);
                } else if (items[4].equals(mExpandableListTitle.get(groupPosition))) {
                    mNavigationManager.showFragmentSetting(selectedItem);
                } else if (items[5].equals(mExpandableListTitle.get(groupPosition))) {
                    mNavigationManager.showFragmentLogout(selectedItem);
                } else {
                    throw new IllegalArgumentException("Not supported fragment type");
                }

                return false;
            }
        });
    }

    private void initItems() {

        items = getResources().getStringArray(R.array.HomeNavigation);

    }

    private void init() {


        Intent intent = getIntent();
        fragmentName = intent.getStringExtra("fragment");

        Log.e("fragmentName",fragmentName);

        if(fragmentName.equals("Doctor")){
            loadFragment(new FragmentDoctor());
            txttitle.setText("Doctor");
        }
        if(fragmentName.equals("Hospital")){
            loadFragment(new FragmentHostipal());
            txttitle.setText("Hospital");
        }
        if(fragmentName.equals("Lab")){
            loadFragment(new FragmentLab());
            txttitle.setText("Lab");
        }
        if(fragmentName.equals("Clinic")){
            loadFragment(new FragmentClinic());
            txttitle.setText("Clinic");
        }
        if(fragmentName.equals("Booking")){
            loadFragment(new FragmentMyBooking());
            txttitle.setText("My Booking");
        }
        if(fragmentName.equals("Favdoctor")){
            loadFragment(new FragmentMyFavouriteDoctor());
            txttitle.setText("My Favourite Doctor");
        }
        if(fragmentName.equals("Favhospital")){
            loadFragment(new FragmentMyFavouriteHospital());
            txttitle.setText("My Favourite Hospital");
        }
        if(fragmentName.equals("Examination")){
            loadFragment(new FragmentExamination());
            txttitle.setText("Blood Donation");
        }
        if(fragmentName.equals("Registration")){
            loadFragment(new FragmentRegistration());
            txttitle.setText("Pharmacy");
        }
        if(fragmentName.equals("Assessment")){
            loadFragment(new FragmentAssessment());
            txttitle.setText("NGO");
        }
        if(fragmentName.equals("Drag")){
            loadFragment(new FragmentDrag());
            txttitle.setText("Emergency");
        }
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();
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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
