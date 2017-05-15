package com.epita.mti.velibs;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            FetchData();

            mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new MyAdapter();
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    private void FetchData() {
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
                    Log.v("OnRESPONSE", response.body().getRecords().get(0).getFields().getLastUpdate());
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
