package com.example.networkingexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView3);
    }

    public void getData(View view) {
        //String url = "https://www.google.com";
        String url = "https://jsonplaceholder.typicode.com/todos";  // JSON object
        if(isConnnected()){
            Toast.makeText(this, " connected", Toast.LENGTH_SHORT).show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String data = downloadData(url);  // should be invoked in background
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText(data);
                        }
                    });
                    // tv.setText(data);   // cannot modify UI element from background => cause crash!
                }
            }).start();
        }else{
            Toast.makeText(this, " not connected", Toast.LENGTH_SHORT).show();
        }
    }

    // Check if the device is connected
    private boolean isConnnected(){
        boolean res = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            res = true;
        }else{
            res = false;
        }
        return res;
    }

    private String downloadData(String url){
        InputStream is = null;
        String data = "";
        try {
            // Getting good connection and downloading data
            // Must be done in a background
            URL myUrl = new URL(url);   // convert url string to URL object
            HttpURLConnection con = (HttpURLConnection) myUrl.openConnection();
            con.setRequestMethod("GET");    // to retrieve information from server
            con.connect();
            int response = con.getResponseCode();   // 200: OK, 404 or 500: Error
            Log.d(TAG, "downloadData: response code = " + response);
            is = con.getInputStream();
            data = processResponse(is); // retrieved data from server
        } catch (MalformedURLException e) {
            Log.d(TAG, "downloadData: " + e.getMessage());
            //e.printStackTrace();
        } catch (IOException e) {
            Log.d(TAG, "downloadData: " + e.getMessage());
            //e.printStackTrace();
        }
        return data;
    }

    private String processResponse(InputStream is) throws IOException {
        // may throw Exception
        // anyone using this method will handle the Exception
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while((line = bufferedReader.readLine()) != null){
            Log.d(TAG, "processResponse: Line = " + line);
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}