package com.example.viewmodel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {
    MyViewModel viewModel;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the ViewModel.
        // Use `ViewModelProvider` to associate ViewModel with Activity.`
        // When the Activity first starts, the ViewModelProvider will create the ViewModel
        // When the Activity is destroyed, for example through a configuration change, the ViewModel persists.
        // When the Activity is re-created, the ViewModelProviders return the existing ViewModel.
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        textView = findViewById(R.id.textView);

        viewModel.getNumber().observe(this, currentValue ->{
            Log.d("TAG", "" + currentValue);
            textView.setText(String.valueOf(currentValue));
        });
    }

    // increment the value when button is clicked
    public void incrementValue(View view) {
        // increment number and UI will be updated
        viewModel.incrementNumber();    // with LiveData
    }

    public void showDialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(" Important Notice ")
                .setMessage(" Update the Software")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_LONG).show();
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
    }
}