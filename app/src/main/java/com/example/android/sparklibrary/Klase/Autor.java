package com.example.android.sparklibrary.Klase;

import java.io.Serializable;

/**
 * Created by Home on 15.6.2017..
 */

public class Autor implements Serializable {

    private int ID;
    public String ime;
    public String prezime;

    public Autor (){}

    public Autor(int ID, String ime, String prezime) {
        this.ID = ID;
        this.ime = ime;
        this.prezime = prezime;
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
}
