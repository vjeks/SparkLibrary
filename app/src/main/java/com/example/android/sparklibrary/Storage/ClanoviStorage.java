package com.example.android.sparklibrary.Storage;

import com.example.android.sparklibrary.Klase.Clanovi;

import java.util.List;


public class ClanoviStorage {

    List<Clanovi> listaCLanova;


    public ClanoviStorage() {
    }

    public ClanoviStorage(List<Clanovi> listaCLanova) {
        this.listaCLanova = listaCLanova;
    }

    public List<Clanovi> getListaCLanova() {
        return listaCLanova;
    }

    public void setListaCLanova(List<Clanovi> listaCLanova) {
        this.listaCLanova = listaCLanova;
    }
}
