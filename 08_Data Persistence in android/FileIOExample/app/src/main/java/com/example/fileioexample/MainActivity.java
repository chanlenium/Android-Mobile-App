package com.example.fileioexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    final String FILE_NAME = "file.txt";    // set file name
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.dataEt);
        textView = findViewById(R.id.textView2);
    }

    public void saveData(View view) {
        writeFileToInternalStorage();   // store data in internal storage
        writeFileToExternalStorage();   // store data in external storage
    }

    private void writeFileToInternalStorage() {
        // write to the file
        String data = editText.getText().toString();
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_APPEND);
            //FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            // View >> Tool Windows >> Device File Explorer >> data >> data >> com.example.fileioexample >> files
            fos.write(data.getBytes()); // write data in byte type
            fos.write("\n".getBytes()); // add newline
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("file error", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFileToExternalStorage(){
        // If Myfolder does not exist, it is created
        File path = getExternalFilesDir("Myfolder");
        File file = new File(path, FILE_NAME);
        String data = editText.getText().toString();
        editText.setText("");
        Toast.makeText(this, " Done ", Toast.LENGTH_SHORT).show();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData(View view) {
        // read data from assets
        readFileFromAssets();

        // read from internal storage
        StringBuilder stringBuilder = new StringBuilder(); // bridge between scanner and textView
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            Scanner sc = new Scanner(fis);
            String line = "";
            while(sc.hasNextLine()){    // if there is line to read
                line = sc.nextLine();
                stringBuilder.append(line).append("\n");
            }
            sc.close();
            String data = stringBuilder.toString();
            textView.setText(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readFileFromAssets(){
        try {
            InputStreamReader fis = new InputStreamReader(getAssets().open("file.txt"));
            Scanner sc = new Scanner(fis);
            String line = "";
            while(sc.hasNextLine()){    // if there is line to read
                line = sc.nextLine();
                Log.d("Name", line);
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}