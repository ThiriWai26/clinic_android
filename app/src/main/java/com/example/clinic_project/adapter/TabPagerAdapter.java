package com.example.clinic_project.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.clinic_project.fragment.HomeFragment;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    String[] tab = {"Home"};

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                HomeFragment fragment = new HomeFragment();
                return fragment;

        }

        return null;
    }

    @Override
    public int getCount() {
        return 1;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tab[position];
    }
}
