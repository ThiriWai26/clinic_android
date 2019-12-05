package com.example.clinic_project.fragment;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clinic_project.Activity.RegistrationDetailActivity;
import com.example.clinic_project.R;
import com.example.clinic_project.Response.OtherServiceListResponse;
import com.example.clinic_project.adapter.RegistrationAdapter;
import com.example.clinic_project.adapter.ViewPagerRegistrationAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.RegistrationHolder;
import com.example.clinic_project.model.Service;
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
public class FragmentRegistration extends Fragment implements RegistrationHolder.OnItemClickListener {

    private RecyclerView recyclerView;
    private RegistrationAdapter adapter;
    private ViewPager viewPager;
    private RetrofitService service;

    private String token = null;
    private String type = "pharmacy";
    private int townId = 0;
    List<Service> services = new ArrayList<>();

    public FragmentRegistration() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment_registration, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new RegistrationAdapter(this);
        service = new RetrofitService();
        token = Token.MyToken.getToken();

        ViewPagerRegistrationAdapter viewPagerRegistrationAdapter = new ViewPagerRegistrationAdapter(getContext());
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(viewPagerRegistrationAdapter);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getPharmacyList();
        return view;
    }

    private void getPharmacyList() {
        Log.e("examinationList","success");
        Api pharmacyListApi = service.getRetrofitService().create(Api.class);
        pharmacyListApi.getOtherServiceList(token,type,townId).enqueue(new Callback<OtherServiceListResponse>() {
            @Override
            public void onResponse(Call<OtherServiceListResponse> call, Response<OtherServiceListResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        Log.e("response.body","success");

                        adapter.addItem(response.body().otherServiceList.data);
                        Log.e("PharmacyData Size",String.valueOf(services.size()));
                        adapter.notifyDataSetChanged();
                    }
                    else {
                        Log.e("response.body.isSuccess","false");
                    }
                }
                else {
                    Log.e("response.body","fail");
                }
            }

            @Override
            public void onFailure(Call<OtherServiceListResponse> call, Throwable t) {
                Log.e("failure",t.toString());

            }
        });
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onRegistrationClick(int id) {
        Intent intent = new Intent(getContext(), RegistrationDetailActivity.class);
        intent.putExtra("serviceId",id);
        Log.e("serviceId", String.valueOf(id));
        startActivity(intent);
    }
}




