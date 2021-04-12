package com.example.weatherapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class WeatherResponseFragment extends Fragment {
//    //When not using ViewModel
//    private static final String ARG_PARAM = "param";
//    private WeatherModel weatherModel;

    private WeatherModel weatherModel;
    private TextView cityNameTv, temperatureTv, weatherDescriptionTv, latitudeTv, longitudeTv;
    private ImageView weatherIv;

    public WeatherResponseFragment() {
    }

    public WeatherResponseFragment(WeatherModel weatherModel) {
        this.weatherModel = weatherModel;
    }

//    /** When not using ViewModel
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     * @param weatherModel Parameter 1.
//     * @return A new instance of fragment WeatherResponseFragment.
//     */
//    public static WeatherResponseFragment newInstance(WeatherModel weatherModel) {
//        this.weatherViewModel = weatherModel;
//        WeatherResponseFragment fragment = new WeatherResponseFragment(weatherViewModel);
//        Bundle args = new Bundle();
//        args.putParcelable(ARG_PARAM, weatherModel);
//        fragment.setArguments(args);
//        return fragment;
//    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // When not using ViewModel
//        if (getArguments() != null) {
//            //weatherModel = getArguments().getParcelable(ARG_PARAM);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_response, container, false);
        // associate fields in this class with components of view(fragment_weather_response.xml)
        cityNameTv = view.findViewById(R.id.cityNameTv);
        temperatureTv = view.findViewById(R.id.temperatureTv);
        weatherDescriptionTv = view.findViewById(R.id.weatherDescriptionTv);
        latitudeTv = view.findViewById(R.id.latitudeTv);
        longitudeTv = view.findViewById(R.id.longitudeTv);
        weatherIv = view.findViewById(R.id.weatherIv);

        // set values using ViewModel
        cityNameTv.setText("The weather of city " + weatherModel.getCityName().toUpperCase());
        temperatureTv.setText(weatherModel.getWeatherTemperature() + " \u2103");
        weatherDescriptionTv.setText(weatherModel.getWeatherDescription());
        latitudeTv.setText(weatherModel.getLatitude() + "\u00B0");
        longitudeTv.setText(weatherModel.getLongitude() + "\u00B0");
        Glide.with(this)
                .load("https://openweathermap.org/img/w/" + weatherModel.getWeatherIcon() + ".png")
                .override(300, 300)
                .into(weatherIv);


        // set values when not using ViewModel
//        cityNameTv.setText("The weather of city " + weatherModel.cityName.toUpperCase());
//        temperatureTv.setText(String.valueOf(weatherModel.weatherTemperature) + " \u2103");
//        weatherDescriptionTv.setText(weatherModel.weatherDescription);
//        latitudeTv.setText(weatherModel.latitude.toString() + "\u00B0");
//        longitudeTv.setText(weatherModel.longitude.toString() + "\u00B0");
//        Glide.with(this)
//                .load("https://openweathermap.org/img/w/" + weatherModel.weatherIcon + ".png")
//                .override(300, 300)
//                .into(weatherIv);

        return view;
    }
}