package com.example.clinic_project.Activity;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.BuildingListResponse;
import com.example.clinic_project.adapter.BuildingAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.BuildingHolder;
import com.example.clinic_project.model.Building;
import com.example.clinic_project.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalListActivity extends AppCompatActivity implements BuildingHolder.OnBuildingClickListener {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private RetrofitService service;
    private TextView txthospital;
    private int typeId = 2;
    private int  townId = 0;
    BuildingAdapter adapter;
    List<Building> buildings = new ArrayList<>();
    List<Building> newBuildings = new ArrayList<>();
    private String token ;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);

        initHospitalList();
        searchViewModify();
        searchViewFilter();
        getHospitalsList(typeId,townId);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    private void  initHospitalList(){

        txthospital = findViewById(R.id.txthospital);
        searchView = findViewById(R.id.sv);
        recyclerView = findViewById(R.id.recyclerView);
        service = new RetrofitService();
        adapter = new BuildingAdapter(this);

    }

    private void getHospitalsList(int typeId, int locationId){

        Api buildingListApi = service.getRetrofitService().create(Api.class);
        buildingListApi.getBuildingList(token, typeId, locationId).enqueue(new Callback<BuildingListResponse>() {
            @Override
            public void onResponse(Call<BuildingListResponse> call, Response<BuildingListResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        buildings = response.body().buildingList.data;
                        adapter.addItem(buildings);

                        Log.e("HospitalLists",String.valueOf(buildings.size()));
                    }
                }
            }

            @Override
            public void onFailure(Call<BuildingListResponse> call, Throwable t) {

            }
        });

    }

    private void searchViewFilter(){

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                query =query.toLowerCase(Locale.getDefault());
                if(query.length() != 0){
                    newBuildings.clear();
                    for (Building building : buildings){
                        if (building.name.toLowerCase(Locale.getDefault()).contains(query)){

                            newBuildings.add(building);
                        }
                    }
                    adapter.addItem(newBuildings);
                }else {
                    adapter.addItem(buildings);
                }
                Toast.makeText(HospitalListActivity.this, query, Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                newText = newText.toLowerCase(Locale.getDefault());
                if(newText.length() != 0){
                    newBuildings.clear();
                    for (Building building : buildings){
                        if(building.name.toLowerCase(Locale.getDefault()).contains(newText)){

                            newBuildings.add(building);
                        }
                    }
                    adapter.addItem(newBuildings);
                }else{
                    adapter.addItem(buildings);
                }
                Toast.makeText(HospitalListActivity.this, newText, Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void searchViewModify() {

        searchView.setIconified(false);
        searchView.setIconifiedByDefault(false);
        android.support.v7.widget.SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setHint("Search Doctors");
        searchAutoComplete.setHintTextColor(Color.WHITE);
        searchAutoComplete.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        searchAutoComplete.setTextSize(21);
    }


    @Override
    public void onBuildingClick(int id) {

    }
}
