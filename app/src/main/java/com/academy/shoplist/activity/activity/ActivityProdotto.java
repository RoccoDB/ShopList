package com.academy.shoplist.activity.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.academy.shoplist.R;

public class ActivityProdotto extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodotto);
    }

    public View dettagliView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    return inflater.inflate(R.layout.fragment_dettaglio_prodotto, container, false);
    }
}
