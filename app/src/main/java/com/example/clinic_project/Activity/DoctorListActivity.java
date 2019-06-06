package com.example.clinic_project.Activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.DoctorListResponse;
import com.example.clinic_project.adapter.DoctorAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.DoctorHolder;
import com.example.clinic_project.model.Doctor;
import com.example.clinic_project.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorListActivity extends AppCompatActivity implements  DoctorHolder.OnDoctorClickListener {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private RetrofitService service;
    private TextView textView;
//    private Spinner spinner;
    DoctorAdapter adapter;

    List<Doctor> doctors = new ArrayList<>();
    List<Doctor> newDoctors = new ArrayList<>();
    private String token ;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDoctorList();

        searchViewModify();
        searchViewFilter();
        getDoctorsList();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    //initial work of activity
    private void initDoctorList() {

        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textdoctor);
        searchView = findViewById(R.id.sv);
        recyclerView = findViewById(R.id.recyclerView);
        service = new RetrofitService();
        adapter = new DoctorAdapter(this);
//        spinner = findViewById(R.id.spinner);

//        List<String> categories = new ArrayList<String>();
//        categories.add("Dentist");
//        categories.add("OG");
//        categories.add("Endocrinologists");
//        categories.add("Cardiologist");
//        categories.add("Addiction");
//        categories.add("Simple");

//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item,categories);
//
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//        spinner.setAdapter(dataAdapter);


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

    //search view filter

    private void searchViewFilter() {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                s = s.toLowerCase(Locale.getDefault());
                if (s.length() != 0) {
                    newDoctors.clear();
                    for (Doctor doctor : doctors) {
                        if (doctor.name.toLowerCase(Locale.getDefault()).contains(s)) {

                            newDoctors.add(doctor);
                        }
                    }
                    adapter.addDoctors(newDoctors);
                } else {
                    adapter.addDoctors(doctors);
                }
                Toast.makeText(DoctorListActivity.this, s, Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                s = s.toLowerCase(Locale.getDefault());
                if (s.length() != 0) {
                    newDoctors.clear();
                    for (Doctor doctor : doctors) {
                        if (doctor.name.toLowerCase(Locale.getDefault()).contains(s)) {

                            newDoctors.add(doctor);
                        }
                    }
                    adapter.addDoctors(newDoctors);
                } else {
                    adapter.addDoctors(doctors);
                }

                Toast.makeText(DoctorListActivity.this, s, Toast.LENGTH_LONG).show();
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
        searchAutoComplete.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        searchAutoComplete.setTextSize(21);

//        /*Code for changing the search icon */
//        ImageView searchIcon = searchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
//
//        searchIcon.setImageResource(R.drawable.ic_search);
//
//        ImageView searchViewIcon = searchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
//
//        ViewGroup linearLayoutSearchView =
//                (ViewGroup) searchViewIcon.getParent();
//        linearLayoutSearchView.removeView(searchViewIcon);
//        linearLayoutSearchView.addView(searchViewIcon);
//        searchViewIcon.setAdjustViewBounds(true);
//        searchViewIcon.setMaxWidth(0);
//        searchViewIcon.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));



    }

    @Override
    public void onDoctorClick(int id) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_setting, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//
//            case R.id.action_settings:
//                // Settings option clicked.
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

}
