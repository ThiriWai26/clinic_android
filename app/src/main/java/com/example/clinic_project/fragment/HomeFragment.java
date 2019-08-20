package com.example.clinic_project.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.clinic_project.Activity.ClinicActivity;
import com.example.clinic_project.Activity.DrawerActivity;
import com.example.clinic_project.Activity.HospitalActivity;
import com.example.clinic_project.Activity.LabActivity;
import com.example.clinic_project.R;
import com.example.clinic_project.constant.NavigationDrawerConstants;
import com.example.clinic_project.service.Token;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button btnDoctor, btnHospital, btnLab, btnClinc;
    private String token;


    public HomeFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(NavigationDrawerConstants.TAG_HOSPITAL);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btnDoctor = view.findViewById(R.id.btn_doctor);
        btnHospital = view.findViewById(R.id.btn_hospital);
        btnLab = view.findViewById(R.id.btn_lab);
        btnClinc = view.findViewById(R.id.btn_clinic);

        btnDoctor.setOnClickListener(this);
        btnHospital.setOnClickListener(this);
        btnLab.setOnClickListener(this);
        btnClinc.setOnClickListener(this);

        token= Token.MyToken.getToken();
        return view;

    }

    @Override
    public void onClick(View v) {

        if (v == btnDoctor) {

        }

        if (v == btnHospital) {
            loadFragment(new FragmentHostipal());
        }

        if (v == btnLab) {
        }

        if (v == btnClinc) {


        }

    }


    private void loadFragment(Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();
    }
}
