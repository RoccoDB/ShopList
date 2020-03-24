package com.academy.shoplist.activity.singleton;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.academy.shoplist.activity.bean.Prodotto;
import com.academy.shoplist.activity.constant.DbConstant;
import com.academy.shoplist.activity.database.DatabaseManager;

import java.util.ArrayList;

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
    public ArrayList<Prodotto> getListaProdottiByCursor (Cursor c){
        ArrayList<Prodotto> listaProdotti = new ArrayList<>();
        int columnIndexNome=c.getColumnIndex(DbConstant.PRODOTTI_TABLE_NOME);
        int columnIndexDescrizione=c.getColumnIndex(DbConstant.PRODOTTI_TABLE_DESCRIZIONE);
        int columnIndexImmagine=c.getColumnIndex(DbConstant.PRODOTTI_TABLE_IMG);
        while(c.moveToNext()) {
            Prodotto p = new Prodotto();
            p.setNome(c.getString(columnIndexNome));
            p.setDescrizione(c.getString(columnIndexDescrizione));
            p.setImmagine(c.getInt(columnIndexImmagine));
            listaProdotti.add(p);
        }
        return listaProdotti;
    }
    public Cursor getAllProduct (){
        return database.query(DbConstant.PRODOTTI_TABLE,null,null,null,null,null,null);
    }
    public void eliminaProdottoByNome (String nome){
        database.delete(DbConstant.PRODOTTI_TABLE, DbConstant.PRODOTTI_TABLE_NOME+"='"+nome+"'", null);
    }
    public void modificaProdottoByNome (String nome, Prodotto p){
        ContentValues values = new ContentValues();
        values.put(DbConstant.PRODOTTI_TABLE_DESCRIZIONE, p.getDescrizione());
        database.update(DbConstant.PRODOTTI_TABLE, values, DbConstant.PRODOTTI_TABLE_NOME+"='"+nome+"'", null );
    }
}
