package com.example.kidsteens;

import com.example.kidsteens.classes.Category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoryServer {
    @POST("/category")
    @FormUrlEncoded
    public Call<ArrayList<Category>> categories(@Field("name") String name);

    @GET("/category/{idx}")
    public Call<Category> category(@Path("idx") int id);
    @PUT("/user")
    public Call<Void> saveCategory(@Body Category category);
}
