package com.example.android.sparklibrary.Fragmenti;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adissertovic on 16/06/17.
 */

public class KnjigaUnos extends Fragment {


    private static final String TAG = "KnjigaUnos";


    EditText naslov_knjige_id, autor_id_imeprezime, naklada_id,
            godina_izdanja_id, broj_stranica_id;
    Spinner klasifikacijski_broj_id;

    Button spremi, nazad;


    Knjiga knjigaUnos;

    // root view
    View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.unos_knjige_fragment, container, false);



        naslov_knjige_id = (EditText) rootView.findViewById(R.id.naslov_knjige_id);
        autor_id_imeprezime = (EditText) rootView.findViewById(R.id.autor_id_imeprezime);
        naklada_id = (EditText) rootView.findViewById(R.id.naklada_id);
        godina_izdanja_id = (EditText) rootView.findViewById(R.id.godina_izdanja_id);
        broj_stranica_id = (EditText) rootView.findViewById(R.id.broj_stranica_id);
        klasifikacijski_broj_id = (Spinner) rootView.findViewById(R.id.klasifikacijski_broj_id);

        spremi = (Button) rootView.findViewById(R.id.spremi);
        nazad = (Button) rootView.findViewById(R.id.nazad);


        knjigaUnos = new Knjiga();

        ArrayAdapter<String> spinnerCategories = new ArrayAdapter<String>(getActivity(), R.layout.route_preview_sp_item_cat_lang_search_text1,
                Storage.listTipoviStringovi());
        spinnerCategories.setDropDownViewResource(R.layout.spinner_dropdown_item);

        klasifikacijski_broj_id.setAdapter(spinnerCategories);

        spremi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unesiKnjigu();
            }
        });
        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });




        SetValuesOnFOrm();
        return rootView;


    }



    private void unesiKnjigu(){

        List<Knjiga> knjigeList = new ArrayList<>();
        KnjigeStorage knjigeStorage = new KnjigeStorage();

        knjigaUnos.setNaziv_knjige(naslov_knjige_id.getText().toString());
        knjigaUnos.setAutor(naslov_knjige_id.getText().toString());
        knjigaUnos.setNaklada(naklada_id.getText().toString());
        knjigaUnos.setGodina_izdanja(Integer.parseInt(godina_izdanja_id.getText().toString()));
        knjigaUnos.setBroj_stranica(Integer.parseInt(broj_stranica_id.getText().toString()));
        int setSelectedItem = klasifikacijski_broj_id.getSelectedItemPosition()+1;
        knjigaUnos.setTip(setSelectedItem);
        Log.d(TAG, "unesiKnjigu: "+" klasifikacijski:broj "+ setSelectedItem);
        knjigaUnos.setDostupnost(true);

        if(AppHelper.getInstance().getKnjigeStorage() != null){
            if(AppHelper.getInstance().getKnjigeStorage().getListaKnjiga()!= null){
                if(AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().size()>0){
                    knjigeStorage = AppHelper.getInstance().getKnjigeStorage();
                    knjigeList = knjigeStorage.getListaKnjiga();
                    knjigeList.add(knjigaUnos);
                    knjigeStorage.setListaKnjiga(knjigeList);
                    AppHelper.getInstance().setKnjigeStorage(knjigeStorage);
                    Log.d(TAG, "unesiKnjigu: ");
                }else{
                    knjigeList.add(knjigaUnos);
                    knjigeStorage.setListaKnjiga(knjigeList);
                    AppHelper.getInstance().setKnjigeStorage(knjigeStorage);
                }
            }else{
                knjigeList.add(knjigaUnos);
                knjigeStorage.setListaKnjiga(knjigeList);
                AppHelper.getInstance().setKnjigeStorage(knjigeStorage);
            }
        }else {
            knjigeList.add(knjigaUnos);
            knjigeStorage.setListaKnjiga(knjigeList);
            AppHelper.getInstance().setKnjigeStorage(knjigeStorage);
        }

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_view,new KnjigeFragment()).commit();
    }

    private void SetValuesOnFOrm(){
        naslov_knjige_id.setText("Druzba pere kvrzice ");
        autor_id_imeprezime.setText("Petar Kocic"); //= (TextView) rootView.findViewById(R.id.autor_id_imeprezime);
        naklada_id.setText("Svjetlost Sarajevo "); //+ knjiga.getNaklada()); //= (TextView) rootView.findViewById(R.id.naklada_id);
        godina_izdanja_id.setText("1985"); //= (TextView) rootView.findViewById(R.id.godina_izdanja_id);
        broj_stranica_id.setText("1234"); //= (TextView) rootView.findViewById(R.id.broj_stranica_id);
        klasifikacijski_broj_id.setSelection(1); //= (TextView) rootView.findViewById(R.id.klasifikacijski_broj_id);


    }
}
