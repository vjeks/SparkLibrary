package com.example.android.sparklibrary.Adapteri;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sparklibrary.Klase.Clanovi;
import com.example.android.sparklibrary.R;
import com.example.android.sparklibrary.Storage.AppHelper;
import com.google.gson.Gson;

import java.util.List;


public class ClanoviAdapter extends BaseAdapter {


    private static final String TAG = "ClanoviAdapter";

    private Context c;
    private LayoutInflater layoutInflater;
    private List<Clanovi> listClanovi;

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
        ImageView cb;
//        CheckBox cb;
    }


    @Override
    public View getView(int pozicija, View convertView, ViewGroup parent) {
        final drzacListe dl;


        if (convertView == null) {
            dl = new drzacListe();
            convertView = layoutInflater.inflate(R.layout.clanovi_list_item, parent, false);
            dl.ime = (TextView) convertView.findViewById(R.id.ime_clana);
            dl.broj = (TextView) convertView.findViewById(R.id.broj_clana);
//            dl.cb = (CheckBox) convertView.findViewById(R.id.posudjena_knjiga_slika);
            dl.cb = (ImageView) convertView.findViewById(R.id.posudjena_knjiga_slika);
            convertView.setTag(dl);
        } else {
            dl = (drzacListe) convertView.getTag();
        }
        Log.d(TAG, "getView:  CLAN:    " + new Gson().toJson(listClanovi.get(pozicija)));
        dl.ime.setText(listClanovi.get(pozicija).getIme() + " " + listClanovi.get(pozicija).getPrezime());
        dl.broj.setText("CL.br. : " + listClanovi.get(pozicija).getID());
        if (AppHelper.getInstance().getPosudjeneKnjigeStorage() != null) {
            if (AppHelper.getInstance().getPosudjeneKnjigeStorage().getPosudjeneKnjigeStorageList() != null) {
                if (AppHelper.getInstance().getPosudjeneKnjigeStorage().getPosudjeneKnjigeStorageList().size() > 0) {
                    for (int i = 0; i < AppHelper.getInstance().getPosudjeneKnjigeStorage().getPosudjeneKnjigeStorageList().size(); i++) {
                        if(AppHelper.getInstance().getPosudjeneKnjigeStorage().getPosudjeneKnjigeStorageList().get(i).getClan_id() ==
                                listClanovi.get(pozicija).getID()){
//                                dl.cb.setImageResource(true);
                                dl.cb.setImageResource(R.color.Yellow);
                        }
                    }
                }
            }
        }

        return convertView;
    }
}
