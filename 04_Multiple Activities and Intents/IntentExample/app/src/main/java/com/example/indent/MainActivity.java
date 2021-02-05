package com.example.indent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText nameEt;
    private TextView ageTv;
    int passedCode = 100;
    int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEt = findViewById(R.id.nameEt);
        ageTv = findViewById(R.id.ageTv);
    }

    public void openSecondActivity(View view) {
        // Generate Intent object
        Intent intent = new Intent(this, SecondActivity.class);
        // Pass the nameEt value to the secondActivity
        intent.putExtra("name", nameEt.getText().toString());
        startActivity(intent);
    }

    /*
    • Launch an activity for which you would like a result when it finished
    • When this activity exits, your 'onActivityResult()' method will be called
      with the given 'requestCode'.
    • Using a 'negative requestCode' is the same as calling MainActivity(Intent)
      (the activity is not launched as a sub-activity).
      Example of 'resultCode' : Activity.RESULT_OK 또는 Activity.RESULT_CANCELED
     */
    public void openSecondActivityusingStartActivityforResult(View view) {
        // Generate Intent object
        Intent intent = new Intent(this, SecondActivity.class);
        // Pass the nameEt value to the secondActivity
        intent.putExtra("name", nameEt.getText().toString());
        startActivityForResult(intent, passedCode);
    }


    // Implicit Intent to connect Google
    // URI는 리소스를 식별하기 위한 문자열 전반을 나타내며, URL은 리소스의 장소(네트워크 상의 위치)를 나타냄
    // => URL은 URI의 subset임
    public void connectGoogle(View view) {
        String url = "http://www.goole.com";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW); // Invoke when ACTION_VIEW exists
        intent.setData(uri);
        startActivity(intent);
    }

    // Implicit Intent to take photos
    // Launch the intent using startActivity for result
    // Override onActivityResult()
    public void dispatchTakePictureIntent(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    @Override   // Callback function once the second activity's work is done
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /* check if the Request Code is same as what is passed Code (here, it is 100) */
        if(requestCode == passedCode){
            if(resultCode == RESULT_OK){
                ageTv.setText(data.getStringExtra("age"));
                Toast.makeText(this, data.getStringExtra("age"), Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "no Result", Toast.LENGTH_LONG).show();
            }
        }

        // get the Bitmap image in the extras, under the key "data"
        // Retrieve image and displays it in an Image view
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bitmap image = (Bitmap) data.getExtras().get("data");
            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageBitmap(image);
        }
    }


}