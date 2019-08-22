package com.example.clinic_project.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.clinic_project.R;
import com.example.clinic_project.service.Token;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button btnDoctor, btnHospital, btnLab, btnClinc;
    private CardView mybooking,myfavdoctor,myfavhospital;
    private String token;


    public HomeFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        btnDoctor = view.findViewById(R.id.btn_doctor);
        btnHospital = view.findViewById(R.id.btn_hospital);
        btnLab = view.findViewById(R.id.btn_lab);
        btnClinc = view.findViewById(R.id.btn_clinic);

        mybooking = view.findViewById(R.id.mybooking);
        myfavdoctor = view.findViewById(R.id.myfavdoctor);
        myfavhospital = view.findViewById(R.id.myfavhospital);

        btnDoctor.setOnClickListener(this);
        btnHospital.setOnClickListener(this);
        btnLab.setOnClickListener(this);
        btnClinc.setOnClickListener(this);

        mybooking.setOnClickListener(this);
        myfavdoctor.setOnClickListener(this);
        myfavhospital.setOnClickListener(this);

        token= Token.MyToken.getToken();
//        Log.e("MyBookingActivityToken", token);
        return view;

    }

//

    @Override
    public void onClick(View v) {

        if (v == btnDoctor) {
            loadFragment(new FragmentDoctor());
        }

        if (v == btnHospital) {
            loadFragment(new FragmentHostipal());
        }

        if (v == btnLab) {
            loadFragment(new FragmentLab());
        }

        if (v == btnClinc) {
            loadFragment(new FragmentClinic());
        }

        if (v == mybooking){
            loadFragment(new FragmentMyBooking());
        }

        if(v == myfavdoctor){
            loadFragment(new FragmentMyFavouriteDoctor());
        }

        if(v == myfavhospital){
            loadFragment(new FragmentMyFavouriteHospital());
        }



    }


    private void loadFragment(Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();

    }
}
