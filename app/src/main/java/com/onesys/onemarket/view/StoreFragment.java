package com.onesys.onemarket.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.onesys.onemarket.R;
import com.onesys.onemarket.utils.BaseFragment;

public class StoreFragment extends BaseFragment {
    private static final String TAG = "OneMarket";

    public StoreFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_store, container, false);

        initMap();

        return rootView;
    }

    public void initMap() {
        int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
        if (i != 0) {
            GooglePlayServicesUtil.getErrorDialog(i, getActivity(), 10).show();
            return;
        }

        final LatLng HOCHIMINH = new LatLng(10.773425, 106.685299);
        final LatLng DGW = new LatLng(10.773403, 106.687247);
        GoogleMap map;
//        map = ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map)).getMap();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Log.d(TAG, "using getFragmentManager");
            map = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        } else {
            Log.d(TAG, "using getChildFragmentManager");
            map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
        }

        Marker hochiminh = map.addMarker(new MarkerOptions().position(HOCHIMINH)
                .title("Ho Chi Minh"));
        Marker dgw = map.addMarker(new MarkerOptions()
                .position(DGW)
                .title("DGW")
                .snippet("Digiworld")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_launcher)));

        // Move the camera instantly to hochiminh with a zoom of 15.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(DGW, 15));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(18), 2000, null);
    }
}
