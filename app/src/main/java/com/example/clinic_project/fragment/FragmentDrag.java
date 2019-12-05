package com.example.clinic_project.fragment;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clinic_project.Activity.DragDetailActivity;
import com.example.clinic_project.R;
import com.example.clinic_project.Response.OtherServiceListResponse;
import com.example.clinic_project.adapter.DragAdapter;
import com.example.clinic_project.adapter.ViewPagerDragAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.DragHolder;
import com.example.clinic_project.model.Service;
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
public class FragmentDrag extends Fragment implements DragHolder.OnItemClickListener {

    private RecyclerView recyclerView;
    private DragAdapter adapter;
    private ViewPager viewPager;
    private RetrofitService service;

    private String token = null;
    private String type = "drag";
    private int town_id = 0;
    List<Service> services = new ArrayList<>();


    public FragmentDrag() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment_drag, container, false);

        service = new RetrofitService();
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new DragAdapter(this);
        token = Token.MyToken.getToken();

        ViewPagerDragAdapter viewPagerDragAdapter = new ViewPagerDragAdapter(getContext());
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(viewPagerDragAdapter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getDragList();
        return view;
    }

    private void getDragList() {

        Log.e("getDragList","success");
        Api DragListApi = service.getRetrofitService().create(Api.class);
        DragListApi.getOtherServiceList(token,type,town_id).enqueue(new Callback<OtherServiceListResponse>() {
            @Override
            public void onResponse(Call<OtherServiceListResponse> call, Response<OtherServiceListResponse> response) {
                if(response.isSuccessful()){
                        Log.e("response.body","success");
                        adapter.addItem(response.body().otherServiceList.data);
                        Log.e("DragData Size",String.valueOf(services.size()));

                        adapter.notifyDataSetChanged();
                    }
                    else {
                        Log.e("response.body","fail");
                    }
            }

            @Override
            public void onFailure(Call<OtherServiceListResponse> call, Throwable t) {
                Log.e("failure",t.toString());

            }
        });
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onDragClick(int id) {
        Intent intent = new Intent(getContext(), DragDetailActivity.class);
        intent.putExtra("serviceId",id);
        Log.e("serviceId",String.valueOf(id));
        startActivity(intent);
    }
}




