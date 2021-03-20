package com.example.sharedpreferencesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String FILE_NAME = "my_Prefs";
    EditText userNameEt, userAgeEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEt = findViewById(R.id.editTextTextPersonName);
        userAgeEt = findViewById(R.id.editTextNumber);
    }

    public void saveData(View view) {
        String name = userNameEt.getText().toString();
        String ageStr = userAgeEt.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit(); // retain object of type Editor

        editor.putString("username", name);
        editor.putInt("age", Integer.parseInt(ageStr));
        editor.commit();

        userNameEt.setText("");
        userAgeEt.setText("");
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
    }

    public void open(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}