package com.example.geolocationbasis;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    Intent intent;

    private GoogleMap mMap;
    LatLng myPlace;
    LatLng BasketBall = new LatLng(52.260361, 104.262611);
    LatLng Bunker = new LatLng(52.272194, 104.260806);
    LatLng Island = new LatLng(52.269677, 104.283267);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        double lat = getIntent().getDoubleExtra("latitude", 52.27537),
                lon = getIntent().getDoubleExtra("longitude", 104.2774);
        myPlace = new LatLng(lat, lon);
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
        mMap.addMarker(new MarkerOptions().position(myPlace).title("Я здесь"));
        mMap.addMarker(new MarkerOptions().position(BasketBall).title("Лёгкая прогулка"));
        mMap.addMarker(new MarkerOptions().position(Bunker).title("Заброшенный бункер"));
        mMap.addMarker(new MarkerOptions().position(Island).title("Остров \"Юность\""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myPlace));
        CameraPosition cameraPosition = new CameraPosition(myPlace, 23, 45, 15);
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                intent = new Intent(MapsActivity.this, Tasks.class);

                startActivity(intent);
            }
        });
//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
//                mMap.addMarker(new MarkerOptions().position(latLng).title("Хочу сюда"));
//            }
//        });
    }
}