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
	
	public StoreFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_store, container, false);
         
        initMap();

        return rootView;
    }

    public  void initMap(){
        int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
        if (i != 0){
            GooglePlayServicesUtil.getErrorDialog(i, getActivity(), 10).show();
            return;
        }

        final LatLng HAMBURG = new LatLng(53.558, 9.927);
        final LatLng KIEL = new LatLng(53.551, 9.993);
        GoogleMap map;
        map = ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map)).getMap();
//        map = getMapFragment().getMap();

        Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG)
                .title("Hamburg"));
        Marker kiel = map.addMarker(new MarkerOptions()
                .position(KIEL)
                .title("Kiel")
                .snippet("Kiel is cool")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_launcher)));

        // Move the camera instantly to hamburg with a zoom of 15.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
    }
}
