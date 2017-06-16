package com.example.android.sparklibrary.Klase;

import java.io.Serializable;

/**
 * Created by adissertovic on 15/06/17.
 */

public class TipKnjige implements Serializable {

    int tip_id;
    String tip_naziv;

    public int getTip_id() {
        return tip_id;
    }

    public void setTip_id(int tip_id) {
        this.tip_id = tip_id;
    }

    public String getTip_naziv() {
        return tip_naziv;
    }

    public void setTip_naziv(String tip_naziv) {
        this.tip_naziv = tip_naziv;
    }

    public TipKnjige() {

    }

    public TipKnjige(int tip_id, String tip_naziv) {

        this.tip_id = tip_id;
        this.tip_naziv = tip_naziv;
    }
}
