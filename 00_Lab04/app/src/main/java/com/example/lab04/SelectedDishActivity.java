package com.example.lab04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SelectedDishActivity extends AppCompatActivity {
    private Dish selectedDish;
    //TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_dish);

        // get the reference to the intent declared in MainActivity
        Intent intent = getIntent();
        if(intent != null){ // get data from caller
            selectedDish = getIntent().getExtras().getParcelable("selectedDish");
        }

        //tv = findViewById(R.id.selectedDishTitleTv);
        //tv.setText("dafd");

        // Activity can get reference to fragment using FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();


        transaction.add(R.id.selectedDishFrag, SelectedDishFragment.newInstance("abc","abc")).commit();

        //if you added fragment via layout xml
        //SelectedDishFragment fragment =  (SelectedDishFragment)fm.findFragmentById(R.id.selectedDishFrag);
        //fragment.selectedDishNameTv.setText("adfda");





        //if(fragment instanceof SelectedDishFragment){
            //Log.d("tag", String.valueOf(((SelectedDishFragment) fragment).selectedDishNameTv.getText()));
            //Log.d("tag", "Rating: " + String.valueOf(selectedDish.getDishRating()));

            //((SelectedDishFragment)fragment).selectedDishNameTv.setText(selectedDish.getDishName());
            //((SelectedDishFragment)fragment).selectedDishRatingTv.setText("Rating: " + String.valueOf(selectedDish.getDishRating()));
            //((SelectedDishFragment)fragment).selectedDishImage.setImageResource(selectedDish.getDishImage());
        //}
    }
}