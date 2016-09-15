package com.dtrajko.zoo.utils;

import com.dtrajko.zoo.models.Animal;
import com.dtrajko.zoo.models.GalleryImage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Dejan Trajkovic on 9/14/2016.
 */
public interface GalleryApiInterface {

    @GET("gallery.json")
    Call<List<GalleryImage>> getStreams();
}
