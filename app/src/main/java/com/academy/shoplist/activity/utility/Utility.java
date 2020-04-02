package com.academy.shoplist.activity.utility;

import android.text.TextUtils;
import android.widget.EditText;

import java.util.HashMap;

public class Utility {

    public static HashMap<Boolean, String> validaInput(EditText editX, EditText editY) {
        HashMap<Boolean, String> mapError = new HashMap<>();
        if (TextUtils.isEmpty(editX.getText().toString())) {
            mapError.put(Boolean.FALSE, "Manca: il nome del prodotto");
        }
        if (TextUtils.isEmpty(editY.getText().toString())) {
            if (mapError.containsKey(Boolean.FALSE)) {
                String stringaErrore = mapError.get(Boolean.FALSE);
                stringaErrore += ", la descrizione del prodotto";
                mapError.put(Boolean.FALSE, stringaErrore);
            } else
                mapError.put(Boolean.FALSE, "Manca: la descrizione del prodotto");
        }
        return mapError;
    }

    public static HashMap<Boolean, String> validaDescrizione(EditText editX) {
        HashMap<Boolean, String> mapError = new HashMap<>();
        if (TextUtils.isEmpty(editX.getText().toString())) {
            mapError.put(Boolean.FALSE, "Manca la descrizione del prodotto");
        }
        return mapError;
    }
}
