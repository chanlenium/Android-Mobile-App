package com.example.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    /** LIVEDATA Class **/
    // LiveData is an observable data holder class which is lifecycle aware
    // It respects the lifecycle of other app components such as Activities, Fragments, and Services
    // This awareness ensures LiveData only updates app component observers in an active lifecycle state
    // if its lifecycle is in the STARTED or RESUMED state.
    // LiveData only notifies active observers about updates.
    // => Can reduce system burden and avoid memory leak.

    /** LiveData 객체 만들기 **/
    // To update data stored within LiveData, must use MutableLiveData instead of LiveData
    // Create a LiveData with an Integer
    private MutableLiveData<Integer> currentNumber;
    public LiveData<Integer> getNumber(){
        if(currentNumber == null){
            currentNumber = new MutableLiveData<Integer>(0);
        }
        return currentNumber;
    }

    public void incrementNumber(){
        Integer v = currentNumber.getValue() + 1;
        // The MutableLiveData class has two public methods that allow to set the value of
        // a LiveData object, setValue(T) and postValue(T).
        currentNumber.setValue(v);
    }
}
