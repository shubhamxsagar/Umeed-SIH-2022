package com.example.umeed.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.viewpager.widget.ViewPager;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Adapter.FragmentsAdapter;
import com.example.umeed.Chat.ChatInfo;
import com.example.umeed.Models.TotalBedDataModel;
import com.example.umeed.Models.TotalBedModel;
import com.example.umeed.R;
import com.example.umeed.databinding.ActivityHospitalInfoBinding;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalInfo extends AppCompatActivity  implements AppBarLayout.OnOffsetChangedListener{

    private ConstraintLayout mLayout;
    ActivityHospitalInfoBinding binding;
    AppBarLayout appBar;
    FragmentsAdapter fragmentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHospitalInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mLayout = binding.shrinkingLayout;
//        mLayout2 = binding.shrinkingLayout2;

        fragmentsAdapter = new FragmentsAdapter(getSupportFragmentManager(),FragmentsAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
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

        binding.HospitalName.setText(organizationname);
        binding.HospitalPhone.setText(mobileno);
        binding.HospitalType.setText(type);
        binding.HospitalEmail.setText(emailid);
        binding.HospitalWebsite.setText(website);

        Log.e("org", "onCreate: "+organizationname );

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.umeed_blue));
        }

        binding.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(HospitalInfo.this, ChatInfo.class);
                intent1.putExtra("receiver", medicalid);
                intent1.putExtra("organizationname", organizationname);
                startActivity(intent1);
            }
        });

        binding.map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HospitalInfo.this, MapActivity.class);
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

        String aadhar = sharedPreferences.getString("aadharno","");

        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
        Call<TotalBedModel> call = apiInterface.totalbedinhospital(medicalid+"");
        call.enqueue(new Callback<TotalBedModel>() {
            @Override
            public void onResponse(Call<TotalBedModel> call, Response<TotalBedModel> response) {
                TotalBedModel model = response.body();

                Log.e("Toral Bed Model", "onResponse: "+ model );

                if (!model.isError()){
                    TotalBedDataModel totalBedDataModel = response.body().getData();

                    Log.e("Toral Bed Model", "onResponse: "+ totalBedDataModel.getTotalbedcount());

                    String totalbed = totalBedDataModel.getTotalbedcount();
                    Integer total = Integer.parseInt(totalbed);
                    double RevPandemic = total*0.2;
                    double RevGovernment = total*0.1;
                    double RevPrivate = total*0.7;

                    double Pandemic = Math.round(RevPandemic * 1000.0) / 1000.0;
                    double Government = Math.round(RevGovernment * 1000.0) / 1000.0;
                    double Private = Math.round(RevPrivate * 1000.0) / 1000.0;

                    String Pan = String.valueOf(Pandemic);
                    String Gov = String.valueOf(Government);
                    String Pri = String.valueOf(Private);

                    binding.RevPandemic.setText(Pan);
                    binding.RevGovernment.setText(Gov);
                    binding.RevPrivate.setText(Pri);
                }
            }
            @Override
            public void onFailure(Call<TotalBedModel> call, Throwable t) {
            }
        });
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