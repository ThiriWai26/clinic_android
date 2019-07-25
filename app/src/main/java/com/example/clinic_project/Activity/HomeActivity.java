package com.example.clinic_project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.clinic_project.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnDoctor, btnHospital, btnLab, btnClinc;
    private RelativeLayout mybooking;
    private CardView myfavDoctor;
    private ImageView ic_mybooking;
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
        mybooking = findViewById(R.id.mybooking);
        ic_mybooking=findViewById(R.id.ic_mybooking);
        myfavDoctor=findViewById(R.id.myfavdoctor);
//        btnHome = findViewById(R.id.btn_home);
//        btnArticle = findViewById(R.id.btn_articles);

        btnDoctor.setOnClickListener(this);
        btnHospital.setOnClickListener(this);
        btnLab.setOnClickListener(this);
        btnClinc.setOnClickListener(this);

        b = getIntent().getExtras();

        token = b.getString("Token");
        Log.e("HomeActivityToken", token);

        myfavDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onclickls","ok");
            }
        });

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

        if (v == mybooking) {
            Log.e("mybookingonclick", "ok");
            intent = new Intent(this, MyBookingActivity.class);
            startActivity(intent);
        }

    }

    private void startNextActivity(Intent intent) {

        intent.putExtra("Token", token);
        startActivity(intent);
    }
}
