package com.example.lab03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // 1-1. declare fields
    RecyclerView recyclerView;
    DishAdapter dishAdapter;
    List<Dish> dishList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init(); // 1-2. declare fields

        // 7-1. define Layout Manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 7-2. attach adapter to recyclerView
        recyclerView.setAdapter(dishAdapter);
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        favoriteDishInit(); // initialize class Dish
        dishAdapter = new DishAdapter(MainActivity.this, dishList);
    }

    private void favoriteDishInit() {
        dishList = new ArrayList<Dish>(){
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
    }
}