package com.example.kidsteens;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
        cv.put(OpenHelper.PRODUCTS_CATEGORY_ID, p.getCategory_id());
        cv.put(OpenHelper.PRODUCTS_PRICE, p.getPrice());
        db.insert(OpenHelper.PRODUCTS_TABLE_NAME, null, cv);
    }
    public ArrayList<Product> getAll(){
        Cursor cursor = db.query(OpenHelper.PRODUCTS_TABLE_NAME,null,null,null,null,null,null);
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
        return productEntities;
    }
}
