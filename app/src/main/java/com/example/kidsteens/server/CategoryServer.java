package com.example.kidsteens.server;

import com.example.kidsteens.classes.Category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoryServer {
    @GET("/category/{id}")
    public Call<Category> category(@Path("id") int id);

    @PUT("/category/{id}")
    public Call<Void> saveCategory(@Path("id") String id, @Body Category category);

    @GET("/category")
    Call<ArrayList<Category>> categories();
}
