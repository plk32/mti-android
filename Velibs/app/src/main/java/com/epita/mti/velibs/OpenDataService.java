package com.epita.mti.velibs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Lorris on 12/05/2017.
 */

public interface OpenDataService {
    public static final String ENDPOINT = "https://opendata.paris.fr";

    @GET("/api/records/1.0/search/?dataset=stations-velib-disponibilites-en-temps-reel" +
            "&rows=100&facet=banking&facet=bonus&facet=status&facet=contract_name")
    Call<VelibResponse> getResponse();
}
