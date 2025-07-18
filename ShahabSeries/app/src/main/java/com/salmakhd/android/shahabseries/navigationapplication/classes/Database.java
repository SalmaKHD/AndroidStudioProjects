package com.salmakhd.android.shahabseries.navigationapplication.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static final String dbName = "MyDatabase";
    private static final int version = 1;
    private static final String tableName = "contacts";
    private String tableQuery =
            "CREATE TABLE " +
                    tableName +
                    " (column_id integer primary key autoincrement," +
                    "name text, phone varchar(15))";

    public Database(Context context) {
        super(context, dbName,null, version);

    }

    // executed upon db creation
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableQuery);
    }

    // executed when the db is upgraded in later versions
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion) {
            db.execSQL("DROP TABLE " + tableName);
            db.execSQL(tableQuery);
        }
    }

    public void saveContact(String name, String number) {
        // to write to database
        SQLiteDatabase db = getWritableDatabase();
        String query = "INSERT INTO " + tableName + " (name, phone) VALUES ('"+ name+",'"+number+"');";
//        db.execSQL(query);

        // alternative approach
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", number);
        db.insert(tableQuery, null, contentValues);
    }

    public ArrayList<ContactModel> getContacts() {
        ArrayList<ContactModel> arrayList = new ArrayList<>();
        String query = "SELECT * FROM contacts" + " ORDER BY name ASC";

        SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);
        // move cursor to the beginning of the result
        if(cursor.moveToFirst()) {
            do {
                ContactModel contactModel = new ContactModel();
                int indexColumnIndex = cursor.getColumnIndex("column_id");
                int nameColumnIndex = cursor.getColumnIndex("name");
                int phoneColumnIndex = cursor.getColumnIndex("phone");

                contactModel.setId(cursor.getInt(indexColumnIndex));
                contactModel.setName(cursor.getString(nameColumnIndex));
                contactModel.setPhone(cursor.getString(phoneColumnIndex));

                arrayList.add(contactModel);
            } while(cursor.moveToNext());
            cursor.close();
        }
        return arrayList;
    }

    public void deleteCContact(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM contact WHERE id="+id;
        db.execSQL(query);
    }
}