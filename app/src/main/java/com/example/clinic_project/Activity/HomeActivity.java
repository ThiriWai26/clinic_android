package com.example.clinic_project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.clinic_project.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnDoctor, btnHospital, btnLab, btnClinc;
    private Intent intent;
    private String token;
    private Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initHomeActivity();
    }

    private void initHomeActivity() {

        setContentView(R.layout.activity_home);
        btnDoctor = findViewById(R.id.btn_doctor);
        btnHospital = findViewById(R.id.btn_hospital);
        btnLab = findViewById(R.id.btn_lab);
        btnClinc = findViewById(R.id.btn_clinic);
//        btnHome = findViewById(R.id.btn_home);
//        btnArticle = findViewById(R.id.btn_articles);

        btnDoctor.setOnClickListener(this);
        btnHospital.setOnClickListener(this);
        btnLab.setOnClickListener(this);
        btnClinc.setOnClickListener(this);

        b = getIntent().getExtras();

        token = b.getString("Token");
        Log.e("HomeActivityToken", token);
    }

    @Override
    public void onClick(View v) {

//        if (v == btnArticle) {
//            Toast.makeText(HomeActivity.this, "This is Articles", Toast.LENGTH_LONG).show();
//
//        }
//
//        if (v == btnHome) {
//            Toast.makeText(HomeActivity.this, "This is Home", Toast.LENGTH_LONG).show();
//        }

        if (v == btnDoctor) {


            intent = new Intent(HomeActivity.this, DrawerActivity.class);

            startActivity(intent);

        }

        if (v == btnHospital) {

            intent = new Intent(HomeActivity.this, HospitalActivity.class);

            startNextActivity(intent);

        }

        if (v == btnLab) {

            intent = new Intent(HomeActivity.this, LabActivity.class);

            startNextActivity(intent);

        }

        if (v == btnClinc) {

            intent = new Intent(HomeActivity.this, ClinicActivity.class);

            startNextActivity(intent);

        }
    }

    private void startNextActivity(Intent intent) {

        intent.putExtra("Token", token);
        startActivity(intent);
    }
}
