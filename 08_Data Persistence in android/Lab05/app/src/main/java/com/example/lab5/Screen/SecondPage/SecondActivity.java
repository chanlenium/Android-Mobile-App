package com.example.lab5.Screen.SecondPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lab5.Model.UserModel;
import com.example.lab5.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle(getString(R.string.user_list));
        init();
    }

    private void init() {
        // Activity can get reference to fragment using FragmentManager
        fragmentManager = getSupportFragmentManager();
        // To make fragment transactions in the activity (such as add, remove, or replace a fragment),
        // Use APIs from FragmentTransaction.
        fragmentTransaction = fragmentManager.beginTransaction();
        intent = getIntent();
    }

    public void displayUsers(View view) {
        if(intent != null){
            String fileName = intent.getStringExtra("filename");    // get String passed from MainActivity
            SharedPreferences sharedPreferences = getSharedPreferences(fileName, MODE_PRIVATE);
            // Get string which key is "username". If the searched key(i.e., "username" does not exist, it returns "no_name")
            String jsonString = sharedPreferences.getString("username", "no_name");

            if(jsonString.equals("no_name")){
                Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
            }else{
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<UserModel>>(){}.getType();
                ArrayList<UserModel> userList = gson.fromJson(jsonString, type);    // change JSON data to type data
                Log.d("TAG", String.valueOf(userList.get(0).getName()));

                // add(associate) "R.id.userListViewFragment" with Fragment 'UserListViewFragment'
                fragmentTransaction.add(R.id.userListViewFragment, new UserListViewFragment(userList)).commit();
            }
        }



    }
}