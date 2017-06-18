package com.example.android.sparklibrary.Fragmenti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.sparklibrary.Klase.Knjiga;
import com.example.android.sparklibrary.Klase.PosudjeneKnjige;
import com.example.android.sparklibrary.R;
import com.example.android.sparklibrary.Storage.AppHelper;
import com.example.android.sparklibrary.Storage.KnjigeStorage;
import com.example.android.sparklibrary.Storage.PosudjeneKnjigeStorage;
import com.example.android.sparklibrary.Storage.Storage;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class PosudjenaKnjigaFragment extends Fragment {


    private static final String TAG = "PosudjeneKnjige";

    PosudjeneKnjige knjiga;

    Knjiga knjiga2;
    TextView naslov_knjige_id, autor_id_imeprezime, naklada_id,
            godina_izdanja_id, broj_stranica_id, klasifikacijski_broj_id;

    Button spremi, nazad;


    List<PosudjeneKnjige> knjigePosudjene;


    private View rootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        rootView = inflater.inflate(R.layout.posudjena_knjiga_fragment, container, false);

        knjiga = (PosudjeneKnjige) getArguments().getSerializable("posudjenaKnjiga");
        knjiga2 = new Knjiga();
        knjigePosudjene = new ArrayList<PosudjeneKnjige>();

        naslov_knjige_id = (TextView) rootView.findViewById(R.id.naslov_knjige_id);
        autor_id_imeprezime = (TextView) rootView.findViewById(R.id.autor_id_imeprezime);
        naklada_id = (TextView) rootView.findViewById(R.id.naklada_id);
        godina_izdanja_id = (TextView) rootView.findViewById(R.id.godina_izdanja_id);
        broj_stranica_id = (TextView) rootView.findViewById(R.id.broj_stranica_id);
        klasifikacijski_broj_id = (TextView) rootView.findViewById(R.id.klasifikacijski_broj_id);

        Log.d(TAG, "POSUDJENE KNJIGEEE " + new Gson().toJson(AppHelper.getInstance().getPosudjeneKnjigeStorage().getPosudjeneKnjigeStorageList()));
        knjigePosudjene = AppHelper.getInstance().getPosudjeneKnjigeStorage().getPosudjeneKnjigeStorageList();

        Log.d(TAG, "POSUDJENE KNJIGEEE  LOCAL" + new Gson().toJson(knjigePosudjene));

        spremi = (Button) rootView.findViewById(R.id.spremi);
        nazad = (Button) rootView.findViewById(R.id.nazad);


        getKnjigaInfo(knjiga);


        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_view, new PosudjeneKnjigeFragment()).commit();
            }
        });

        spremi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //treba da izbaci Knjigu  iz liste


                PosudjeneKnjigeStorage posudjeneKnjigeStorage = new PosudjeneKnjigeStorage();


                Log.d(TAG, "onClick: " + "PRIJE BRISANJA       " + new Gson().toJson(knjigePosudjene));

                for (int i = 0; i < knjigePosudjene.size(); i++) {
                    if (knjigePosudjene.get(i).getKnjiga_id() == knjiga.getKnjiga_id()) {
                        Log.d(TAG, "onClick: " + "ima pšosudjena knjiga");
                        knjigePosudjene.remove(i);
                    }
                }


                posudjeneKnjigeStorage.setPosudjeneKnjigeStorageList(knjigePosudjene);
                AppHelper.getInstance().setPosudjeneKnjigeStorage(posudjeneKnjigeStorage);

                //remove knjiga from posudjene i setovanje vrijednosti njene u njenoj klasi

                List<Knjiga> listKnjiga = AppHelper.getInstance().getKnjigeStorage().getListaKnjiga();

                Log.d(TAG, "onClick: " + " KNJIGE STORAGE" + new Gson().toJson(listKnjiga));

                for (int i = 0; i < listKnjiga.size(); i++) {
                    if (listKnjiga.get(i).getID() == knjiga.getKnjiga_id()) {
                        Log.d(TAG, "onClick: " + "ima pšosudjena knjiga");
                        listKnjiga.get(i).setDostupnost(true);
                    }else{
                        Log.d(TAG, "onClick: " + "NEMA KNJIGE");
                    }
                }

                KnjigeStorage knjigeStorage = new KnjigeStorage();
                knjigeStorage.setListaKnjiga(listKnjiga);
                Log.d(TAG, "onClick: " + "POSLIJE BRISANJA       " + new Gson().toJson(knjigePosudjene));
                Log.d(TAG, "onClick: " + "Knjiga iy posudjenig       " + new Gson().toJson(listKnjiga));
////
                //knjigePosudjene.remove(knjiga);

                AppHelper.getInstance().setKnjigeStorage(knjigeStorage);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_view,new KnjigeFragment()).commit();

            }
        });


        return rootView;
    }

    private void getKnjigaInfo(PosudjeneKnjige knjiga) {
        if (AppHelper.getInstance().getKnjigeStorage() != null) {
            if (AppHelper.getInstance().getKnjigeStorage().getListaKnjiga() != null) {
                if (AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().size() > 0) {

                    for (int i = 0; i < AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().size(); i++) {
                        if (AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().get(i).getID() == knjiga.getKnjiga_id()) {
                            knjiga2 = AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().get(i);
                            setValuesOnForm(knjiga2);
                        }
                    }

                }


            }
        }
    }

    private void setValuesOnForm(Knjiga knjiga) {
        naslov_knjige_id.setText("Naziv: " + knjiga.getNaziv_knjige());
        autor_id_imeprezime.setText("Autor: " + knjiga.getAutor()); //= (TextView) rootView.findViewById(R.id.autor_id_imeprezime);
        naklada_id.setText("Naklada: " + knjiga.getNaklada()); //= (TextView) rootView.findViewById(R.id.naklada_id);
        godina_izdanja_id.setText("god. izd.: " + String.valueOf(knjiga.getGodina_izdanja())); //= (TextView) rootView.findViewById(R.id.godina_izdanja_id);
        broj_stranica_id.setText("Broj stranica: " + String.valueOf(knjiga.getBroj_stranica()));

        //= (TextView) rootView.findViewById(R.id.broj_stranica_id);
        for (int i = 0; i < Storage.listTipovi().size(); i++) {

            Log.d(TAG, "setValuesOnForm: " + " ID: " + Storage.listTipovi().get(i).getTip_id());
            Log.d(TAG, "setValuesOnForm: " + " Naziv: " + Storage.listTipovi().get(i).getTip_naziv());
            Log.d(TAG, "KNJIGA TIP: " + knjiga.getTip());
            if (knjiga.getTip() == Storage.listTipovi().get(i).getTip_id()) {

                Log.d(TAG, "setValuesOnForm: " + new Gson().toJson(Storage.listTipovi().get(i)));
                klasifikacijski_broj_id.setText("Tip: " + Storage.listTipovi().get(i).getTip_naziv());
            } else {
                Log.d(TAG, "setValuesOnForm: " + " nije pronadjenTip");
            }
        }
    }
}
