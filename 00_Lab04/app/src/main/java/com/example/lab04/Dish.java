package com.example.lab04;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Dish implements Parcelable {
    String dishName;    // variable for dish name
    int dishImage;  // variable for dish image
    float dishRating;   // variable for dish rating

    // full constructor
    public Dish(String dishName, int dishImage, float dishRating) {
        this.dishName = dishName;
        this.dishImage = dishImage;
        this.dishRating = dishRating;
    }

    protected Dish(Parcel in) {
        dishName = in.readString();
        dishImage = in.readInt();
        dishRating = in.readFloat();
    }

    public static final Creator<Dish> CREATOR = new Creator<Dish>() {
        @Override
        public Dish createFromParcel(Parcel in) {
            return new Dish(in);
        }

        @Override
        public Dish[] newArray(int size) {
            return new Dish[size];
        }
    };

    public String getDishName() {
        return dishName;
    }

    public int getDishImage() {
        return dishImage;
    }

    public float getDishRating() {
        return dishRating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dishName);
        dest.writeInt(dishImage);
        dest.writeFloat(dishRating);
    }
}
