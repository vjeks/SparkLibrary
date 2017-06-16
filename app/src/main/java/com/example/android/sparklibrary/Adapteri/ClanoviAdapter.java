package com.example.android.sparklibrary.Adapteri;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.sparklibrary.Klase.Clanovi;
import com.example.android.sparklibrary.R;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Home on 14.6.2017..
 */

public class ClanoviAdapter extends BaseAdapter {


    private static final String TAG = "ClanoviAdapter";

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
        TextView ime;
        TextView broj;
    }


    @Override
    public View getView(int pozicija, View convertView, ViewGroup parent) {
        final drzacListe dl;


        if (convertView == null) {
            dl = new drzacListe();
            convertView = layoutInflater.inflate(R.layout.clanovi_list_item, parent, false);
            dl.ime = (TextView) convertView.findViewById(R.id.ime_clana);
            dl.broj = (TextView) convertView.findViewById(R.id.broj_clana);
            convertView.setTag(dl);
        } else {
            dl = (drzacListe) convertView.getTag();
        }
        Log.d(TAG, "getView:  CLAN:    " + new Gson().toJson(listClanovi.get(pozicija)));
        dl.ime.setText(listClanovi.get(pozicija).getIme() + " " + listClanovi.get(pozicija).getPrezime());
        dl.broj.setText("CL.br. : " + listClanovi.get(pozicija).getClan_broj());
        return convertView;
    }
}
