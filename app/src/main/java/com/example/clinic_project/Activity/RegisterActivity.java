package com.example.clinic_project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.RegisterResponse;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.service.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity{

    private EditText edtphone, edtpassword, edtconpassword;
    private TextView txtregister, txtregphone, txtregpassword, txtconpassword, signin;
    private long phoneNumber;
    private String password;
    private String confirmpassword;
    private ProgressBar progressBar;

    private RetrofitService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        edtphone = findViewById(R.id.regusername_id);
        edtpassword = findViewById(R.id.regpassword_id);
        edtconpassword = findViewById(R.id.regconpassword_id);
        txtregphone = findViewById(R.id.txtreg_phoneno);
        txtregpassword = findViewById(R.id.txtreg_password);
        txtconpassword = findViewById(R.id.txtreg_conpassword);
        txtregister = findViewById(R.id.tx_register);
        signin = findViewById(R.id.txt_signin);

        service=new RetrofitService();

        txtregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("btn","success");
                phoneNumber = Long.parseLong(edtphone.getText().toString());
                password = edtpassword.getText().toString();
                confirmpassword = edtconpassword.getText().toString();

                Log.e("Phone num", String.valueOf(phoneNumber));
                Log.e("password", password);
                Log.e("pass num", confirmpassword);

                if (password.length() < 6) {
                    edtpassword.setError("minimum 6 characters");
                }
                else if (confirmpassword.length() < 6){
                    edtconpassword.setError("minimum 6 characters ");
                }
                else if (password.equals(confirmpassword)) {

                    registerUser(phoneNumber, password, confirmpassword);

                    Log.e("ONclick", "success");
                }else {

                    edtpassword.setError("Incorrect password");
                    edtconpassword.setError("Incorrect password");
                }

            }
        });
    }

    private void registerUser(long phoneNumber, String password, String confirmpassword) {
//        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Please Wait");
//        progressDialog.show();
        Api api = service.getRetrofitService().create(Api.class);

        Log.e("phone Number", String.valueOf(this.phoneNumber));
        Log.e("PasswordConfirm", this.confirmpassword);

        api.userRegister(this.phoneNumber, this.password, this.confirmpassword).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    Log.e("response.isSuccess", "work");

                if(response.isSuccessful()){
                        Log.e("Register token", response.body().token);
                    Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                        progressDialog.dismiss();
                        startActivity(intent);
                        Log.e("RegisterOnResponse", "success");


                    }

                else {
                    Log.e("response.isSuccess", "fail");
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call,Throwable t) {
                Log.e("onFailure", t.toString());
                Toast.makeText(RegisterActivity.this, "Register fail", Toast.LENGTH_LONG).show();

            }
        });
//        Log.e("passowrd", this.password);

    }

}

