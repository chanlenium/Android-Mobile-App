package com.example.lab03;

import androidx.annotation.NonNull;

public class Dish {
    String dishName;
    int dishImage;
    float dishRating;

    public Dish(String dishName, int dishImage, float dishRating) {
        this.dishName = dishName;
        this.dishImage = dishImage;
        this.dishRating = dishRating;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getDishImage() {
        return dishImage;
    }

    public void setDishImage(int dishImage) {
        this.dishImage = dishImage;
    }

    public float getDishRating() {
        return dishRating;
    }

    public void setDishRating(float dishRating) {
        this.dishRating = dishRating;
    }

    @NonNull
    @Override
    public String toString() {
        return (getDishName() + " : " + getDishRating());
    }
}
