package com.example.tabsprojectone;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerTabAdapter extends FragmentPagerAdapter {

    ArrayList<String> titles;
    ArrayList<Fragment> fragments;

    public ViewPagerTabAdapter(FragmentManager fm) {
        super(fm);

        titles = new ArrayList<>();
        fragments = new ArrayList<>();

    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void add(String tabTitle, Fragment tabFragment) {
        titles.add(tabTitle);
        fragments.add(tabFragment);
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
