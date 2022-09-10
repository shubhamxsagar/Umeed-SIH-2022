package com.example.umeed.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Adapter.HospitalListAdapter;
import com.example.umeed.Adapter.LabListAdapter;
import com.example.umeed.Models.AllListDataModel;
import com.example.umeed.Models.AllListModel;
import com.example.umeed.databinding.ActivityLabListBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LabList extends AppCompatActivity {

    ActivityLabListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLabListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<AllListDataModel> list = new ArrayList<>();

        HospitalListAdapter adapter = new HospitalListAdapter(list);
        binding.rvLab.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvLab.setLayoutManager(linearLayoutManager);

        rvLab();

    }

    private void rvLab() {

        Intent intent = getIntent();
        String lat = intent.getStringExtra("CLatitude");
        String lng =intent.getStringExtra("CLongitude");


        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
        Call<AllListModel> call = apiInterface.listoflab(lat+"",lng+"");
        call.enqueue(new Callback<AllListModel>() {
            @Override
            public void onResponse(Call<AllListModel> call, Response<AllListModel> response) {
                AllListModel model = response.body();

                Log.e("Doctor List Data", "onResponse: "+ model.getData());
                if (!model.isError()){
                    if (model.getData()!= null){
                        AllListModel list = response.body();
                        Log.e("Doctor List Data", "onResponse: "+ list);
                        LabListAdapter adapter = new LabListAdapter(list.getData());
                        binding.rvLab.setAdapter(adapter);
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