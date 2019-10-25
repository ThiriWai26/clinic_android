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
import com.example.clinic_project.adapter.LabAdapter;
import com.example.clinic_project.adapter.ViewPagerLabAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.BuildingHolder;
import com.example.clinic_project.holder.LabHolder;
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
    private ViewPager viewPager;
    private BuildingAdapter adapter;
    private String token = null;
    private String type = "labs";
    private int townId = 0;
    List<Building> building = new ArrayList<>();

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

        ViewPagerLabAdapter viewPagerLabAdapter = new ViewPagerLabAdapter(getContext());
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(viewPagerLabAdapter);

        adapter = new BuildingAdapter(this);
        token = Token.MyToken.getToken();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getBuildingList(type,townId);
        return view;

    }

    private void getBuildingList(String type, int townId) {
        Log.e("getbuildingList","success");
        Api buildingListApi = service.getRetrofitService().create(Api.class);
        buildingListApi.getBuildingList(token,type,townId).enqueue(new Callback<BuildingListResponse>() {
            @Override
            public void onResponse(Call<BuildingListResponse> call, Response<BuildingListResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        Log.e("response.body","success");
                        building = response.body().buildingList.data;
                        Log.e("Lab_BuildingSize", String.valueOf(building.size()));
                        adapter.addItem(building);
                    }else {
                            Log.e("response.body","success");
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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void onBuildingClick(int id) {
        Intent intent = new Intent(getContext(), LabDetailActivity.class);
        intent.putExtra("buildingId", id);
        intent.putExtra("typeId",2);
        Log.e("building_id",String.valueOf(id));
        startActivity(intent);
    }
}
