package com.example.umeed.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Adapter.DoctorListAdapter;
import com.example.umeed.Adapter.HospitalListAdapter;
import com.example.umeed.Models.AllListDataModel;
import com.example.umeed.Models.AllListModel;
import com.example.umeed.R;
import com.example.umeed.databinding.ActivityDoctorListBinding;
import com.example.umeed.databinding.ActivityLabListBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorList extends AppCompatActivity {

    ActivityDoctorListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoctorListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<AllListDataModel> list = new ArrayList<>();

//        HospitalListAdapter adapter = new HospitalListAdapter(list);
//        binding.rvDoctor.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvDoctor.setLayoutManager(linearLayoutManager);

        rvDoctor();

    }

    private void rvDoctor() {

        Intent intent = getIntent();
        String lat = intent.getStringExtra("CLatitude");
        String lng =intent.getStringExtra("CLongitude");

        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
        Call<AllListModel> call = apiInterface.listofppd(lat+"",lng+"");
        call.enqueue(new Callback<AllListModel>() {
            @Override
            public void onResponse(Call<AllListModel> call, Response<AllListModel> response) {
                AllListModel model = response.body();
                if (!model.isError()){
                    if (model.getData()!= null){
                        AllListModel list = response.body();
                        Log.e("Doctor List Data", "onResponse: "+ list);
                        DoctorListAdapter adapter = new DoctorListAdapter(list.getData());
                        binding.rvDoctor.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<AllListModel> call, Throwable t) {
                Log.e("Doctor List Data", "Error: "+ t);
            }
        });

    }
}