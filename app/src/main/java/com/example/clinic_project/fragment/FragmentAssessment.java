package com.example.clinic_project.fragment;


import android.content.Intent;
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

import com.example.clinic_project.Activity.AssessmentDetailActivity;
import com.example.clinic_project.R;
import com.example.clinic_project.adapter.AssessmentAdapter;
import com.example.clinic_project.adapter.ExaminationAdapter;
import com.example.clinic_project.adapter.ViewPagerAdapter;
import com.example.clinic_project.adapter.ViewPagerAssessmentAdapter;
import com.example.clinic_project.holder.AssessmentHolder;
import com.example.clinic_project.holder.ExaminationHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAssessment extends Fragment implements AssessmentHolder.OnItemClickListener {

    private RecyclerView recyclerView;
    private AssessmentAdapter adapter;
    private ViewPager viewPager;

    public FragmentAssessment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.layout_fragment_assessment, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new AssessmentAdapter(this);

        ViewPagerAssessmentAdapter viewPagerAssessmentAdapter = new ViewPagerAssessmentAdapter(getContext());
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(viewPagerAssessmentAdapter);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onAssessmentClick() {
        Intent intent = new Intent(getContext(), AssessmentDetailActivity.class);
        startActivity(intent);
    }
}




