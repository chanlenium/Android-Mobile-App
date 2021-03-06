/** Adapter for associating RecyclerView with row item data
 * (1) Implement default onCreateViewHolder, onBindViewHolder, getItemCount, and ViewHolder
 * (2) Implement click listener
 * (3) Open another activity using intent with passed data
 */
package com.example.lab04.screens;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab04.Dish;
import com.example.lab04.R;
import com.example.lab04.SelectedDishActivity;

import java.util.ArrayList;

// Extend RecyclerView.Adapter<ViewHolderName>
public class DishRecyclerViewAdapter extends RecyclerView.Adapter<DishRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Dish> dishList;
    private Context context;

    // Constructor of Adapter class
    public DishRecyclerViewAdapter(ArrayList<Dish> dishList, Context context) {
        this.dishList = dishList;
        this.context = context;
    }

    // The first life-cycle when the recyclerView is created (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Transfer XML to an object and create each row(View object) using LayoutInflater
        // Inflate the layout for this ViewHolder
        // In here, view indicate "row_item.xml"
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;    // Create actual instance of ViewHolder and return it
    }

    // Make subclass ViewHolder which inherits from "RecyclerView.ViewHolder"
    // Role: manage rows (i.e., itemView) => 각 row에 어떤 view들이 present될지 관리
    // Provide a reference to the type of views that we are using
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView dishName;
        private RatingBar dishRating;
        private ImageView dishImage;

        // Receive View object (itemView) and associate view elements with ViewHolder class fields
        // In here, itemView indicates 'row_item.xml'
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dishName = itemView.findViewById(R.id.dishName);
            dishRating = itemView.findViewById(R.id.dishRating);
            dishRating.setIsIndicator(true);    // read only number of stars
            dishImage = itemView.findViewById(R.id.dishImage);
        }
    }

    // Lifecyle when actual view is added to list (Bind time!)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dishName.setText(String.valueOf(dishList.get(position).getDishName()));
        holder.dishRating.setRating(dishList.get(position).getDishRating());
        holder.dishImage.setImageResource(dishList.get(position).getDishImage());

        // When click a image, call Alter Dialog
        holder.dishImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog(view, position);
            }
        });
    }

    // Dialog is a small window that prompts the user to make a decision or enter additional information.
    private void alertDialog(View view, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Alert")
                .setMessage("Display information of selected dish")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Open the Select Dish Activity
                        openSelectedDishDetail(view, position);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Cancel", Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void openSelectedDishDetail(View view, int position) {
        // Explicit intent
        Intent intent = new Intent(view.getContext(), SelectedDishActivity.class);

        // set passed value(selected Dish) as clicked dish
        Dish selectedDish = new Dish(String.valueOf(dishList.get(position).getDishName()),
                dishList.get(position).getDishImage(),
                dishList.get(position).getDishRating());
        // put data into intent
        intent.putExtra("selectedDish", selectedDish);

        view.getContext().startActivity(intent);
    }

    // return number of items in dishList
    @Override
    public int getItemCount() {
        return (dishList != null ? dishList.size() : 0);
    }
}
