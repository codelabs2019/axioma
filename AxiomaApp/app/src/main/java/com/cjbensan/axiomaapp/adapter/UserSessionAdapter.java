package com.cjbensan.axiomaapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserSessionAdapter extends FragmentPagerAdapter {

    private final List<Fragment> items = new ArrayList<Fragment>();

    public UserSessionAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public void addFragment(Fragment fragment) {
        items.add(fragment);
    }

    @Override
    public Fragment getItem(int i) {
        return items.get(i);
    }

    @Override
    public int getCount() {
        return items.size();
    }
}
