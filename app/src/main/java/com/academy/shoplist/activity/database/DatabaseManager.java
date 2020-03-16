package com.academy.shoplist.activity.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {

    protected static SQLiteDatabase database;
    protected static DatabaseHelper dbHelper;

    public DatabaseManager(Context context){
        dbHelper = new DatabaseHelper(context);
    }
     public void open(){

        try {
            dbHelper.createDataBase();
            database = dbHelper.openDataBase();
        }catch (Exception e){
            e.printStackTrace();
        }
     }
     public void close (){dbHelper.close(); }
}
