package com.example.week01_buttonclickdisplay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tvDisplay;     // variable for textDisplay
    private EditText etName;        // variable for Name info
    private EditText etPassword;    // variable for Password info

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // after viewing, find elements using id(findViewById)
        tvDisplay = findViewById(R.id.textView);   // find element in res>layout>activity_main with id "textView"
        etName = findViewById(R.id.EtName);
        etPassword = findViewById(R.id.EtPassword);

        tvDisplay.setText("Hello");                 // initialize(setText) textDisplay
    }

    public void displayText(View view) {
        // When the user enters both userName and Password
        if(etName.length() != 0 && etPassword.length() != 0){
            // display user Info when the user enters his/her information
            tvDisplay.setText("Hi, " + etName.getText().toString() + "!!");

            // output to Logcat window (debug message)
            Log.d("User Information", "UserName is "+ etName.getText().toString()
                    + ", Password is " + etPassword.getText().toString());

            // output to Toast message
            Toast toast = Toast.makeText(this, "UserName is "+ etName.getText().toString()
                    + ", Password is " + etPassword.getText().toString(), Toast.LENGTH_SHORT);
            //toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.START, 90, 0); // set the message position
            toast.show();
        }else {
            if(etName.length() != 0) {  // When the user only enters userName
                Toast.makeText(this, "Enter your Password", Toast.LENGTH_SHORT).show();
            }else if(etPassword.length() != 0) {    // When the user only enters Password
                Toast.makeText(this, "Enter your Name", Toast.LENGTH_SHORT).show();
            }else { // When the user do not enter either userName and Password
                Toast.makeText(this, "Enter your Name & Password", Toast.LENGTH_SHORT).show();
            }
        }
    }
}