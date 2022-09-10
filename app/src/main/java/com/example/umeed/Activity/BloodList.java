package com.example.umeed.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Adapter.BloodListAdapter;
import com.example.umeed.Adapter.DoctorListAdapter;
import com.example.umeed.Adapter.HospitalListAdapter;
import com.example.umeed.DraggableFloatingActionButton;
import com.example.umeed.Models.AllListDataModel;
import com.example.umeed.Models.AllListModel;
import com.example.umeed.R;
import com.example.umeed.databinding.ActivityBloodListBinding;
import com.example.umeed.databinding.ActivityDoctorListBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BloodList extends AppCompatActivity {

    ActivityBloodListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBloodListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<AllListDataModel> list = new ArrayList<>();

//        HospitalListAdapter adapter = new HospitalListAdapter(list);
//        binding.rvBlood.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvBlood.setLayoutManager(linearLayoutManager);

        rvBlood();

    }

    private void rvBlood() {

        Intent intent = getIntent();
        String lat = intent.getStringExtra("CLatitude");
        String lng =intent.getStringExtra("CLongitude");

        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
        Call<AllListModel> call = apiInterface.listofbloodbank(lat+"",lng+"");
        call.enqueue(new Callback<AllListModel>() {
            @Override
            public void onResponse(Call<AllListModel> call, Response<AllListModel> response) {
                AllListModel model = response.body();
                if (!model.isError()){
                    if (model.getData()!= null){
                        AllListModel list = response.body();
                        Log.e("Doctor List Data", "onResponse: "+ list);
                        BloodListAdapter adapter = new BloodListAdapter(list.getData());
                        binding.rvBlood.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<AllListModel> call, Throwable t) {
                Log.e("Doctor List Data", "Error: "+ t);
            }
        });

    }

//    private void animateFab(){
//        if(isOpen){
//
//            call.startAnimation(fabClose);
//            message.startAnimation(fabClose);
//            call.setClickable(false);
//            message.setClickable(false);
//            isOpen=false;
//        }
//        else{
//            call.setX(sos.getX());
//            call.setY(sos.getY()-350);
//            message.setX(sos.getX());
//            message.setY(sos.getY()-200);
//
//            call.startAnimation(fabOpen);
//            message.startAnimation(fabOpen);
//            call.setClickable(true);
//            message.setClickable(true);
//            isOpen=true;
//        }
//    }
}