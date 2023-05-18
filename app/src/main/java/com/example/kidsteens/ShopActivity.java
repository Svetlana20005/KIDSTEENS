package com.example.kidsteens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;


public class ShopActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayList<Category> categories;
    ArrayList<Product> products;
    DBCategories dbCategories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        spinner = findViewById(R.id.spinner);
        dbCategories = new DBCategories(getBaseContext());
        categories = dbCategories.getAll();
        spinner.setAdapter(new ArrayAdapter<Category>(getBaseContext(), android.R.layout.simple_list_item_1, categories));
        spinner.setSelection(-1);
    }
}