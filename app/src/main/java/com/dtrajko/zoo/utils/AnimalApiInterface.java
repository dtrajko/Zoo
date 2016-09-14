package com.dtrajko.zoo.utils;

import com.dtrajko.zoo.models.Animal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Dejan Trajkovic on 9/14/2016.
 */
public interface AnimalApiInterface {

    @GET("Exhibits.json")
    Call<List<Animal>> getStreams();
}
