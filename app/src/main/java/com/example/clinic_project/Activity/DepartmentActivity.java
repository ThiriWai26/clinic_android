package com.example.clinic_project.Activity;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.clinic_project.R;

public class DepartmentActivity extends AppCompatActivity {

    private ImageView imgback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        initActivity();

    }

    private void initActivity() {

        imgback = findViewById(R.id.deptback);

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HospitalDetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
