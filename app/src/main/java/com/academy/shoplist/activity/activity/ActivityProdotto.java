package com.academy.shoplist.activity.activity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.academy.shoplist.R;
import com.academy.shoplist.activity.bean.Prodotto;
import com.academy.shoplist.activity.constant.Constant;
import com.academy.shoplist.activity.fragment.FragmentDettaglioProdotto;
import com.academy.shoplist.activity.fragment.FragmentModificaProdotto;
import com.academy.shoplist.activity.interfaces.FragmentInterface;
import com.academy.shoplist.activity.singleton.ShoplistDatabaseManager;
import com.academy.shoplist.activity.utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityProdotto extends AppCompatActivity{

    Button modificaProdotto;
    EditText txtModificaDettaglio;
    TextView errore;
    Bundle bundle;
    TextView txtNome;
    String  nome;
    String descrizioneProdotto;
    String descrizione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodotto);
        errore = findViewById(R.id.errorF);

        txtModificaDettaglio = findViewById(R.id.editDescription);
        bundle = this.getIntent().getExtras();
        setActionBar();

        FragmentManager fragmentContainer = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentContainer.beginTransaction();

        int posizione = bundle.getInt("position", 0);
        ArrayList<Prodotto> listaProdotti = ShoplistDatabaseManager.getInstance(ActivityProdotto.this).getListaProdottiByCursor(ShoplistDatabaseManager.getInstance(ActivityProdotto.this).getAllProduct());
        nome = listaProdotti.get(posizione).getNome();
        descrizione = listaProdotti.get(posizione).getDescrizione();

        if (bundle.getInt("modalità di apertura", 0) == Constant.VISUALIZZA) {
            FragmentDettaglioProdotto fragmentDettaglio = FragmentDettaglioProdotto.newInstance(nome, descrizione);
            fragmentTransaction.replace(R.id.container, fragmentDettaglio);
            fragmentTransaction.commit();
        } else if (bundle.getInt("modalità di apertura", 0) == Constant.MODIFICA) {
            FragmentModificaProdotto fragmentModifica = FragmentModificaProdotto.newInstance(nome, descrizione);
            fragmentModifica.setListener(new FragmentInterface() {
                @Override
                public void catchDescrizione(String descrizione) {
                    if (descrizione != null) {
                        descrizioneProdotto = descrizione;
                    }
                }
            });

            fragmentTransaction.replace(R.id.container, fragmentModifica);
            fragmentTransaction.commit();
        } else {
            Context context = getApplicationContext();
            String textString = "ERRORE";
            Toast toast = Toast.makeText(context, textString, Toast.LENGTH_LONG);
            toast.show();
        }

    }

    private void setActionBar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(myToolbar);
        ImageView addImage = (ImageView) findViewById(R.id.aggiungi_product);
        ImageView back = (ImageView) findViewById(R.id.back_toolbar);
        TextView shopList = (TextView) findViewById(R.id.textViewShopList);
        TextView testoToolbar = (TextView) findViewById(R.id.textViewTitolo);
        shopList.setVisibility(View.INVISIBLE);
        if(bundle.getInt("modalità di apertura", 0) == Constant.MODIFICA){
            addImage.setVisibility(View.VISIBLE);
            testoToolbar.setText("MODIFICA PRODOTTO");
        }else if(bundle.getInt("modalità di apertura", 0) == Constant.VISUALIZZA){
            addImage.setVisibility(View.INVISIBLE);
            testoToolbar.setText("VISUALIZZA PRODOTTO");
        }

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
                HashMap<Boolean, String> mapError = Utility.validaDescrizione(descrizioneProdotto);
                if (mapError.containsKey(Boolean.FALSE)) {
                    errore.setText(mapError.get(Boolean.FALSE));
                } else {
                    Prodotto p = new Prodotto();
                    p.setDescrizione(descrizioneProdotto);
                    ShoplistDatabaseManager.getInstance(ActivityProdotto.this).modificaProdottoByNome(nome, p);
                    finish();
                }
            }
        });
    }
}
