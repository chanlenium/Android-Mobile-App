package com.example.sharedpreferencesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    final String FILE_NAME = "my_Prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void readData(View view) {
        SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        String name = preferences.getString("username", "no_name");
        int age = preferences.getInt("age", -1);
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }

    public void deleteData(View view) {
        SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        Toast.makeText(this, "Clear Done", Toast.LENGTH_SHORT).show();
    }
}