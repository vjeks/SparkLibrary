package com.example.android.sparklibrary.Fragmenti;

import android.app.AlertDialog;
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
import com.example.android.sparklibrary.Klase.TipKnjige;
import com.example.android.sparklibrary.R;
import com.example.android.sparklibrary.Storage.AppHelper;
import com.example.android.sparklibrary.Storage.KnjigeStorage;
import com.example.android.sparklibrary.Storage.TipoviStorage;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class KnjigaUnos extends Fragment {


    private static final String TAG = "KnjigaUnos";


    EditText naslov_knjige_id, autor_id_imeprezime, naklada_id,
            godina_izdanja_id, broj_stranica_id;
    Spinner klasifikacijski_broj_id;

    Button spremi, nazad, add_tip;


    Knjiga knjigaUnos;
    List<String> TipoviList;

    // root view
    View rootView;

    private AlertDialog.Builder asd;


    TipKnjige tipKnjige = new TipKnjige();
    TipoviStorage tipoviStorage = new TipoviStorage();
    List<TipKnjige> tipKnjigeList = new ArrayList<TipKnjige>();


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
        TipoviList = new ArrayList<>();
        TipoviList.add("Odaberi Tip");
        spremi = (Button) rootView.findViewById(R.id.spremi);
        nazad = (Button) rootView.findViewById(R.id.nazad);
        add_tip = (Button) rootView.findViewById(R.id.add_tip);

        asd = new AlertDialog.Builder(getActivity());

        add_tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.d("ShowDialog", "showDIalogBox:  pokazan");
                LayoutInflater factory = LayoutInflater.from(getActivity());
                //text_entry is an Layout XML file containing two text field to display in alert dialog

                final View dialogBox = factory.inflate(R.layout.dialog_create_tip, null);
                final EditText tip_unos = (EditText) dialogBox.findViewById(R.id.tip_unos);
                final Button spremi = (Button) dialogBox.findViewById(R.id.spremi);
                final Button otkazi = (Button) dialogBox.findViewById(R.id.otkazi);

                asd.setView(dialogBox);
                final AlertDialog alertDialog = asd.create();
                Log.d("ShowDialog", "showDIalogBox:  Kreiran");

                alertDialog.show();


                otkazi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });


                spremi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (AppHelper.getInstance().getTipoviStorage() != null) {
                            if (AppHelper.getInstance().getTipoviStorage().getTipoviLista() != null) {
                                if (AppHelper.getInstance().getTipoviStorage().getTipoviLista().size() > 0) {
                                    tipKnjigeList = AppHelper.getInstance().getTipoviStorage().getTipoviLista();
                                    tipKnjige.setTip_id(AppHelper.getInstance().getTipoviStorage().getTipoviLista().size());
                                    tipKnjige.setTip_naziv(tip_unos.getText().toString());

                                    Log.d(TAG, "onClick: " + "IF: --- "+new Gson().toJson(tipKnjige));
                                    tipKnjigeList.add(tipKnjige);
                                    tipoviStorage.setTipoviLista(tipKnjigeList);
                                    AppHelper.getInstance().setTipoviStorage(tipoviStorage);
                                } else {
                                    Log.d(TAG, "onClick: " +"ELSE1"+ new Gson().toJson(tipKnjige));

                                    tipKnjige.setTip_id(1);
                                    tipKnjige.setTip_naziv(tip_unos.getText().toString());
                                    tipKnjigeList.add(tipKnjige);
                                    tipoviStorage.setTipoviLista(tipKnjigeList);
                                    AppHelper.getInstance().setTipoviStorage(tipoviStorage);
                                }
                            } else {
                                Log.d(TAG, "onClick: " +"ELSE2"+ new Gson().toJson(tipKnjige));

                                tipKnjige.setTip_id(1);
                                tipKnjige.setTip_naziv(tip_unos.getText().toString());
                                tipKnjigeList.add(tipKnjige);
                                tipoviStorage.setTipoviLista(tipKnjigeList);
                                AppHelper.getInstance().setTipoviStorage(tipoviStorage);
                            }
                        } else {
                            Log.d(TAG, "onClick: " +"ELSE3"+ new Gson().toJson(tipKnjige));

                            tipKnjige.setTip_id(1);
                            tipKnjige.setTip_naziv(tip_unos.getText().toString());
                            tipKnjigeList.add(tipKnjige);
                            tipoviStorage.setTipoviLista(tipKnjigeList);
                            Log.d(TAG, "onClick: " +"ELSE"+ new Gson().toJson(tipKnjige));

                            AppHelper.getInstance().setTipoviStorage(tipoviStorage);
                        }
                        alertDialog.dismiss();


                       // KnjigaUnos knjigaUnos = new KnjigaUnos();
                        //knjigaUnos = knjigaUnos

                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_view, new KnjigaUnos()).commit();
                       // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_vie)
                    }
                });
            }
        });


        if (AppHelper.getInstance().getTipoviStorage() != null) {
            if (AppHelper.getInstance().getTipoviStorage().getTipoviLista() != null) {
                if (AppHelper.getInstance().getTipoviStorage().getTipoviLista().size() > 0) {
                    for (int i = 0; i < AppHelper.getInstance().getTipoviStorage().getTipoviLista().size(); i++) {
                        TipoviList.add(AppHelper.getInstance().getTipoviStorage().getTipoviLista().get(i).getTip_naziv());
                    }
                }
            }
        }


        ArrayAdapter<String> spinnerCategories = new ArrayAdapter<String>(getActivity(), R.layout.route_preview_sp_item_cat_lang_search_text1,
                TipoviList);
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


//        SetValuesOnFOrm();
        return rootView;


    }


    private void unesiKnjigu() {

        List<Knjiga> knjigeList = new ArrayList<>();
        KnjigeStorage knjigeStorage = new KnjigeStorage();

        knjigaUnos.setNaziv_knjige(naslov_knjige_id.getText().toString());
        knjigaUnos.setAutor(naslov_knjige_id.getText().toString());
        knjigaUnos.setNaklada(naklada_id.getText().toString());
        knjigaUnos.setGodina_izdanja(Integer.parseInt(godina_izdanja_id.getText().toString()));
        knjigaUnos.setBroj_stranica(Integer.parseInt(broj_stranica_id.getText().toString()));
        int setSelectedItem = klasifikacijski_broj_id.getSelectedItemPosition();
        knjigaUnos.setTip(setSelectedItem);
        Log.d(TAG, "unesiKnjigu: " + " klasifikacijski:broj " + setSelectedItem);
        knjigaUnos.setDostupnost(true);

        //Log.d(TAG, "unesiKnjigu: " +"Lista knjiga "+ new Gson().toJson(AppHelper.getInstance().getKnjigeStorage().getListaKnjiga() ));

        if (AppHelper.getInstance().getKnjigeStorage() != null) {
            if (AppHelper.getInstance().getKnjigeStorage().getListaKnjiga() != null) {
                if (AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().size() > 0) {
                    knjigeStorage = AppHelper.getInstance().getKnjigeStorage();
                    knjigeList = knjigeStorage.getListaKnjiga();
                    knjigaUnos.setID(AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().size() + 1);

                    knjigeList.add(knjigaUnos);
                    knjigeStorage.setListaKnjiga(knjigeList);
                    AppHelper.getInstance().setKnjigeStorage(knjigeStorage);
                    Log.d(TAG, "unesiKnjigu: ");
                } else {
                    knjigaUnos.setID(1);
                    knjigeList.add(knjigaUnos);
                    knjigeStorage.setListaKnjiga(knjigeList);
                    AppHelper.getInstance().setKnjigeStorage(knjigeStorage);
                }
            } else {
                knjigaUnos.setID(1);
                knjigeList.add(knjigaUnos);
                knjigeStorage.setListaKnjiga(knjigeList);
                AppHelper.getInstance().setKnjigeStorage(knjigeStorage);
            }
        } else {
            knjigaUnos.setID(1);
            knjigeList.add(knjigaUnos);
            knjigeStorage.setListaKnjiga(knjigeList);
            AppHelper.getInstance().setKnjigeStorage(knjigeStorage);
        }

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_view, new KnjigeFragment()).commit();
    }

    private void SetValuesOnFOrm() {
        naslov_knjige_id.setText("Druzba pere kvrzice ");
        autor_id_imeprezime.setText("Petar Kocic"); //= (TextView) rootView.findViewById(R.id.autor_id_imeprezime);
        naklada_id.setText("Svjetlost Sarajevo "); //+ knjiga.getNaklada()); //= (TextView) rootView.findViewById(R.id.naklada_id);
        godina_izdanja_id.setText("1985"); //= (TextView) rootView.findViewById(R.id.godina_izdanja_id);
        broj_stranica_id.setText("1234"); //= (TextView) rootView.findViewById(R.id.broj_stranica_id);
        klasifikacijski_broj_id.setSelection(1); //= (TextView) rootView.findViewById(R.id.klasifikacijski_broj_id);


    }
}
