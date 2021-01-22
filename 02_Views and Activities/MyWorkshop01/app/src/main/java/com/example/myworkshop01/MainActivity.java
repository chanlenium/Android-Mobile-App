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
    private EditText fisrtNameEt, lastNameEt;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Refer to Layout

        initUI();
    }

    // UI View initialization
    private void initUI() {
        userInfoTv = findViewById(R.id.userInfoTv);
        userInfoTv.setText(getString(R.string.hi_1));

        fisrtNameEt = findViewById(R.id.fisrtNameEt);
        lastNameEt = findViewById(R.id.lastNameEt);

        submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {// To specify an action when the button is pressed, set a click listener on the button object
            public void onClick(View v) {
                hideKeyboard();

                // display user Info when the user input is validated
                if (validateInput(fisrtNameEt.getText().toString(), lastNameEt.getText().toString())) {
                    userInfoTv.setText(getFormattedMessage(fisrtNameEt.getText().toString(), lastNameEt.getText().toString()));

                    // print output in Logcat window (debug message)
                    Log.d("USER_INFO", "First name is "+ fisrtNameEt.getText().toString() + ", Last Name is " + lastNameEt.getText().toString());
                }else{
                    userInfoTv.setText(getString(R.string.hi_there));
                }
            }
        });
    }

    private String getFormattedMessage(String fName, String lName) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append(getString(R.string.hi_2));
        messageBuilder.append(fName);
        messageBuilder.append(" ");
        messageBuilder.append(lName);

        return messageBuilder.toString();
    }

    public void hideKeyboard() {
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