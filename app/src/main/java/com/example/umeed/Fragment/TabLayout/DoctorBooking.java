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
import com.example.umeed.Adapter.TablayoutAdapted.DoctorTreatmentAdapter;
import com.example.umeed.Models.TablayoutModels.HospitalTreatmentModel;
import com.example.umeed.R;
import com.example.umeed.databinding.FragmentDoctorBookingBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DoctorBooking extends Fragment {

    FragmentDoctorBookingBinding binding;
    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.rvBooking.setLayoutManager(linearLayoutManager);

        Log.e("Medical ID", "onCreateView: "+medicalid );

        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
        Call<HospitalTreatmentModel> call = apiInterface.listoftreatmentoffered(medicalid+"");
        call.enqueue(new Callback<HospitalTreatmentModel>() {
            @Override
            public void onResponse(Call<HospitalTreatmentModel> call, Response<HospitalTreatmentModel> response) {
                HospitalTreatmentModel model = response.body();
                if (!model.isError()){
                    if (model.getData()!= null){
                        HospitalTreatmentModel list = response.body();
                        Log.e("Hospital List Data", "onResponse: "+ list);
                        DoctorTreatmentAdapter adapter = new DoctorTreatmentAdapter(model.getData());
                        binding.rvBooking.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<HospitalTreatmentModel> call, Throwable t) {
                Log.e("Hospital List Data", "Error: "+ t);
            }
        });

//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.home_frame, new HospitalListFragment(), null);
//        fragmentTransaction.addToBackStack(null).commit();

        return binding.getRoot();
    }
}