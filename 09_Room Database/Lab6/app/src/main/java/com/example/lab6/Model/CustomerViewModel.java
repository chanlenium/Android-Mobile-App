package com.example.lab6.Model;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.lab6.Database.CustomerDao;
import com.example.lab6.Database.MyDatabase;
import com.example.lab6.Screen.FeedbackActivity;
import com.example.lab6.Screen.MainActivity;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CustomerViewModel extends AndroidViewModel {
    public LiveData<List<Customer>> customerList;   // reference to LiveData
    private CustomerDao customerDao;    // reference to CustomerDao
    private MyDatabase db;  // reference to database

    Intent intent;

    public CustomerViewModel(@NonNull Application application) {    // constructor
        super(application);
        // get instances for all references
        db = Room.databaseBuilder(application, MyDatabase.class, "customer").build();
        customerDao = db.getCustomerDao();
        customerList = customerDao.getAll();
    }

    public void insert(Customer customer) {
        new Thread(new Runnable() {
            public void run() {
                // code in a background
                customerDao.insert(customer);   // add new Customer
            }
        }).start();
    }

    public Customer getCustomer(String customerName) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final Customer[] customer = new Customer[1];
        new Thread(new Runnable() {
            @Override
            public void run() {
                customer[0] = customerDao.getCustomer(customerName);
                latch.countDown(); // Release await() in the test thread.
            }
        }).start();
        latch.await(); // Wait for countDown() in the UI thread. Or could uiThread.join();
        return customer[0];
    }

    public void update(Customer customer) {
        new Thread(new Runnable() {
            public void run() {
                // code in a background
                customerDao.update(customer);   // add new Customer
            }
        }).start();
    }

    public void delete(Customer customer) {
        new Thread(new Runnable() {
            public void run() {
                // code in a background
                customerDao.delete(customer);   // add new Customer
            }
        }).start();
    }
}
