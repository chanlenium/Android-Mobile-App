package com.example.lab5.Screen.SecondPage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab5.Model.UserModel;
import com.example.lab5.R;

import java.util.ArrayList;

public class UserListViewFragment extends Fragment {
    RecyclerView recyclerView;  // reference to RecyclerView
    ArrayList<UserModel> userList;

    public UserListViewFragment(ArrayList<UserModel> userList) {// constructor
        this.userList = userList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // inflate : 레이아웃들을 메모리에 객체화시키는 행동으로, XML을 객체화하여 코드에서 사용하기 위한 것을 목적으로 함
        // In here, view indicate "fragment_user_list_view.xml"
        View view = inflater.inflate(R.layout.fragment_user_list_view, container, false);
        // Since view is "fragment_user_list_view.xml", userListRecyclerView component can be accessed using 'view.findViewById(XXX)'
        recyclerView = view.findViewById(R.id.userListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));   // associated with LayoutManager
        recyclerView.setAdapter(new UserRecyclerViewAdapter(userList, getActivity()));
        return view;
    }
}