package com.example.lab5.Screen.SecondPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5.Model.UserModel;
import com.example.lab5.R;

import java.util.ArrayList;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder> {
    private ArrayList<UserModel> userList;
    private Context context;

    // Constructor of Adapter class
    public UserRecyclerViewAdapter(ArrayList<UserModel> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Transfer XML to an object and create each row(View object) using LayoutInflater
        // Inflate the layout for this ViewHolder
        // In here, view indicate "row_item.xml"
        View view = layoutInflater.inflate(R.layout.user_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;    // Create actual instance of ViewHolder and return it
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.userName.setText(String.valueOf(userList.get(position).getName()));
    }

    @Override
    public int getItemCount() {
        return (userList != null ? userList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userName;
        // Receive View object (itemView) and associate view elements with ViewHolder class fields
        // In here, itemView indicates 'user_row.xml'
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userNameTv);
        }
    }
}
