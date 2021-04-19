package com.example.lab6.Model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.lab6.Database.CustomerDao;
import com.example.lab6.Database.MyDatabase;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CustomerViewModel extends AndroidViewModel {
    private CustomerRepository repository;          // reference to CustomerRepository
    private LiveData<List<Customer>> allCustomers;  // reference to LiveData
    // UI를 업데이트 하고 싶으면 liveData를 UI에서(Activity or Fragment) observe함.
    // query는 Repository에서 실행

    public CustomerViewModel(@NonNull Application application) {    // constructor
        super(application);
        repository = new CustomerRepository(application);
        allCustomers = repository.getAll();
    }

    public void insert(Customer customer) {
        repository.insert(customer);
    }

    public Customer getCustomer(String customerName) throws InterruptedException {
        return repository.getCustomer(customerName);
    }

    public void update(Customer customer) {
        repository.update(customer);
    }

    public void delete(Customer customer) {
        repository.delete(customer);
    }

    public LiveData<List<Customer>> getAllCustomers(){
        return allCustomers;
    }
}
