package com.epita.mti.velibs;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            FetchData(null);

            mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new MyAdapter();
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = getIntent().getStringExtra(SearchManager.QUERY).toUpperCase();
            FetchData(query);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_items_main_activity, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_credit:
                Toast.makeText(MainActivity.this, "Credits to :\nOufkir Moussa\nSaint-Genez Lorris", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void FetchData(final String query) {
        final SingletonVelib singletonVelib = SingletonVelib.getInstance();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OpenDataService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OpenDataService openDataService = retrofit.create(OpenDataService.class);

        Call<VelibResponse> responseCall = openDataService.getResponse();
        responseCall.enqueue(new Callback<VelibResponse>() {
            @Override
            public void onResponse(Call<VelibResponse> call, Response<VelibResponse> response) {
                if (response.isSuccessful()) {
                    if (query != null) {
                        ArrayList<Velib> compatibleVelibAdresses = new ArrayList<>();
                        for (Velib v : response.body().getRecords()) {
                            if (v.getFields().getAddress().contains(query))
                                compatibleVelibAdresses.add(v);
                        }
                        if (compatibleVelibAdresses.size() > 0)
                            singletonVelib.setVelibs(compatibleVelibAdresses);
                        else {
                            Toast.makeText(MainActivity.this, "No result found", Toast.LENGTH_SHORT).show();
                            singletonVelib.setVelibs(response.body().getRecords());
                        }
                    }
                    else
                        singletonVelib.setVelibs(response.body().getRecords());
                    mAdapter.setData(singletonVelib.getVelibs());
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<VelibResponse> call, Throwable t) {

            }
        });
    }
}
