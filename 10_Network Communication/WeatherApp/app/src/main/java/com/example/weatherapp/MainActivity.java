package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText cityNameEt;    // reference to EditText
    private FragmentManager fragmentManager;    // reference to FragmentManager
    private FragmentTransaction fragmentTransaction;    // reference to FragmentTransaction
    private WeatherModel weatherModel;  // reference to Weather Model
    final String TAG = "MainActivity";
    private WeatherViewModel weatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class); // creating viewModel object
        initUi();
        fragmentManager = getSupportFragmentManager();  // Activity can get reference to fragment using FragmentManager
        // To make fragment transactions in the activity(add, remove, or replace), use APIs from FragmentTransaction
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    private void initUi() {
        cityNameEt = findViewById(R.id.cityNameEt);
        weatherViewModel.getWeather().observe(this, currentValue -> {
            cityNameEt.setText(currentValue.getCityName());
        });
        //cityNameEt.setText("");   // When not using viewModel
    }

    public void findWeather(View view) {
        String city = cityNameEt.getText().toString();
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=f750ec08d2971618228ed8326824270a";

        if(cityNameEt.getText().toString().isEmpty()){  // check input is empty or not
            Toast.makeText(this, "Enter a city name", Toast.LENGTH_SHORT).show();
        }else{
            if(isConnnected()){ // check network is connected or not
                //Toast.makeText(this, " connected", Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String data = downloadData(url);    // download data from url
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    JSONObject response = new JSONObject(data); // convert String to JSON object
                                    Log.d(TAG, "City name : " + cityNameEt.getText().toString().trim());
                                    Log.d(TAG, String.valueOf((int)Float.parseFloat(response.getJSONObject("main").getString("temp"))));
                                    Log.d(TAG, "WeatherDescription : " + ((JSONObject) response.getJSONArray("weather").get(0)).getString("main"));
                                    Log.d(TAG, "Weather Icon : " + ((JSONObject) response.getJSONArray("weather").get(0)).getString("icon"));
                                    Log.d(TAG, "Latitude : " + response.getJSONObject("coord").getString("lat"));
                                    Log.d(TAG, "Longitude : " + response.getJSONObject("coord").getString("lon"));

                                    // Change Kelvin into Celsius
                                    int temperatureCelcius = (int) (Float.parseFloat(response.getJSONObject("main").getString("temp")) -273.15 );
                                    weatherModel = new WeatherModel(
                                            cityNameEt.getText().toString().trim(),
                                            ((JSONObject) response.getJSONArray("weather").get(0)).getString("icon"),
                                            ((JSONObject) response.getJSONArray("weather").get(0)).getString("main"),
                                            temperatureCelcius,
                                            Float.valueOf(response.getJSONObject("coord").getString("lat")),
                                            Float.valueOf(response.getJSONObject("coord").getString("lon")));

                                    weatherViewModel.setWeatherViewModel(weatherModel);

                                    if(fragmentTransaction.isEmpty()){
                                        fragmentTransaction.add(R.id.weatherDetail, new WeatherResponseFragment(weatherViewModel)).commit();
                                        // When not using ViewModel
                                        //fragmentTransaction.add(R.id.weatherDetail, WeatherResponseFragment.newInstance(weatherModel)).commit();
                                    }else{
                                        fragmentTransaction = fragmentManager.beginTransaction();
                                        fragmentTransaction.add(R.id.weatherDetail, new WeatherResponseFragment(weatherViewModel)).commit();
                                        // When not using ViewModel
                                        //fragmentTransaction.replace(R.id.weatherDetail, WeatherResponseFragment.newInstance(weatherModel)).commit();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(MainActivity.this, " Input is invalid", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "run: ERROR");
                                }
                            }
                        });
                    }
                }).start();
            }else{
                Toast.makeText(this, " not connected", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String downloadData(String url) {
        InputStream inputStream = null;
        String data = "";
        try {
            // Getting good connection and downloading data (Must be done in a background)
            URL myUrl = new URL(url);   // convert url string to URL object
            HttpURLConnection httpURLConnection = (HttpURLConnection) myUrl.openConnection();
            httpURLConnection.setRequestMethod("GET");    // to retrieve information from server
            httpURLConnection.connect();
            int connectionResponseCode = httpURLConnection.getResponseCode();   // 200: OK, 404 or 500: Error
            Log.d(TAG, "downloadData: response code = " + connectionResponseCode);
            inputStream = httpURLConnection.getInputStream();
            data = processResponse(inputStream); // retrieved data from server
        } catch (MalformedURLException e) {
            Log.d(TAG, "downloadData: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.d(TAG, "downloadData: " + e.getMessage());
            e.printStackTrace();
        }
        return data;
    }

    private String processResponse(InputStream inputStream) throws IOException{
        // may throw Exception => anyone using this method will handle the Exception
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while((line = bufferedReader.readLine()) != null){
            Log.d(TAG, "processResponse: Line = " + line);
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    // Check if the device is connected
    private boolean isConnnected() {
        boolean connectionResult;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            connectionResult = true;
        }else{
            connectionResult = false;
        }
        return connectionResult;
    }
}