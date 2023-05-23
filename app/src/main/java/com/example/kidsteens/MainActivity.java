package com.example.kidsteens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.ui_view.ViewProvider;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MapView mapview;
    ArrayList<Shop> shops = new ArrayList<>();
    MyRunnable runnable = new MyRunnable() {
        @Override
        public void run(ArrayList<Feature> arrayList) {
            runOnUiThread(()->{
                for (Feature feature:
                        arrayList) {
                    Shop shop = new Shop();
                    Address address = new Address();
                    address.setLat(feature.geometry.coordinates[0]);
                    address.setLon(feature.geometry.coordinates[1]);
                    shop.setAddress(address);
                    shop.setName(feature.properties.address);
                    addPlacemark(shop);
                    shops.add(shop);
                }
            });
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapKitFactory.setApiKey("fc9af0dd-f0af-4679-87db-9442334d08f4");
        MapKitFactory.initialize(this);
        setContentView(R.layout.activity_main);
        mapview = (MapView)findViewById(R.id.mapview);
        mapview.getMap().move(
                new CameraPosition(new Point(55.506943, 47.491764), 11.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);
        ShopsServer.sendRequest(runnable);
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
        view.setOnClickListener((v)-> {
        });
        //((VectorDrawable) view.getBackground()).setTint(mo.getIntColor(false));
        Point point = new Point(mo.getAddress().getLat(), mo.getAddress().getLon());
        mo.setMapObjectTapListener(new MapObjectTapListener() {
            @Override
            public boolean onMapObjectTap(@NonNull MapObject mapObject, @NonNull Point point) {
                DialogFragment newFragment = new ChooseShopDialogFragment(()->{
                    Intent intent = new Intent(MainActivity.this, ShopActivity.class);
                    startActivity(intent);
                },()->{});
                newFragment.show(getSupportFragmentManager(),"chooseShop");
                return true;
            }
        });
        mapview.getMap().getMapObjects()
                .addPlacemark(point, new ViewProvider(view))
                .addTapListener(mo.getMapObjectTapListener());

    }
}