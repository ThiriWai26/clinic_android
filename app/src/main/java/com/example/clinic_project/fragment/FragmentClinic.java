package com.example.clinic_project.fragment;


import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic_project.Activity.ClinicDetailActivity;
import com.example.clinic_project.Activity.MainActivity;
import com.example.clinic_project.R;
import com.example.clinic_project.Response.BuildingListResponse;
import com.example.clinic_project.Response.TownListResponse;
import com.example.clinic_project.adapter.BuildingAdapter;
import com.example.clinic_project.adapter.ClinicAdapter;
import com.example.clinic_project.adapter.ViewPagerClinicAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.BuildingHolder;
import com.example.clinic_project.model.Building;
import com.example.clinic_project.model.TownList;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentClinic extends Fragment implements BuildingHolder.OnBuildingClickListener {

    private RetrofitService service;
    private RecyclerView recyclerView;
    private ViewPager viewPager;
    private ClinicAdapter adapter;
    private String token = null;
    private String type = "labs";
    private int townId = 0;
    List<Building> building = new ArrayList<>();


    public FragmentClinic() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_clinic, container, false);

        return view;
    }

//    private void getLocationList(String token) {
//
//        Log.e("LocationList","success");
//        Api townListApi = service.getRetrofitService().create(Api.class);
//        townListApi.getTownList(token).enqueue(new Callback<TownListResponse>() {
//
//            @Override
//            public void onResponse(Call<TownListResponse> call, Response<TownListResponse> response) {
//                if(response.isSuccessful()){
//                    if(response.body().isSuccess){
//                        Log.e("response.body","success");
//                        townLists = response.body().towns;
//                        for (TownList townList : townLists) {
//                            locations.add(townList.name);
//                            Log.e("locations", townList.name);
//                        }
//                        adapter.notifyDataSetChanged();
//                        Toast.makeText(getContext(), "Successful!", Toast.LENGTH_SHORT).show();
//                    }
//                    else{
//                        Log.e("response","fail");
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<TownListResponse> call, Throwable t) {
//                Log.e("onfailure",t.toString());
//            }
//        });
//    }

    private void getClinicList(String type, int townId){
        Log.e("clinicList","success");
        Api buildingListApi = service.getRetrofitService().create(Api.class);
        buildingListApi.getBuildingList(token,type,townId).enqueue(new Callback<BuildingListResponse>() {
            @Override
            public void onResponse(Call<BuildingListResponse> call, Response<BuildingListResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        Log.e("response.body","success");
                        building = response.body().buildingList.data;
                        adapter.addItem(building);
                        Log.e("Clinic_buildingSize",String.valueOf(building.size()));
                    }
                    else{
                        Log.e("response.body","fail");
                    }
                }else{
                    Log.e("response","fail");
                }
            }

            @Override
            public void onFailure(Call<BuildingListResponse> call, Throwable t) {
                Log.e("onfailure",t.toString());
            }
        });

    }

    @Override
    public void onBuildingClick(int id) {

        Intent intent = new Intent(getContext(), ClinicDetailActivity.class);
        intent.putExtra("buildingId", id);
        intent.putExtra("typeId",2);
        Log.e("building_id",String.valueOf(id));
        startActivity(intent);

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_toolbar, menu);
        MenuItem item = menu.findItem(R.id.action_search);


    }
}
