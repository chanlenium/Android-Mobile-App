package com.example.lab6.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.lab6.Model.Customer;
import com.example.lab6.R;

import java.util.List;

public class FeedbackActivity extends AppCompatActivity {
    Intent intent;
    List<Customer> customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        intent = getIntent();
        if(intent != null) { // get data from caller
            customerList = intent.getParcelableArrayListExtra("FeedbackPage");
        }

        for(Customer customer : customerList){
            Log.d("Customer", String.valueOf(customer.getRating()));
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.feedbackFragment,
                CustomerListViewFragment.newInstance(customerList)).commit();

        //fragmentTransaction.add(R.id.userListViewFragment, new UserListViewFragment(userList)).commit();
    }
}