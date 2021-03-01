/** A ViewModel
 * It holds the app's UI data in a lifecycle-conscious way that survive configuration changes.
 * ViewModel can take care of holding and processing all the data needed for the UI.
 */

package com.example.lab04;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    // 'LiveData' is a lifecycle-aware observable data holder class.
    // (only updates app component observers that are in an active(STARTED or RESUMED) lifecycle state)
    // To update data stored within LiveData, must use 'MutableLiveData' using setValue(T) or postValue(T)
    public MutableLiveData<ArrayList<Dish>> liveArrayDish;

    // Usually, MutableLiveData is used within theViewModel,
    // then the ViewModel only exposes immutable LiveData objects to the observers.
    public LiveData<ArrayList<Dish>> getLiveArrayDish(){
        liveArrayDish = new MutableLiveData<>();
        ArrayList<Dish> dishList = new ArrayList<Dish>(){   // initialize arraylist
            {
                add(new Dish("Steak", R.mipmap.steak, (float) 4.5));
                add(new Dish("Hamburger", R.mipmap.hamburger, 4));
                add(new Dish("Sushi", R.mipmap.sushi, 3));
                add(new Dish("Salmon", R.mipmap.salmon, (float) 3.5));
                add(new Dish("Poutine", R.mipmap.poutine, 5));
                add(new Dish("Pasta", R.mipmap.pasta, 4));
                add(new Dish("Pizza", R.mipmap.pizza, 4));
                add(new Dish("Rice", R.mipmap.rice, 5));
            }
        };
        liveArrayDish.setValue(dishList);   // set MutableLiveData using dishList
        return liveArrayDish;
    }
}
