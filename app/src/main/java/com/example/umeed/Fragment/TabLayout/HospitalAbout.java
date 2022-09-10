package com.example.umeed.Fragment.TabLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umeed.R;
import com.example.umeed.databinding.FragmentHospitalAboutBinding;

public class HospitalAbout extends Fragment {

    Activity context;
    FragmentHospitalAboutBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = binding.inflate(inflater, container, false);
        context = getActivity();

        Intent intent = context.getIntent();

        String info = intent.getStringExtra("info");
        String ownername = intent.getStringExtra("ownername");
        String landlineno = intent.getStringExtra("landlineno");
        String address = intent.getStringExtra("address");
        String covidpatientaccepted = intent.getStringExtra("covidpatientaccepted");
        String ayushmancardaccepted = intent.getStringExtra("ayushmancardaccepted");
        String mediclaimaccepted = intent.getStringExtra("mediclaimaccepted");
        String emergencypatientaccepted = intent.getStringExtra("emergencypatientaccepted");
        String bloodbank = intent.getStringExtra("bloodbank");


        binding.hospitalOwner.setText("Owner: "+ownername);
        binding.hospitalAddress.setText("Address: "+address);
        binding.hospitalLandno.setText("Landline number: "+landlineno);
        binding.hospitalCovid.setText("Are Covid patients accepted?: "+covidpatientaccepted);
        binding.hospitalAyushman.setText("Is Ayushman Card accepted?: "+ayushmancardaccepted);
        binding.hospitalMediclaim.setText("Is MediClaim Accepted?: "+mediclaimaccepted);
        binding.hospitalEmergency.setText("Are emergency patients accepted?: "+emergencypatientaccepted);
        binding.hospitalBlood.setText("Is blood bank available?: "+bloodbank);
        binding.hospitalAbout.setText("About Us: "+info);

        return binding.getRoot();
    }
}