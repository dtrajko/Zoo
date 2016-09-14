package com.dtrajko.zoo.fragments;

import android.support.v4.app.Fragment;

/**
 * Created by Dejan Trajkovic on 9/13/2016.
 */
public class GalleryFragment extends Fragment {

    public static GalleryFragment getInstance() {
        GalleryFragment fragment;
        fragment = new GalleryFragment();

        return fragment;
    }
}
