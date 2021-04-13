package com.example.firebaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLDisplay;

public class MainActivity extends AppCompatActivity {
    EditText nameEt, idEt;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idEt = findViewById(R.id.idEt);
        nameEt = findViewById(R.id.nameEt);
        // write a message to the database
        // getInstance()를 사용하여 데이터베이스의 인스턴스를 검색하고 쓰려는 위치를 참조
        database = FirebaseDatabase.getInstance();
    }

    public void addData(View view) {
        String userName = nameEt.getText().toString();
        String userId = idEt.getText().toString();

        //DatabaseReference myRef = database.getReference("message"); // set key
        //myRef.setValue("Welcome to Android!");  // set value

        DatabaseReference myRef = database.getReference("users"); // set key
        User user = new User(userId, userName, userName + "@gmail.com");
        myRef.child(userId).setValue(user);  // set value


    }

    public void getData(View view) {
        // Read from the database
        DatabaseReference myRef = database.getReference("users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                List<User> list = new ArrayList<>();
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    User user = child.getValue(User.class); // create User.class object and assign value to it
                    list.add(user);
                    Log.d("TAG", "Value is: " + user.getName());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }
}