package com.example.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getData(View view) {
        ApiService service = ApiManager.getService();   // get instance of interface ApiService
        Call<List<TodoItem>> call = service.getList();
        Call<JsonArray> jsonArrayCall = service.getListAssJson();

        call.enqueue(new Callback<List<TodoItem>>(){

            @Override   // when response exists
            public void onResponse(Call<List<TodoItem>> call, Response<List<TodoItem>> response) {
                Log.d("TAG", "onResponse: " + response.code());
                if(response.isSuccessful()){
                    List<TodoItem> items = response.body();
                    for(TodoItem item : items){
                        Log.d("TAG", "onResponse: " + item.getTitle());
                    }
                    displayData(items);
                }
            }

            @Override   // when response is not exist
            public void onFailure(Call<List<TodoItem>> call, Throwable t) {
                Toast.makeText(MainActivity.this , "Error" , Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void displayData(List<TodoItem> items) {
        ListView listView = findViewById(R.id.list_view);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
    }
}