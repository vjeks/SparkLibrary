package com.example.android.sparklibrary.Storage;

import com.example.android.sparklibrary.Klase.PosudjeneKnjige;

import java.util.List;

public class PosudjeneKnjigeStorage {

    List<PosudjeneKnjige> posudjeneKnjigeStorageList;

    public PosudjeneKnjigeStorage(List<PosudjeneKnjige> posudjeneKnjigeStorageList) {
        this.posudjeneKnjigeStorageList = posudjeneKnjigeStorageList;
    }

    public PosudjeneKnjigeStorage() {
    }

    public List<PosudjeneKnjige> getPosudjeneKnjigeStorageList() {
        return posudjeneKnjigeStorageList;
    }

    public void setPosudjeneKnjigeStorageList(List<PosudjeneKnjige> posudjeneKnjigeStorageList) {
        this.posudjeneKnjigeStorageList = posudjeneKnjigeStorageList;
    }
}
