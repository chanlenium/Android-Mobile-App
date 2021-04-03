package com.example.weatherapp;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherModel implements Parcelable {
    String cityName;
    String weatherIcon;
    String weatherDescription;
    int weatherTemperature;
    Float latitude;
    Float longitude;

    public WeatherModel(String cityName, String weatherIcon, String weatherDescription, int weatherTemperature, Float latitude, Float longitude) {
        this.cityName = cityName;
        this.weatherIcon = weatherIcon;
        this.weatherDescription = weatherDescription;
        this.weatherTemperature = weatherTemperature;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected WeatherModel(Parcel in) {
        cityName = in.readString();
        weatherIcon = in.readString();
        weatherDescription = in.readString();
        weatherTemperature = in.readInt();
        if (in.readByte() == 0) {
            latitude = null;
        } else {
            latitude = in.readFloat();
        }
        if (in.readByte() == 0) {
            longitude = null;
        } else {
            longitude = in.readFloat();
        }
    }

    public static final Creator<WeatherModel> CREATOR = new Creator<WeatherModel>() {
        @Override
        public WeatherModel createFromParcel(Parcel in) {
            return new WeatherModel(in);
        }

        @Override
        public WeatherModel[] newArray(int size) {
            return new WeatherModel[size];
        }
    };

    public String getCityName() {
        return cityName;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public int getWeatherTemperature() {
        return weatherTemperature;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cityName);
        dest.writeString(weatherIcon);
        dest.writeString(weatherDescription);
        dest.writeInt(weatherTemperature);
        if (latitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(latitude);
        }
        if (longitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(longitude);
        }
    }
}
