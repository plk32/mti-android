package com.example.lorris.tp_retrofit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Lorris on 12/05/2017.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Article> articles;

    public SectionsPagerAdapter(FragmentManager fm, ArrayList<Article> listArticle) {
        super(fm);
        articles = listArticle;
    }

    @Override
    public Fragment getItem(int position) {
        return PlaceholderFragment.newInstance(articles.get(position));
    }

    @Override
    public int getCount() {
        return articles != null ? articles.size() : 0;
    }

    public CharSequence getPageTitle(int position) {
        return articles.get(position).getTitle();
    }
}
