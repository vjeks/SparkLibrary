package com.example.android.sparklibrary.Adapteri;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.sparklibrary.Klase.PosudjeneKnjige;
import com.example.android.sparklibrary.R;
import com.example.android.sparklibrary.Storage.AppHelper;

import java.util.List;

/**
 * Created by adissertovic on 17/06/17.
 */

public class PosudjeneKnjigeAdapterList extends BaseAdapter {

    public static final String TAG = "PosudjeneKnjige";

    private Context c;
    private LayoutInflater layoutInflater;
    private List<PosudjeneKnjige> listUwp;

    public PosudjeneKnjigeAdapterList() {
    }

    public PosudjeneKnjigeAdapterList(Context context, List<PosudjeneKnjige> uwpList) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listUwp = uwpList;
    }

    public PosudjeneKnjigeAdapterList(Context cnt) {
        this.c = cnt;
    }

    @Override
    public int getCount() {
        return listUwp.size();
    }

    @Override
    public Object getItem(int pozicija) {
        return listUwp.get(pozicija);
    }

    @Override
    public long getItemId(int pozicija) {
        return pozicija;
    }


    static class drzacListe {

        TextView knjigaNaziv;
        TextView knjigaAutor;
        TextView clanskiBroj;
        TextView datum_posudjene_knjige;
    }


    @Override
    public View getView(int pozicija, View convertView, ViewGroup parent) {
        final drzacListe dl;


        if (convertView == null) {
            dl = new drzacListe();
            convertView = layoutInflater.inflate(R.layout.posudjene_knjige_list_item_za_fragment, parent, false);
            dl.knjigaNaziv = (TextView) convertView.findViewById(R.id.posudjene_knjige_naziv);
            dl.knjigaAutor = (TextView) convertView.findViewById(R.id.clan_ime_prezime);
            dl.clanskiBroj = (TextView) convertView.findViewById(R.id.clanski_broj);
            dl.datum_posudjene_knjige = (TextView) convertView.findViewById(R.id.datum_posudjene_knjige);
            convertView.setTag(dl);
        } else {
            dl = (drzacListe) convertView.getTag();
        }

        if(AppHelper.getInstance().getKnjigeStorage()!= null){
            if(AppHelper.getInstance().getKnjigeStorage().getListaKnjiga()!= null){
                if (AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().size()>0) {
                    for (int i = 0; i < AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().size(); i++) {
                        if(listUwp.get(pozicija).getKnjiga_id() == AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().get(i).getID()) {
                            Log.d(TAG, "getView: " + " ima knjiga");

                            dl.knjigaNaziv.setText(" " + AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().get(i).getNaziv_knjige());
                            //dl.knjigaAutor.setText(" " + AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().get(i).getAutor());
                            dl.datum_posudjene_knjige.setText(listUwp.get(pozicija).getDatum());
                        }
                    }
                }
            }
        }


        if(AppHelper.getInstance().getClanoviStorage()!= null){
            if(AppHelper.getInstance().getClanoviStorage().getListaCLanova()!= null){
                if (AppHelper.getInstance().getClanoviStorage().getListaCLanova().size()>0) {
                    for (int i = 0; i < AppHelper.getInstance().getClanoviStorage().getListaCLanova().size(); i++) {
                        if(listUwp.get(pozicija).getClan_id() == AppHelper.getInstance().getClanoviStorage().getListaCLanova().get(i).getID()) {

                            Log.d(TAG, "getView: " + " ima clan");

                            dl.knjigaAutor.setText(" " + AppHelper.getInstance().getClanoviStorage().getListaCLanova().get(i).getIme() + " " +
                                    AppHelper.getInstance().getClanoviStorage().getListaCLanova().get(i).getIme());
                            //dl.knjigaAutor.setText(" " + AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().get(i).getAutor());
                            dl.clanskiBroj.setText(AppHelper.getInstance().getClanoviStorage().getListaCLanova().get(i).getClan_broj());
                        }
                    }
                }
            }
        }


        return convertView;
    }
}
