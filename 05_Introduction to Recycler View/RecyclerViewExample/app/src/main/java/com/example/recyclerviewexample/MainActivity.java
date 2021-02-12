package com.example.recyclerviewexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Declaration of RecyclerView and RecyclerAdapter fields
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<Product> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        list = getData(); // object creation and initialization

        recyclerAdapter = new RecyclerAdapter(list);

        // define Layout Manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // attach adapter to recyclerView
        recyclerView.setAdapter(recyclerAdapter);
    }

    private List<Product> getData() {
        List<Product> products = new ArrayList<Product>();
        Product p1 = new Product("Android", R.mipmap.dell);
        products.add(p1);
        Product p2 = new Product("Dell", R.mipmap.dell);
        products.add(p2);
        return products;
    }
}