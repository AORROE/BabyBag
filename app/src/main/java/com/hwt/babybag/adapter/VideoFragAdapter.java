package com.hwt.babybag.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class VideoFragAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> fragTitle;

    public VideoFragAdapter(FragmentManager fm, List<Fragment> fragments, List<String> fragTitle) {
        super(fm);
        this.fragments = fragments;
        this.fragTitle = fragTitle;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragTitle.get(position);
    }


}
