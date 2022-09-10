package com.example.umeed.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Adapter.HospitalListAdapter;
import com.example.umeed.Models.AllListDataModel;
import com.example.umeed.Models.AllListModel;
import com.example.umeed.R;
import com.example.umeed.databinding.ActivityHospitalInfoBinding;
import com.example.umeed.databinding.ActivityHospitalListBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalList extends AppCompatActivity {

    ActivityHospitalListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHospitalListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<AllListDataModel> list = new ArrayList<>();

//        HospitalListAdapter adapter = new HospitalListAdapter(list);
//        binding.rvHospital.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvHospital.setLayoutManager(linearLayoutManager);

        rvHospital();

//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.home_frame, new HospitalListFragment(), null);
//        fragmentTransaction.addToBackStack(null).commit();

    }

    private void rvHospital() {

        Intent intent = getIntent();
        String lat = intent.getStringExtra("CLatitude");
        String lng =intent.getStringExtra("CLongitude");

        Log.e("LatLng Hospital", "rvHospital: "+lat );
        Log.e("LatLng Hospital", "rvHospital: "+lng );

        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
        Call<AllListModel> call = apiInterface.listofhospital(lat+"",lng+"");
        call.enqueue(new Callback<AllListModel>() {
            @Override
            public void onResponse(Call<AllListModel> call, Response<AllListModel> response) {
                AllListModel model = response.body();
                if (!model.isError()){
                    if (model.getData()!= null){
                        AllListModel list = response.body();
                        Log.e("Hospital List Data", "onResponse: "+ list);
                        HospitalListAdapter adapter = new HospitalListAdapter(list.getData());
                        binding.rvHospital.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<AllListModel> call, Throwable t) {
                Log.e("Hospital List Data", "Error: "+ t);
            }
        });
    }
}