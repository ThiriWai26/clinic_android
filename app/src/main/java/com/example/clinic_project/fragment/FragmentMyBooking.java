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
import com.example.clinic_project.Response.MyBookingResponse;
import com.example.clinic_project.adapter.MyBookingAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.MyBookingHolder;
import com.example.clinic_project.model.Bookings;
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
public class FragmentMyBooking extends Fragment implements MyBookingHolder.OnItemClickListener {

    private RetrofitService service;
    private RecyclerView recyclerView;
    private String token = null;

    private MyBookingAdapter adapter;
    List<Bookings> bookings = new ArrayList<>();

    public FragmentMyBooking() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_my_booking, container, false);

        service = new RetrofitService();
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new MyBookingAdapter(this);
        token = Token.MyToken.getToken();
//        Log.e("token",token);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getMyBooking();
        return view;
    }

    private void getMyBooking() {

        Log.e("mybooking","success");
        Api myBookingApi = service.getRetrofitService().create(Api.class);
        myBookingApi.getMyBooking(token).enqueue(new Callback<MyBookingResponse>() {
            @Override
            public void onResponse(Call<MyBookingResponse> call, Response<MyBookingResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        Log.e("response.body","success");

                        adapter.addItem(response.body().upcomingBooking.bookings);
                        Log.e("mybookingsize",String.valueOf(bookings.size()));
                        adapter.notifyDataSetChanged();
                    }
                    else {
                        Log.e("response.body","fail");
                    }
                }else {

                    Log.e("response","fail");
                }
            }

            @Override
            public void onFailure(Call<MyBookingResponse> call, Throwable t) {

                Log.e("onfailure", t.toString());
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
    public void onItemClick(int parseInt) {

    }
}
