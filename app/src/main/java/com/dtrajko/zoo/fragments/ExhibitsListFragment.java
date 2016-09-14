package com.dtrajko.zoo.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;

import com.dtrajko.zoo.R;
import com.dtrajko.zoo.adapters.ExhibitsAdapter;
import com.dtrajko.zoo.models.Animal;
import com.dtrajko.zoo.utils.AnimalApiInterface;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dejan Trajkovic on 9/13/2016.
 */
public class ExhibitsListFragment extends ListFragment {

    private ExhibitsAdapter mAdapter;

    public static ExhibitsListFragment getInstance() {

        ExhibitsListFragment fragment;
        fragment = new ExhibitsListFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListShown(false);
        mAdapter = new ExhibitsAdapter(getActivity(), 0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.exhibits_feed))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AnimalApiInterface animalApiInterface =
                retrofit.create(AnimalApiInterface.class);

        Call<List<Animal>> streams = animalApiInterface.getStreams();
        streams.enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                if (response.isSuccessful()) {
                    for (Animal animal : response.body()) {
                        mAdapter.add(animal);
                        // Log.e("Zoo", animal.getName());
                        // Log.e("Zoo", animal.getThumbnail());
                    }
                    mAdapter.notifyDataSetChanged();
                    setListAdapter(mAdapter);
                    setListShown(true);
                } else {
                    Log.e("Zoo", "Retrofit response not successful: " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Animal>> call, Throwable t) {
                Log.e("Zoo", "Retrofit error" + t.getMessage());
            }
        });
    }
}
