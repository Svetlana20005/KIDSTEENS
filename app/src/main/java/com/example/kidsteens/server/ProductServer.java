package com.example.kidsteens.server;

import com.example.kidsteens.classes.Category;
import com.example.kidsteens.classes.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductServer {
    @GET("/product/{id}")
    Call<Product> product(@Path("id") int id);

    @PUT("/product/{id}")
    Call<Void> saveProduct(@Path("id") String id, @Body Product product);

    @GET("/product")
    Call<ArrayList<Product>> products();
    @GET("/product")
    Call<ArrayList<Product>> productsByCategory(@Query("category_id") int category_id);
}
