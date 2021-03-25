## Save users names to file
* Demo link : https://appetize.io/app/x22zbcg2fb449kmqnnymwtyrem?device=nexus5&scale=75&orientation=portrait&osVersion=8.1

## Objectives
* Save users names to file ( Shared Preference )
* Read the list of users name and display it in RecyclerView
* Implement option menu

## Outline
* Design option menu to open second activity
* Override onCreateOptionsMenu() to inflate a menu resource(defined in XML)
```
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    return true;
}
```

* When the user selects an item from the options menu, the system calls your activity's onOptionsItemSelected() method
```
public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    int id = item.getItemId();
    if(id == R.id.pageMenuOption){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("filename", FILE_NAME);
        startActivity(intent);
        Toast.makeText(this, "page2", Toast.LENGTH_SHORT).show();
        return true;
    }
    return super.onOptionsItemSelected(item);
}
```

* Implementing event listner (button clicking) in MainActivity
   * When clicking `ADD USER TO FILE` button, the app uses the `Editor` to write values to the SharedPreferences
   * Store user input by coverting `Java String object` to `JSON string` 
```
// GSON : library to change java object to JSON(key, value)
Gson gson = new Gson();
String jsonString = gson.toJson(userList);  // change userList to JSON string

// get Shared Preference instance
// object points to a file containing key-value pairs
SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
SharedPreferences.Editor editor = sharedPreferences.edit(); // using the Editor to write values to the SharedPreferences
editor.putString("username", jsonString);
editor.commit();
```
   * When clicking `DELETE FILE` button, the app delete all contents in file using alert dialog
```
AlertDialog.Builder builder = new AlertDialog.Builder(this);
builder.setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.ic_baseline_warning_24));
builder.setTitle("Deleting USERS_LIST_FILE")
        .setMessage("Are you sure you want to delete the file?")
        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear(); // clear all contents in file
                editor.commit();
                Toast.makeText(MainActivity.this, "Delete done", Toast.LENGTH_LONG).show();
            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_LONG).show();
            }
        });
AlertDialog dialog = builder.create();
dialog.show();
```

* Implementing event listner (button clicking) in SecondActivity
   * When clicking `DISPLAY USER` button, the app display all stored user data in RecyclerView
   * Display user name data by coverting `JSON string` to `Java String` object 

```
public void displayUsers(View view) {
    if(intent != null){
        String fileName = intent.getStringExtra("filename");    // get String passed from MainActivity
        SharedPreferences sharedPreferences = getSharedPreferences(fileName, MODE_PRIVATE);
        // Get string which key is "username". If the searched key(i.e., "username" does not exist, it returns "no_name")
        String jsonString = sharedPreferences.getString("username", "no_name");

        if(jsonString.equals("no_name")){
            Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
        }else{
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<UserModel>>(){}.getType();
            ArrayList<UserModel> userList = gson.fromJson(jsonString, type);    // change JSON data to type data
            Log.d("TAG", String.valueOf(userList.get(0).getName()));

            // add(associate) "R.id.userListViewFragment" with Fragment 'UserListViewFragment'
            fragmentTransaction.add(R.id.userListViewFragment, new UserListViewFragment(userList)).commit();
        }
    }
}
```

## Video Recoding
* Video link : https://github.com/chanlenium/Android-Mobile-App/blob/main/08_Data%20Persistence%20in%20android/Lab05_Screen%20Recording.mov
