package com.example.clinic_project.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
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

import com.example.clinic_project.Activity.HospitalDetailActivity;
import com.example.clinic_project.R;
import com.example.clinic_project.Response.BuildingListResponse;
import com.example.clinic_project.Response.TownListResponse;
import com.example.clinic_project.adapter.BuildingAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.BuildingHolder;
import com.example.clinic_project.model.Building;
import com.example.clinic_project.model.TownList;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHostipal extends Fragment implements BuildingHolder.OnBuildingClickListener{

    private SearchView searchView;
    private RecyclerView recyclerView;
    private RetrofitService service;
    private TextView txthospital;
    private ImageView imgsetting,imageView1;
    private int typeId = 2;
    private int townId = 0;
    Bundle b;

    private BuildingAdapter adapter;
    List<String> locations = new ArrayList<>();
    List<TownList> townLists = new ArrayList<>();

    List<Building> building = new ArrayList<>();
    List<Building> newBuildings = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private String token = null;

    public FragmentHostipal() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment_hostipal, container, false);

//        searchView = view.findViewById(R.id.sv);
        recyclerView = view.findViewById(R.id.recyclerView);
        service = new RetrofitService();
        txthospital = view.findViewById(R.id.txthospital);
        imgsetting = view.findViewById(R.id.imgsetting);
        imageView1 = view.findViewById(R.id.imageView1);
        adapter = new BuildingAdapter(this);

        token = Token.MyToken.getToken();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getLocationList(token);
        getBuildingList(typeId,townId);

//        searchViewFilter();
//        searchViewModify();

        return view;
    }

    private void getLocationList(String token) {

        Log.e("LocationList","success");
        Api townListApi = service.getRetrofitService().create(Api.class);
        townListApi.getTownList(token).enqueue(new Callback<TownListResponse>() {

            @Override
            public void onResponse(Call<TownListResponse> call, Response<TownListResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        townLists = response.body().towns;
                        for (TownList townList : townLists) {
                            locations.add(townList.name);
                            Log.e("locations", townList.name);
                        }
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Successful!", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<TownListResponse> call, Throwable t) {

            }
        });
    }

    private void getBuildingList(int typeId, int townId) {

        Log.e("buildingList","success");

        Api buildingListApi = service.getRetrofitService().create(Api.class);
        buildingListApi.getBuildingList(token,typeId,townId).enqueue(new Callback<BuildingListResponse>() {
            @Override
            public void onResponse(Call<BuildingListResponse> call, Response<BuildingListResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){

                        building = response.body().buildingList.data;
                        adapter.addItem(building);
                        Log.e("Hospital_buildingSize",String.valueOf(building.size()));

                    }

                }
            }

            @Override
            public void onFailure(Call<BuildingListResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBuildingClick(int id) {

        Intent intent = new Intent(getContext(), HospitalDetailActivity.class);
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
        inflater.inflate(R.menu.menu_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);

//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setOnQueryTextListener(this);
//        searchView.setQueryHint("Search");

    }

//    @Override
//    public boolean onQueryTextSubmit(String query) {
//
//        query = query.toLowerCase(Locale.getDefault());
//        if(query.length() != 0){
//            newBuildings.clear();
//            for (Building building : building) {
//                if (building.name.toLowerCase(Locale.getDefault()).contains(query)) {
//
//                    newBuildings.add(building);
//                }
//            }
//            adapter.addItem(newBuildings);
//        } else {
//            adapter.addItem(building);
//        }
//        Toast.makeText(getContext(), query, Toast.LENGTH_LONG).show();
//        return false;
//    }
//
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//
//        newText = newText.toLowerCase(Locale.getDefault());
//        if(newText.length() != 0){
//            newBuildings.clear();
//            for (Building building : building) {
//                if (building.name.toLowerCase(Locale.getDefault()).contains(newText)) {
//
//                    newBuildings.add(building);
//                }
//            }
//            adapter.addItem(newBuildings);
//        } else {
//            adapter.addItem(building);
//        }
//
//        Toast.makeText(getContext(), newText, Toast.LENGTH_LONG).show();
//        return false;
//    }



    }




