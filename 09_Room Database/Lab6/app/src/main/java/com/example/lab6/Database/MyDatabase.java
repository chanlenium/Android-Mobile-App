package com.example.lab6.Database;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lab6.Model.Customer;

@Database(entities = {Customer.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract CustomerDao getCustomerDao();
}
