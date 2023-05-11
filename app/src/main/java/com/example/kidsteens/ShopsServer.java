package com.example.kidsteens;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShopsServer {
    public static void sendRequest(Runnable runnable) {
        HttpUrl.Builder url = HttpUrl.parse("https://5ka.ru/api/v2/stores/?bbox=55.4734638973159,47.361387184204105,55.54039353321981,47.622140815795895").newBuilder();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url.build().toString())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                int code = response.code();
                String result = " ";
                switch (code){
                    case 200:
                    case 201:
                        result = response.body().string().substring(9);
                        result = result.substring(0, result.length()-2);
                        Gson gson = new Gson();
                        ShopResponse sr = gson.fromJson(result, ShopResponse.class);
                        //
                        runnable.run(sr.data.features);
                        break;
                    default:
                        result = "Произошла ошибка";
                        Log.i(ShopsServer.class.toString(), response.body().toString());
                }
                final String toTv = result;
            }
        });
    }
}
