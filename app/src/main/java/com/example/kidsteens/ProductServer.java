package com.example.kidsteens;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductServer {
    @POST("/product")
    @FormUrlEncoded
    public Call<ArrayList<Product>> products(@Field("name") String name, @Field("price") Double price, @Field("categoryId") int categoryId);

    @GET("/product/{idx}")
    public Call<Product> product(@Path("idx") int id);
    @PUT("/product")
    public Call<Void> savProduct(@Body Product product);
}
