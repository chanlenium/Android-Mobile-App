package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView orderDetail;

    private boolean isToppingPepperoni, isToppingGreen, isToppingOnion; // variable to indicate topping is selected or not
    private boolean sizeChecked, orderTypeChecked;
    private String selectedSize;    // String to store which size option is selected
    private String selectedOrderType;
    private String toppingSelection;    // String to describe selected toppings

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uIinit();   // call UI initialization function
    }

    private void uIinit(){  // UI initialization function
        orderDetail = findViewById(R.id.orderDetailTV);
        orderDetail.setText(getString(R.string.hi));
    }

    public void onSizeRadioBtnClicked(View view) {  // In here, view is radio button
        // Is the button now checked?
        sizeChecked = ((RadioButton) view).isChecked(); // "True" only if the button is clicked

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.sizeLRadioBtn:
                if (sizeChecked) {
                    selectedSize = getString(R.string.sizeLarge);
                    break;
                }
            case R.id.sizeMRadioBtn:
                if (sizeChecked) {
                    selectedSize = getString(R.string.sizeMedium);
                    break;
                }
            case R.id.sizeSRadioBtn:
                if (sizeChecked)
                    selectedSize = getString(R.string.sizeSmall);
        }
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        Boolean toppingChecked = ((CheckBox) view).isChecked(); // "True" only if the box is checked, not unchecked
        // Even checkbox is clicked, if the click is for unchecked, the value is "false"

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.toppingPepperoniCBox:
                isToppingPepperoni = toppingChecked;
                break;
                // if (toppingChecked)
                //     toppingPepperoni = true;
                // else
                //     toppingPepperoni = false;
            case R.id.toppingGreenCBox:
                isToppingGreen = toppingChecked;
                break;
            case R.id.toppingOnionCBox:
                isToppingOnion = toppingChecked;
        }
        displaySelection();
    }

    public void displaySelection(){    // function to identify which topping is selected
        toppingSelection = "";

        if (isToppingPepperoni){
            toppingSelection += getString(R.string.toppingPepperoni);
        }

        if (isToppingGreen) {
            if (!TextUtils.isEmpty(toppingSelection)) {
                toppingSelection += ", ";
            }
            toppingSelection += getString(R.string.toppingGreenPepper);
        }

        if (isToppingOnion) {
            if (!TextUtils.isEmpty(toppingSelection)) {
                toppingSelection += ", ";
            }
            toppingSelection += getString(R.string.toppingOnion);
        }
    }

    public void onOrderRadioBtnClicked(View view) {
        // Is the button now checked?
        orderTypeChecked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.orderPickupRadioBtn:
                if (orderTypeChecked) {
                    selectedOrderType = getString(R.string.orderPickupRadioBtn);
                    break;
                }
            case R.id.orderDeliveryRadioBtn:
                if (orderTypeChecked)
                    selectedOrderType = getString(R.string.orderDeliveryRadioBtn);
        }
    }

    public void displayOrderDetail(View view) { // called when click "Place the Order" button
        if(sizeChecked){
            if(toppingSelection.length() > 0){
                if(orderTypeChecked){
                    orderDetail.setText(String.format("%s Pizza with %s\nOrder type %s", selectedSize, toppingSelection, selectedOrderType));
                }else{
                    orderDetail.setText(String.format("Please select %s", getString(R.string.orderTypeTV)));
                }
            }else{
                orderDetail.setText(String.format("Please select %s", getString(R.string.toppingsTV)));
            }
        }else{
            orderDetail.setText(String.format("Please select %s", getString(R.string.sizeTV)));
        }
    }
}