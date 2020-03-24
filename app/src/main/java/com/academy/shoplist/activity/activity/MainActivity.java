package com.academy.shoplist.activity.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.academy.shoplist.R;
import com.academy.shoplist.activity.adapter.ProdottoAdapter;
import com.academy.shoplist.activity.bean.Prodotto;
import com.academy.shoplist.activity.constant.DbConstant;
import com.academy.shoplist.activity.fragment.FragmentDettaglioProdotto;
import com.academy.shoplist.activity.interfaces.OnItemClickListener;
import com.academy.shoplist.activity.singleton.ShopList;
import com.academy.shoplist.activity.singleton.ShoplistDatabaseManager;

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
        mRecyclerView.setLayoutManager(mLayoutManager);
        generaAdapter();
        btnIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2ndActivity = new Intent(MainActivity.this, AddCardActivity.class);
                startActivityForResult(intent2ndActivity, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1) {
           generaAdapter();
        }
    }

    public void generaAdapter () {
        mAdapter = new ProdottoAdapter(ShoplistDatabaseManager.getInstance(MainActivity.this).getListaProdottiByCursor(ShoplistDatabaseManager.getInstance(MainActivity.this).getAllProduct()));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intentDetail = new Intent(MainActivity.this, ActivityProdotto.class);
                Prodotto p = new Prodotto();
                p = mAdapter.prodottiList.get(position);
                intentDetail.putExtra(ShoplistDatabaseManager.getInstance(MainActivity.this));
                startActivityForResult(intentDetail, 2);
            }

            @Override
            public void onItemDelete(int position) {
                Prodotto p = new Prodotto();
                p = mAdapter.prodottiList.get(position);
               ShoplistDatabaseManager.getInstance(MainActivity.this).eliminaProdottoByNome(p.getNome());
               generaAdapter();
            }

            @Override
            public void onItemEdit(int position) {
                Prodotto p = new Prodotto();
                ShoplistDatabaseManager.getInstance(MainActivity.this).modificaProdottoByNome(p.getNome(), p);
            }
        });
    }
}
