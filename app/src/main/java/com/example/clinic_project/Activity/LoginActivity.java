package com.example.clinic_project.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.LoginResponse;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    TextView phoneno, password, signup, Login;
    EditText edtphoneno, edtpassword;
    private String PhoneNo;
    private String PassWord;
    private RetrofitService service;
    private String token = null;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();
        initLogin();

    }

    private void initLogin() {

        phoneno = findViewById(R.id.txt_phoneno);
        password = findViewById(R.id.txt_password);
        signup = findViewById(R.id.txt_signup);
        Login = findViewById(R.id.btn_login);
        edtphoneno = findViewById(R.id.phoneno_id);
        edtpassword = findViewById(R.id.password_id);
        token = pref.getString("token", null);

        service = new RetrofitService();
        if (token != null) {

            Token.MyToken.setToken(token);
            Intent intent = new Intent(getApplicationContext(), HomenaviActivity.class);
            intent.putExtra("Token", token);
            startActivity(intent);
            finish();
        } else {

            Login.setOnClickListener(this);

            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        if (v == Login) {
//            progressBar.setVisibility(View.VISIBLE);
            String phone = edtphoneno.getText().toString();
            String pass1 = edtpassword.getText().toString();

            Log.e("phone_num", phone);
            Log.e("pass", pass1);

//            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
//            intent.putExtra("Token","This is token id.");
//            startActivity(intent);
            userLogin(phone, pass1);

        } else if (v == signup) {

            userRegister();
        }

    }

    private void userRegister() {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    private void userLogin(String phone, String pass1) {

        Api api = service.getRetrofitService().create(Api.class);
        api.login(phone,pass1).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    if(response.body().isSuccess) {
                        Log.e("Token", response.body().token);

                        token = response.body().token;
                        Token.MyToken.setToken(token);

                        editor.putString("token", token);
                        editor.apply();
                        editor.commit();

                        Intent intent = new Intent(getApplicationContext(), HomenaviActivity.class);
                        intent.putExtra("Token", response.body().token);
                        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_LONG).show();
                        startActivity(intent);

                    }else{
                        Toast.makeText(LoginActivity.this,response.body().errorMessage,Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Login Fail", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("Throwable", t.toString());
                Log.e("onfailure",call.toString());
            }
        });
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}