package com.example.lab04;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectedDishFragment extends Fragment {

    private static final String ARG_NAME_PARAM = "ARG_NAME_PARAM";
    private static final String ARG_RATE_PARAM = "ARG_RATE_PARAM";

    private String name;
    private String rate;

    private TextView selectedDishNameTv;
    private ImageView selectedDishImage;
    private TextView selectedDishRatingTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME_PARAM);
            rate = getArguments().getString(ARG_RATE_PARAM);
        }
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
        selectedDishNameTv.setText(name);
        selectedDishRatingTv.setText(rate);

        return view;
    }

    public void setSelectedDishNameTv(String str) {
        this.selectedDishNameTv.setText(str);
    }

    public static SelectedDishFragment newInstance(String param1, String param2) {
        SelectedDishFragment fragment = new SelectedDishFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME_PARAM, param1);
        args.putString(ARG_RATE_PARAM, param2);
        fragment.setArguments(args);
        return fragment;
    }

}