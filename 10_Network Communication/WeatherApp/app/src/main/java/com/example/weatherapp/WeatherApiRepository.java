package com.example.weatherapp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// 서버나 database에서 data를 받아오는 로직은 전부 Repository라는 class를 만들어서 처리 해야 함.
// ViewModel은 repository에서 받은 데이터(여기서는 return weatherModel)를 UI에서 사용하기 편하게 가공한후,
// (가공이 필요한 경우) livedata를 updata하는 방식으로 UI에 전달함.
public class WeatherApiRepository {
    final String TAG = "WeatherApiRepository";

    public WeatherApiRepository() {}

    public WeatherModel getWeatherByCity(String cityName) {
        // make URL and store it into variable
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=f750ec08d2971618228ed8326824270a";
        String data = downloadData(url);// download data from url
        try {
            JSONObject response = new JSONObject(data); // convert String to JSON object
            Log.d(TAG, "City name : " + cityName);
            Log.d(TAG, "Temperature : " + String.valueOf((int)Float.parseFloat(response.getJSONObject("main").getString("temp"))));
            Log.d(TAG, "WeatherDescription : " + ((JSONObject) response.getJSONArray("weather").get(0)).getString("main"));
            Log.d(TAG, "Weather Icon : " + ((JSONObject) response.getJSONArray("weather").get(0)).getString("icon"));
            Log.d(TAG, "Latitude : " + response.getJSONObject("coord").getString("lat"));
            Log.d(TAG, "Longitude : " + response.getJSONObject("coord").getString("lon"));

            // Change Kelvin into Celsius
            int temperatureCelsius = (int) (Float.parseFloat(response.getJSONObject("main").getString("temp")) -273.15 );
            WeatherModel weatherModel = new WeatherModel(
                        cityName,
                        ((JSONObject) response.getJSONArray("weather").get(0)).getString("icon"),
                        ((JSONObject) response.getJSONArray("weather").get(0)).getString("main"),
                        temperatureCelsius,
                        Float.valueOf(response.getJSONObject("coord").getString("lat")),
                        Float.valueOf(response.getJSONObject("coord").getString("lon")));
            return weatherModel;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, "run: ERROR");
        }
        return null;
    }


    private String downloadData(String url) {
        InputStream inputStream = null;
        String data = "";
        try {
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

}
