package com.example.lab6.Screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
import com.example.lab6.Model.CustomerViewModel;
import com.example.lab6.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText customerNameEt;    // reference to UI customer name
    private RatingBar ratingBar;        // reference to UI ratingbar
    private TextView commentLine;       // reference to UI commentLine
    private CustomerViewModel model;    // reference to ViewModel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();   // UI initialization

        // creating viewModel object
        model = new ViewModelProvider(this).get(CustomerViewModel.class);
        // Since model.customerList is "LiveData" by executing customerDao.getAll()
        // observer can update its state whenever the value of model.customerList is changed
        model.customerList.observe(this, new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {
                // Whenever the value of model.customerList is changed, the App prints its elements
                for(Customer customer : customers){
                    Log.d("User", customer.getName());
                }
            }
        });
    }

    private void initUI() {
        customerNameEt = findViewById(R.id.custNameEt);
        ratingBar = findViewById(R.id.ratingBar);
        commentLine = findViewById(R.id.commentsTml);
    }

    // When the "SAVE USER" button is clicked..
    public void saveUser(View view) throws InterruptedException {
        String name = customerNameEt.getText().toString();
        double rating = ratingBar.getRating();
        String comment = commentLine.getText().toString();

        if(name.trim().equals("") || rating == 0 || comment.trim().equals("")){ // check all fields are filled
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        }else{
            // check input user is already in a list or not
            Customer searchedCustomer = model.getCustomer(name);
            if(searchedCustomer != null){
                Toast.makeText(this, "This user is already registered", Toast.LENGTH_SHORT).show();
            }else{
                // if input user is a new user, the app create user object and insert the user into a DB
                Customer customer = new Customer(name, rating, comment);
                model.insert(customer);

                // clear UI elements
                customerNameEt.setText("");
                ratingBar.setRating(0);
                commentLine.setText("");
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
            }
        }
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
            Intent intent = new Intent(this, FeedbackActivity.class);   // explicit intent
            model.customerList.observe(this, currentValue -> {
                // In here, currentValue is user list got from @Query("select * from customers")
                intent.putParcelableArrayListExtra("FeedbackPage", (ArrayList<? extends Parcelable>) currentValue);
            });
            startActivity(intent);
            Toast.makeText(this, "Feedback Activity page", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}