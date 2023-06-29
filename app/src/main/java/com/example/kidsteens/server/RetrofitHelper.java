package com.example.kidsteens.server;

import static android.content.Context.MODE_PRIVATE;

import static com.example.kidsteens.StartActivity.TOKEN;

import android.content.SharedPreferences;

import com.example.kidsteens.MyRunnable;
import com.example.kidsteens.classes.Category;
import com.example.kidsteens.classes.Product;
import com.example.kidsteens.classes.User;
import com.example.kidsteens.server.CategoryServer;
import com.example.kidsteens.server.ProductServer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static Retrofit instance = null;
    public static SharedPreferences sharedPreferences;
    private RetrofitHelper(){}

    public static Retrofit getServer(){
        if(instance == null){
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(chain -> {
                        String token = sharedPreferences.getString(TOKEN, "");
                        Request.Builder newRequest = chain.request().newBuilder();
                            newRequest.addHeader("Authorization", token);
                        return chain.proceed(newRequest.build());
                    }).build();
            instance = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.251:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return instance;
    }

    public static ArrayList<Category> getAllCategories(MyRunnable<ArrayList<Category>> runnable){
        CategoryServer cs = getServer().create(CategoryServer.class);
        Call<ArrayList<Category>> categories = cs.categories();
        categories.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                runnable.run(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return null;
    }
    public static ArrayList<Product> getAllProducts(MyRunnable<ArrayList<Product>> runnable){
        return getAllProductsByCategory(null, runnable);
    }
    public static ArrayList<Product> getAllProductsByCategory(Category category, MyRunnable<ArrayList<Product>> runnable){
        ProductServer cs = getServer().create(ProductServer.class);
        Call<ArrayList<Product>> products;
        if(category != null){
            products = cs.productsByCategory((int) category.getId());
        }else{
            products = cs.products();
        }
        products.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                runnable.run(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return null;
    }

    public static Void login(MyRunnable<Token> runnable, LoginData loginData){
        UserServer us = getServer().create(UserServer.class);
        Call<Token> users = us.login(loginData.getPhone(), loginData.getCode());
        users.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                runnable.run(response.body());
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return null;
    }
}
