package com.example.roomexample;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Step1: Create Entity Model class
/** An Entity class represents a table in database
 * Must be annotated with @Entity
 * Fields must either be public or have getters and setters
 * At least one field must be marked as a "primary key" using the @PrimaryKey annotation
 * Use @ColumnInfo if you want to assign column name
 * **/
@Entity(tableName = "users")    // change table name as "users"
public class User {
    @PrimaryKey
    int id;
    @ColumnInfo(name = "user_name")
    String name;
    @ColumnInfo(name = "email")
    String email;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
