package com.example.weatherapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WeatherViewModel extends ViewModel {
    public MutableLiveData<WeatherModel> weatherViewModel = new MutableLiveData<>();   // reference to LiveData

    public void setWeatherViewModel(WeatherModel input) {
        this.weatherViewModel.setValue(input);
    }

    public LiveData<WeatherModel> getWeather(){
        return weatherViewModel;
    }
}
