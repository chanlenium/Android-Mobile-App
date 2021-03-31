package com.example.lab6.Screen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab6.Model.Customer;
import com.example.lab6.R;

import java.util.ArrayList;

public class CustomerRecyclerViewAdapter extends RecyclerView.Adapter<CustomerRecyclerViewAdapter.ViewHolder>{
    private ArrayList<Customer> userList;
    private Context context;

    // Constructor of Adapter class
    public CustomerRecyclerViewAdapter(ArrayList<Customer> userList, Context context) {
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
        // In here, view indicate "customer_row.xml"
        View view = layoutInflater.inflate(R.layout.customer_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;    // Create actual instance of ViewHolder and return it
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.userName.setText(String.valueOf(userList.get(position).getName()));
        holder.usreRatingBar.setRating((float) userList.get(position).getRating());
        holder.userComment.setText(userList.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return (userList != null ? userList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView userName;
        private RatingBar usreRatingBar;
        private TextView userComment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.rowCustName);
            usreRatingBar = itemView.findViewById(R.id.rowRatingBar);
            userComment = itemView.findViewById(R.id.rowCustComment);
        }
    }
}
