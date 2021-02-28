package com.example.lab04;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DishListFragment extends Fragment {
    RecyclerView recyclerView;  // reference variable for RecyclerView
    MainViewModel viewModel;    // reference variable for ViewModel
    //FrameLayoutManager layoutManager;
    LinearLayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dish_list, container, false);
        recyclerView = view.findViewById(R.id.dishListRv);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Use ViewModelProvider to associate the ViewModel with yourActivity.
        // When the Activity first starts, the ViewModelProvider will create the ViewModel.
        // When the activity is destroyed, the ViewModel persists.
        // When the activity is re-created, the ViewModelProviders return the existing ViewModel
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getLiveArrayDish().observe(getViewLifecycleOwner(), currentValue -> {
            recyclerView.setAdapter(new CustomAdapter(currentValue, null));
        });
        return view;
    }
}