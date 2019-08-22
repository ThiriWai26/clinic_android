package com.example.clinic_project.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.MyFavouriteDoctorResponse;
import com.example.clinic_project.Response.SpecializationListResponse;
import com.example.clinic_project.adapter.MyFavouriteDoctorAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.MyFavouriteDoctorHolder;
import com.example.clinic_project.model.Doctor;
import com.example.clinic_project.model.SpecializationList;
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
public class FragmentMyFavouriteDoctor extends Fragment implements MyFavouriteDoctorHolder.OnItemClickListener {

    private RecyclerView recyclerView;
    private RetrofitService service;
    private TextView txtdoctor;
    private ImageView imgsetting;
    private MyFavouriteDoctorAdapter adapter;

    String token = null;
    List<Doctor> doctors = new ArrayList<>();
    List<Doctor> newDoctors = new ArrayList<>();
    List<SpecializationList> specializationLists = new ArrayList<>();
    List<String> categories = new ArrayList<>();


    public FragmentMyFavouriteDoctor() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_my_favourite_doctor, container, false);

        txtdoctor = view.findViewById(R.id.textdoctor);
        imgsetting = view.findViewById(R.id.imgsetting);
        recyclerView = view.findViewById(R.id.recyclerView);
        service = new RetrofitService();

        adapter = new MyFavouriteDoctorAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        token = Token.MyToken.getToken();
        getMyFavDoctor();
        getSpecializationList(token);

        return view;
    }

    private void getMyFavDoctor() {

        Log.e("myfavDoctor", "success");
        Api myfavdoctorApi = service.getRetrofitService().create(Api.class);
        myfavdoctorApi.getMyFavouriteDoctor(token).enqueue(new Callback<MyFavouriteDoctorResponse>() {
            @Override
            public void onResponse(Call<MyFavouriteDoctorResponse> call, Response<MyFavouriteDoctorResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){

                    }
                }
            }

            @Override
            public void onFailure(Call<MyFavouriteDoctorResponse> call, Throwable t) {

            }
        });

    }

    private void getSpecializationList(String token){

        Log.e("spicializationlist","success");
        Api getSpeciallizationListApi = service.getRetrofitService().create(Api.class);
        getSpeciallizationListApi.getSpecializationList(token).enqueue(new Callback<SpecializationListResponse>() {
            @Override
            public void onResponse(Call<SpecializationListResponse> call, Response<SpecializationListResponse> response) {
                if (response.isSuccessful()){
                    if(response.body().isSuccess){

                        specializationLists = response.body().specializations;
                        Log.e("Special Size", String.valueOf(response.body().specializations.size()));

                        for(SpecializationList special : specializationLists) {
                            categories.add(special.name);
                            Log.e("name",special.name);
                        }

                        categories.add(0,"All");
                        Toast.makeText(getContext(), "Successful!", Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<SpecializationListResponse> call, Throwable t) {

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

}
