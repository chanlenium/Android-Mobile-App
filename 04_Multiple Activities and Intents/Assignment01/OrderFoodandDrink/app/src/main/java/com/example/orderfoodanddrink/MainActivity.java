package com.example.orderfoodanddrink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button clickHeretoStartBtn = findViewById(R.id.clickHeretoStartBtn);
        clickHeretoStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectFoodActivity();   // call function to open selectFoodActivity
            }
        });
    }

    private void openSelectFoodActivity() {
        // Generate explicit Intent
        Intent intent = new Intent(this, selectFoodActivity.class);
        startActivity(intent);  // launch an Activity
    }
}