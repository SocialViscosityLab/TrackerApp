package com.example.dani.exampletracker;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    //Reference of the logic
    private Logic logic;
    //Marker of my position
    private MarkerOptions myMarker;
    //Marker of the goal position
    private MarkerOptions goalMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //Link the current reference of the logic
        logic =  Logic.getInstance();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(13.0f);
        mMap.setMaxZoomPreference(20.0f);

        // Add a marker in my location and move the camera
        LatLng my_location = new LatLng(logic.getMyLoc().getLatitude(),logic.getMyLoc().getLongitude());
        myMarker = new MarkerOptions().position(my_location).title("Here I am");
        myMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.my_location));

        mMap.addMarker(myMarker);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(my_location));


        // Add a marker in the goal location and move the camera
        LatLng the_goal_location = new LatLng(logic.getGoalLoc().getLatitude(),logic.getGoalLoc().getLongitude());
        goalMarker = new MarkerOptions().position(the_goal_location).title("Here is where I should be");
        goalMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.goal_location));
        mMap.addMarker(goalMarker);
    }
}
