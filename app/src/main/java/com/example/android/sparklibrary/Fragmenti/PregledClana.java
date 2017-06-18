package com.example.android.sparklibrary.Fragmenti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.sparklibrary.Adapteri.PosudjeneKnjigeAdapter;
import com.example.android.sparklibrary.Klase.Clanovi;
import com.example.android.sparklibrary.Klase.PosudjeneKnjige;
import com.example.android.sparklibrary.R;
import com.example.android.sparklibrary.Storage.AppHelper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;



public class PregledClana extends Fragment {


    TextView clan_id, clan_ime_prezime, clan_adresa, clan_tel_broj, clan_clanski_broj;

    ListView clan_posudjene_knjige_lv;
    PosudjeneKnjigeAdapter posudjeneKnjigeAdapter;
    List<PosudjeneKnjige> posudjeneKnjigePoUseru;


    View rootView;

    Clanovi clan;
    private static final String TAG= "PregledClana";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        rootView = inflater.inflate(R.layout.pregled_clana_layout, container, false);


        clan_id = (TextView) rootView.findViewById(R.id.clan_id);
        clan_ime_prezime = (TextView) rootView.findViewById(R.id.clan_ime_prezime);
        clan_adresa = (TextView) rootView.findViewById(R.id.clan_adresa);
        clan_tel_broj = (TextView) rootView.findViewById(R.id.clan_tel_broj);
        clan_clanski_broj = (TextView) rootView.findViewById(R.id.clan_clanski_broj);

        clan = new Clanovi();
        clan = (Clanovi) getArguments().getSerializable("clan");

        Log.d(TAG,"CLAN INFO: " + new Gson().toJson(clan));


        clan_posudjene_knjige_lv = (ListView) rootView.findViewById(R.id.clan_posudjene_knjige_lv);
        posudjeneKnjigePoUseru = new ArrayList<>();
//        posudjeneKnjigeAdapter = new PosudjeneKnjigeAdapter(getActivity(), )
        SetValuesOnForm();
        return rootView;
    }

    private void SetValuesOnForm(){

        clan_id.setText(String.valueOf(clan.getID()));
        clan_ime_prezime.setText(clan.getIme() + " " +clan.getPrezime());
         clan_adresa.setText(""+ clan.getAdresa());
        clan_tel_broj.setText(""+clan.getTel_broj());
        clan_clanski_broj.setText(clan.getClan_broj());


        if(AppHelper.getInstance().getPosudjeneKnjigeStorage()!= null){
            if(AppHelper.getInstance().getPosudjeneKnjigeStorage().getPosudjeneKnjigeStorageList()!= null){
                if(AppHelper.getInstance().getPosudjeneKnjigeStorage().getPosudjeneKnjigeStorageList().size()>0){
                    for (int i = 0; i < AppHelper.getInstance().getPosudjeneKnjigeStorage().getPosudjeneKnjigeStorageList().size(); i++) {
                        if(AppHelper.getInstance().getPosudjeneKnjigeStorage().getPosudjeneKnjigeStorageList().get(i).getClan_id() == clan.getID()){
                            posudjeneKnjigePoUseru.add(AppHelper.getInstance().getPosudjeneKnjigeStorage().getPosudjeneKnjigeStorageList().get(i));
                        }
                    }
                    posudjeneKnjigeAdapter = new PosudjeneKnjigeAdapter(getActivity(), posudjeneKnjigePoUseru);
                    clan_posudjene_knjige_lv.setAdapter(posudjeneKnjigeAdapter);
                }
            }
        }

    }
}
