package com.example.lab04;

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

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    ArrayList<Dish> dishList;
    Context context;

    public CustomAdapter(ArrayList<Dish> dishList, Context context) {
        this.dishList = dishList;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dishName;
        RatingBar dishRating;
        ImageView dishImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dishName = itemView.findViewById(R.id.dishName);
            dishRating = itemView.findViewById(R.id.dishRating);
            dishRating.setIsIndicator(true);    // read only number of stars
            dishImage = itemView.findViewById(R.id.dishImage);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;    // Create actual instance of ViewHolder and return it
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dishName.setText(String.valueOf(dishList.get(position).getDishName()));
        holder.dishRating.setRating(dishList.get(position).getDishRating());
        holder.dishImage.setImageResource(dishList.get(position).getDishImage());

        holder.dishImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog(view, position);
            }
        });
    }

    private void alertDialog(View view, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle(" Important Notice ")
                .setMessage("Display information of selected dish")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openSelectedDishDetail(view, position);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(), "Cancel", Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void openSelectedDishDetail(View view, int position) {
        Intent intent = new Intent(view.getContext(), SelectedDishActivity.class);

        Dish selectedDish = new Dish(String.valueOf(dishList.get(position).getDishName()),
                dishList.get(position).getDishImage(),
                dishList.get(position).getDishRating());
        intent.putExtra("selectedDish", selectedDish);

        view.getContext().startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return (dishList != null ? dishList.size() : 0);
    }


}
