package com.example.weatherapp;

import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WeatherViewModel extends ViewModel {
    // reference to LiveData
    public MutableLiveData<WeatherModel> _weatherData = new MutableLiveData<>();
    public LiveData<WeatherModel> weatherData = _weatherData;
    // UI를 업데이트 하고 싶으면 liveData를 UI에서(Activity or Fragment) observe하고,
    // viewModel에서는 미리 선언해 놓은 MutableLiveData의 data를 변경하면 됨

    // The logic getting weather data from server is included in 'WetherApiRepository' class
    public WeatherApiRepository weatherApiRepository = new WeatherApiRepository();

    public void getWeatherByCity(String city, Activity activity) {
        new Thread(new Runnable() { // background working
            @Override
            public void run() {
                WeatherModel data = weatherApiRepository.getWeatherByCity(city);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // If you like to update UI, observe LiveData in UI (i.e., Activity or Fragment)
                        // In viewModel, just change data of MutableLiveData which is predefined
                        _weatherData.setValue(data);
                    }
                } );
            }
        }).start();
    }
}
