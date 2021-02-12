package com.example.lab03;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// 2. extends RecyclerView.Adapter<AdapterClassName.ViewHolderName>
// (DishAdapter.DishViewHolder>
public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder>{

    // 3. create field and constructor
    List<Dish> dishList;
    Context context;

    public DishAdapter(MainActivity context, List<Dish> dishList) {
        this.context=context;
        this.dishList = dishList;
    }

    // 4. Implement default onCreateViewHolder, onBindViewHolder, getItemCount
    @NonNull
    // 5. Create new views (invoked by the layout manager when list view is firstly created
    // Transfer xml(row_item.xml) to an View object using LayoutInflater
    // Create ViewHolder instance with the view
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_view, parent, false);
        return new DishViewHolder(view);    // Create actual instance of ViewHolder and return it
    }

    // 6. Lifecyle when actual view is added to list (Bind time!)
    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {
        holder.dishImage.setImageResource(dishList.get(position).getDishImage());
        holder.dishName.setText(String.valueOf(dishList.get(position).getDishName()));
        holder.dishRating.setRating(dishList.get(position).getDishRating());

        holder.dishImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to image click
                Toast.makeText(context, dishList.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    // 6-2. Return the number of views(rows)
    public int getItemCount()  {
        return (dishList != null ? dishList.size() : 0);
    }

    // 2. Create subclass DishViewHolder which inherits from "RecyclerView.ViewHolder"
    // Role: manage rows (i.e., itemView) => responsible for presenting contents for each view(row)
    // Provide a reference to the type of views that we are using (custom ViewHolder)
    class DishViewHolder extends RecyclerView.ViewHolder{
        ImageView dishImage;
        TextView dishName;
        RatingBar dishRating;

        // Receive View object (itemView) and allocate view element to fields of DishViewHolder class
        // Constructor : receiving an actual itemView and mapping components in the itemView to fields
        public DishViewHolder(@NonNull View itemView) {
            super(itemView);    // itemView is actual view that we have just create
            // Mapping components in the itemView to fields
            dishImage = itemView.findViewById(R.id.dishImage);
            dishName = itemView.findViewById(R.id.dishName);
            dishRating = itemView.findViewById(R.id.ratingBar);
            dishRating.setIsIndicator(true);    // read only number of stars
        }
    }


}
