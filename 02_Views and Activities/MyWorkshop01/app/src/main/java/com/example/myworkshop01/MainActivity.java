package com.example.myworkshop01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // variable initialization
    private TextView userInformation;
    private EditText firstName, lastName;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Refer to Layout
        userInformation = findViewById(R.id.userInfoTv);
        userInformation.setText("Hi!");
        firstName = findViewById(R.id.fisrtNameEt);
        lastName = findViewById(R.id.lastNameEt);
        submit = findViewById(R.id.submitBtn);
        //final Button button = findViewById(R.id.submitBtn);

        // To specify an action when the button is pressed, set a click listener on the button object
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // display user Info when the user input is validated
                if(validateInput(firstName, lastName)){
                    userInformation.setText("Hi, " + firstName.getText().toString() + " " + lastName.getText().toString() + "!");
                    // print output in Logcat window (debug message)
                    Log.d("USER_INFO", "First name is "+ firstName.getText().toString() + ", Last Name is " + lastName.getText().toString());
                }else{
                    userInformation.setText("Hi, there !");
                }
            }
        });
    }

    private boolean validateInput(EditText fName, EditText lName){
        // Input is Invalid only when both first and last name are empty
        if(isEmpty(fName.getText().toString()) && isEmpty(lName.getText().toString()))
            Toast.makeText(this, "Both first and last name are empty", Toast.LENGTH_SHORT).show();
        return !(isEmpty(fName.getText().toString()) && isEmpty(lName.getText().toString()));
    }

    private boolean isEmpty(String field) {
        return (field.trim().length() == 0) ? true : false;
    }
}