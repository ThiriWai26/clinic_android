package com.example.clinic_project.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.adapter.LabContactNumberAdapter;
import com.example.clinic_project.holder.ServiceByClinicHolder;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;

public class ClinicDetailActivity extends AppCompatActivity implements ServiceByClinicHolder.OnItemClickListener {

    private ImageView imgfeature, imgback, imgprofile, imgfav;
    private TextView tvname, tvlocation, tvaddress, tvabout, tvcancel, tvok;
    private RatingBar ratingBar;
    private RelativeLayout service, department;
    private Button btnappointment;
    private RetrofitService retrofitService;
    private LabContactNumberAdapter adapter;
    private RecyclerView recyclerView;
    private String token = null;
    private int buildingId = -1;
    private String type = "clinics";
    private int rateableId = -1;
    private String rateableType = "clinics";
    private int value = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_detail);

        initActivity();
    }


    @SuppressLint("WrongViewCast")
    private void initActivity() {

        imgfeature = findViewById(R.id.imgfeaturedphoto);
        imgback = findViewById(R.id.imgback);
        imgprofile = findViewById(R.id.profile);
        imgfav = findViewById(R.id.imgfav);
        tvname = findViewById(R.id.tvName);
        tvlocation = findViewById(R.id.location);
        tvaddress = findViewById(R.id.address);
        tvabout = findViewById(R.id.about);
        ratingBar = findViewById(R.id.rating);
        service = findViewById(R.id.relativeservice);
        department = findViewById(R.id.relativedepartment);
        btnappointment = findViewById(R.id.btnbook);
        retrofitService = new RetrofitService();
        token = Token.MyToken.getToken();

        Bundle bundle = getIntent().getExtras();
//        buildingId = bundle.getInt("buildingId");
        Log.e("buildingId",String.valueOf(buildingId));

//        getClinicDetail();

        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ServiceByClinicActivity.class);
                startActivity(intent);
            }
        });

        department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DepartmentActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onbackClick(View view) {
        finish();
    }

    public void onClinicPhoneDilogClick(View view) {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_clinic_phoneno_dialog);

//        adapter = new ClinicContactNumberAdapter(this);
        recyclerView = dialog.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dialog.show();

    }


//    private void getRating(){
//
//        Log.e("getRating","success");
//        Api raingApi = service.getRetrofitService().create(Api.class);
//        raingApi.getRating(token,value,rateableId,rateableType).enqueue(new Callback<RatingResponse>() {
//            @Override
//            public void onResponse(Call<RatingResponse> call, Response<RatingResponse> response) {
//                if(response.isSuccessful()){
//                    if(response.body().isSuccess){
//                        Log.e("response.body","success");
//                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
//                    }
//                    else{
//                        Log.e("response.body","fail");
//                        Toast.makeText(getApplicationContext(), response.body().errorMessage, Toast.LENGTH_LONG).show();
//                    }
//                }else{
//                    Log.e("response","fail");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RatingResponse> call, Throwable throwable) {
//                Log.e("onfailure",throwable.toString());
//            }
//        });
//
//    }
//
//    private void getUserRating(){
//
//        Log.e("getUserRating","success");
//        Api userRatingApi = service.getRetrofitService().create(Api.class);
//        userRatingApi.getUserRating(token,rateableId,rateableType).enqueue(new Callback<UserRatingResponse>() {
//            @Override
//            public void onResponse(Call<UserRatingResponse> call, Response<UserRatingResponse> response) {
//                if(response.isSuccessful()){
//                    if(response.body().isSuccess){
//                        Log.e("response.body","success");
//                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
//                    }else{
//                        Log.e("response.body","fail");
//                        Toast.makeText(getApplicationContext(), response.body().errorMessage,Toast.LENGTH_LONG).show();
//                    }
//                }else{
//                    Log.e("response","fail");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserRatingResponse> call, Throwable throwable) {
//                Log.e("onfailure",throwable.toString());
//            }
//        });
//
//
//
//    }
}

//    private void getClinicDetail() {
//
//        Log.e("Building_detail","successs");
//
//        Api buildingDetailApi = service.getRetrofitService().create(Api.class);
//        buildingDetailApi.getBuildingDetail(token, type, buildingId).enqueue(new Callback<BuildingDetailResponse>() {
//            @Override
//            public void onResponse(Call<BuildingDetailResponse> call, Response<BuildingDetailResponse> response) {
//                if(response.isSuccessful()){
//                    if(response.body().isSuccess){
//                       Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + response.body().buildingDetails.featurePhoto).into(imageView);
//
////                        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + response.body().buildingDetails.photos)
////                                .resize(40, 40)
////                                .onlyScaleDown()
////                                .centerCrop()
////                                .into(imgprofile);
//
//                        tvname.setText(response.body().buildingDetails.name);
//                        tvaddress.setText(response.body().buildingDetails.townName);
//                        tvlocation.setText(response.body().buildingDetails.address);
//                        tvabout.setText(response.body().buildingDetails.about);
//                        ratingBar.setRating(response.body().buildingDetails.rating);
//
//                        Log.e("featured photo",response.body().buildingDetails.featurePhoto);
//                        Log.e("photo",response.body().buildingDetails.photos);
//                        Log.e("name",response.body().buildingDetails.name);
//                        Log.e("address",response.body().buildingDetails.address);
//                        Log.e("location",response.body().buildingDetails.townName);
//                        Log.e("about",response.body().buildingDetails.about);
//                        Log.e("rating", String.valueOf(response.body().buildingDetails.rating));
//
//                        getRating();
//
//                    }
//                    else {
//                        Log.e("response.body","fail");
//                    }
//                }else {
//                    Log.e("response","fail");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<BuildingDetailResponse> call, Throwable t) {
//                Log.e("onfailure", t.toString());
//            }
//        });
//    }

