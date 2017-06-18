package com.example.android.sparklibrary.Klase;

import java.io.Serializable;

public class Knjiga implements Serializable {

    private int ID;
    public  String naziv_knjige;
    public   String autor;
    public String naklada;
    public int godina_izdanja;
    public int broj_stranica;
    public  boolean dostupnost;
    public int tip;


    public Knjiga(){}

    public Knjiga(int ID, String naziv_knjige, String autor, String naklada, int godina_izdanja,
                  int broj_stranica,  boolean dostupnost, int tip) {
        this.ID = ID;
        this.naziv_knjige = naziv_knjige;
        this.autor = autor;
        this.naklada = naklada;
        this.godina_izdanja = godina_izdanja;
        this.broj_stranica = broj_stranica;
        this.dostupnost = dostupnost;
        this.tip = tip;
    }



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNaziv_knjige() {
        return naziv_knjige;
    }

    public void setNaziv_knjige(String naziv_knjige) {
        this.naziv_knjige = naziv_knjige;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNaklada() {
        return naklada;
    }

    public void setNaklada(String naklada) {
        this.naklada = naklada;
    }

    public int getGodina_izdanja() {
        return godina_izdanja;
    }

    public void setGodina_izdanja(int godina_izdanja) {
        this.godina_izdanja = godina_izdanja;
    }

    public int getBroj_stranica() {
        return broj_stranica;
    }

    public void setBroj_stranica(int broj_stranica) {
        this.broj_stranica = broj_stranica;
    }

    public boolean isDostupnost() {
        return dostupnost;
    }

    public void setDostupnost(boolean dostupnost) {
        this.dostupnost = dostupnost;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }
}
