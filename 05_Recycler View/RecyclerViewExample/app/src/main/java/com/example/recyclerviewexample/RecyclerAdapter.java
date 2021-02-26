package com.example.recyclerviewexample;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

// 2. extends RecyclerView.Adapter<ViewHolderName> (Here, ViewHolderName is "MyViewHolder>
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private static final String TAG = "RecyclerAdapter";
    int count = 0;
    List<Product> list;

    // Constructor of Adapter class
    public RecyclerAdapter(List<Product> list) {
        this.list = list;
    }

    // 3. Implement default onCreateViewHolder, onBindViewHolder, getItemCount
    @NonNull
    // 4. Create new views (invoked by the layout manager)
    // 리스트 뷰가 처음 생성될때의 생명주기(onCreate와 유사)
    // Transfer xml(row_item.xml) to an object and create each row(View object) using LayoutInflater
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create an individual row necessary to display the item
        // Create a new view, which defines the UI of the list item

        Log.i(TAG, "onCreateViewHolder: " + count++);

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);

        // Create actual instance of MyViewHolder class and return it
        return new MyViewHolder(view);
    }

    // 5. Lifecyle when actual view is added to list (Bind time!)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.textView.setText(String.valueOf(list.get(position).getName()));
        holder.rowCountTextView.setText(String.valueOf(position));
    }

    // 6. Represent the number of views in a recycler view
    @Override
    public int getItemCount() {
        return (list != null ? list.size() : 0);
    }

    // 1. Make subclass MyViewHolder which inherits from "RecyclerView.ViewHolder"
    // Role: manage rows (i.e., itemView) => 각 row에 어떤 view들이 present될지 관리
    // Provide a reference to the type of views that we are using (custom ViewHolder)
    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView, rowCountTextView;

        // Receive View object (itemView) and allocate view element to fields of MyViewHolder class
        // Constructor : receiving an actual itemView and mapping components in the itemView to fields
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);    // itemView is actual view that we have just created
            // Mapping components in the itemView to fields
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            rowCountTextView = itemView.findViewById(R.id.rowCountTextView);
        }
    }

}
