package com.example.intencje;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import  android.Manifest;
import android.os.Bundle;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    private  Button btnStart, btnStop;
    private TextView tvSzer,tvDlug;
    public LocationManager locManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        btnStart = (Button) findViewById(R.id.locStart);
        btnStop = (Button) findViewById(R.id.locStop);


        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                String szer = String.valueOf(location.getLatitude());
                String dlug = String.valueOf(location.getLongitude());

                tvSzer = (TextView) findViewById(R.id.tvSzer);
                tvSzer.setText(szer);

                tvDlug = (TextView) findViewById(R.id.tvDlug);
                tvDlug.setText(dlug);
            }
        };

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    ActivityResultLauncher<String[]> locationPermissionRequest = registerForActivityResult(new ActivityResultContracts
                                    .RequestMultiplePermissions(), result -> {
                                Boolean fineLocationGranted = result.getOrDefault(
                                        Manifest.permission.ACCESS_FINE_LOCATION, false);
                                Boolean coarseLocationGranted = result.getOrDefault(
                                        Manifest.permission.ACCESS_COARSE_LOCATION,false);
                                if (fineLocationGranted != null && fineLocationGranted) {
                                    return;
                                } else if (coarseLocationGranted != null && coarseLocationGranted) {
                                    return;
                                } else {
                                    return;
                                }
                            }
                    );

                    locationPermissionRequest.launch(new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    });
                }

                locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,(LocationListener) listener);
                btnStart.setEnabled(false);
                btnStop.setEnabled(true);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locManager.removeUpdates(listener);

                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
            }
        });
    }
}