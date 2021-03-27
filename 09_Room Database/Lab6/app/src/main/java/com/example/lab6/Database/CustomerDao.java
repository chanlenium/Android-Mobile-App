package com.example.lab6.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.lab6.Model.Customer;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CustomerDao {
    @Insert
    public void addCustomer(Customer customer);

    @Query("select * from customers")
    public List<Customer> getAll();

    @Query("select * from customers where user_name LIKE :name")
    public Customer getCustomer(String name);
}
