package com.epita.mti.velibs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_view_pager);
        setSupportActionBar(toolbar);

        SingletonVelib singletonVelib = SingletonVelib.getInstance();

        mSPA = new SectionsPagerAdapter(getSupportFragmentManager(), singletonVelib.getVelibs());
        mVP = (ViewPager) findViewById(R.id.container);
        mVP.setAdapter(mSPA);
        mVP.setCurrentItem((int) getIntent().getExtras().get("position"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_items_view_pager, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_credit:
                Toast.makeText(PagerActivity.this, "Credits to :\nOufkir Moussa\nSaint-Genez Lorris", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.first_action:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this app for velibs free spots !");
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.share)));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
