package com.academy.shoplist.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.academy.shoplist.R;
import com.academy.shoplist.activity.bean.Prodotto;
import com.academy.shoplist.activity.database.ShoplistDatabaseManager;
import com.academy.shoplist.activity.utility.Utility;

import java.util.HashMap;

public class AddCardActivity extends AppCompatActivity {
    ImageView btnAddCard;
    EditText txtNome;
    EditText txtDescrizione;
    TextView errore;
    private ImageView btnIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        btnAddCard = findViewById(R.id.aggiungi_product);
        txtNome = findViewById(R.id.editTxtNome);
        btnIntent = findViewById(R.id.aggiungi_product);
        txtDescrizione = findViewById(R.id.editTxtDescrizione);
        errore = findViewById(R.id.error);
        setActionBar();


    }

    private void setActionBar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(myToolbar);
        ImageView addImage = (ImageView) findViewById(R.id.aggiungi_product);
        ImageView back = (ImageView) findViewById(R.id.back_toolbar);
        TextView testoToolbar = (TextView) findViewById(R.id.textViewTitolo);
        testoToolbar.setText("AGGIUNGI PRODOTTO");
        addImage.setVisibility(View.VISIBLE);
        back.setVisibility(View.VISIBLE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<Boolean, String> mapError = Utility.validaInput(txtNome, txtDescrizione);
                if (mapError.containsKey(Boolean.FALSE)) {
                    errore.setText(mapError.get(Boolean.FALSE));
                } else {

                    Prodotto p = new Prodotto(0, R.drawable.coffi, txtNome.getText().toString(), txtDescrizione.getText().toString());
                    ShoplistDatabaseManager.getInstance(AddCardActivity.this).addProdotto(p);
                    finish();
                }

            }



        });


    }



}


