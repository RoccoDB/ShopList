package com.academy.shoplist.activity.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.academy.shoplist.R;


public class FragmentModificaProdotto extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String nome;
    private String descrizione;
    private EditText txtNome;
    private EditText txtDescrizione;


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
            nome = getArguments().getString(txtNome.toString());
            descrizione = getArguments().getString(txtDescrizione.toString());
            newInstance(nome, descrizione);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_modifica_prodotto, container, false);
        txtNome = v.findViewById(R.id.nome_prodotto);
        txtDescrizione = v.findViewById(R.id.descrizione_prodotto);
        return v;
    }

}
