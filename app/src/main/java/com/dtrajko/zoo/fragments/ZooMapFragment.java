package com.dtrajko.zoo.fragments;

import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by Dejan Trajkovic on 9/13/2016.
 */
public class ZooMapFragment extends SupportMapFragment {

    public static ZooMapFragment getInstance() {
        ZooMapFragment fragment;
        fragment = new ZooMapFragment();

        return fragment;
    }
}
