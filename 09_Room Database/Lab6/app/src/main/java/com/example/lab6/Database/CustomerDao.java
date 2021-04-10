package com.example.lab6.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lab6.Model.Customer;

import java.util.List;

@Dao
public interface CustomerDao {  // define queries
    @Insert
    void insert(Customer customer);

    @Query("select * from customers where user_name LIKE :name")
    Customer getCustomer(String name);

    @Update
    void update(Customer customer);

    @Delete
    void delete(Customer customer);

    @Query("select * from customers")
    LiveData<List<Customer>> getAll();
}
