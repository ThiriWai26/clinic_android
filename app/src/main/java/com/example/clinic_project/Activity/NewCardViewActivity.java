package com.example.clinic_project.Activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.clinic_project.R;
import com.example.clinic_project.adapter.NewCardAdapter;
import com.example.clinic_project.holder.NewCardHolder;

public class NewCardViewActivity extends AppCompatActivity implements NewCardHolder.OnItemClickListener {

    private RecyclerView recyclerView;
    private NewCardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActivity();
    }

    private void initActivity() {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_phonenumber_dialog);

        if (dialog.isShowing()) {
            Log.e("Success","ok");
            recyclerView = findViewById(R.id.recyclerView);
            adapter = new NewCardAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            Log.e("Fail","fail");
        }

        dialog.show();

    }
}
