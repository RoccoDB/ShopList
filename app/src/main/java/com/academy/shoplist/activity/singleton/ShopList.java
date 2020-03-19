package com.academy.shoplist.activity.singleton;

import android.database.Cursor;

import com.academy.shoplist.activity.bean.Prodotto;

import java.util.ArrayList;

public class ShopList {
    private static  ShopList singleIstance = new ShopList();
    //public ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();

    private ShopList (){

    }

    public static ShopList getInstance() {
        if (singleIstance==null)
            return singleIstance = new ShopList();
        else
            return singleIstance;
    }

    //public void addProdottoToLista (Prodotto p){ listaProdotti.add(p);
   // }
}
