package com.example.menuexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Creating an Options Menu **/
    // To specify the options menu for an activity,
    // override onCreateOptionsMenu() (fragments provide their own onCreateOptionsMenu() callback).
    // In this method, you can inflate your menu resource(defined in XML) into the Menu provided in the callback.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /** Handling click events **/
    // When the user selects an item from the options menu,
    // the system calls your activity's onOptionsItemSelected() method.
    // This method passes the MenuItem selected.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.item1){
            Toast.makeText(this, this.getString(R.string.toastMsg) + " "
                    + this.getString(R.string.item1), Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.item2){
            Toast.makeText(this, this.getString(R.string.toastMsg) + " "
                    + this.getString(R.string.item2), Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.item3){
            Toast.makeText(this, this.getString(R.string.toastMsg) + " "
                    + this.getString(R.string.item3), Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}