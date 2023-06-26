package com.example.kidsteens.server;


import com.example.kidsteens.classes.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserServer {
    @GET("/user/{id}")
    public Call<User> user(@Path("id") int id);

    @PUT("/user/{id}")
    public Call<Void> saveUser(@Path("id") String id, @Body User user);

    @GET("/user")
    Call<ArrayList<User>> users();

    @PUT("/login")
    @FormUrlEncoded
    Call<Void> login(@Field("phone") String phone, @Field("code") String code);
}
