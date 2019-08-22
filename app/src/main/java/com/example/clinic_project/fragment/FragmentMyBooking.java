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
import android.widget.Button;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.MyBookingResponse;
import com.example.clinic_project.adapter.MyBookingAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.MyBookingHolder;
import com.example.clinic_project.service.RetrofitService;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMyBooking extends Fragment implements MyBookingHolder.OnItemClickListener {

    private RetrofitService service;
    private RecyclerView recyclerView;
    private CircleImageView imgdoctor;
    private Button btncancel;
    private TextView txtdoctorname,txthospitalname,txtdate,txtmap;
    private String token = null;

    private MyBookingAdapter adapter;


    public FragmentMyBooking() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_my_booking, container, false);

        txtdoctorname = view.findViewById(R.id.tvName);
        txthospitalname = view.findViewById(R.id.tvhospitalname);
        txtdate = view.findViewById(R.id.tvtime);
        txtmap = view.findViewById(R.id.tvMap);
        imgdoctor = view.findViewById(R.id.profile);
        btncancel = view.findViewById(R.id.btncancel);
        service = new RetrofitService();

        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new MyBookingAdapter(this);
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


                    }
                }
            }

            @Override
            public void onFailure(Call<MyBookingResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void onItemClick(String date, int timeId) {

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
