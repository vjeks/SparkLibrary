package com.example.android.sparklibrary.Storage;

import com.example.android.sparklibrary.Klase.TipKnjige;

import java.util.List;

/**
 * Created by adissertovic on 18/06/17.
 */

public class TipoviStorage {


    List<TipKnjige> tipoviLista;


    public TipoviStorage() {
    }

    public TipoviStorage(List<TipKnjige> tipoviLista) {
        this.tipoviLista = tipoviLista;
    }

    public List<TipKnjige> getTipoviLista() {
        return tipoviLista;
    }

    public void setTipoviLista(List<TipKnjige> tipoviLista) {
        this.tipoviLista = tipoviLista;
    }
}
