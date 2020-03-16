package com.academy.shoplist.activity.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.academy.shoplist.activity.constant.DbConstant;

import java.io.File;
import java.io.IOException;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "shoplist.db";
    private final static int CURRENT_DB_VERSION = 1;
    protected final Context MyContexy;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, CURRENT_DB_VERSION);
        this.MyContexy = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("createDataBase","UPGRADE DB FROM"+ oldVersion + "TO" + newVersion);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        if (Build.VERSION.SDK_INT >= 28){
            db.disableWriteAheadLogging();
        }
    }

    public SQLiteDatabase openDataBase()throws SQLException{
        String myPath = MyContexy.getDatabasePath(DB_NAME).getPath();
        return SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void createDataBase()throws IOException{
        boolean dbExist = checkDataBase();
        SQLiteDatabase db = this.getReadableDatabase();
        if (!dbExist){
            createShoplistDB(db);
        }
    }

    private boolean checkDataBase(){
        File database = MyContexy.getDatabasePath(DB_NAME);
        return database.exists();
    }

    private void createShoplistDB (SQLiteDatabase database){
        Log.d("createDataBase","create database" + DB_NAME);
        database.execSQL(DatabaseTables.SQL_CREATE_PRODOTTO);
    }

    private void dropAllTable (SQLiteDatabase database){
        Log.d("createDataBase", "DROP ALL TABLES");
        dropTable (database, DbConstant.PRODOTTI_TABLE);
    }

    public void dropTable(SQLiteDatabase database, String table){
        try{
            String dropTable = "DROP TABLE IF EXISTS" + table + ";";
            database.execSQL(dropTable);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
