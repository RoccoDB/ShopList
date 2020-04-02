package com.academy.shoplist.activity.activity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.academy.shoplist.R;
import com.academy.shoplist.activity.bean.Prodotto;
import com.academy.shoplist.activity.constant.Constant;
import com.academy.shoplist.activity.fragment.FragmentDettaglioProdotto;
import com.academy.shoplist.activity.fragment.FragmentModificaProdotto;
import com.academy.shoplist.activity.singleton.ShoplistDatabaseManager;
import com.academy.shoplist.activity.utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityProdotto extends AppCompatActivity {

    Button modificaProdotto;
    EditText txtModificaDettaglio;
    TextView errore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodotto);

        FragmentManager fragmentContainer = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentContainer.beginTransaction();
        Bundle bundle = this.getIntent().getExtras();
        int posizione = bundle.getInt("position", 0);
        ArrayList<Prodotto> listaProdotti = ShoplistDatabaseManager.getInstance(ActivityProdotto.this).getListaProdottiByCursor(ShoplistDatabaseManager.getInstance(ActivityProdotto.this).getAllProduct());
        String nome = listaProdotti.get(posizione).getNome();
        String descrizione = listaProdotti.get(posizione).getDescrizione();

        if (bundle.getInt("modalità di apertura", 0) == Constant.VISUALIZZA) {
            FragmentDettaglioProdotto fragmentDettaglio = FragmentDettaglioProdotto.newInstance(nome, descrizione);
            fragmentTransaction.replace(R.id.container, fragmentDettaglio);
            fragmentTransaction.commit();
        } else if (bundle.getInt("modalità di apertura", 0) == Constant.MODIFICA) {
            FragmentModificaProdotto fragmentModifica = FragmentModificaProdotto.newInstance(nome, descrizione);
            fragmentTransaction.replace(R.id.container, fragmentModifica);
            fragmentTransaction.commit();
        } else {
            Context context = getApplicationContext();
            String textString = "ERRORE";
            Toast toast = Toast.makeText(context, textString, Toast.LENGTH_LONG);
            toast.show();
        }
        errore = findViewById(R.id.errorF);
        txtModificaDettaglio = findViewById(R.id.editDescription);
        modificaProdotto = findViewById(R.id.editButton);
        modificaProdotto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<Boolean, String> mapError = Utility.validaDescrizione(txtModificaDettaglio);
                if (mapError.containsKey(Boolean.FALSE)) {
                    errore.setText(mapError.get(Boolean.FALSE));
                }else {
                    ShoplistDatabaseManager.getInstance(ActivityProdotto.this).modificaProdottoByNome();
                    finish();
                }
            }
        });
    }
}
