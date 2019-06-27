//package com.example.clinic_project.Activity;
//
//import android.graphics.Color;
//import android.os.Build;
//import android.support.annotation.RequiresApi;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.widget.SearchView;
//import android.util.Log;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.clinic_project.R;
//import com.example.clinic_project.Response.BuildingListResponse;
//import com.example.clinic_project.adapter.BuildingAdapter;
//import com.example.clinic_project.api.Api;
//import com.example.clinic_project.holder.BuildingHolder;
//import com.example.clinic_project.model.Building;
//import com.example.clinic_project.service.RetrofitService;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class LabListActivity extends AppCompatActivity implements BuildingHolder.OnBuildingClickListener {
//
//    private SearchView searchView;
//    private RecyclerView recyclerView;
//    private RetrofitService service;
//    private TextView txtlab;
//    private String token = null;
//    private int typeId = 3;
//    private int townId = 0;
//    BuildingAdapter adapter;
//    List<Building> building = new ArrayList<>();
//    List<Building> newBuildings = new ArrayList<>();
//
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_lab_list);
//
//        initLabList();
//        searchViewModify();
//        searchViewFilter();
//        getLabList();
//
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//    }
//
//    private void initLabList() {
//
//        txtlab = findViewById(R.id.textlab);
//        recyclerView = findViewById(R.id.sv);
//        searchView = findViewById(R.id.action_search);
//        service = new RetrofitService();
//        adapter = new BuildingAdapter(this);
//
//    }
//
//    private void getLabList() {
//
//        Api buildingListApi = service.getRetrofitService().create(Api.class);
//        buildingListApi.getBuildingList(token,typeId,townId).enqueue(new Callback<BuildingListResponse>() {
//            @Override
//            public void onResponse(Call<BuildingListResponse> call, Response<BuildingListResponse> response) {
//                if(response.isSuccessful()){
//                    if(response.body().isSuccess){
//                        building = response.body().buildingList.data;
//                        Log.e("Lab_BuildingSize", String.valueOf(building.size()));
//                        adapter.addItem(building);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<BuildingListResponse> call, Throwable t) {
//
//            }
//        });
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
//    private void searchViewModify() {
//
//        android.support.v7.widget.SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
//        searchAutoComplete.setTextColor(Color.BLACK);
//        searchAutoComplete.setHint("Search Labs");
//        searchAutoComplete.setHintTextColor(Color.BLACK);
//        searchAutoComplete.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
//    }
//
//    private void searchViewFilter() {
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
//
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//
//                s = s.toLowerCase(Locale.getDefault());
//                if(s.length() != 0){
//                    newBuildings.clear();
//                    for (Building building :building){
//                        if(building.name.toLowerCase(Locale.getDefault()).contains(s)){
//                            newBuildings.add(building);
//                        }
//                    }
//                    adapter.addItem(newBuildings);
//                }else{
//                    adapter.addItem(building);
//                }
//                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//
//                s = s.toLowerCase(Locale.getDefault());
//                if(s.length() != 0){
//                    newBuildings.clear();
//                    for(Building building : building){
//                        if(building.name.toLowerCase(Locale.getDefault()).contains(s)){
//                            newBuildings.add(building);
//                        }
//                    }
//                    adapter.addItem(newBuildings);
//                }else{
//                    adapter.addItem(building);
//                }
//                Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();
//                return false;
//
//            }
//        });
//    }
//
//    @Override
//    public void onBuildingClick(int id) {
//
//
//    }
//}
