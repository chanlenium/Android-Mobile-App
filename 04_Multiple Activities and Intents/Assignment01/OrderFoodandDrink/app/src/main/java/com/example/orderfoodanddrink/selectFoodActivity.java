package com.example.orderfoodanddrink;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import java.util.ArrayList;

public class selectFoodActivity extends AppCompatActivity {
    static final int SUMMARY_CODE_REQUEST = 100;  // parameter identifying the call
    private ArrayList<Menu> foodList;   // variable for all food list
    private ArrayList<Menu> drinkList;  // variable for all drink list
    private ArrayList<Menu> selectedFoods; // variable for storing selected foods
    private Menu selectedDrink;  // variable for storing selected drink
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_food);
        initData(); // function to initialize variables

        // When clicking the checkout button..
        Button checkoutBtn = findViewById(R.id.checkoutBtn);
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputValidate(); // validate check and open summaryActivity
            }
        });
    }

    private void initData() {   // Initialize ArrayLists
        foodList = new ArrayList<>();
        foodList.add(new Menu("Fish", 12.0));
        foodList.add(new Menu("Chicken", 11.0));
        foodList.add(new Menu("Roasted Veggies", 10.0));
        foodList.add(new Menu("Grilled Steak", 15.0));

        drinkList = new ArrayList<>();
        drinkList.add(new Menu("Tea", 1.7));
        drinkList.add(new Menu("Coffee", 1.8));
        drinkList.add(new Menu("Orange Juice", 2.0));
        drinkList.add(new Menu("Apple Juice", 3.0));

        selectedFoods = new ArrayList<>();
        selectedDrink = null;
    }

    private void inputValidate() {  // Check at least one of food items is selected
        boolean foodChecked = selectedFoods.size() > 0;
        boolean drinkChecked = selectedDrink != null;
        if(!foodChecked && !drinkChecked){
            Toast.makeText(this, "Select Food and Drink", Toast.LENGTH_LONG).show();
        }else if(foodChecked && !drinkChecked){
            Toast.makeText(this, "Select Drink", Toast.LENGTH_LONG).show();
        }else if(!foodChecked){
            Toast.makeText(this, "Select Food", Toast.LENGTH_LONG).show();
        }else
            openSummaryActivity();  // call to open summaryActivity
    }

    private void openSummaryActivity() {    // Open summaryActivity by passing arrayList
        Intent intent = new Intent(this, summaryActivity.class);
        intent.putParcelableArrayListExtra("selectedFoods", selectedFoods);
        intent.putExtra("selectedDrink", selectedDrink);
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
                if (checked) {  // checked
                    selectedFoods.add(foodList.get(0));
                } else {    // unchecked
                    selectedFoods.remove(foodList.get(0));
                }
                break;
            case R.id.foodChickenCb:
                if (checked) {
                    selectedFoods.add(foodList.get(1));
                } else {
                    selectedFoods.remove(foodList.get(1));
                }
                break;
            case R.id.foodRoastedVeggiesCb:
                if (checked) {
                    selectedFoods.add(foodList.get(2));
                } else {
                    selectedFoods.remove(foodList.get(2));
                }
                break;
            case R.id.foodGrilledSteakCb:
                if (checked) {
                    selectedFoods.add(foodList.get(3));
                } else {
                    selectedFoods.remove(foodList.get(3));
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    public void onRadioButtonClicked(View view) {
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.teaRbtn:
                selectedDrink = drinkList.get(0);
                break;
            case R.id.coffeeRbtn:
                selectedDrink = drinkList.get(1);
                break;
            case R.id.orangeJuiceRbtn:
                selectedDrink = drinkList.get(2);
                break;
            case R.id.appleJuiceRbtn:
                selectedDrink = drinkList.get(3);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
}
