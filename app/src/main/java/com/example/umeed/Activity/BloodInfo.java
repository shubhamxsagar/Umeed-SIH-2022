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
import android.view.animation.Animation;

import com.example.umeed.Adapter.FragmentsAdapter;
import com.example.umeed.Adapter.TwoTabLayoutBlood;
import com.example.umeed.Chat.ChatInfo;
import com.example.umeed.DraggableFloatingActionButton;
import com.example.umeed.R;
import com.example.umeed.databinding.ActivityBloodInfoBinding;
import com.example.umeed.databinding.ActivityHospitalInfoBinding;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BloodInfo extends AppCompatActivity   implements AppBarLayout.OnOffsetChangedListener{

    private ConstraintLayout mLayout;
    ActivityBloodInfoBinding binding;
    AppBarLayout appBar;
    TwoTabLayoutBlood fragmentsAdapter;

    FloatingActionButton call,message;
    DraggableFloatingActionButton sos;
    Animation fabOpen,fabClose;
    boolean isOpen=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBloodInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mLayout = binding.shrinkingLayout;
//        mLayout2 = binding.shrinkingLayout2;
//        m = binding.ss1;

        fragmentsAdapter = new TwoTabLayoutBlood(getSupportFragmentManager(),FragmentsAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
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

        binding.BloodOrgName.setText(organizationname);
        binding.BloodPhone.setText(mobileno);
        binding.BloodEmail.setText(emailid);
        binding.BloodWebsite.setText(website);

        Log.e("org", "onCreate: "+organizationname );

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red_blood));
        }

        binding.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(BloodInfo.this, ChatInfo.class);
                intent1.putExtra("receiver", medicalid);
                intent1.putExtra("organizationname", organizationname);
                startActivity(intent1);
            }
        });

        binding.map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BloodInfo.this, MapActivity.class);
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


//        ViewGroup.LayoutParams lp3 = m.getLayoutParams();
//        int newHeight3 = Math.max(m.getMinHeight(), m.getMaxHeight() + verticalOffset + m.getTop());
//        if (newHeight3 != lp3.height) {
//            lp3.height = newHeight3;
//            m.setLayoutParams(lp3);
//        }

//        ViewGroup.LayoutParams lp2 = mLayout2.getLayoutParams();
//        int newHeight2 = Math.max(mLayout2.getMinHeight(), mLayout2.getMaxHeight() + verticalOffset + mLayout2.getTop());
//        if (newHeight2 != lp2.height) {
//            lp2.height = newHeight2;
//            mLayout.setLayoutParams(lp2);
//        }

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