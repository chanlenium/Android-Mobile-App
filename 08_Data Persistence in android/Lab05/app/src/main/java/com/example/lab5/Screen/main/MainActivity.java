package com.example.lab5.Screen.main;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab5.Model.UserModel;
import com.example.lab5.R;
import com.example.lab5.Screen.SecondPage.SecondActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final String FILE_NAME = "my_SharedPrefs";  // set file name as a constant value
    private EditText userNameEt;    // reference to edit text for user name
    private ArrayList<UserModel> userList;  // reference to user list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init(); // initialization
    }

    private void init() {   // Initialize all components
        userList = new ArrayList<>();
        userNameEt = findViewById(R.id.userNameEt);
        userNameEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(); // call the function to hide a keyboard
            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /** Creating an Options Menu **/
    // To specify the options menu for an activity,
    // override onCreateOptionsMenu() (fragments provide their own onCreateOptionsMenu() callback).
    // In this method, you can inflate your menu resource(defined in XML) into the Menu provided in the callback.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /** Handling click events **/
    // When the user selects an item from the options menu,
    // the system calls your activity's onOptionsItemSelected() method.
    // This method passes the MenuItem selected.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.pageMenuOption){
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("filename", FILE_NAME);
            startActivity(intent);
            Toast.makeText(this, "page2", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addUserToFile(View view) {
        if(userNameEt.length() != 0){
            String userName = userNameEt.getText().toString();
            if(userName.chars().allMatch(Character::isLetter)){ // check if user inputs are all alphabet
                // if user input is valid (i.e., alphabet name is keyed in), user input is added into a model
                userList.add(new UserModel(userName));

                // GSON : library to change java object to JSON(key, value)
                Gson gson = new Gson();
                String jsonString = gson.toJson(userList);  // change userList to JSON string

                // get Shared Preference instance
                // object points to a file containing key-value pairs
                SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit(); // using the Editor to write values to the SharedPreferences
                editor.putString("username", jsonString);
                editor.commit();
                // commit() : The method will block until the changes are reflected both in memory as well as the underlying XML file.
                // Returns true on success, false on failure
                userNameEt.setText(""); // clear text editor
                Toast.makeText(this, "Adding user to file is done", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Type only alphabets (no space)", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Enter your name", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteFile(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.ic_baseline_warning_24));
        builder.setTitle("Deleting USERS_LIST_FILE")
                .setMessage("Are you sure you want to delete the file?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear(); // clear all contents in file
                        editor.commit();
                        Toast.makeText(MainActivity.this, "Delete done", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}