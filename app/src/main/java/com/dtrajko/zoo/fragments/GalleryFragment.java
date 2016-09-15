package com.dtrajko.zoo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.dtrajko.zoo.R;
import com.dtrajko.zoo.adapters.ExhibitsAdapter;
import com.dtrajko.zoo.adapters.GalleryImageAdapter;
import com.dtrajko.zoo.models.Animal;
import com.dtrajko.zoo.models.GalleryImage;
import com.dtrajko.zoo.utils.GalleryApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dejan Trajkovic on 9/13/2016.
 */
public class GalleryFragment extends Fragment {

    private GridView mGridView;
    private GalleryImageAdapter mAdapter;

    public static GalleryFragment getInstance() {
        GalleryFragment fragment;
        fragment = new GalleryFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery_grid, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGridView = (GridView) view.findViewById(R.id.grid);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new GalleryImageAdapter(getActivity(), 0);
        mGridView.setAdapter(mAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.gallery_feed))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GalleryApiInterface galleryApiInterface =
                retrofit.create(GalleryApiInterface.class);

        Call<List<GalleryImage>> streams = galleryApiInterface.getStreams();
        streams.enqueue(new Callback<List<GalleryImage>>() {
            @Override
            public void onResponse(Call<List<GalleryImage>> call, Response<List<GalleryImage>> response) {
                if (response.isSuccessful()) {
                    for (GalleryImage galleryImage : response.body()) {
                        mAdapter.add(galleryImage);
                        // Log.e("Zoo", galleryImage.getThumbnail());
                        // Log.e("Zoo", galleryImage.getImage());
                        // Log.e("Zoo", galleryImage.getCaption());
                    }
                    mAdapter.notifyDataSetChanged();
                } else {
                    Log.e("Zoo", "Retrofit response not successful: " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<GalleryImage>> call, Throwable t) {
                Log.e("Zoo", "Retrofit error" + t.getMessage());
            }
        });
    }
}
