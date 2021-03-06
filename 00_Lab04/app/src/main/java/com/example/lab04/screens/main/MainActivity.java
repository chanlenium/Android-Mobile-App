package com.example.lab04.screens.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.lab04.R;
import com.example.lab04.screens.dishlist.DishListFragment;

public class MainActivity extends AppCompatActivity {
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