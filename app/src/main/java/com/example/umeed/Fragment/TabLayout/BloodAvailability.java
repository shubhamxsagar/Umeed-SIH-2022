package com.example.umeed.Fragment.TabLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Adapter.TablayoutAdapted.BloodAvailabilityAdapter;
import com.example.umeed.Adapter.TablayoutAdapted.HospitalAvailabilityAdapter;
import com.example.umeed.Models.TablayoutModels.BloodAvailabilityModel;
import com.example.umeed.Models.TablayoutModels.HospitalAvailabilityModel;
import com.example.umeed.R;
import com.example.umeed.databinding.FragmentBloodAvailabilityBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BloodAvailability extends Fragment {

    Activity context;
    FragmentBloodAvailabilityBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = binding.inflate(inflater, container, false);
        context = getActivity();

        Intent intent = context.getIntent();
        String organizationname = intent.getStringExtra("organizationname");
        String ownername = intent.getStringExtra("ownername");
        String medicalusertype = intent.getStringExtra("medicalusertype");
        String emailid = intent.getStringExtra("emailid");
        String website = intent.getStringExtra("website");
        String mobileno = intent.getStringExtra("mobileno");
        String landlineno = intent.getStringExtra("landlineno");
        String medicalid = intent.getStringExtra("medicalid");
        String address = intent.getStringExtra("address");
        String lattitude = intent.getStringExtra("lattitude");
        String longitude = intent.getStringExtra("longitude");
        String specialization = intent.getStringExtra("specialization");
        String covidpatientaccepted = intent.getStringExtra("covidpatientaccepted");
        String ayushmancardaccepted = intent.getStringExtra("ayushmancardaccepted");
        String mediclaimaccepted = intent.getStringExtra("mediclaimaccepted");
        String emergencypatientaccepted = intent.getStringExtra("emergencypatientaccepted");
        String info = intent.getStringExtra("info");
        String type = intent.getStringExtra("type");
        String bloodbank = intent.getStringExtra("bloodbank");
        String distance = intent.getStringExtra("distance");

        ArrayList<HospitalAvailabilityModel> list = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.rvAvailability.setLayoutManager(linearLayoutManager);

        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
        Call<BloodAvailabilityModel> call = apiInterface.bloodstockinbloodbank(medicalid+"");
        call.enqueue(new Callback<BloodAvailabilityModel>() {
            @Override
            public void onResponse(Call<BloodAvailabilityModel> call, Response<BloodAvailabilityModel> response) {
                BloodAvailabilityModel model = response.body();
                if (!model.isError()){
                    if (model.getData()!= null){
                        BloodAvailabilityModel list = response.body();
                        Log.e("Hospital List Data", "onResponse: "+ list);
                        BloodAvailabilityAdapter adapter = new BloodAvailabilityAdapter(model.getData());
                        binding.rvAvailability.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<BloodAvailabilityModel> call, Throwable t) {
                Log.e("Hospital List Data", "Error: "+ t);
            }
        });



        return binding.getRoot();
    }
}