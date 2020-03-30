package com.academy.shoplist.activity.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.academy.shoplist.R;
import com.academy.shoplist.activity.constant.Constant;
import com.academy.shoplist.activity.fragment.FragmentDettaglioProdotto;
import com.academy.shoplist.activity.fragment.FragmentModificaProdotto;

public class ActivityProdotto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodotto);

        FragmentManager fragmentContainer = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentContainer.beginTransaction();
        Bundle bundle = this.getIntent().getExtras();
        if(bundle.getInt("modalità di apertura", 0) == Constant.VISUALIZZA){
            FragmentDettaglioProdotto fragmentDettaglio = new FragmentDettaglioProdotto();
            fragmentTransaction.replace(R.id.container, fragmentDettaglio);
            fragmentTransaction.commit();
        }else if(bundle.getInt("modalità di apertura", 0) == Constant.MODIFICA){
            FragmentModificaProdotto fragmentModifica = FragmentModificaProdotto.newInstance();       //nome e descrizione dalla query di shoplistDatabaseManager getProdottoByNome
            fragmentTransaction.replace(R.id.container, fragmentModifica);
            fragmentTransaction.commit();
        }else{
            Context context = getApplicationContext();
            String textString = "ERRORE";
            Toast toast = Toast.makeText(context, textString, Toast.LENGTH_LONG);
            toast.show();
        }

    }
}
