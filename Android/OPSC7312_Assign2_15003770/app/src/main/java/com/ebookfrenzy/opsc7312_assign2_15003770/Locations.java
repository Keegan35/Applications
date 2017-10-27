//15003770
//Keegan Scannell
package com.ebookfrenzy.opsc7312_assign2_15003770;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Locations extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //Markers for the locations of the company
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-29.8587, 31.0218), 12));

        mMap.addMarker(new MarkerOptions().position(new LatLng(-29.848027, 30.936383)).title("Pavilion"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(-29.7260, 31.0658)).title("Gateway Theatre of Shopping"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(-30.0340, 30.8989)).title("Galleria"));
    }
}
