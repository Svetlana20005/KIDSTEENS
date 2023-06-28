package com.example.kidsteens.server;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserServer {
    @POST("/login")
    @FormUrlEncoded
    Call<Token> login(@Field("phone") String phone, @Field("code") String code);
}
