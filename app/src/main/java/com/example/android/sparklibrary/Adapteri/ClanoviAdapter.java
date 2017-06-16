package com.example.android.sparklibrary.Adapteri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.sparklibrary.Klase.Clanovi;
import com.example.android.sparklibrary.Klase.Knjiga;
import com.example.android.sparklibrary.R;

import java.util.List;

/**
 * Created by Home on 14.6.2017..
 */

public class ClanoviAdapter extends BaseAdapter {


    private Context c;
    private LayoutInflater layoutInflater;
    private List<Clanovi>listClanovi;

    public ClanoviAdapter(Context context, List<Clanovi> uwpList) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listClanovi = uwpList;
    }

    public ClanoviAdapter(Context cnt) {
        this.c = cnt;
    }

    @Override
    public int getCount() {
        return listClanovi.size();
    }

    @Override
    public Object getItem(int pozicija) {
        return listClanovi.get(pozicija);
    }

    @Override
    public long getItemId(int pozicija) {
        return pozicija;
    }


    static class drzacListe {
        //        TextView user_id;
//        TextView first_name;
//        TextView last_name;
        TextView id;
        TextView ime;
        TextView prezime;
    }


    @Override
    public View getView(int pozicija, View convertView, ViewGroup parent) {
        final drzacListe dl;


        if (convertView == null) {
            dl = new drzacListe();
            convertView = layoutInflater.inflate(R.layout.knjge_list_item, parent, false);
            dl.id = (TextView) convertView.findViewById(R.id.naziv_knjige);
            dl.ime = (TextView) convertView.findViewById(R.id.autor);
            dl.prezime = (TextView) convertView.findViewById(R.id.tipKnjige);
            convertView.setTag(dl);
        } else {
            dl = (drzacListe) convertView.getTag();
        }

        dl.id.setText("Naziv Knjige : "+listClanovi.get(pozicija).getID());
        dl.ime.setText("AUTOR: " + listClanovi.get(pozicija).getIme());
        dl.prezime.setText("TIP: " + listClanovi.get(pozicija).getPrezime());
        return convertView;
    }
}
