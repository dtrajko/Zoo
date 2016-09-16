package com.dtrajko.zoo.utils;

import com.dtrajko.zoo.models.Pin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Dejan Trajkovic on 9/16/2016.
 */
public interface PinsApiInterface {

    @GET("pins.json")
    Call<List<Pin>> getStreams();
}
