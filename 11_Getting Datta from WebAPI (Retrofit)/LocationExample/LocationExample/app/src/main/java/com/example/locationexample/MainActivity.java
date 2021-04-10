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


    LocationManager manager;
    TextView tv;
   final int Request_Code = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView2);
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    public void getLocation(View view) {

        Criteria criteria = new Criteria();
        String provider = manager.getBestProvider(criteria, false);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

           // Toast.makeText(this, " Permission is not granted" , Toast.LENGTH_LONG).show();

              requestPermission();

        }else {
            Location location = manager.getLastKnownLocation(provider);

            double lat = location.getLatitude();
            double lng = location.getLongitude();


            tv.setText(" Location  Lat"+ lat + " lng "+lng);
        }

    }



    void requestPermission(){


        if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)){
             new AlertDialog.Builder(MainActivity.this)
                     .setMessage(" The App need this permission in oder ...")
                     .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                             // ask for permission
                             ActivityCompat.requestPermissions(MainActivity.this,

                                     new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, Request_Code);
                         }
                     })
                     .setNegativeButton(" Cancel" , null)
                     .create()
                     .show();


        }else {

            ActivityCompat.requestPermissions(this,

                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, Request_Code);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch ( requestCode) {

            case Request_Code :
                if( grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, " Permission granted" , Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, " Permission not granted" , Toast.LENGTH_SHORT).show();
                }

        }
    }
}