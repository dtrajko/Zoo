package com.dtrajko.zoo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.dtrajko.zoo.R;
import com.dtrajko.zoo.models.GalleryImage;
import com.dtrajko.zoo.models.Pin;
import com.dtrajko.zoo.utils.PinsApiInterface;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dejan Trajkovic on 9/13/2016.
 */
public class ZooMapFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap map;

    public static ZooMapFragment getInstance() {
        ZooMapFragment fragment;
        fragment = new ZooMapFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.map = googleMap;

        setUpMap();
    }

    public void setUpMap(){

        LatLng mLatLng = new LatLng(44.825656,20.4540042);

        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.setTrafficEnabled(true);
        // map.setMyLocationEnabled(true);
        CameraPosition position = CameraPosition.builder()
                .target(mLatLng)
                .zoom(16f)
                .bearing(0.0f)
                .tilt(0.0f)
                .build();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(position), null);
        map.setMapType( GoogleMap.MAP_TYPE_HYBRID);
        map.getUiSettings().setZoomControlsEnabled(true);

        MarkerOptions options = new MarkerOptions().position(mLatLng);
        options.title(getString(R.string.app_name));
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        map.addMarker(options);

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                return true;
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.pins_feed))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PinsApiInterface pinsApiInterface = retrofit.create(PinsApiInterface.class);

        Call<List<Pin>> streams = pinsApiInterface.getStreams();
        streams.enqueue(new Callback<List<Pin>>() {
            @Override
            public void onResponse(Call<List<Pin>> call, Response<List<Pin>> response) {
                if (response.body() == null || response.body().isEmpty() || !isAdded()) {
                    Log.e("Zoo", "Retrofit response not successful: " + response.toString());
                    return;
                }
                for (Pin pin : response.body()) {
                    MarkerOptions options = new MarkerOptions().position(new LatLng(pin.getLatitude(), pin.getLongitude()));
                    options.title(pin.getName());
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    map.addMarker(options);
                }
            }

            @Override
            public void onFailure(Call<List<Pin>> call, Throwable t) {
                Log.e("Zoo", "Retrofit error" + t.getMessage());
            }
        });
    }
}
