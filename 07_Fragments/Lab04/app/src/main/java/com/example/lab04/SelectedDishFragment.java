package com.example.lab04;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab04.models.Dish;

public class SelectedDishFragment extends Fragment {
    private static final String ARG_DISH_PARAM = "ARG_DISH_PARAM";
    private static final String ARG_NAME_PARAM = "ARG_NAME_PARAM";
    private static final String ARG_RATE_PARAM = "ARG_RATE_PARAM";
    private static final String ARG_IMAG_PARAM = "ARG_IMAG_PARAM";

    private Dish dish;
    private String dishName;
    private String dishRate;
    private int dishImageId;

    private TextView selectedDishNameTv;
    private TextView selectedDishRatingTv;
    private ImageView selectedDishImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dish = getArguments().getParcelable(ARG_DISH_PARAM);
            dishName = getArguments().getString(ARG_NAME_PARAM);
            dishImageId = getArguments().getInt(ARG_IMAG_PARAM);
            dishRate = getArguments().getString(ARG_RATE_PARAM);
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
        //selectedDishNameTv.setText(dish.getDishName());
        selectedDishNameTv.setText(dishName);
        selectedDishRatingTv.setText(dishRate);
        selectedDishImage.setImageResource(dishImageId);

        return view;
    }

    public static SelectedDishFragment newInstance(String param1, String param2, int param3) {
        SelectedDishFragment fragment = new SelectedDishFragment();
        Bundle args = new Bundle(); // Bundle : 여러가지의 타입의 값을 저장하는 Map 클래스
        // Android에서는 Activity간에 데이터를 주고 받을 때 Bundle 클래스를 사용하여 데이터를 전송
        //args.putParcelable(ARG_DISH_PARAM, dish);
        args.putString(ARG_NAME_PARAM, param1);
        args.putString(ARG_RATE_PARAM, param2);
        args.putInt(ARG_IMAG_PARAM, param3);
        fragment.setArguments(args);
        return fragment;
    }
}