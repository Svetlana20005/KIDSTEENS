package com.example.kidsteens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddProductsActivity extends AppCompatActivity {
    EditText productName, price, categoryName;
    Button saveProduct;
    DBProducts dbProducts;
    DBCategories dbCategories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        productName = findViewById(R.id.productName);
        price = findViewById(R.id.price);
        categoryName = findViewById(R.id.categoryName);
        saveProduct = findViewById(R.id.saveProduct);
        saveProduct.setOnClickListener((v) -> save());
        dbProducts = new DBProducts(getBaseContext());
        dbCategories = new DBCategories(getBaseContext());
        ShopsServer.sendRequest();
    }
    public void save(){
        String cN = categoryName.getText().toString();
        Category category = dbCategories.selectByName(cN);
        String p = price.getText().toString();
        Double priceDouble = Double.parseDouble(p);
        String name = productName.getText().toString();
        Product product = new Product(name, category.getId(), priceDouble);
        dbProducts.insert(product);
        finish();
    }
}