package com.example.lab6.Screen;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab6.Model.Customer;
import com.example.lab6.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerListViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerListViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_CUSTLIST_PARAM = "ARG_CUSTLIST_PARAM";

    // TODO: Rename and change types of parameters
    private List<Customer> customerList;
    private RecyclerView recyclerView;  // reference to RecyclerView
    private CustomerRecyclerViewAdapter.RecyclerViewClickListener listener;

    public CustomerListViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment UserListViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomerListViewFragment newInstance(List<Customer> param1) {
        CustomerListViewFragment fragment = new CustomerListViewFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_CUSTLIST_PARAM, (ArrayList<? extends Parcelable>) param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            customerList = getArguments().getParcelableArrayList(ARG_CUSTLIST_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setOnClickListener();
        CustomerRecyclerViewAdapter adapter = new CustomerRecyclerViewAdapter(customerList, listener);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_list_view, container, false);
        // associate fields in this class with components of view(fragment_customer_list_view.xml)
        recyclerView = view.findViewById(R.id.customerListRecyclerView);
        recyclerView.setLayoutManager(layoutManager);   // associated with LayoutManager
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void setOnClickListener() {
        listener = new CustomerRecyclerViewAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                intent.putExtra("selectedCustomer", customerList.get(position));
                startActivity(intent);
            }
        };
    }
}