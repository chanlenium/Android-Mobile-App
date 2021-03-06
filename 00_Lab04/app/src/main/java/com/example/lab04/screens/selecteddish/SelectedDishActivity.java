/** Selected Dish Activity
 * (1) Get the reference to the intent and data from caller
 * (2) Get reference to fragment using FragmentManager
 * (3) Set the values of fields in fragment using 'SelectedDishFragment.newInstance(XXX)' which is static method
 */

package com.example.lab04.screens.selecteddish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.lab04.R;
import com.example.lab04.models.Dish;

public class SelectedDishActivity extends AppCompatActivity {
    private Dish selectedDish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_dish);

        // get the reference to the intent declared in MainActivity
        Intent intent = getIntent();
        if(intent != null){ // get data from caller
            selectedDish = getIntent().getExtras().getParcelable("selectedDish");
        }

        // Activity can get reference to fragment using FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.selectedDishFrag, SelectedDishFragment.newInstance(selectedDish.getDishName(), "Rating: " + String.valueOf(selectedDish.getDishRating()), selectedDish.getDishImage())).commit();
    }
}