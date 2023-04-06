package com.example.kidsteens;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class OpenHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "products.db";
    public static final String PRODUCTS_TABLE_NAME = "products";
    public static final int DB_VERSION = 2;
    public static final String PRODUCTS_ID = "id";
    public static final int PRODUCTS_ID_NUM = 0;
    public static final String PRODUCTS_NAME = "name";
    public static final int PRODUCTS_NAME_NUM = 1;
    public static final String PRODUCTS_CATEGORY_ID = "category_id";
    public static final int PRODUCTS_CATEGORY_ID_NUM = 2;
    public static final String PRODUCTS_PRICE = "price";
    public static final int PRODUCTS_PRICE_NUM = 3;

    public static final String CATEGORY_TABLE_NAME = "category";
    public static final String CATEGORIES_ID = "id";
    public static final int CATEGORIES_ID_NUM = 0;
    public static final String CATEGORIES_NAME = "name";
    public static final int CATEGORIES_NAME_NUM = 1;
    public static final String CATEGORIES_PARENT_ID = "parent_id";
    public static final int CATEGORIES_PARENT_ID_NUM = 2;

    public OpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createProductsTable(db);
        createCategoriesTable(db);
    }

    private void createProductsTable(SQLiteDatabase db) {
        String query = "create table " + PRODUCTS_TABLE_NAME + "(" +
                PRODUCTS_ID + " integer primary key autoincrement," +
                PRODUCTS_NAME + " TEXT," +
                PRODUCTS_CATEGORY_ID + " INTEGER," +
                PRODUCTS_PRICE + " DOUBLE" +
                ")";
        db.execSQL(query);
    }

    private void createCategoriesTable(SQLiteDatabase db) {
        String query1 = "create table " + CATEGORY_TABLE_NAME + "(" +
                CATEGORIES_ID + " integer primary key autoincrement," +
                CATEGORIES_NAME + " TEXT," +
                CATEGORIES_PARENT_ID + " INTEGER" +
                ")";
        db.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE " + PRODUCTS_TABLE_NAME);
        db.execSQL("DROP TABLE " + CATEGORY_TABLE_NAME);
        onCreate(db);
    }
}
