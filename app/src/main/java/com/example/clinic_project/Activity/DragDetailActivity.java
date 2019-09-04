package com.example.clinic_project.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.R;


public class DragDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView name,address,about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_detail);

        initActivity();

    }

    @SuppressLint("WrongViewCast")
    private void initActivity() {



    }


}
