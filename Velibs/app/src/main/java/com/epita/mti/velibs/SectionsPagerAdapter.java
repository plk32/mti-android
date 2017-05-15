package com.epita.mti.velibs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Lorris on 12/05/2017.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Velib> velibs;

    public SectionsPagerAdapter(FragmentManager fm, ArrayList<Velib> listVelib) {
        super(fm);
        velibs = listVelib;
    }

    @Override
    public Fragment getItem(int position) {
        return PlaceholderFragment.newInstance(velibs.get(position));
    }

    @Override
    public int getCount() {
        return velibs != null ? velibs.size() : 0;
    }

    public CharSequence getPageTitle(int position) {
        return velibs.get(position).getFields().getName();
    }
}
