package com.example.simpleviewmodel;

import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    String data = "Text"; // initialization

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
