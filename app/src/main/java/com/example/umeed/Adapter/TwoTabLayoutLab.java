package com.example.umeed.Adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.umeed.Fragment.TabLayout.LabAbout;
import com.example.umeed.Fragment.TabLayout.LabBooking;

import java.util.HashMap;
import java.util.Map;

public class TwoTabLayoutLab extends FragmentPagerAdapter {

    private Map<Integer, String> mFragmentTags;
    static final int NUM_ITEMS = 2;
    private Fragment mFragmentAtPos0;
    private final FragmentManager mFragmentManager;


    public TwoTabLayoutLab(@NonNull FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    public TwoTabLayoutLab(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        mFragmentManager = fm;
        mFragmentTags = new HashMap<Integer, String>();
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                return new LabBooking();
            case 1:
                return new LabAbout();
            default:
                return new LabBooking();

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
            title = "Availability";
        }
        if(position==1){
            title = "About";
        }
        return title;
    }
}

