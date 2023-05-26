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
import com.example.kidsteens.server.RetrofitHelper;

import java.util.ArrayList;


public class ShopActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayList<Category> categories = new ArrayList<>();
    ArrayList<Product> products = new ArrayList<>();
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
        RetrofitHelper.getAllCategories(new MyRunnable<ArrayList<Category>>() {
            @Override
            public void run(ArrayList<Category> result) {
                categories.clear();
                if(result != null){
                    categories.addAll(result);
                }
                ArrayAdapter adapter = (ArrayAdapter) spinner.getAdapter();
                adapter.notifyDataSetChanged();
            }
        });
        RetrofitHelper.getAllProducts(new MyRunnable<ArrayList<Product>>() {
            @Override
            public void run(ArrayList<Product> result) {
                products.clear();
                if(result != null){
                    products.addAll(result);
                }
                ArrayAdapter adapter = (ArrayAdapter) productsLV.getAdapter();
                adapter.notifyDataSetChanged();
            }
        });
        spinner.setAdapter(new ArrayAdapter<Category>(getBaseContext(), android.R.layout.simple_list_item_1, categories));
        productsLV.setAdapter(myProductAdapter = new MyProductAdapter(getBaseContext(), products));

        spinner.setSelection(-1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category category = categories.get(position);
                RetrofitHelper.getAllProductsByCategory(category, new MyRunnable<ArrayList<Product>>() {
                    @Override
                    public void run(ArrayList<Product> result) {
                        products.clear();
                        if(result != null){
                            products.addAll(result);
                        }
                        ArrayAdapter adapter = (ArrayAdapter) productsLV.getAdapter();
                        adapter.notifyDataSetChanged();
                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}