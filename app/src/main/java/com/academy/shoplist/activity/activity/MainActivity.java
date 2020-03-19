package com.academy.shoplist.activity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.academy.shoplist.R;
import com.academy.shoplist.activity.adapter.ProdottoAdapter;
import com.academy.shoplist.activity.database.ShoplistDatabaseManager;
import com.academy.shoplist.activity.interfaces.OnItemClickListener;
import com.academy.shoplist.activity.singleton.ShopList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ProdottoAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView btnIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mRecyclerView = findViewById(R.id.recyclerView);
        btnIntent = findViewById(R.id.aggiungi_product);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ProdottoAdapter(ShoplistDatabaseManager.getInstance(MainActivity.this).getlistaProdottiByCursor(ShoplistDatabaseManager.getInstance(MainActivity.this).getAllProduct()));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnClickListener(new OnItemClickListener() {

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemDelete(int postion) {

    }

    @Override
    public void onItemEdit(int position) {

    }
});

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
