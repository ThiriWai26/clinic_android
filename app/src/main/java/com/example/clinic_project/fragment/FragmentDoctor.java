package com.example.clinic_project.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic_project.Activity.DoctorDetailActivity;
import com.example.clinic_project.R;
import com.example.clinic_project.Response.DoctorListResponse;
import com.example.clinic_project.Response.SpecializationListResponse;
import com.example.clinic_project.adapter.DoctorAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.DoctorHolder;
import com.example.clinic_project.model.Doctor;
import com.example.clinic_project.model.SpecializationList;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDoctor extends Fragment implements DoctorHolder.OnDoctorClickListener {

    private RecyclerView recyclerView;
    private RetrofitService service;
    private SearchView searchView;
    private TextView txtdoctor;
    private ImageView imgsetting;

    private DoctorAdapter adapter;
    ArrayAdapter<String> dataAdapter;
    List<SpecializationList> specializationLists = new ArrayList<>();
    List<String> categories = new ArrayList<>();

    List<Doctor> doctors = new ArrayList<>();
    List<Doctor> newDoctors = new ArrayList<>();
    private String token = null;


    public FragmentDoctor() {
        // Required empty public constructor
    }


    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_doctor, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        service = new RetrofitService();
        txtdoctor = view.findViewById(R.id.textdoctor);
        imgsetting = view.findViewById(R.id.imgsetting);
        searchView = view.findViewById(R.id.action_search);

        adapter = new DoctorAdapter(this);
        token = Token.MyToken.getToken();
//        Log.e("DrawerActivityToken", token);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getSpecializationList(token);
        getDoctorList();
//        searchViewFilter();
//        searchViewModify();

        return view;

    }

//    private void searchViewFilter() {
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//
//                s = s.toLowerCase(Locale.getDefault());
//                if(s.length() != 0){
//                    newDoctors.clear();
//                    for (Doctor doctors: doctors){
//                        if (doctors.name.toLowerCase(Locale.getDefault()).contains(s)){
//                            newDoctors.add(doctors);
//                        }
//                    }
//                    adapter.addDoctors(newDoctors);
//                }else{
//                    adapter.addDoctors(doctors);
//                }
//                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//
//                s = s.toLowerCase(Locale.getDefault());
//                if (s.length() != 0){
//                    newDoctors.clear();
//                    for (Doctor doctors : doctors) {
//                        if (doctors.name.toLowerCase(Locale.getDefault()).contains(s)) {
//                            newDoctors.add(doctors);
//                        }
//                    }
//                    adapter.addDoctors(newDoctors);
//                }else {
//                    adapter.addDoctors(doctors);
//                }
//                Toast.makeText(getContext(),s, Toast.LENGTH_LONG).show();
//
//                return false;
//            }
//        });
//
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
//    private void searchViewModify() {
//
//        android.support.v7.widget.SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(R.id.search_src_text);
//        searchAutoComplete.setTextColor(Color.BLACK);
//        searchAutoComplete.setHint("Search Doctor");
//        searchAutoComplete.setHintTextColor(Color.BLACK);
//        searchAutoComplete.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
//
//    }

    private void getDoctorList() {

        Api doctorListApi = service.getRetrofitService().create(Api.class);
        doctorListApi.getDoctorList(token).enqueue(new Callback<DoctorListResponse>() {
            @Override
            public void onResponse(Call<DoctorListResponse> call, Response<DoctorListResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().isSuccess) {
                        doctors = response.body().doctorLists.doctors;
                        adapter.addDoctors(doctors);

                        Log.e("DoctorLists", String.valueOf(doctors.size()));
                    }
                }

            }

            @Override
            public void onFailure(Call<DoctorListResponse> call, Throwable t) {

            }
        });
    }

    private void getSpecializationList(String token) {

        Api specializationListApi = service.getRetrofitService().create(Api.class);

        specializationListApi.getSpecializationList(token).enqueue(new Callback<SpecializationListResponse>() {
            @Override
            public void onResponse(Call<SpecializationListResponse> call, Response<SpecializationListResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess) {

                        specializationLists = response.body().specializations;
                        Log.e("Special size", String.valueOf(response.body().specializations.size()));


                        for (SpecializationList special : specializationLists) {
                            categories.add(special.name);
                            Log.e("name", special.name);
                        }
                        categories.add(0, "All");
//                        dataAdapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Successful!", Toast.LENGTH_SHORT).show();

                    }
                }

            }

            @Override
            public void onFailure(Call<SpecializationListResponse> call, Throwable t) {

            }

        });
    }

    @Override
    public void onDoctorClick(int id) {

        Intent intent = new Intent(getContext(), DoctorDetailActivity.class);
        intent.putExtra("doctorId", id);
        intent.putExtra("Token", token);
        Log.e("doctor_id", String.valueOf(id));
        startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_toolbar, menu);

//        MenuItem menuItem =  menu.findItem(R.id.action_search);
//        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                searchView.clearFocus();
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });

    }

    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onPrepareOptionsMenu(menu);
//        inflater.inflate(R.menu.menu_action_search, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        searchView = (SearchView) MenuItemCompat.getActionView(item);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                searchView.clearFocus();
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                final List<MyModel> filteredList = filter(mArrayList, s.toLowerCase());
//                mAdapter.setFilter(filteredList);
//                return true;
//            }
//        });
//
//    }
//
//    private List<MyModel.Data> filter(List<MyModel.Data> models, String query) {
//        query = query.toLowerCase();
//        final List<MyModel.Data> filteredList = new ArrayList<>();
//        for (MyModel model : models) {
//            final String text = (model.getCustmorName()).toLowerCase();
//            if (text.contains(query)) {
//                filteredList.add(model);
//            }
//        }
//        return filteredList;
//    }
//
//Class- MyFilterAdpater
//
//private ArrayList<MyModel>  mResultList;
//
//public void setFilter(List<MyModel.Data> model) {
//        mResultList = new ArrayList<>();
//        mResultList.addAll(model);
//        notifyDataSetCh