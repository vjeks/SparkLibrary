package com.example.android.sparklibrary.Storage;

import com.example.android.sparklibrary.Klase.Knjiga;

import java.util.List;

/**
 * Created by adissertovic on 16/06/17.
 */

public class KnjigeStorage {


    List<Knjiga> listaKnjiga;


    public KnjigeStorage() {
    }

    public List<Knjiga> getListaKnjiga() {
        return listaKnjiga;
    }

    public void setListaKnjiga(List<Knjiga> listaKnjiga) {
        this.listaKnjiga = listaKnjiga;
    }

    public KnjigeStorage(List<Knjiga> listaKnjiga) {

        this.listaKnjiga = listaKnjiga;
    }
}
