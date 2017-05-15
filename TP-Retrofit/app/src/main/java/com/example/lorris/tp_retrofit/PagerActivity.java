package com.example.lorris.tp_retrofit;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Lorris on 12/05/2017.
 */

public class PagerActivity extends AppCompatActivity {
    private SectionsPagerAdapter mSPA;
    private ViewPager mVP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        SingletonArticle singletonArticle = SingletonArticle.getInstance();

        mSPA = new SectionsPagerAdapter(getSupportFragmentManager(), singletonArticle.getArticles());
        mVP = (ViewPager) findViewById(R.id.view_pager_tp3);
        mVP.setAdapter(mSPA);
        mVP.setCurrentItem((int) getIntent().getExtras().get("position"));
    }
}
