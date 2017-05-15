package com.example.lorris.tp_retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Lorris on 12/05/2017.
 */

public interface TP3Service {
    public static final String ENDPOINT = "http://www.tutos-android.com";

    @GET("/MTI/2018/TP3.json")
    Call<List<Article>> listArticles();
}
