package com.example.lab04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Activity can get reference to fragment using FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // To make fragment transactions in the activity (such as add, remove, or replace a fragment),
        // Use APIs from FragmentTransaction.
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // add(associate) Fragment 'DishListFragment' to the activity
        fragmentTransaction.add(R.id.DishListFragment, new DishListFragment()).commit();
    }
}