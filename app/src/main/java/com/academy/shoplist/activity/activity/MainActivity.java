package com.academy.shoplist.activity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.academy.shoplist.R;
import com.academy.shoplist.activity.adapter.ProdottoAdapter;
import com.academy.shoplist.activity.bean.Prodotto;
import com.academy.shoplist.activity.singleton.ShopList;
import com.academy.shoplist.activity.singleton.ShoplistDatabaseManager;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView btnIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShoplistDatabaseManager.getInstance(MainActivity.this).addProdotto(new Prodotto(0, R.drawable.ic_launcher_background, "nome di test", "Descrizione di test"));

        mRecyclerView = findViewById(R.id.recyclerView);
        btnIntent = findViewById(R.id.aggiungi_product);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ProdottoAdapter(ShopList.getInstance().listaProdotti);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);


        btnIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2ndActivity = new Intent(MainActivity.this, AddCardActivity.class);
                startActivity(intent2ndActivity);

            }
        });
    }

    public static class ActivityProdotto extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_prodotto);
        }
    }
}
