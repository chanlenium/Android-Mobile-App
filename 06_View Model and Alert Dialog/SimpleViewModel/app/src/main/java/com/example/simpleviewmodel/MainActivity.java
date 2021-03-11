package com.example.simpleviewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // declare variables
    TextView tv;
    EditText editText;
    MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize variables
        tv = findViewById(R.id.textView);
        editText = findViewById(R.id.nameId);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);   // create once only
        //tv.setText(myViewModel.getData());
        myViewModel.getData().observe(this, newValue ->{
            tv.setText(newValue);
        });
    }

    public void greet(View view) {
        String name = editText.getText().toString();
        myViewModel.setData("Hi " + name);
        tv.setText(String.valueOf(myViewModel.getData().getValue()));
        //tv.setText(myViewModel.getData());  // data will persist
        //tv.setText("Hi " + name);
    }
}