package com.example.lab6.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.lab6.Model.Customer;
import com.example.lab6.R;

import java.util.List;

public class FeedbackActivity extends AppCompatActivity {
    private Intent intent;  // reference to intent
    private List<Customer> customerList;    // reference to List<Customer>
    private FragmentManager fragmentManager;    // reference to FragmentManager
    private FragmentTransaction fragmentTransaction;    // reference to FragmentTransaction

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        intent = getIntent();
        if(intent != null) { // get data from caller
            customerList = intent.getParcelableArrayListExtra("FeedbackPage");
        }

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.feedbackFragment,
                CustomerListViewFragment.newInstance(customerList)).commit();
    }
}