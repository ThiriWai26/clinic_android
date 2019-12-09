package com.example.clinic_project.fragment;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.clinic_project.Activity.AnotherHomenaviActivity;
import com.example.clinic_project.R;
import com.example.clinic_project.service.Token;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button btnDoctor, btnHospital, btnLab, btnClinc;
    private CardView mybooking, myfavdoctor, myfavhospital, examination, registration, assessment, drag;
    private String token;

    public HomeFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        btnDoctor = view.findViewById(R.id.btn_doctor);
        btnHospital = view.findViewById(R.id.btn_hospital);
        btnLab = view.findViewById(R.id.btn_lab);
        btnClinc = view.findViewById(R.id.btn_clinic);

        mybooking = view.findViewById(R.id.mybooking);
        myfavdoctor = view.findViewById(R.id.myfavdoctor);
        myfavhospital = view.findViewById(R.id.myfavhospital);

        examination = view.findViewById(R.id.cardexam);
        registration = view.findViewById(R.id.cardregi);
        assessment = view.findViewById(R.id.cardassess);
        drag = view.findViewById(R.id.carddrag);

        btnDoctor.setOnClickListener(this);
        btnHospital.setOnClickListener(this);
        btnLab.setOnClickListener(this);
        btnClinc.setOnClickListener(this);

        mybooking.setOnClickListener(this);
        myfavdoctor.setOnClickListener(this);
        myfavhospital.setOnClickListener(this);

        examination.setOnClickListener(this);
        registration.setOnClickListener(this);
        assessment.setOnClickListener(this);
        drag.setOnClickListener(this);
        token = Token.MyToken.getToken();

        return view;
    }

    @Override
    public void onClick(View v) {

        if (v == btnDoctor) {
            Intent intent = new Intent(getContext(), AnotherHomenaviActivity.class);
            intent.putExtra("fragment","Doctor");
            startActivity(intent);
        }

        if (v == btnHospital) {

            Intent intent = new Intent(getContext(), AnotherHomenaviActivity.class);
            intent.putExtra("fragment","Hospital");
            startActivity(intent);
        }

        if (v == btnLab) {

            Intent intent = new Intent(getContext(), AnotherHomenaviActivity.class);
            intent.putExtra("fragment","Lab");
            startActivity(intent);
        }

        if (v == btnClinc) {

            Intent intent = new Intent(getContext(), AnotherHomenaviActivity.class);
            intent.putExtra("fragment","Clinic");
            startActivity(intent);
        }

        if (v == mybooking) {

            Intent intent = new Intent(getContext(), AnotherHomenaviActivity.class);
            intent.putExtra("fragment","Booking");
            startActivity(intent);
        }

        if (v == myfavdoctor) {

            Intent intent = new Intent(getContext(), AnotherHomenaviActivity.class);
            intent.putExtra("fragment","Favdoctor");
            startActivity(intent);
        }


        if (v == myfavhospital) {

            Intent intent = new Intent(getContext(), AnotherHomenaviActivity.class);
            intent.putExtra("fragment","Favhospital");
            startActivity(intent);
        }

        if (v == examination) {

            Intent intent = new Intent(getContext(), AnotherHomenaviActivity.class);
            intent.putExtra("fragment", "Examination");
            startActivity(intent);
        }

        if (v == registration) {

            Intent intent = new Intent(getContext(), AnotherHomenaviActivity.class);
            intent.putExtra("fragment", "Registration");
            startActivity(intent);
        }

        if (v == assessment) {

            Intent intent = new Intent(getContext(), AnotherHomenaviActivity.class);
            intent.putExtra("fragment","Assessment");
            startActivity(intent);
        }

        if (v == drag) {

            Intent intent = new Intent(getContext(), AnotherHomenaviActivity.class);
            intent.putExtra("fragment", "Drag");
            startActivity(intent);
        }


    }



}
