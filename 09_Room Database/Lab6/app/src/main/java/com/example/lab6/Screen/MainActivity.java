package com.example.lab6.Screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab6.Database.MyDatabase;
import com.example.lab6.Model.Customer;
import com.example.lab6.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyDatabase myDatabase;    // reference to db
    private EditText custNameEt;
    private RatingBar ratingBar;
    private TextView commentsTml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabase =MyDatabase.getInstance(this);
        custNameEt = findViewById(R.id.custNameEt);
        ratingBar = findViewById(R.id.ratingBar);
        commentsTml = findViewById(R.id.commentsTml);
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

    public void saveUser(View view) {
        String name = custNameEt.getText().toString();
        double rating = ratingBar.getRating();
        String comment = commentsTml.getText().toString();

        if(name.trim().equals("") || rating == 0 || comment.trim().equals("")){
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        }else{
            Customer customer = new Customer(name, rating, comment);
            Customer cust = myDatabase.customerDao().getCustomer(customer.getName());
            if(cust != null){
                Toast.makeText(this, "The customer is already registered", Toast.LENGTH_SHORT).show();
            }else{
                myDatabase.customerDao().addCustomer(customer); // background thread
                custNameEt.setText("");
                ratingBar.setRating(0);
                commentsTml.setText("");
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /** Handling click events **/
    // When the user selects an item from the options menu,
    // the system calls your activity's onOptionsItemSelected() method.
    // This method passes the MenuItem selected.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.pageMenuOption){
            Intent intent = new Intent(this, FeedbackActivity.class);
            List<Customer> customerList = myDatabase.customerDao().getAll();
            for(Customer customer : customerList){
                Log.d("Customer", customer.getName());
            }
            intent.putParcelableArrayListExtra("FeedbackPage", (ArrayList<? extends Parcelable>) customerList);
            startActivity(intent);
            Toast.makeText(this, "Feedback Activity page", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}