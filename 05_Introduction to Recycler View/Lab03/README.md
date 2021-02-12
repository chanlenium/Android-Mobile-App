## My Favorite Dishes
* Demo link : https://appetize.io/app/1acrdz9d0a383k934x0q140h5w?device=pixel4&scale=75&orientation=portrait&osVersion=10.0

## Objectives
* Familiar with using `RecyclerView`.
    * RecyclerView is the *ViewGroup* that contains the views corresponding to data.
* Design simple *RecyclerView*.
* Implement custom Adapter, ViewHolder, and OnClickListener.
* Display items in List array.

## Outline
* Design
    * Add *RecyclerView* component to activity_main.xml with customID (e.g., `recyclerView`)
    * Create another custom layout for each row (i.e., for each view) (e.g., `row_view.xml`)

* Create Dish class
    * `String dishName`, `int dishImage`, `float dishRating`;
    * Make *constructor* and *getter/setter*
    * Override `toString()`

* Create a custom adapter class (e.g., DishAdapter) which inherit from `RecyclerView.Adapter` 
    * Adapter is composed of (1)*onCreateViewHolder*, (2)*onBindViewHolder*, (3)*getItemCount*, and (4)*ViewHolder subclass*
* Create *ViewHolder* subclass which inherit from `RecyclerView.ViewHolder`
    * Role : Manage each row related to components in row (`row_view.xml`)
    * Mapping components in the itemView to fields
```
class DishViewHolder extends RecyclerView.ViewHolder{
    ImageView dishImage;
    TextView dishName;
    RatingBar dishRating;

    public DishViewHolder(@NonNull View itemView) {
        super(itemView);    // itemView is actual view that we have just create
        // Mapping components in the itemView to fields
        dishImage = itemView.findViewById(R.id.dishImage);
        dishName = itemView.findViewById(R.id.dishName);
        dishRating = itemView.findViewById(R.id.ratingBar);
        dishRating.setIsIndicator(true);    // read only number of stars
    }
}
```
* Add `extends RecyclerView.Adapter<RecyclerAdapter.ViewHoldersubclassName>`
* create constructor to receive data
* Implement default `onCreateViewHolder`, `onBindViewHolder`, `getItemCount`
* Modify `onCreateViewHolder` to create new views (invoked by the layout manager)
    * Transfer xml(`row_view.xml`) to an object and create each row(View object) using `LayoutInflater`
    * Create instance of `MyViewHolder` class with argument `View object` and return it
```
public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.row_view, parent, false);
    return new DishViewHolder(view);    // Create actual instance of ViewHolder and return it
}
```
* Modify `onBindViewHolder` to bind data with actual view
    * called when actual view is added
```
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
```
* Modify `getItemCount` (optional)
    * represent the number of views in a recycler view
```
public int getItemCount()  {
    return (dishList != null ? dishList.size() : 0);
}
```
* In MainActivity, define `Layout Manager`
* In MainActivity, attach `adapter` to `recyclerView`
```
recyclerView.setLayoutManager(new LinearLayoutManager(this));
recyclerView.setAdapter(dishAdapter);
```

## Screenshot
<img src="https://github.com/chanlenium/Android-Mobile-App/blob/main/05_Introduction%20to%20Recycler%20View/Lab03/Lab3Screenshot.png" width="900" height="350" />
