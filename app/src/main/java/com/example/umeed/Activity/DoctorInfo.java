package com.example.umeed.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.umeed.Adapter.TwoTabLayoutDoctor;
import com.example.umeed.Chat.ChatInfo;
import com.example.umeed.R;
import com.example.umeed.databinding.ActivityDoctorInfoBinding;
import com.google.android.material.appbar.AppBarLayout;

public class DoctorInfo extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

    ActivityDoctorInfoBinding binding;
    AppBarLayout appBar;
    TwoTabLayoutDoctor fragmentsAdapter;
    private ConstraintLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoctorInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mLayout = binding.shrinkingLayout;
//        mLayout2 = binding.shrinkingLayout2;

        fragmentsAdapter = new TwoTabLayoutDoctor(getSupportFragmentManager(), TwoTabLayoutDoctor.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        binding.viewPager.setAdapter(fragmentsAdapter);
        binding.tablayout.setupWithViewPager(binding.viewPager);

        appBar = binding.appbar;
        appBar.addOnOffsetChangedListener(this);
        setSupportActionBar(binding.toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Intent intent = getIntent();
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

        binding.DoctorName.setText(ownername);
        binding.DoctorSpecialization.setText(specialization);
        binding.DoctorPhone.setText(mobileno);
        binding.DoctorEmail.setText(emailid);
        binding.DoctorWebsite.setText(website);

        Log.e("org", "onCreate: "+organizationname );

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.green_doctor));
        }

        binding.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DoctorInfo.this, ChatInfo.class);
                intent1.putExtra("receiver", medicalid);
                intent1.putExtra("ownername", ownername);
                startActivity(intent1);
            }
        });

        binding.map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorInfo.this, MapActivity.class);
                intent.putExtra("lattitude",lattitude);
                intent.putExtra("longitude",longitude);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("credentials", MODE_PRIVATE);
        if (sharedPreferences.contains("aadharno")) {
            binding.chat.setVisibility(View.VISIBLE);
        }else{
            binding.chat.setVisibility(View.GONE);
        }

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        ViewGroup.LayoutParams lp = mLayout.getLayoutParams();
        int newHeight = Math.max(mLayout.getMinHeight(), mLayout.getMaxHeight() + verticalOffset + mLayout.getTop());
        if (newHeight != lp.height) {
            lp.height = newHeight;
            mLayout.setLayoutParams(lp);
        }

//        ViewGroup.LayoutParams lp2 = mLayout2.getLayoutParams();
//        int newHeight2 = Math.max(mLayout2.getMinHeight(), mLayout2.getMaxHeight() + verticalOffset + mLayout2.getTop());
//        if (newHeight2 != lp2.height) {
//            lp2.height = newHeight2;
//            mLayout2.setLayoutParams(lp2);
//        }


//        ViewGroup.LayoutParams lp2 = mLayout2.getLayoutParams();
//        int newHeight2 = Math.max(mLayout2.getMinHeight(), mLayout2.getMaxHeight() + verticalOffset + mLayout2.getTop());
//        if (newHeight2 != lp2.height) {
//            lp2.height = newHeight2;
//            mLayout.setLayoutParams(lp2);
//        }

    }
}