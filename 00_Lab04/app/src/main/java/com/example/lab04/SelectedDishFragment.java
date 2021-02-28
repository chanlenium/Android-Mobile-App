package com.example.lab04;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectedDishFragment extends Fragment {
    public TextView selectedDishNameTv;
    public ImageView selectedDishImage;
    public TextView selectedDishRatingTv;

    public SelectedDishFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selected_dish, container, false);
        selectedDishNameTv = view.findViewById(R.id.selectedDishNameTv);
        selectedDishImage = view.findViewById(R.id.selectedDishImage);
        selectedDishRatingTv = view.findViewById(R.id.selectedDishRatingTv);

        //Log.d("tagraw", String.valueOf(selectedDishNameTv.getText()));
        selectedDishNameTv.setText("aaa");
        selectedDishRatingTv.setText("5.5");

        return view;
    }

    public void setSelectedDishNameTv(String str) {
        this.selectedDishNameTv.setText(str);
    }
}