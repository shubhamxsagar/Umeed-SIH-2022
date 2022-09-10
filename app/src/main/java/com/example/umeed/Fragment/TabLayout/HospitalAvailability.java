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
import com.example.umeed.Adapter.HospitalListAdapter;
import com.example.umeed.Adapter.TablayoutAdapted.HospitalAvailabilityAdapter;
import com.example.umeed.Models.AllListModel;
import com.example.umeed.Models.TablayoutModels.HospitalAvailabilityDataModel;
import com.example.umeed.Models.TablayoutModels.HospitalAvailabilityModel;
import com.example.umeed.databinding.FragmentHospitalAvailbiliyBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HospitalAvailability extends Fragment {

    Activity context;
    FragmentHospitalAvailbiliyBinding binding;

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
        Call<HospitalAvailabilityModel> call = apiInterface.listofroominhospital(medicalid+"");
        call.enqueue(new Callback<HospitalAvailabilityModel>() {
            @Override
            public void onResponse(Call<HospitalAvailabilityModel> call, Response<HospitalAvailabilityModel> response) {
                HospitalAvailabilityModel model = response.body();
                if (!model.isError()){
                    if (model.getData()!= null){
                        HospitalAvailabilityModel list = response.body();
                        Log.e("Hospital List Data", "onResponse: "+ list);
                        HospitalAvailabilityAdapter adapter = new HospitalAvailabilityAdapter(model.getData());
                        binding.rvAvailability.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<HospitalAvailabilityModel> call, Throwable t) {
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