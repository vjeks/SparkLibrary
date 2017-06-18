package com.example.android.sparklibrary.Klase;

import java.io.Serializable;

public class PosudjeneKnjige implements Serializable {

    private int clan_id;
    private int knjiga_id;
    private boolean posudjena;
    private String datum;

    public PosudjeneKnjige(int clan_id, int knjiga_id, boolean posudjena, String datum) {
        this.clan_id = clan_id;
        this.knjiga_id = knjiga_id;
        this.posudjena = posudjena;
        this.datum = datum;
    }

    public PosudjeneKnjige() {
    }


    public int getClan_id() {
        return clan_id;
    }

    public void setClan_id(int clan_id) {
        this.clan_id = clan_id;
    }

    public int getKnjiga_id() {
        return knjiga_id;
    }

    public void setKnjiga_id(int knjiga_id) {
        this.knjiga_id = knjiga_id;
    }

    public boolean isPosudjena() {
        return posudjena;
    }

    public void setPosudjena(boolean posudjena) {
        this.posudjena = posudjena;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
}
