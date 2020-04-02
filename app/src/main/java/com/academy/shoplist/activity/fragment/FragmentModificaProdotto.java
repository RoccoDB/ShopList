package com.academy.shoplist.activity.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.academy.shoplist.R;
import com.academy.shoplist.activity.interfaces.FragmentInterface;
import com.academy.shoplist.activity.interfaces.OnItemClickListener;
import com.academy.shoplist.activity.singleton.ShoplistDatabaseManager;


public class FragmentModificaProdotto extends Fragment implements FragmentInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "nome";
    private static final String ARG_PARAM2 = "descrizione";

    // TODO: Rename and change types of parameters
    private String nome;
    private String descrizione;
    private TextView txtNome;
    private EditText txtDescrizione;
    private FragmentInterface listener;
    public void setListener (FragmentInterface listener) {
        this.listener = listener;
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentModificaProdotto newInstance(String nome, String descrizione) {
        FragmentModificaProdotto fragment = new FragmentModificaProdotto();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, nome);
        args.putString(ARG_PARAM2, descrizione);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            nome = getArguments().getString(ARG_PARAM1);
            descrizione = getArguments().getString(ARG_PARAM2);
            newInstance(nome, descrizione);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_modifica_prodotto, container, false);
        txtNome = v.findViewById(R.id.editName);
        txtDescrizione = v.findViewById(R.id.editDescription);
        txtNome.setText(nome);
        txtDescrizione.setText(descrizione);
        txtDescrizione.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                String descrizione = s.toString();
                listener.catchDescrizione(descrizione);
            }
        });
        return v;
    }

    @Override
    public void catchDescrizione(String descrizione) {
    }
}
