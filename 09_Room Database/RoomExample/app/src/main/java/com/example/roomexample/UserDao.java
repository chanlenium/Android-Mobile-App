package com.example.roomexample;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// Step2: Create Dao class (data access object)

/** Will be used to define queries to
 * - write data to database
 * - retrieve data from databases
 * - delete and update queries
 * **/
@Dao
public interface UserDao {
    @Insert
    public void addUser(User u);

//    @Delete
//    public void deleteUser(Integer userId);

//    @Update
//    public void update(User user);

    @Query("select * from users")
    public List<User> getAll();

//    @Query("select * from users where id = :id")
//    public User getUser(int id);
}
