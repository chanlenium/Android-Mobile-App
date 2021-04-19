package com.example.lab6.Model;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.lab6.Database.CustomerDao;
import com.example.lab6.Database.MyDatabase;

import java.util.List;
import java.util.concurrent.CountDownLatch;

// 서버나 database에서 data를 받아오는 로직은 전부 Repository라는 class를 만들어서 처리 해야 함.
// ViewModel은 repository에서 받은 데이터(query의 결과값)를 UI에서 사용하기 편하게 가공하며, livedata를 updata하는 방식으로 UI에 전달함.
public class CustomerRepository {
    private CustomerDao customerDao;              // reference to CustomerDao
    private LiveData<List<Customer>> allCustomer;   // reference to LiveData
    private Customer searchedCustomer;

    public CustomerRepository(Application application){
        MyDatabase database = Room.databaseBuilder(application, MyDatabase.class, "customer").build();
        customerDao = database.getCustomerDao();
        allCustomer = customerDao.getAll();
    }

    public LiveData<List<Customer>> getAll(){
        return allCustomer;
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                searchedCustomer = customerDao.getCustomer(customerName);
                latch.countDown(); // Release await() in the test thread.
            }
        }).start();
        latch.await(); // Wait for countDown() in the UI thread. Or could uiThread.join();
        return searchedCustomer;
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
