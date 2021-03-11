package com.example.simpleviewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    MutableLiveData<String> data = new MutableLiveData<>();
    public MutableLiveData<String> getData() {
        return data;
    }
    public void setData(String data) {
        this.data.setValue(data);
        Log.d("TAG", String.valueOf(this.data.getValue()));
    }

    //    String data = "Text"; // initialization
//    public String getData() {
//        return data;
//    }
//    public void setData(String data) {
//        this.data = data;
//    }
}
