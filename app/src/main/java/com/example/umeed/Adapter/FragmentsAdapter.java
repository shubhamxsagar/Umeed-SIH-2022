package com.example.umeed.Adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.example.umeed.Fragment.TabLayout.HospitalAbout;
import com.example.umeed.Fragment.TabLayout.HospitalAvailability;
import com.example.umeed.Fragment.TabLayout.HospitalBooking;

import java.util.HashMap;
import java.util.Map;

public class FragmentsAdapter extends FragmentPagerAdapter {

    private Map<Integer, String> mFragmentTags;
    static final int NUM_ITEMS = 3;
    private Fragment mFragmentAtPos0;
    private final FragmentManager mFragmentManager;

    
    public FragmentsAdapter(@NonNull FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    public FragmentsAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        mFragmentManager = fm;
        mFragmentTags = new HashMap<Integer, String>();
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {


        String q = "NoPass";
        String h = "Pass";

        switch (position) {

            case 0:
                return new HospitalBooking();
            case 1:
                return new HospitalAvailability();
            case 2:
                return new HospitalAbout();
            default:
                return new HospitalBooking();

        }

        }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);
        if (obj instanceof Fragment){
            //record fragment tag
            Fragment f = (Fragment) obj;
            String tag = f.getTag();
            mFragmentTags.put(position, tag);
        }
        return obj;
    }

    public Fragment getFragment(int position){
        String tag = mFragmentTags.get(position);
        if (tag == null){
            return null;
        }
        return mFragmentManager.findFragmentByTag(tag);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if(position==0){
            title = "Treatments";
        }
        if(position==1){
            title = "Availability";
        }
        if(position==2){
            title = "About";
        }
        return title;
    }
}

