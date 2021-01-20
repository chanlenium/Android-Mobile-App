package com.example.applifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    String TAG = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Called when the activity is first created.
        // This is where you should do all initialization: create views, bind data to lists, etc.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, " onCreate Stage");
    }

    @Override
    protected void onStart() {
        // Called when the activity is becoming "visible" to the user.
        super.onStart();
        Log.d(TAG, " onStart Stage");
    }

    @Override
    protected void onResume() {
        // Called when the activity will start "interacting" with the user.
        // At this point your activity is at the "top of its activity" stack with user input going to it .
        // Always followed by onPause().
        super.onResume();
        Log.d(TAG, " onResume Stage");
    }

    @Override
    protected void onPause() {
        // Called when the activity loses "foreground state"
        // No longer focusable or be fore transition to stopped/hidden or destroyed state
        // Ex. When the Bluetooth or Sensor ON/OFF
        super.onPause();
        Log.d(TAG, " onPause Stage");
    }

    @Override
    protected void onStop() {
        // Called when the activity is "no longer visible" to the user.
        super.onStop();
        Log.d(TAG, " onStop Stage");
    }

    @Override
    protected void onRestart() {
        // Called after your activity has been stopped, prior to it being "started again".
        // Always followed by onStar t()
        super.onRestart();
        Log.d(TAG, " onRestart Stage");
    }

    @Override
    protected void onDestroy() {
        // called before your activity is destroyed.
        // This can happen either because the activity is "finishing" or
        // because the system is temporarily destroying this instance of the activity "to save space".
        // Invoked only once during the entire activity lifecycle.
        super.onDestroy();
        Log.d(TAG, " onDestroy Stage");
    }
}