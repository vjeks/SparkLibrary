package com.example.android.sparklibrary.Adapteri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.sparklibrary.Klase.PosudjeneKnjige;
import com.example.android.sparklibrary.R;
import com.example.android.sparklibrary.Storage.AppHelper;

import java.util.List;

public class PosudjeneKnjigeAdapter extends BaseAdapter {


    private Context c;
    private LayoutInflater layoutInflater;
    private List<PosudjeneKnjige> listUwp;

    public PosudjeneKnjigeAdapter() {
    }

    public PosudjeneKnjigeAdapter(Context context, List<PosudjeneKnjige> uwpList) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listUwp = uwpList;
    }

    public PosudjeneKnjigeAdapter(Context cnt) {
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
        //        TextView user_id;
//        TextView first_name;
//        TextView last_name;
        TextView knjigaNaziv;
        TextView knjigaAutor;
        TextView datum_posudjene_knjige;
    }


    @Override
    public View getView(int pozicija, View convertView, ViewGroup parent) {
        final drzacListe dl;


        if (convertView == null) {
            dl = new drzacListe();
            convertView = layoutInflater.inflate(R.layout.posudjene_knjige_list_item, parent, false);
            dl.knjigaNaziv = (TextView) convertView.findViewById(R.id.naziv_posudjene_knjige);
            dl.knjigaAutor = (TextView) convertView.findViewById(R.id.autor);
            dl.datum_posudjene_knjige = (TextView) convertView.findViewById(R.id.datum_posudjene_knjige);
            convertView.setTag(dl);
        } else {
            dl = (drzacListe) convertView.getTag();
        }

        if(AppHelper.getInstance().getKnjigeStorage()!= null){
            if(AppHelper.getInstance().getKnjigeStorage().getListaKnjiga()!= null){
                if (AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().size()>0) {
                    for (int i = 0; i < AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().size(); i++) {
                        if(listUwp.get(pozicija).getKnjiga_id() == AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().get(i).getID()){
                            dl.knjigaNaziv.setText(" " + AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().get(i).getNaziv_knjige());
                            dl.knjigaAutor.setText(" " + AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().get(i).getAutor());
                            dl.datum_posudjene_knjige.setText(listUwp.get(pozicija).getDatum());
                        }
                    }
                }
            }
        }


        return convertView;
    }
}
