package com.example.clinic_project.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.FavouriteListResponse;
import com.example.clinic_project.adapter.MyFavouriteHospitalAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.MyFavouriteHospitalHolder;
import com.example.clinic_project.model.Favourite;
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
public class FragmentMyFavouriteHospital extends Fragment implements MyFavouriteHospitalHolder.OnItemClickListener {

    private RecyclerView recyclerView;
    private RetrofitService service;
    private MyFavouriteHospitalAdapter adapter;
    private List<Favourite> favourite = new ArrayList<>();

    private String token = null;
    private String favouriteableType = "hospitals";

    public FragmentMyFavouriteHospital() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_my_favourite_hospital, container, false);

        recyclerView =view.findViewById(R.id.recyclerView);
        service = new RetrofitService();

        adapter = new MyFavouriteHospitalAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        token = Token.MyToken.getToken();

        getfavouriteList();
        return view;
    }

    private void getfavouriteList() {

        Log.e("favouriteList","success");
        Api favouriteListApi = service.getRetrofitService().create(Api.class);
        favouriteListApi.getFavouriteList(token,favouriteableType).enqueue(new Callback<FavouriteListResponse>() {
            @Override
            public void onResponse(Call<FavouriteListResponse> call, Response<FavouriteListResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        Log.e("response.body","success");
                        adapter.addItem(response.body().favouriteList.favourites);
                        Log.e("favouriteListsize", String.valueOf(favourite.size()));
                    }
                    else{
                        Log.e("response.body","success");
                    }
                }else{
                    Log.e("response","fail");
                }
            }

            @Override
            public void onFailure(Call<FavouriteListResponse> call, Throwable throwable) {
                Log.e("onfailure", throwable.toString());
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
