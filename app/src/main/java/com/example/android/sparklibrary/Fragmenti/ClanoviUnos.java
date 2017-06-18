package com.example.android.sparklibrary.Fragmenti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.sparklibrary.Klase.Clanovi;
import com.example.android.sparklibrary.R;
import com.example.android.sparklibrary.Storage.AppHelper;
import com.example.android.sparklibrary.Storage.ClanoviStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adissertovic on 16/06/17.
 */

public class ClanoviUnos extends Fragment {

    public static final  String TAG="ClanoviUnos";
    EditText clan_ime, clan_prezime, clan_broj,
            clan_adresa, clan_tel_broj;
    Button nazad, spremi;

    View rootView;

    Clanovi clanUnos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        rootView = inflater.inflate(R.layout.clanovi_unos_fragment, container, false);


        clan_ime = (EditText)rootView.findViewById(R.id.clan_ime);
        clan_prezime = (EditText)rootView.findViewById(R.id.clan_prezime);
        clan_broj = (EditText)rootView.findViewById(R.id.clan_broj);
        clan_adresa = (EditText)rootView.findViewById(R.id.clan_adresa);
        clan_tel_broj = (EditText)rootView.findViewById(R.id.clan_tel_broj);
        clan_broj.setEnabled(false);
        nazad= (Button) rootView.findViewById(R.id.nazad);
        spremi= (Button) rootView.findViewById(R.id.spremi);

        clanUnos = new Clanovi();

        spremi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unesiCLana();
            }
        });
        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });




        //popuniPolja();



        return rootView;
    }

    private void popuniPolja() {

        clan_ime.setText("Vjekoslav");// = (EditText)rootView.findViewById(R.id.clan_ime);
        clan_prezime.setText("Vjekoslav");
        clan_broj.setText("Vjekoslav");
        clan_adresa.setText("Vjekoslav");
        clan_tel_broj.setText("");


    }

    private void unesiCLana(){

        List<Clanovi> clanoviList = new ArrayList<>();
        ClanoviStorage clanoviStorage = new ClanoviStorage();


        clanUnos.setIme(clan_ime.getText().toString());
        clanUnos.setPrezime(clan_prezime.getText().toString());
        clanUnos.setClan_broj(clan_broj.getText().toString());
        clanUnos.setAdresa(clan_adresa.getText().toString());

        if(AppHelper.getInstance().getClanoviStorage() != null){
            if(AppHelper.getInstance().getClanoviStorage().getListaCLanova()!=null){
                if (AppHelper.getInstance().getClanoviStorage().getListaCLanova().size()>0){
                    clanoviStorage = AppHelper.getInstance().getClanoviStorage();
                    clanoviList = clanoviStorage.getListaCLanova();
                    clanUnos.setID(clanoviList.size()+1);
                    clanUnos.setTel_broj(String.valueOf(clanUnos.getID()));
                    clanoviList.add(clanUnos);
                    clanoviStorage.setListaCLanova(clanoviList);
                    AppHelper.getInstance().setClanoviStorage(clanoviStorage);
                    Log.d(TAG, "unesiKnjigu: ");
                }else{
                    clanUnos.setID(1);
                    clanoviList.add(clanUnos);
                    clanUnos.setTel_broj(String.valueOf(clanUnos.getID()));

                    clanoviStorage.setListaCLanova(clanoviList);
                    AppHelper.getInstance().setClanoviStorage(clanoviStorage);
                }
            }else{
                clanUnos.setID(1);
                clanUnos.setTel_broj(String.valueOf(clanUnos.getID()));
                clanoviList.add(clanUnos);
                clanoviStorage.setListaCLanova(clanoviList);
                AppHelper.getInstance().setClanoviStorage(clanoviStorage);
            }

        }else {
            clanUnos.setID(1);
            clanUnos.setTel_broj(String.valueOf(clanUnos.getID()));
            clanoviList.add(clanUnos);
            clanoviStorage.setListaCLanova(clanoviList);
            AppHelper.getInstance().setClanoviStorage(clanoviStorage);
        }

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_view,new ClanoviFragment()).commit();
    }
}
