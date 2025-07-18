package com.salmakhd.android.androidwithjavaandxml.tablayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdaptor extends FragmentPagerAdapter {
    private int numberOfTabs;

    public PagerAdaptor (FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                return new ChatFragment();
            }
            case 1:
                return new StatusFragment();
            default:
                return new CallsFragment();
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
