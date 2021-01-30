package com.example.uielements;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ImageView imageV;
    ToggleButton toggleButton;
    String optionRadioButton;
    boolean green = false, red = false, black = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();   // Call initialization function
    }

    // UI View initialization
    private void initUI() {
        imageV = findViewById(R.id.imageView);
        toggleButton = findViewById(R.id.toggleButton);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    imageV.setVisibility(View.VISIBLE);
                }else{
                    imageV.setVisibility(
                            View.INVISIBLE);
                }
                Toast.makeText(MainActivity.this, toggleButton.getText().toString(), Toast.LENGTH_SHORT).show();
           }

        });
    }

    public void onRadioButtonClicked(View view) {   // Here, view indicates 'radio button'
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.yesRadioBtn:
                if (checked) {
                    optionRadioButton = "Yes";
                    break;
                }
            case R.id.noRadioBtn:
                if (checked) {
                    optionRadioButton = "No";
                    break;
                }
            case R.id.maybeRadioBtn:
                if (checked)
                    optionRadioButton = "Maybe";
        }
        Toast.makeText(this, optionRadioButton, Toast.LENGTH_SHORT).show();
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.greenCheckBox:
                green = (checked)?true:false;
                break;
            case R.id.redCheckBox:
                red = (checked)?true:false;
                break;
            case R.id.blackCheckBox:
                black = (checked)?true:false;
        }
        displaySelection();
    }

    private void displaySelection() {
        String select = "";
        if(green){
            select += "Green";
        }
        if(red){
            select += "Red";
        }
        if(black){
            select += "Black";
        }
        Toast.makeText(this, select, Toast.LENGTH_SHORT).show();
        Log.d("Tag", select);
    }
}