package com.example.kidsteens;

import android.app.Application;

import com.example.kidsteens.server.RetrofitHelper;
import com.yandex.mapkit.MapKitFactory;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MapKitFactory.setApiKey("fc9af0dd-f0af-4679-87db-9442334d08f4");
        MapKitFactory.initialize(this);
        RetrofitHelper.sharedPreferences = getSharedPreferences("Tokens", MODE_PRIVATE);
    }
}
