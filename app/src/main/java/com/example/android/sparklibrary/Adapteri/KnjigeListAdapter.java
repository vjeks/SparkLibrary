package com.example.android.sparklibrary.Adapteri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sparklibrary.Klase.Knjiga;
import com.example.android.sparklibrary.R;

import java.util.List;


public class KnjigeListAdapter extends BaseAdapter {


    private Context c;
    private LayoutInflater layoutInflater;
    private List<Knjiga> listUwp;

    public KnjigeListAdapter(){}

    public KnjigeListAdapter(Context context, List<Knjiga> uwpList) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listUwp = uwpList;
    }

    public KnjigeListAdapter(Context cnt) {
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
        ImageView ikonaIznajmljena;
    }


    @Override
    public View getView(int pozicija, View convertView, ViewGroup parent) {
        final drzacListe dl;


        if (convertView == null) {
            dl = new drzacListe();
            convertView = layoutInflater.inflate(R.layout.knjge_list_item, parent, false);
            dl.knjigaNaziv = (TextView) convertView.findViewById(R.id.naziv_knjige);
            dl.knjigaAutor = (TextView) convertView.findViewById(R.id.autor);
            dl.ikonaIznajmljena = (ImageView) convertView.findViewById(R.id.tipKnjige);
            convertView.setTag(dl);
        } else {
            dl = (drzacListe) convertView.getTag();
        }

        dl.knjigaNaziv.setText("Naziv Knjige : "+listUwp.get(pozicija).getNaziv_knjige());
        dl.knjigaAutor.setText("AUTOR: " + listUwp.get(pozicija).getAutor());

        //Picasso.with(c).load()
        if(listUwp.get(pozicija).isDostupnost()){
            dl.ikonaIznajmljena.setImageResource(R.color.colorPrimary);
        }else{
            dl.ikonaIznajmljena.setImageResource(R.color.colorAccent);
        }
        return convertView;
    }
}
