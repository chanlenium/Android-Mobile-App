## Virtual food and drink order App
* Demo link : https://appetize.io/app/1acrdz9d0a383k934x0q140h5w?device=pixel4&scale=75&orientation=portrait&osVersion=10.0

## Objectives
* Using `Intent` to pass and feedback message between Activities.
    * An Intent is a messaging object used to request an action from another app component (Activity).
* Use `UI element` to select and display order items.
* Display total price in Canada currency

## Outline
- Making a class `Menu` to generate the object for food and drink.
- To pass the object for food and drink to another `activity`, implement `Parcelable` interface as follows.
```
public class Menu implements Parcelable {
    private String name;    // variable for menu name
    private double price;   // variable for menu price
    private boolean checked;// variable for menu checked
    ...
}
```
* *Main activity* opens the second activitiy(*selectFoodActivity*) where user selects food and drink items as follows.
    * Here, there is no message passed.
```
private void openSelectFoodActivity() {
    Intent intent = new Intent(this, selectFoodActivity.class);
    startActivity(intent);  // launch an Activity
}
``` 
* The second activity(*selectFoodActivity*) opens the third activity(*summaryActivity*) where user confirms the menu ordered.
    * Here, message for ordered menu items is passed to the third activity
    * User can edit the order, so we implemented the `Intent` using `startActivityForResult` as follows.
```
private void openSummaryActivity() {    // Open summaryActivity by passing arrayList
    Intent intent = new Intent(this, summaryActivity.class);
    intent.putParcelableArrayListExtra("foodList", foodList);
    intent.putParcelableArrayListExtra("drinkList", drinkList);
    startActivityForResult(intent, SUMMARY_CODE_REQUEST);
}
```
```
// Callback function once summaryActivity works done
@Override
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if(requestCode == SUMMARY_CODE_REQUEST && resultCode == RESULT_OK)
        Toast.makeText(this, "Edit an order", Toast.LENGTH_SHORT).show();
}
```
```
private void requestEditOrder() { // Open selectedFoodActivity for editing order
    Intent intent = new Intent();
    setResult(RESULT_OK, intent); // Here, 100 is same as the REQUEST_CODE from selectFoodActivity
    finish();
}
```
* Using 'NumberFormat' to display total price in terms of CAD as follows.
```
private void displayTotal(double total) {
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    formatter.setMaximumFractionDigits(2);
    formatter.setCurrency(Currency.getInstance("CAD"));
    totalEt.setText(formatter.format(total));
}
```

## Screenshot
<img src="https://github.com/chanlenium/Android-Mobile-App/blob/main/04_Multiple%20Activities%20and%20Intents/Assignment01/Screenshots.png" width="900" height="350" />
