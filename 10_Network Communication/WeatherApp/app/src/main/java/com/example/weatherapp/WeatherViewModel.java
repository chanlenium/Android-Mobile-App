package com.example.weatherapp;

import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WeatherViewModel extends ViewModel {

    public MutableLiveData<WeatherModel> _weatherData = new MutableLiveData<>();   // reference to LiveData
    public LiveData<WeatherModel> weatherData = _weatherData;

    public WeatherApiRepository weatherApiRepository = new WeatherApiRepository(); // weather data를 서버에서 가저 오는 로직은 여기 들어 있음

    public void getWeatherByCity(String city, Activity activity) {
        new Thread(new Runnable() { // background working
            @Override
            public void run() {
                WeatherModel data = weatherApiRepository.getWeatherByCity(city);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _weatherData.setValue(data);
                    }
                } );
            }
        }).start();
    }

}
