package com.example.android.sparklibrary.Klase;

import java.io.Serializable;

public class Clanovi implements Serializable {

    private int ID;
    public String ime;
    public String prezime;
    public String adresa;
    public String tel_broj;
    public String clan_broj;

    public Clanovi() {
    }

    public Clanovi(int ID, String ime, String prezime, String adresa, String tel_broj, String clan_broj) {
        this.ID = ID;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.tel_broj = tel_broj;
        this.clan_broj = clan_broj;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTel_broj() {
        return tel_broj;
    }

    public void setTel_broj(String tel_broj) {
        this.tel_broj = tel_broj;
    }

    public String getClan_broj() {
        return clan_broj;
    }

    public void setClan_broj(String clan_broj) {
        this.clan_broj = clan_broj;
    }
}
