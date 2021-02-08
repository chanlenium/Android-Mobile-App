package com.example.orderfoodanddrink;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;
import java.util.ArrayList;

public class selectFoodActivity extends AppCompatActivity {
    private ArrayList<Menu> foodList;   // variable for food list
    private ArrayList<Menu> drinkList;  // variable for drink list
    static final int SUMMARY_CODE_REQUEST = 100;  // parameter identifying the call
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_food);
        init(); // function to initialize variables

        // When clicking the checkout button..
        Button checkoutBtn = findViewById(R.id.checkoutBtn);
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputValidate(); // validate check and open summaryActivity
            }
        });
    }

    private void inputValidate() {  // Check at least one of food items is selected
        boolean foodChecked = false, drinkChecked = false;
        for(int i = 0; i < foodList.size() && !foodChecked; i++)
            foodChecked = foodList.get(i).isChecked();

        // Check at least one of drink items is selected
        for(int i = 0; i < drinkList.size() && !drinkChecked; i++)
            drinkChecked = drinkList.get(i).isChecked();

        if(!foodChecked && !drinkChecked){
            Toast.makeText(this, "Select Food and Drink", Toast.LENGTH_LONG).show();
        }else if(foodChecked && !drinkChecked){
            Toast.makeText(this, "Select Drink", Toast.LENGTH_LONG).show();
        }else if(!foodChecked){
            Toast.makeText(this, "Select Food", Toast.LENGTH_LONG).show();
        }else
            openSummaryActivity();  // call to open summaryActivity
    }

    private void init() {   // Initialize ArrayLists
        foodList = new ArrayList<>();
        foodList.add(new Menu("Fish", 12.0, false));
        foodList.add(new Menu("Chicken", 11.0, false));
        foodList.add(new Menu("Roasted Veggies", 10.0, false));
        foodList.add(new Menu("Grilled Steak", 15.0, false));

        drinkList = new ArrayList<>();
        drinkList.add(new Menu("Tea", 1.7, false));
        drinkList.add(new Menu("Coffee", 1.8, false));
        drinkList.add(new Menu("Orange Juice", 2.0, false));
        drinkList.add(new Menu("Apple Juice", 3.0, false));
    }

    private void openSummaryActivity() {    // Open summaryActivity by passing arrayList
        Intent intent = new Intent(this, summaryActivity.class);
        intent.putParcelableArrayListExtra("foodList", foodList);
        intent.putParcelableArrayListExtra("drinkList", drinkList);
        startActivityForResult(intent, SUMMARY_CODE_REQUEST);
    }

    // Callback function once summaryActivity works done
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SUMMARY_CODE_REQUEST && resultCode == RESULT_OK)
            Toast.makeText(this, "Edit an order", Toast.LENGTH_SHORT).show();
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.foodFishCb:
                foodList.get(0).setChecked(checked);
                break;
            case R.id.foodChickenCb:
                foodList.get(1).setChecked(checked);
                break;
            case R.id.foodRoastedVeggiesCb:
                foodList.get(2).setChecked(checked);
                break;
            case R.id.foodGrilledSteakCb:
                foodList.get(3).setChecked(checked);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.teaRbtn:
                drinkList.get(0).setChecked(checked);
                break;
            case R.id.coffeeRbtn:
                drinkList.get(1).setChecked(checked);
                break;
            case R.id.orangeJuiceRbtn:
                drinkList.get(2).setChecked(checked);
                break;
            case R.id.appleJuiceRbtn:
                drinkList.get(3).setChecked(checked);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
}