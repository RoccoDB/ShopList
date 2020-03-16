package com.academy.shoplist.activity.singleton;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.academy.shoplist.activity.bean.Prodotto;
import com.academy.shoplist.activity.constant.DbConstant;
import com.academy.shoplist.activity.database.DatabaseManager;

public class ShoplistDatabaseManager extends DatabaseManager {

    private static ShoplistDatabaseManager instance;

    private ShoplistDatabaseManager(Context context) {
        super(context);
    }

    public static synchronized ShoplistDatabaseManager getInstance(Context context) {
        if (instance == null) {
            synchronized (ShoplistDatabaseManager.class) {
                instance = new ShoplistDatabaseManager(context);
                instance.open();
            }
        }
        return instance;
    }

    public void addProdotto(Prodotto prodotto) {
        database.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(DbConstant.PRODOTTI_TABLE_NOME, prodotto.getNome());
            values.put(DbConstant.PRODOTTI_TABLE_DESCRIZIONE, prodotto.getDescrizione());
            database.insert(DbConstant.PRODOTTI_TABLE, null, values);
            Log.i("Elemento inserito ", "Prodotto con nome : " + prodotto.getNome());
            database.setTransactionSuccessful();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            database.endTransaction();
        }
    }
}
