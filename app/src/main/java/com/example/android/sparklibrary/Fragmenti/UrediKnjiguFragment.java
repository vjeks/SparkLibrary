package com.example.android.sparklibrary.Fragmenti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.android.sparklibrary.Klase.Knjiga;
import com.example.android.sparklibrary.R;
import com.example.android.sparklibrary.Storage.AppHelper;
import com.example.android.sparklibrary.Storage.KnjigeStorage;
import com.example.android.sparklibrary.Storage.Storage;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class UrediKnjiguFragment extends Fragment {


    private static String TAG = "UREDI_KNJIGU";
    EditText naslov_knjige_id, autor_id_imeprezime, naklada_id,
            godina_izdanja_id, broj_stranica_id;
    Spinner klasifikacijski_broj_id;

    Button spremi, nazad;


    FloatingActionButton fab;
    Knjiga knjiga1;
    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        rootView = inflater.inflate(R.layout.uredi_knjigu_fragment, container, false);

        naslov_knjige_id = (EditText) rootView.findViewById(R.id.naslov_knjige_id);
        autor_id_imeprezime = (EditText) rootView.findViewById(R.id.autor_id_imeprezime);
        naklada_id = (EditText) rootView.findViewById(R.id.naklada_id);
        godina_izdanja_id = (EditText) rootView.findViewById(R.id.godina_izdanja_id);
        broj_stranica_id = (EditText) rootView.findViewById(R.id.broj_stranica_id);

        klasifikacijski_broj_id = (Spinner) rootView.findViewById(R.id.klasifikacijski_broj_id);


        knjiga1 = new Knjiga();
        knjiga1 = (Knjiga) getArguments().getSerializable("knjiga");


        Log.d(TAG, "onCreateView: "+ new Gson().toJson(knjiga1));

        fillItemsOnForm(knjiga1);


        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.GONE);


        spremi = (Button) rootView.findViewById(R.id.spremi);

        nazad = (Button) rootView.findViewById(R.id.nazad);

        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        List<String> tipovi = new ArrayList<>();
        tipovi.add("Svi tipovi");

        if (AppHelper.getInstance().getTipoviStorage() != null) {
            if (AppHelper.getInstance().getTipoviStorage().getTipoviLista() != null) {
                if (AppHelper.getInstance().getTipoviStorage().getTipoviLista().size() > 0) {
                    for (int i = 0; i < AppHelper.getInstance().getTipoviStorage().getTipoviLista().size(); i++) {
                        tipovi.add(AppHelper.getInstance().getTipoviStorage().getTipoviLista().get(i).getTip_naziv());
                    }
                }
            }
        }


        ArrayAdapter<String> spinnerCategories = new ArrayAdapter<String>(getActivity(), R.layout.route_preview_sp_item_cat_lang_search_text1,
                tipovi);

        spinnerCategories.setDropDownViewResource(R.layout.spinner_dropdown_item);

        klasifikacijski_broj_id.setAdapter(spinnerCategories);

        spremi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Knjiga knjigaIzmjene = new Knjiga();
                knjigaIzmjene.setID(knjiga1.getID());
                knjigaIzmjene.setNaziv_knjige(naslov_knjige_id.getText().toString());
                knjigaIzmjene.setAutor(naslov_knjige_id.getText().toString());
                knjigaIzmjene.setNaklada(naklada_id.getText().toString());
                knjigaIzmjene.setGodina_izdanja(Integer.parseInt(godina_izdanja_id.getText().toString()));
                knjigaIzmjene.setBroj_stranica(Integer.parseInt(broj_stranica_id.getText().toString()));
                knjigaIzmjene.setDostupnost(knjiga1.isDostupnost());
                int setSelectedItem = klasifikacijski_broj_id.getSelectedItemPosition() + 1;
                knjigaIzmjene.setTip(setSelectedItem);
                Log.d(TAG, "unesiKnjigu: " + " klasifikacijski:broj " + setSelectedItem);

                Log.d(TAG, "onClick: " + new Gson().toJson(knjigaIzmjene));
                Log.d(TAG, "onClick: " + new Gson().toJson(AppHelper.getInstance().getKnjigeStorage().getListaKnjiga()));

                List<Knjiga> knjigeList = new ArrayList<>();
                KnjigeStorage knjigeStorage = new KnjigeStorage();

                if (AppHelper.getInstance().getKnjigeStorage() != null) {
                    if (AppHelper.getInstance().getKnjigeStorage().getListaKnjiga() != null) {
                        if (AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().size() > 0) {

                            knjigeList = AppHelper.getInstance().getKnjigeStorage().getListaKnjiga();
                            Log.d(TAG, "onCLick" + new Gson().toJson(knjigeList) );

                            for (int i = 0; i < AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().size(); i++) {
                                if (knjigeList.get(i).getID() == knjiga1.getID()){
                                    Log.d(TAG, "onClick: IMA KNJIGEEEE");
                                    Log.d(TAG, "onClick: " + new Gson().toJson(knjigeList.get(i)));

                                    knjigeList.remove(i);
                                    knjigeList.add(i,knjigaIzmjene);

                                    break;
                                }else{
                                    Log.d(TAG, "onClick: NEMA KNJIGEEEE");
                                }
                            }
                        } else {

                        }
                    } else {

                    }
                } else {

                }
                knjigeStorage.setListaKnjiga(knjigeList);
                AppHelper.getInstance().setKnjigeStorage(knjigeStorage);

               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_view, new KnjigeFragment()).commit();

            }
        });


        return rootView;
    }

    private void fillItemsOnForm(Knjiga knjiga) {
        naslov_knjige_id.setText(knjiga.getNaziv_knjige());
        autor_id_imeprezime.setText(knjiga.getAutor()); //= (TextView) rootView.findViewById(R.id.autor_id_imeprezime);
        naklada_id.setText(knjiga.getNaklada()); //= (TextView) rootView.findViewById(R.id.naklada_id);
        godina_izdanja_id.setText(String.valueOf(knjiga.getGodina_izdanja())); //= (TextView) rootView.findViewById(R.id.godina_izdanja_id);
        broj_stranica_id.setText(String.valueOf(knjiga.getBroj_stranica()));

        //= (TextView) rootView.findViewById(R.id.broj_stranica_id);
        for (int i = 0; i < Storage.listTipovi().size(); i++) {

            Log.d(TAG, "setValuesOnForm: " + " ID: " + Storage.listTipovi().get(i).getTip_id());
            Log.d(TAG, "setValuesOnForm: " + " Naziv: " + Storage.listTipovi().get(i).getTip_naziv());
            Log.d(TAG, "KNJIGA TIP: " + knjiga.getTip());
            if (knjiga.getTip() == Storage.listTipovi().get(i).getTip_id()) {

                Log.d(TAG, "setValuesOnForm: " + new Gson().toJson(Storage.listTipovi().get(i)));
                klasifikacijski_broj_id.setSelection(Storage.listTipovi().get(i).getTip_id());
            } else {
                Log.d(TAG, "setValuesOnForm: " + " nije pronadjenTip");
            }
        }
    }
}
