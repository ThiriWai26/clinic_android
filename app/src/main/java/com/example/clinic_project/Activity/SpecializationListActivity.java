package com.example.clinic_project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.SpecializationListResponse;
import com.example.clinic_project.adapter.SpecializationAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.SpecializationHolder;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpecializationListActivity extends AppCompatActivity implements  SpecializationHolder.OnHolderItemClickListener {

    private RecyclerView recyclerView;
    private RetrofitService service;
    private String token;
    private SpecializationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialization_list);

        initActivity();
        getSpecializationList();
    }

    private void initActivity() {

        recyclerView = findViewById(R.id.recyclerView);
        service = new RetrofitService();
        token = Token.MyToken.getToken();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SpecializationAdapter(this);
        recyclerView.setAdapter(adapter);

    }

    private void getSpecializationList() {

        Api specializationListApi = service.getRetrofitService().create(Api.class);

        specializationListApi.getSpecializationList(token).enqueue(new Callback<SpecializationListResponse>() {
            @Override
            public void onResponse(Call<SpecializationListResponse> call, Response<SpecializationListResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().isSuccess) {

                        adapter.addData(response.body().specializations);
                    }
                }
            }

            @Override
            public void onFailure(Call<SpecializationListResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void onHolderitemClick(int id) {

        Intent intent = new Intent(this, DrawerActivity.class);
        Log.e("SpecializationId", String.valueOf(id));
        intent.putExtra("specializationId", id);
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

