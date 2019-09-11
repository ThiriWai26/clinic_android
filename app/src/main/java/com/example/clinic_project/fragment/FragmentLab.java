package com.example.clinic_project.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.clinic_project.Activity.LabDetailActivity;
import com.example.clinic_project.R;
import com.example.clinic_project.Response.BuildingListResponse;
import com.example.clinic_project.Response.TownListResponse;
import com.example.clinic_project.adapter.BuildingAdapter;
import com.example.clinic_project.adapter.ViewPagerLabAdapter;
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
public class FragmentLab extends Fragment implements BuildingHolder.OnBuildingClickListener {

    private RetrofitService service;
    private RecyclerView recyclerView;
    BuildingAdapter adapter;

    private ViewPager viewPager;

    List<Building> building = new ArrayList<>();
    List<Building> newBuildings = new ArrayList<>();
    List<String> location = new ArrayList<>();
    private List<TownList> townLists = new ArrayList<>();
    private String token = null;
    private int typeId = 3;
    private int townId = 0;


    public FragmentLab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_lab, container, false);

        service = new RetrofitService();
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new BuildingAdapter(this);

        ViewPagerLabAdapter viewPagerLabAdapter = new ViewPagerLabAdapter(getContext());
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(viewPagerLabAdapter);

        token = Token.MyToken.getToken();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getLocationList(token);
        getBuildingList(typeId,townId);
        return view;

    }

    private void getBuildingList(int typeId, int townId) {

        Api buildingListApi = service.getRetrofitService().create(Api.class);
        buildingListApi.getBuildingList(token,typeId,townId).enqueue(new Callback<BuildingListResponse>() {
            @Override
            public void onResponse(Call<BuildingListResponse> call, Response<BuildingListResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        building = response.body().buildingList.data;
                        Log.e("Lab_BuildingSize", String.valueOf(building.size()));
                        adapter.addItem(building);
                    }
                }
            }

            @Override
            public void onFailure(Call<BuildingListResponse> call, Throwable t) {

            }
        });
    }

    private void getLocationList(String token) {

        Api townListApi = service.getRetrofitService().create(Api.class);
        townListApi.getTownList(token).enqueue(new Callback<TownListResponse>() {
            @Override
            public void onResponse(Call<TownListResponse> call, Response<TownListResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        townLists = response.body().towns;
                        for(TownList townList:townLists){
                            location.add(townList.name);
                            Log.e("Locations",townList.name);
                        }
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getContext(),"Successful", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<TownListResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBuildingClick(int id) {

        Intent intent = new Intent(getContext(), LabDetailActivity.class);
        intent.putExtra("townId", id);
        intent.putExtra("typeId",2);
        Log.e("town_Id",String.valueOf(id));
        startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }
}
