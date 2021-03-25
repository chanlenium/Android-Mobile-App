package com.example.roomexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Database database;    // reference to db
    EditText userIdEt, userNameEt, userEmailEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = Database.getInstance(this);
        userIdEt = findViewById(R.id.userIdEt);
        userNameEt = findViewById(R.id.userNameEt);
        userEmailEt = findViewById(R.id.userEmailEt);
    }

    public void addUser(View view) {
        int id = Integer.parseInt(userIdEt.getText().toString());
        String name = userNameEt.getText().toString();
        String email = userEmailEt.getText().toString();
        User user = new User(id, name, email);
        database.userDao().addUser(user);

        userIdEt.setText("");
        userNameEt.setText("");
        userEmailEt.setText("");
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
    }

    public void displayUser(View view) {
        List<User> userList = database.userDao().getAll();
        for(User user : userList){
            Log.d("User", user.getName());
        }
    }
}