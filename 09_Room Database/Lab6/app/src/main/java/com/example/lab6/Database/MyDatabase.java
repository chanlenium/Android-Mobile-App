package com.example.lab6.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.lab6.Model.Customer;

@Database(entities = Customer.class, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract CustomerDao customerDao();
    private static MyDatabase instance;

    public static MyDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, MyDatabase.class, "MyDB")
                    .allowMainThreadQueries() // used when main thread
                    .build();
        }
        return instance;
    }
}
