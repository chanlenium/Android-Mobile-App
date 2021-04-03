## Customer feedback Database
* Demo link : https://appetize.io/app/q7hhev775xcyjjx3r07pu4ux6g?device=nexus5&scale=75&orientation=portrait&osVersion=8.1

## Objectives
* Using `Room Database` and `LiveData` to implement database
* Storing data to SQLite database
* Retrieving data from database and displaying it using recyclerview
* Creating/Using `Entity`, `Dao`, `Database`, and `ViewModel` class

## Outline
* The App is composed of `Model`, `Database`, and `Screen` packages
### Model
* Implement `Customer` Entity and `CustomerViewModel`
* Customer Entity : define *tableName* and *fileds* in a database
    * Represent a table within the database
    * Implements `Parcelable` interface for data serialization 
```
public class Customer implements Parcelable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "user_name")
    private String name;
    @ColumnInfo(name = "user_rating")
    private double rating;
    @ColumnInfo(name = "user_comment")
    private String comment;
    ...
}
```
* Create `ViewModel` class by extending AndroidViewModel to use LiveData
    * Implement SQL command such as `insert`, `getCustomer` run in background
```
public LiveData<List<Customer>> customerList;   // reference to LiveData
private CustomerDao customerDao;    // reference to CustomerDao
private MyDatabase db;  // reference to database

public CustomerViewModel(@NonNull Application application) {    // constructor
    super(application);
    // get instances for all references
    db = Room.databaseBuilder(application, MyDatabase.class, "customer").build();
    customerDao = db.getCustomerDao();
    customerList = customerDao.getAll();
}

public void insert(Customer customer) {
    new Thread(new Runnable() {
        public void run() {
            // code in a background
            customerDao.insert(customer);   // add new Customer
        }
    }).start();
}
```

### Database
* Dao (Data access object)
    * Contain the methods used for accessing the database
    * Define queries
```
@Dao
public interface CustomerDao {  // define queries
    @Insert
    void insert(Customer customer);

    @Query("select * from customers where user_name LIKE :name")
    Customer getCustomer(String name);

    @Query("select * from customers")
    LiveData<List<Customer>> getAll();
}
```
* Database
    * Abstract calss that extends `RommeDatabase`
    * Contain abstract method to retrieve the `Dao` implementation
```
@Database(entities = {Customer.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract CustomerDao getCustomerDao();
}
```

### Screen
* Create a viewModel object
```
model = new ViewModelProvider(this).get(CustomerViewModel.class);
// Since model.customerList is "LiveData" by executing customerDao.getAll()
// observer can update its state whenever the value of model.customerList is changed
model.customerList.observe(this, new Observer<List<Customer>>() {
    @Override
    public void onChanged(List<Customer> customers) {
        // Whenever the value of model.customerList is changed, the App prints its elements
        for(Customer customer : customers){
            Log.d("User", customer.getName());
        }
    }
});
```
* Implement an action of *SAVE USER* button
    * check input validation and insert the user into a table
```
// check input user is already in a list or not
Customer searchedCustomer = model.getCustomer(name);
// if input user is a new user, the app create user object and insert the user into a DB
Customer customer = new Customer(name, rating, comment);
model.insert(customer);
```
* Open intent by passing current customer list
```
Intent intent = new Intent(this, FeedbackActivity.class);   // explicit intent
model.customerList.observe(this, currentValue -> {
    // In here, currentValue is user list got from @Query("select * from customers")
    intent.putParcelableArrayListExtra("FeedbackPage", (ArrayList<? extends Parcelable>) currentValue);
});
startActivity(intent);
```

## Screenshots
* link : https://github.com/chanlenium/Android-Mobile-App/tree/main/09_Room%20Database/Lab6/screenshots
