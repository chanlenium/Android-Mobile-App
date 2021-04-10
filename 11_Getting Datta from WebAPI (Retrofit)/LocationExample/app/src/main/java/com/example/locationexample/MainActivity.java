package com.example.locationexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Step2. Create an object of location manager
    LocationManager manager;
    TextView tv;
    final int REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Step3. initialize objects
        tv = findViewById(R.id.textView2);
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    public void getUserLocation(View view) {
        // Location provider
        //Criteria criteria = new Criteria();
        //String provider = manager.getBestProvider(criteria, false);
        String provider = LocationManager.NETWORK_PROVIDER;

        // check a permission is granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // permission is not granted
            //Toast.makeText(this, "Permission is not granted", Toast.LENGTH_SHORT).show();
            requestPermission();
        }else{
            Location location = manager.getLastKnownLocation(provider);    // return location object
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            tv.setText("Latitude: " + lat + ", Longitude: " + lng);

        }

    }

    public void requestPermission(){
        if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)){
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage("The App needs this permission in order to provide a service")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // ask for permission
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                                    REQUEST_CODE);
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .create()
                    .show();

        }else{
            /**
             * @param2 : list of permission that we want
             */
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE);
        }
    }

    // callback function for user response (answer "deny" or "allow")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission is granted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Permission is not granted", Toast.LENGTH_SHORT).show();
                }

        }
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}