package com.example.weatherapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {

    // Check if the device is connected
    public static boolean isConnnected(Context context) {
        boolean connectionResult;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            connectionResult = true;
        }else{
            connectionResult = false;
        }
        return connectionResult;
    }

}
