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
import com.example.clinic_project.service.Token;

//import com.example.mounts.clinic.activity.DrawerActivity;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button btnDoctor, btnHospital, btnLab, btnClinc;
    private Intent intent;
    private String token;


    public HomeFragment() {
        // Required empty public constructor
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

//        b = getActivity().getIntent().getExtras();


//        token = b.getString("Token");
        token= Token.MyToken.getToken();
        return view;

    }

    @Override
    public void onClick(View v) {


//        if (v == btnArticle) {
//            Toast.makeText(getActivity(), "This is Articles", Toast.LENGTH_LONG).show();
//
//        }

//        if (v == btnHome) {
//            Toast.makeText(getActivity(), "This is Home", Toast.LENGTH_LONG).show();
//        }
        if (v == btnDoctor) {


            intent = new Intent(getActivity(), DrawerActivity.class);


            startNextActivity(intent);

        }
        if (v == btnHospital) {


            intent = new Intent(getActivity(), HospitalActivity.class);



            startNextActivity(intent);

        }
        if (v == btnLab) {
            intent = new Intent(getActivity(), LabActivity.class);



            startNextActivity(intent);

        }
        if (v == btnClinc) {


            intent = new Intent(getActivity(), ClinicActivity.class);



            startNextActivity(intent);

        }

    }

    private void startNextActivity(Intent intent) {

        intent.putExtra("Token", token);
        intent.putExtra("specializationId",-1);
        startActivity(intent);
    }
}
