package com.academy.shoplist.activity.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.academy.shoplist.R;

public class FragmentDettaglioProdotto extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    TextView nomeProdotto;
    TextView descrizioneProdotto;

    // TODO: Rename and change types and number of parameters
    public static FragmentDettaglioProdotto newInstance(String param1, String param2) {
        FragmentDettaglioProdotto fragment = new FragmentDettaglioProdotto();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dettaglio_prodotto, container, false);
        nomeProdotto = v.findViewById(R.id.name);
        descrizioneProdotto = v.findViewById(R.id.dettaglioDescrizione);
        return v ;
    }



}
