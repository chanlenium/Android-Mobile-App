package com.example.threadexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void taskA(View view) {  // Main Thread
        //action();   // Until this function finishes, the app does not response to any input
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask);
        thread.start(); // start thread which run task that you want in parallel

        // Using anonymous class
        new Thread(new Runnable() {
            @Override
            public void run() {
                // task run behind background
            }
        }).start();
    }

    private void action() {
        try {
            Thread.sleep(7000); // hold other work until time expired
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Implementation way 1
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() { // Execute this part in a Main Thread
//                Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
//            }
//        });

        // Implementation way 2: using handler
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class MyTask implements Runnable{
        @Override
        public void run() {
            action(); // task run behind a background
        }
    }
}