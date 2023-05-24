package com.example.kidsteens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.kidsteens.classes.Category;
import com.example.kidsteens.classes.Product;
import com.example.kidsteens.db.DBCategories;
import com.example.kidsteens.db.DBProducts;

import java.util.ArrayList;


public class ShopActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayList<Category> categories;
    ArrayList<Product> products;
    DBCategories dbCategories;
    DBProducts dbProducts;
    ListView productsLV;
    MyProductAdapter myProductAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        spinner = findViewById(R.id.spinner);
        productsLV = findViewById(R.id.productsLV);
        dbCategories = new DBCategories(getBaseContext());
        dbProducts = new DBProducts(getBaseContext());
        categories = dbCategories.getAll();
        products = dbProducts.getAll();
        spinner.setAdapter(new ArrayAdapter<Category>(getBaseContext(), android.R.layout.simple_list_item_1, categories));
        productsLV.setAdapter(myProductAdapter = new MyProductAdapter(getBaseContext(), products));

        spinner.setSelection(-1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category category = categories.get(position);
                products.clear();
                products.addAll(dbProducts.getAllByCategory(category));
                myProductAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}