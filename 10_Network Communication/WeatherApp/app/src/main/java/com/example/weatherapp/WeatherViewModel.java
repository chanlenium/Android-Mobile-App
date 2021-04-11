package com.example.weatherapp;

import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WeatherViewModel extends ViewModel {

    public MutableLiveData<WeatherModel> _weatherData = new MutableLiveData<>();   // reference to LiveData
    public LiveData<WeatherModel> weatherData = _weatherData; // UI를 업데이트 하고 싶으면 liveData를 UI에서 (Activity나 fragment) observe하고 viewModel에서는 미리 선언해 놓은 MutableLiveData의 data를 변경하면 됨

    public WeatherApiRepository weatherApiRepository = new WeatherApiRepository(); // weather data를 서버에서 가저 오는 로직은 여기 들어 있음

    public void getWeatherByCity(String city, Activity activity) {
        new Thread(new Runnable() { // background working
            @Override
            public void run() {

                WeatherModel data = weatherApiRepository.getWeatherByCity(city);

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _weatherData.setValue(data); // UI를 업데이트 하고 싶으면 liveData를 UI에서 (Activity나 fragment) observe하고 viewModel에서는 미리 선언해 놓은 MutableLiveData의 data를 변경하면 됨
                    }
                } );
            }
        }).start();
    }

}
