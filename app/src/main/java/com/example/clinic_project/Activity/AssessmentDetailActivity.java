package com.example.clinic_project.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.OtherServiceDetailResponse;
import com.example.clinic_project.adapter.ExaminationPhoneNumberAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.fragment.FragmentAssessment;
import com.example.clinic_project.holder.ExaminationPhoneNumberHolder;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AssessmentDetailActivity extends AppCompatActivity implements ExaminationPhoneNumberHolder.OnItemClickListener  {

    private RetrofitService service;
    private ImageView featurephoto, profile, imgback;
    private TextView tvname, tvtown, tvaddress, tvabout, tvstarttime, tvendtime;

    private String token = null;
    private int serviceId;
    private String type = "ngo";

    private ExaminationPhoneNumberAdapter adapter;
    private RecyclerView phonenumberRecyclerView;
    private List<String> phoneNumbers = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail);

        initActivity();
    }

    @SuppressLint("WrongViewCast")
    private void initActivity() {

        service = new RetrofitService();
        featurephoto = findViewById(R.id.imageView);
        profile = findViewById(R.id.profile);
        imgback = findViewById(R.id.imgback);
        tvname = findViewById(R.id.tvName);
        tvtown = findViewById(R.id.tvTown);
        tvaddress = findViewById(R.id.tvaddress);
        tvabout = findViewById(R.id.tvabout);
        tvstarttime = findViewById(R.id.tvstarttime);
        tvendtime = findViewById(R.id.tvendtime);
        token = Token.MyToken.getToken();

        Bundle bundle = getIntent().getExtras();
        serviceId = bundle.getInt("serviceId");
        Log.e("serviceId",String.valueOf(serviceId));

        getAssessmentDetail();
    }

    private void getAssessmentDetail() {
        Log.e("getAssessmentDetail","success");
        Api examinationDetailApi = service.getRetrofitService().create(Api.class);
        examinationDetailApi.getOtherServiceDetail(token,type,serviceId).enqueue(new Callback<OtherServiceDetailResponse>() {
            @Override
            public void onResponse(Call<OtherServiceDetailResponse> call, Response<OtherServiceDetailResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        Log.e("response.body","success");

                        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + response.body().otherServiceDetail.featurePhoto).into(featurephoto);
                        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + response.body().otherServiceDetail.profile)
                                .resize(40,40)
                                .onlyScaleDown()
                                .centerCrop()
                                .into(profile);

                        phoneNumbers.addAll(response.body().otherServiceDetail.phoneNumber);
                        Log.e("phoneNumbersdSize",String.valueOf(phoneNumbers.size()));
                        tvname.setText(response.body().otherServiceDetail.name);
                        tvtown.setText(response.body().otherServiceDetail.townName);
                        tvaddress.setText(response.body().otherServiceDetail.address);
                        tvabout.setText(response.body().otherServiceDetail.about);
                        tvstarttime.setText(String.valueOf(response.body().otherServiceDetail.startTime));
                        tvendtime.setText(String.valueOf(response.body().otherServiceDetail.endTime));

                        Log.e("Name",response.body().otherServiceDetail.name);
                        Log.e("Town", String.valueOf(response.body().otherServiceDetail.townName));
                        Log.e("Address",response.body().otherServiceDetail.address);
                        Log.e("About",response.body().otherServiceDetail.about);
                        Log.e("StartTime", String.valueOf(response.body().otherServiceDetail.startTime));
                        Log.e("EndTime", String.valueOf(response.body().otherServiceDetail.endTime));
                        Log.e("FeaturePhoto",response.body().otherServiceDetail.featurePhoto);
                        Log.e("Profile",response.body().otherServiceDetail.profile);
                    }
                    else {
                        Log.e("response.body.isSuccess","false");
                    }
                }
                else {
                    Log.e("response.body","fail");
                }
            }

            @Override
            public void onFailure(Call<OtherServiceDetailResponse> call, Throwable t) {
                Log.e("failure",t.toString());
            }
        });

    }


    public void onBackAssessmentClick(View view) {
        finish();
    }

    public void onAssessmentPhoneNumberClick(View view) {
        Log.e("DoctorPhoneNumber","success");
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_phonenumber_dialog);

        adapter = new ExaminationPhoneNumberAdapter(this);
        phonenumberRecyclerView = dialog.findViewById(R.id.PhonenumberrecyclerView);
        phonenumberRecyclerView.setAdapter(adapter);
        phonenumberRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.addItem(phoneNumbers);
        dialog.show();

        PhoneStateListener phoneListener = new PhoneStateListener();
        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneListener,PhoneStateListener.LISTEN_CALL_STATE);

        new PhoneCallListener();

    }

    @Override
    public void onExaminationPhoneNumberClick(TextView txphoneno) {
//        Log.e("intent","ok");
//        try {
//            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
//            Log.e("PhoneNumber",String.valueOf(phoneNumbers));
//            dialIntent.setData(Uri.parse("tel:"+ phoneNumbers));
//            startActivity(dialIntent);
//        }catch(Exception e) {
//            Toast.makeText(getApplicationContext(),"Your call has failed...", Toast.LENGTH_LONG).show();
//            e.printStackTrace();
//        }
    }

    private class PhoneCallListener extends PhoneStateListener {

        private boolean isPhoneCalling = false;

        String LOG_TAG = "LOGGING 123";

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            if (TelephonyManager.CALL_STATE_RINGING == state) {
                // phone ringing
                Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
                Toast.makeText(getApplicationContext(), incomingNumber + " calls you", Toast.LENGTH_LONG).show();
            }

            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                // active
                Log.i(LOG_TAG, "OFFHOOK");
                isPhoneCalling = true;
                Toast.makeText(getApplicationContext(), "on call...", Toast.LENGTH_LONG).show();
            }

            if (TelephonyManager.CALL_STATE_IDLE == state) {
                // run when class initial and phone call ended,
                // need detect flag from CALL_STATE_OFFHOOK
                Log.i(LOG_TAG, "IDLE");

                if (isPhoneCalling) {

                    Log.i(LOG_TAG, "restart app");
                    Toast.makeText(getApplicationContext(), "restart app after call", Toast.LENGTH_LONG).show();
                    // restart app
                    Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    isPhoneCalling = false;
                }

            }
        }
    }
}
