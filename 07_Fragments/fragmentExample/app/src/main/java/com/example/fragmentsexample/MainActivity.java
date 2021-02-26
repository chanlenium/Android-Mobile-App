package com.example.fragmentsexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MyListener{
    TextView activityTv;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(MainActivity.this);
        viewPager.setAdapter(adapter);

        button = findViewById(R.id.sendMsgToFragBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        // Activity can get reference to fragment using FragmentManager
        FragmentManager manager = getSupportFragmentManager();
        // instance of current Fragment in this activity associated with R.id.fragment
        Fragment fragment = manager.findFragmentById(R.id.fragment);

        // Check which Fragment is attached
        if(fragment instanceof Fragment1){
            ((Fragment1) fragment).tv.setText("Message from Activity to Fragment1");
        }else{
            if(fragment instanceof Fragment2){
                ((Fragment2) fragment).displayMessage("Hi there");
            }
        }
    }

    // ADDING FRAGMENT
    int val = 0;
    public void replaceFragment(View view) {
        // retain an instance of class FragmentManager to manage fragments in activity
        FragmentManager manager = getSupportFragmentManager();
        // To make fragment transactions in an activity (such as add, remove, or replace a fragment),
        // you must use APIs from FragmentTransaction.
        FragmentTransaction transaction = manager.beginTransaction();

        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();

        if(val%2 == 0){
            transaction.add(R.id.fragment, fragment2);
            // The first argument passed to add() is the viewGroup id in which the
            // fragment should be placed, and the second parameter is the fragment to add.
            // transaction.addToBackStack(null);    // in order to add the transaction to a back stack of fragment transactions.
            // back키를 눌렀을 때 previous state로 감
        }else{
            transaction.add(R.id.fragment, fragment1);
        }
        val += 1;
        transaction.commit();   // actual transaction is done at this time (save all changes)
    }

    @Override
    public void sendMessageToActivity(String message) {
        activityTv = findViewById(R.id.activityTv);
        activityTv.setText(message);
    }
}