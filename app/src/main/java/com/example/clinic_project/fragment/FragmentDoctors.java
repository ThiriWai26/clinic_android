package com.example.clinic_project.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clinic_project.R;
import com.example.clinic_project.adapter.DoctorAdapter;
import com.example.clinic_project.holder.DoctorHolder;
import com.example.clinic_project.service.RetrofitService;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDoctors extends Fragment implements DoctorHolder.OnDoctorClickListener {

    private RecyclerView recyclerView;
    private RetrofitService service;
    DoctorAdapter adapter;


    public FragmentDoctors() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_doctors, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        service = new RetrofitService();
        adapter = new DoctorAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onDoctorClick(int id) {

    }
}
