package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.weatherapp.utils.NetworkUtils;


public class MainActivity extends AppCompatActivity {
    private EditText cityNameEt;    // reference to EditText
    private FragmentManager fragmentManager;    // reference to FragmentManager
    private FragmentTransaction fragmentTransaction;    // reference to FragmentTransaction
    final String TAG = "MainActivity";
    private WeatherViewModel weatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        weatherViewModel = new WeatherViewModelFactory().create(WeatherViewModel.class); // Factory를 사용해서 생성 하는 방법도 있음.

        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class); // creating viewModel object
        weatherViewModel.weatherData.observe(this, weatherData -> { // liveData를 UI에서 (Activity나 fragment) observe하고 data가 변경되거나 하면 아래 listener로 data가 전달됨
            if(fragmentTransaction.isEmpty()){
                fragmentTransaction.add(R.id.weatherDetail, new WeatherResponseFragment(weatherData)).commit();
                // When not using ViewModel
                //fragmentTransaction.add(R.id.weatherDetail, WeatherResponseFragment.newInstance(weatherModel)).commit();
            }else{
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.weatherDetail, new WeatherResponseFragment(weatherData)).commit();
                // When not using ViewModel
                //fragmentTransaction.replace(R.id.weatherDetail, WeatherResponseFragment.newInstance(weatherModel)).commit();
            }
        });

        initUi();   // initialize User Interface
        fragmentManager = getSupportFragmentManager();  // Activity can get reference to fragment using FragmentManager
        // To make fragment transactions in the activity(add, remove, or replace), use APIs from FragmentTransaction
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    private void initUi() {
        cityNameEt = findViewById(R.id.cityNameEt);
    }

    // click event
    public void findWeather(View view) {
        String city = cityNameEt.getText().toString();
        if (!TextUtils.isEmpty(city) && NetworkUtils.isConnnected(this)) {
            weatherViewModel.getWeatherByCity(city, this);
        }
    }

}