/** Fragment for dish list
 *  (1) Inflate a fragment layout (fragment_dish_list.xml)
 *  (2) Access recyclerView component (dishListRecyclerView) in the fragment layout using 'view.findViewById(XXX)'
 *  (3) Associate current view model value with recyclerView using adapter (DishRecyclerViewAdapter)
 */

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // inflate : 레이아웃들을 메모리에 객체화시키는 행동으로, XML을 객체화하여 코드에서 사용하기 위한 것을 목적으로 함
        // In here, view indicate "fragment_dish_list.xml"
        View view = inflater.inflate(R.layout.fragment_dish_list, container, false);
        // Since view is "fragment_dish_list.xml", dishListRecyclerView component can be accessed using 'view.findViewById(XXX)'
        recyclerView = view.findViewById(R.id.dishListRecyclerView);

        // associated with LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Use 'ViewModelProvider' to associate the ViewModel with yourActivity.
        // When the Activity first starts, the ViewModelProvider will create the ViewModel.
        // When the activity is destroyed, the ViewModel persists.
        // When the activity is re-created, the ViewModelProviders return the existing ViewModel
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getLiveArrayDish().observe(getViewLifecycleOwner(), currentValue -> {
            // associate current view model value with recyclerView using adapter
            recyclerView.setAdapter(new DishRecyclerViewAdapter(currentValue, getActivity()));
        });
        return view;
    }
}