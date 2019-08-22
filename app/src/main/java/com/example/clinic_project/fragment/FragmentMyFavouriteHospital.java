package com.example.clinic_project.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clinic_project.R;
import com.example.clinic_project.adapter.MyFavouriteHospitalAdapter;
import com.example.clinic_project.holder.MyFavouriteHospitalHolder;
import com.example.clinic_project.service.RetrofitService;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMyFavouriteHospital extends Fragment implements MyFavouriteHospitalHolder.OnItemClickListener {

    private RecyclerView recyclerView;
    private RetrofitService service;
    private MyFavouriteHospitalAdapter adapter;

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

        return view;
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
