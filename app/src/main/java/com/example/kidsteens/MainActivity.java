package com.example.kidsteens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.view.View;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraListener;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CameraUpdateReason;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.ui_view.ViewProvider;

public class MainActivity extends AppCompatActivity {
    MapView mapview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapKitFactory.setApiKey("fc9af0dd-f0af-4679-87db-9442334d08f4");
        MapKitFactory.initialize(this);
        setContentView(R.layout.activity_main);
        mapview = (MapView)findViewById(R.id.mapview);
        mapview.getMap().move(
                new CameraPosition(new Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);
        ShopsServer.sendRequest(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(()->{

                });
            }
        });
    }
    @Override
    protected void onStop() {
        mapview.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapview.onStart();
    }
    private  void addPlacemark(Shop mo){
        View view = new View(this);
        view.setBackground(this.getDrawable(R.drawable.ic_baseline_location_on_24));
        view.setMinimumWidth(100);
        view.setMinimumHeight(100);
        //((VectorDrawable) view.getBackground()).setTint(mo.getIntColor(false));
        mapview.getMap().getMapObjects()
                .addPlacemark(new Point(mo.getAddress().getLat(),mo.getAddress().getLon()), new ViewProvider(view));
    }
}