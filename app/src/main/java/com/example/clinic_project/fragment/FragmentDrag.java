package com.example.clinic_project.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clinic_project.R;
import com.example.clinic_project.adapter.DragAdapter;
import com.example.clinic_project.adapter.ExaminationAdapter;
import com.example.clinic_project.adapter.ViewPagerAdapter;
import com.example.clinic_project.adapter.ViewPagerDragAdapter;
import com.example.clinic_project.holder.DragHolder;
import com.example.clinic_project.holder.ExaminationHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDrag extends Fragment implements DragHolder.OnItemClickListener {

    private RecyclerView recyclerView;
    private DragAdapter adapter;
    private ViewPager viewPager;

    public FragmentDrag() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment_drag, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new DragAdapter(this);

        ViewPagerDragAdapter viewPagerDragAdapter = new ViewPagerDragAdapter(getContext());
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(viewPagerDragAdapter);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    }




