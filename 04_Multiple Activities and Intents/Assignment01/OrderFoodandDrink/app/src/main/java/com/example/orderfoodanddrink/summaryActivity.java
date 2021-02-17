package com.example.orderfoodanddrink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;

public class summaryActivity extends AppCompatActivity {
    public static final String FOODLIST_KEY = "selectedFoods", DRINK_KEY = "selectedDrink";
    private ArrayList<Menu> selectedFoods;  // var to store selected foods from selectFoodActivity
    private Menu selectedDrink; // var to store selected drink from selectFoodActivity
    private ArrayList<String> selectedMenu; // var to store selected items(foods, drink)
    private ListView listView;
    private double total;
    private EditText totalEt;
    private Button editOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        init(); // function to initialize variables
        makeListItems(selectedFoods);    // function to make a list composed of selected items(menus)
        makeListItems(new ArrayList<>(Arrays.asList(selectedDrink)));

        // make array adapter to display makeListItems on ListView
        ArrayAdapter<String> menuAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selectedMenu);
        listView.setAdapter(menuAdapter);

        displayTotal(total);    // function to display order Total

        editOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestEditOrder();   // function to open selectFoodActivity
            }
        });

        Button submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity(); // function to open MainActivity
            }
        });
    }

    private void displayTotal(double total) {
        // Display total in terms of CAD
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setCurrency(Currency.getInstance("CAD"));
        totalEt.setText(formatter.format(total));
    }

    private void init() {
        // get reference to the intent and get data from intent
        if(getIntent().hasExtra(FOODLIST_KEY) && getIntent().hasExtra(DRINK_KEY)){
            // It is better to figure out there are FOODLIST_KEY("selectedFoods") and DRINK_KEY("selectedDrink")
            selectedFoods = getIntent().getParcelableArrayListExtra("selectedFoods");
            selectedDrink = getIntent().getExtras().getParcelable("selectedDrink");
        }
        listView = findViewById(R.id.listView);
        selectedMenu = new ArrayList<String>();
        total = 0;
        totalEt = findViewById(R.id.totalEt);
        editOrder = findViewById(R.id.editOrderBtn);
    }

    private void makeListItems(ArrayList<Menu> menuList) {
        // add the menu checked and calculate total
        for(int i = 0; i < menuList.size(); i++){
            selectedMenu.add(menuList.get(i).getName());
            total += menuList.get(i).getPrice();
        }
    }

    private void openMainActivity() {   // Open MainActivity without passing parameters
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void requestEditOrder() { // Open selectedFoodActivity for editing order
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(FOODLIST_KEY, selectedFoods);
        intent.putExtra(DRINK_KEY, selectedDrink);
        setResult(RESULT_OK, intent); // Here, 100 is same as the REQUEST_CODE from selectFoodActivity
        finish();
    }
}