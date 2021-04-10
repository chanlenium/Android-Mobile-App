package com.example.retrofitexample;

import com.google.gson.JsonArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("todos")
    Call<List<TodoItem>> getList();

    @GET("todos")
    Call<JsonArray>  getListAssJson();

//    @GET("users")
//    Call<List<TodoItem>> getList();
}
