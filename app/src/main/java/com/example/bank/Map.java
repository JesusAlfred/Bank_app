package com.example.bank;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker1"));
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(1, 0))
                .title("Marker2"));
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(2, 0))
                .title("Marker3"));
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(3, 0))
                .title("Marker4"));
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(4, 0))
                .title("Marker5"));
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(5, 0))
                .title("Marker6"));
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(6, 0))
                .title("Marker7"));
    }
}
