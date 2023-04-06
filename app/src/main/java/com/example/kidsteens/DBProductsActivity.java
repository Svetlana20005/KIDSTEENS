package com.example.kidsteens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class DBProductsActivity extends AppCompatActivity {
    Button addProducts;
    ListView productsList;
    DBProducts dbProducts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbproducts);
        addProducts = findViewById(R.id.addProducts);
        productsList = findViewById(R.id.productsList);
        dbProducts = new DBProducts(getBaseContext());
        addProducts.setOnClickListener((v) ->{
            startActivity(new Intent(getBaseContext(), AddProductsActivity.class));
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Product> list = dbProducts.getAll();
        ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(
                getBaseContext(),
                android.R.layout.simple_list_item_1,
                list
        );
        productsList.setAdapter(adapter);
    }
}