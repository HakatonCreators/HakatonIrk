package com.example.geolocationbasis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button showMapButton;
    TextView latText, lonText, timeText;

    LocationManager locationManager;
    Location location;

    private boolean granted = false;
    private final int LOCATION_PERMISSION = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showMapButton = findViewById(R.id.toMapButton);
        lonText = findViewById(R.id.lon);
        latText = findViewById(R.id.lat);
        timeText = findViewById(R.id.timeText);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        showMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                if (location != null) {
                    intent.putExtra("latitude", location.getLatitude());
                    intent.putExtra("longitude", location.getLongitude());
                    startActivity(intent);
                }
            }
        });
    }

    LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            if (location == null) {
                return;
            }
            else {
                showLocation(location);
            }
        }
    };

    private void showLocation(Location location) {
        String koord = String.valueOf(location.getLatitude());
        latText.setText(koord);
        koord = String.valueOf(location.getLongitude());
        lonText.setText(koord);
        koord = new Date(location.getTime()).toString();
        timeText.setText(koord);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (granted || checkPermission()) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 10, 30, listener);
            if (locationManager != null) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) {
                    showLocation(location);
                }
            }
        }
    }

    private boolean checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION);
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION) {
            granted = true;
            if (grantResults.length > 0) {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        granted = false;
                    }
                }
            }
            else {
                granted = false;
            }
        }
    }
}