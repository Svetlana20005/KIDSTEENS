package com.example.kidsteens.db;

import static com.example.kidsteens.db.OpenHelper.PRODUCTS_CATEGORY_ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.kidsteens.Category;
import com.example.kidsteens.Product;

import java.util.ArrayList;

public class DBProducts {
    private OpenHelper openHelper;
    private SQLiteDatabase db;
    public DBProducts(Context ctx){
        openHelper = new OpenHelper(ctx);
        db = openHelper.getWritableDatabase();
    }
    public void insert(Product p){
        ContentValues cv = new ContentValues();
        cv.put(OpenHelper.PRODUCTS_NAME, p.getName());
        cv.put(PRODUCTS_CATEGORY_ID, p.getCategory_id());
        cv.put(OpenHelper.PRODUCTS_PRICE, p.getPrice());
        db.insert(OpenHelper.PRODUCTS_TABLE_NAME, null, cv);
    }
    public ArrayList<Product> getAll(){
        Cursor cursor = db.query(OpenHelper.PRODUCTS_TABLE_NAME,null,null,null,null,null,null);
        return getProductArrayList(cursor);
    }

    @NonNull
    private ArrayList<Product> getProductArrayList(Cursor cursor) {
        ArrayList<Product> productEntities = new ArrayList<>();
        cursor.moveToFirst();
        if(!cursor.isAfterLast()){
            do{
                Product p = new Product(
                        cursor.getString(OpenHelper.PRODUCTS_NAME_NUM),
                        cursor.getInt(OpenHelper.PRODUCTS_CATEGORY_ID_NUM),
                        cursor.getDouble(OpenHelper.PRODUCTS_PRICE_NUM)
                );
                productEntities.add(p);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return productEntities;
    }

    public ArrayList<Product> getAllByCategory(Category category){
        String[] args = {category.getId()+""};
        Cursor cursor = db.query(OpenHelper.PRODUCTS_TABLE_NAME,null,PRODUCTS_CATEGORY_ID + " = ? " ,args,null,null,null);
        return getProductArrayList(cursor);
    }
}
