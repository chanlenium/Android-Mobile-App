package com.example.lab04.screens.selecteddish;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab04.R;
import com.example.lab04.models.Dish;

public class SelectedDishFragment extends Fragment {
    private static final String ARG_DISH_PARAM = "ARG_DISH_PARAM";

    private Dish dish;

    private TextView selectedDishNameTv;
    private TextView selectedDishRatingTv;
    private ImageView selectedDishImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dish = getArguments().getParcelable(ARG_DISH_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selected_dish, container, false);
        // associate fields in this class with components of view(fragment_selected_dish.xml)
        selectedDishNameTv = view.findViewById(R.id.selectedDishNameTv);
        selectedDishImage = view.findViewById(R.id.selectedDishImage);
        selectedDishRatingTv = view.findViewById(R.id.selectedDishRatingTv);

        // set values
        selectedDishNameTv.setText(dish.getDishName());
        selectedDishRatingTv.setText("Rating: " + dish.getDishRating());
        selectedDishImage.setImageResource(dish.getDishImage());

        return view;
    }

    public static SelectedDishFragment newInstance(Dish dish) {
        SelectedDishFragment fragment = new SelectedDishFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_DISH_PARAM, dish);
        fragment.setArguments(args);
        return fragment;
    }
}