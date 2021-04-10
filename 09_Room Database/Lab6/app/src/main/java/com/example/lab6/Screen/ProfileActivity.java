package com.example.lab6.Screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab6.Model.Customer;
import com.example.lab6.Model.CustomerViewModel;
import com.example.lab6.R;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private Customer customer;
    private Intent intent;
    private EditText customerDetailNameEt;
    private RatingBar customerDetailRatingBar;
    private TextView customerDetailComments;
    private CustomerViewModel customerViewModel;
    private Button editBtn, deleteBtn;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        customerDetailNameEt = findViewById(R.id.customerDetailNameEt);
        customerDetailRatingBar = findViewById(R.id.customerDetailRatingBar);
        customerDetailComments = findViewById(R.id.customerDetailComments);
        editBtn = findViewById(R.id.editBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        customerViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);

        intent = getIntent();
        if(intent != null) { // get data from caller
            customer = intent.getParcelableExtra("selectedCustomer");
        }
        id = customer.getId();
        customerDetailNameEt.setText(customer.getName());
        customerDetailRatingBar.setRating((float) customer.getRating());
        customerDetailComments.setText(customer.getComment());

        editBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = customerDetailNameEt.getText().toString();
                double rating = customerDetailRatingBar.getRating();
                String comment = customerDetailComments.getText().toString();

                if(name.trim().equals("") || rating == 0 || comment.trim().equals("")){ // check all fields are filled
                    Toast.makeText(ProfileActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                }else{
                    Customer customer = new Customer(name, rating, comment);
                    customer.setId(id);
                    customerViewModel.update(customer);
                    Toast.makeText(getApplication(), "Update Done", Toast.LENGTH_SHORT).show();

                    final int[] count = {0};
                    customerViewModel.customerList.observe(ProfileActivity.this, currentVal ->{
                        count[0] = count[0] + 1;
                        for(Customer c : currentVal){
                            Log.d("profile", "ID : " + c.getId());
                            Log.d("profile", "Name : " + c.getName());
                            Log.d("profile", "Rating : " + c.getRating());
                            Log.d("profile", "Comment : " + c.getComment());
                            Log.d("profile", "count[0] : " + count[0]);
                        }
                        if(count[0] == 2){
                            intent = new Intent(ProfileActivity.this, FeedbackActivity.class);   // explicit intent
                            intent.putParcelableArrayListExtra("FeedbackPage", (ArrayList<? extends Parcelable>) currentVal);
                            startActivity(intent);
                        }
                    });
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                customerViewModel.delete(customer);
                Toast.makeText(getApplication(), "delete Done", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}