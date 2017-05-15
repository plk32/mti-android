package com.example.lorris.tp_retrofit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            final SingletonArticle singletonArticle = SingletonArticle.getInstance();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(TP3Service.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            TP3Service tp3Service = retrofit.create(TP3Service.class);

            Call<List<Article>> articleList = tp3Service.listArticles();
            articleList.enqueue(new Callback<List<Article>>() {
                @Override
                public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                    if (response.isSuccessful()) {
                        singletonArticle.setArticles((ArrayList<Article>) response.body());
                        mAdapter.setData(singletonArticle.getArticles());
                        mAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<List<Article>> call, Throwable t) {

                }
            });

            mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new MyAdapter(singletonArticle.getArticles());
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
