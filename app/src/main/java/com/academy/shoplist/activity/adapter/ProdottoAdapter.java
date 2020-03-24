package com.academy.shoplist.activity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.academy.shoplist.R;
import com.academy.shoplist.activity.bean.Prodotto;
import com.academy.shoplist.activity.interfaces.OnItemClickListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProdottoAdapter extends RecyclerView.Adapter<ProdottoAdapter.ProdottoViewHolder> {
    public OnItemClickListener listener;

    public void setOnItemClickListener (OnItemClickListener listener) {
        this.listener = listener;
    }


    public  ArrayList<Prodotto> prodottiList = new ArrayList<>();

    public static class ProdottoViewHolder extends RecyclerView.ViewHolder{
        public ImageView img_Prodotto;
        public ImageView img_elimina_prodotto;
        public TextView textView_nomeProdotto;
        public TextView textView_descrizioneProdotto;



        public ProdottoViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            img_Prodotto = itemView.findViewById(R.id.img_prodotto);
            img_elimina_prodotto = itemView.findViewById(R.id.elimina_prodotto);
            textView_nomeProdotto = itemView.findViewById(R.id.nome_prodotto);
            textView_descrizioneProdotto = itemView.findViewById(R.id.descrizione_prodotto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
            img_elimina_prodotto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemDelete(position);
                        }
                    }
                }
            });
        }
    }

    public ProdottoAdapter(ArrayList<Prodotto> prodottiList){
        this.prodottiList = prodottiList;
    }

    @NonNull
    @Override
    public ProdottoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.prodotto_item,parent, false);
        ProdottoViewHolder pvh = new ProdottoViewHolder(v,listener);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProdottoViewHolder holder, int position) {
        Prodotto currentProdotto =prodottiList.get(position);
        holder.img_Prodotto.setImageResource(currentProdotto.getImmagine());
        holder.textView_nomeProdotto.setText(currentProdotto.getNome());
        holder.textView_descrizioneProdotto.setText(currentProdotto.getDescrizione());

    }

    @Override
    public int getItemCount() {
        return prodottiList.size();
    }

}
