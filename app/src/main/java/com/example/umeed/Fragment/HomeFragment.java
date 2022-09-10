package com.example.umeed.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Activity.BloodList;
import com.example.umeed.Activity.DoctorList;
import com.example.umeed.Activity.FAQs;
import com.example.umeed.Activity.HospitalList;
import com.example.umeed.Activity.LabInfo;
import com.example.umeed.Activity.LabList;
import com.example.umeed.Activity.Policies;
import com.example.umeed.Activity.WebView;
import com.example.umeed.Models.ProfileDataModel;
import com.example.umeed.Models.ProfileModel;
import com.example.umeed.R;
import com.example.umeed.databinding.FragmentHomeBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = binding.inflate(inflater, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("credentials", Context.MODE_PRIVATE);
        String aadhar = sharedPreferences.getString("aadharno","");

        if (sharedPreferences.contains("aadharno")){
            ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
            Call<ProfileModel> call = apiInterface.userdetails(aadhar+"");
            call.enqueue(new Callback<ProfileModel>() {
                @Override
                public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                    ProfileModel profileModel = response.body();
                    if(!profileModel.isError()){
                        ProfileDataModel profileDataModel = response.body().getData();
                        binding.user.setText("Hi, " + profileDataModel.getFirstname());

                    }
                }

                @Override
                public void onFailure(Call<ProfileModel> call, Throwable t) {

                }
            });
        }


        Intent intent = getActivity().getIntent();
        String lat = intent.getStringExtra("CLatitude");
        String lng = intent.getStringExtra("CLongitude");

        Log.e("LatLng Hospital", "rvHospital: "+lat);
        Log.e("LatLng Hospital", "rvHospital: "+lng);

        binding.HospitalData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                replaceFragment(new HospitalListFragment());
                Intent intent = new Intent(getActivity(), HospitalList.class);
                intent.putExtra("ClickHospital","ClickHospital");
                intent.putExtra("CLatitude",lat);
                intent.putExtra("CLongitude",lng);
                startActivity(intent);
            }
        });

        binding.doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DoctorList.class);
                intent.putExtra("ClickDoctor","ClickDoctor");
                intent.putExtra("CLatitude",lat);
                intent.putExtra("CLongitude",lng);
                startActivity(intent);
            }
        });

        binding.lab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LabList.class);
                intent.putExtra("CLatitude",lat);
                intent.putExtra("CLongitude",lng);
                startActivity(intent);
            }
        });

        binding.Policies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Policies.class);
                intent.putExtra("CLatitude",lat);
                intent.putExtra("CLongitude",lng);
                startActivity(intent);
            }
        });

        binding.cowin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WebView.class);
                startActivity(intent);
            }
        });

        binding.blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BloodList.class);
                intent.putExtra("CLatitude",lat);
                intent.putExtra("CLongitude",lng);
                startActivity(intent);
            }
        });

        binding.faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FAQs.class));
            }
        });

        return binding.getRoot();
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }

}