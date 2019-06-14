package com.cjbensan.axiomaapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.CardView;

import com.cjbensan.axiomaapp.fragment.CardFragment;

import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter extends FragmentStatePagerAdapter {

    private List<CardFragment> items = new ArrayList<>();

    public CardPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public void add(CardFragment cardFragment) {
        items.add(cardFragment);
    }

    public CardView getCardView(int position) {
        return items.get(position).getCardView();
    }

    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }
}
