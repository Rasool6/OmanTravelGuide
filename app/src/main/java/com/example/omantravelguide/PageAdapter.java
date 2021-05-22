package com.example.omantravelguide;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class PageAdapter extends FragmentPagerAdapter {
    int tabcount;

    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: return new Pending_frg();
            case 1: return new COnfirm_frg();
            default:  return null;
        }

    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
