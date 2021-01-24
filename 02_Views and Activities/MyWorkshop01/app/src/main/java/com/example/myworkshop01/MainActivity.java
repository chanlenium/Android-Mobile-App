package com.example.myworkshop01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // variable initialization
    private TextView userInfoTv;
    private EditText firstNameEt, lastNameEt;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Refer to Layout

        initUI();   // Call initialization function
    }

    // UI View initialization
    private void initUI() {
        userInfoTv = findViewById(R.id.userInfoTv);
        userInfoTv.setText(getString(R.string.hi_1));   // set string value("Hi!") defined in strings.xml as initial value

        firstNameEt = findViewById(R.id.firstNameEt);
        lastNameEt = findViewById(R.id.lastNameEt);

        submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {// To specify an action when the button is pressed, set a click listener on the button object
            public void onClick(View v) {
                hideKeyboard(); // call the function to hide a keyboard

                // display user information when the user input is validated
                if (validateInput(firstNameEt.getText().toString(), lastNameEt.getText().toString())) { // pass the value as primitive type
                    userInfoTv.setText(getFormattedMessage(firstNameEt.getText().toString(), lastNameEt.getText().toString()));

                    // print output in Logcat window (debug message)
                    Log.d("USER_INFO", "First name is "+ firstNameEt.getText().toString() + ", Last Name is " + lastNameEt.getText().toString());
                }else{
                    userInfoTv.setText(getString(R.string.hi_there));
                }
            }
        });
    }

    // Gets the Message formatted as a String.
    // Each Message implementation determines the appropriate way to format the data encapsulated in the Message.
    // Messages that provide more than one way of formatting the Message will implement MultiformatMessage.
    private String getFormattedMessage(String fName, String lName) {
        return String.format(getString(R.string.hi_message_format), fName, lName);
    }

    public void hideKeyboard() {    // keyboard is hidden once clicking a button
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private boolean validateInput(String fName, String lName) {
        // Input is Invalid only when both first and last name are empty
        if (TextUtils.isEmpty(fName) && TextUtils.isEmpty(lName)) {
            Toast.makeText(this, getString(R.string.empty_text_message), Toast.LENGTH_SHORT).show();
        }
        return !(TextUtils.isEmpty(fName) && TextUtils.isEmpty(lName));
    }

}