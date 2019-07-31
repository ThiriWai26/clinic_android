package com.example.clinic_project.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.adapter.NewAdapter;
import com.example.clinic_project.holder.NewHolder;

public class NewActivity extends AppCompatActivity implements NewHolder.OnItemClickListener {

    private RecyclerView recyclerView;
    NewAdapter adapter;
    private TextView txt1,txt2,txt3;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        initActivity();
    }

    private void initActivity() {

        recyclerView = findViewById(R.id.recyclerView);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        btn = findViewById(R.id.btn);

        adapter = new NewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
