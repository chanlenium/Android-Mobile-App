package com.example.lab6.Screen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab6.Model.Customer;
import com.example.lab6.R;

import java.util.List;

public class CustomerRecyclerViewAdapter extends RecyclerView.Adapter<CustomerRecyclerViewAdapter.ViewHolder>{
    private List<Customer> userList;
    //private Context context;
    private RecyclerViewClickListener listener;

    // Constructor of Adapter class
    public CustomerRecyclerViewAdapter(List<Customer> userList, RecyclerViewClickListener listener) {
        this.userList = userList;
        this.listener = listener;
    }

    // define interface for click event
    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
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
        ViewHolder holder = new ViewHolder(view);
        return holder;    // Create actual instance of ViewHolder and return it
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

    // implement OnClickListener
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView userName;
        private RatingBar usreRatingBar;
        private TextView userComment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.rowCustName);
            usreRatingBar = itemView.findViewById(R.id.rowRatingBar);
            userComment = itemView.findViewById(R.id.rowCustComment);
            itemView.setOnClickListener(this);
        }

        @Override   // callback function when clicking viewHolder
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }
}
