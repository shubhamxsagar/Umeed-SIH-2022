package com.example.umeed.Fragment.TabLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umeed.R;
import com.example.umeed.databinding.FragmentBloodAboutBinding;

public class BloodAbout extends Fragment {

    Activity context;
    FragmentBloodAboutBinding binding;
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


        binding.bloodOwner.setText("Owner: "+ownername);
        binding.bloodAddress.setText("Address: "+address);
        binding.bloodLandno.setText("Landline number: "+landlineno);
//        binding.bloodCovid.setText("Are Covid patients accepted?: "+covidpatientaccepted);
//        binding.bloodAyushman.setText("Is Ayushman Card accepted?: "+ayushmancardaccepted);
//        binding.bloodMediclaim.setText("Is MediClaim Accepted?: "+mediclaimaccepted);
//        binding.bloodEmergency.setText("Are emergency patients accepted?: "+emergencypatientaccepted);
//        binding.bloodBlood.setText("Is blood bank available?: "+bloodbank);
        binding.bloodAbout.setText("About Us: "+info);

        return binding.getRoot();
    }
}