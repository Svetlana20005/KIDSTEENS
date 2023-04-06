package com.example.kidsteens;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBCategories {
    private OpenHelper openHelper;
    private SQLiteDatabase db;
    public DBCategories(Context ctx){
        openHelper = new OpenHelper(ctx);
        db = openHelper.getWritableDatabase();
    }
    public long insert(Category c){
        ContentValues cv = new ContentValues();
        cv.put(OpenHelper.CATEGORIES_NAME, c.getName());
        cv.put(OpenHelper.CATEGORIES_PARENT_ID, c.getParent_id());
        return db.insert(OpenHelper.CATEGORY_TABLE_NAME, null, cv);
    }
    public ArrayList<Category> getAll(){
        Cursor cursor = db.query(OpenHelper.CATEGORY_TABLE_NAME,null,null,null,null,null,null);
        ArrayList<Category> categoryEntities = new ArrayList<>();
        cursor.moveToFirst();
        if(!cursor.isAfterLast()){
            do{
                Category p = new Category(
                        cursor.getInt(OpenHelper.CATEGORIES_ID_NUM),
                        cursor.getString(OpenHelper.CATEGORIES_NAME_NUM),
                        cursor.getInt(OpenHelper.CATEGORIES_PARENT_ID_NUM)
                );
                categoryEntities.add(p);
            }while (cursor.moveToNext());
        }
        return categoryEntities;
    }
    public Category selectByName(String name){
        Cursor cursor = db.query(OpenHelper.CATEGORY_TABLE_NAME,null,
                OpenHelper.CATEGORIES_NAME + "= ?",new String[]{name},null,null,null);
        cursor.moveToFirst();
        if(!cursor.isAfterLast()){
                Category c = new Category(
                        cursor.getInt(OpenHelper.CATEGORIES_ID_NUM),
                        cursor.getString(OpenHelper.CATEGORIES_NAME_NUM),
                        cursor.getInt(OpenHelper.CATEGORIES_PARENT_ID_NUM)
                );
                return c;
        }
        Category c = new Category(name);
        c.setId(this.insert(c));
        return c;
    }
}
