package com.salmakhd.android.onlineshop.claases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper  {
    private static final String dbName = "Cafe_db";
    private static final int version = 1;
    private static final String tableName = "favorite_items";
    private String tableQuery ="CREATE TABLE " +
            tableName +
            "(column_id integer primary key autoincrement," +
            "item text)";

    public Database(Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + tableName);
        db.execSQL(tableQuery);
    }

    public void saveFavoriteItem(String itemName) {
        String query = "INSERT INTO " + tableName + " (item) VALUES ('" + itemName + "'";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }

    public ArrayList<String> getFavoriteItems() {
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * FROM "+ tableName;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do {
                int itemIndex = cursor.getColumnIndex("item");
                String item = cursor.getString(itemIndex);
                list.add(item);
            } while(cursor.moveToNext());
        }
        return list;
    }
}