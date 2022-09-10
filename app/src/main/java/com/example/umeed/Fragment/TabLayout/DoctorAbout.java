package com.example.umeed.Fragment.TabLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umeed.R;
import com.example.umeed.databinding.FragmentDoctorAboutBinding;

public class DoctorAbout extends Fragment {

    Activity context;
    FragmentDoctorAboutBinding binding;
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

        binding.doctorOwner.setText("Owner: "+ownername);
        binding.doctorAddress.setText("Address: "+address);
        binding.doctorLandno.setText("Landline number: "+landlineno);
        binding.doctorCovid.setText("Are Covid patients accepted?: "+covidpatientaccepted);
//        binding.doctorAyushman.setText("Is Ayushman Card accepted?: "+ayushmancardaccepted);
//        binding.doctorMediclaim.setText("Is MediClaim Accepted?: "+mediclaimaccepted);
//        binding.doctorEmergency.setText("Are emergency patients accepted?: "+emergencypatientaccepted);
//        binding.doctorBlood.setText("Is blood bank available?: "+bloodbank);
        binding.doctorAbout.setText("About Us: "+info);

        return binding.getRoot();
    }
}