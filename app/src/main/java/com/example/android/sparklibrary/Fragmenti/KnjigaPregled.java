package com.example.android.sparklibrary.Fragmenti;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.sparklibrary.Klase.Clanovi;
import com.example.android.sparklibrary.Klase.Knjiga;
import com.example.android.sparklibrary.R;
import com.example.android.sparklibrary.Storage.Storage;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by adissertovic on 15/06/17.
 */

public class KnjigaPregled extends Fragment {


    public static String TAG = "KnjigaPregled";

    View rootView;
    //inicijalizacija podataka
    TextView naslov_knjige_id, autor_id_imeprezime, naklada_id,
            godina_izdanja_id, broj_stranica_id, klasifikacijski_broj_id;

    EditText clan_broj_identifikacije;
    Button spremi, nazad;

    private AlertDialog.Builder asd;
    Knjiga knjiga;

    List<Clanovi> listaClanova;
    Clanovi clanZaProvjeru;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.knjiga_pregled_layout, container, false);

        naslov_knjige_id = (TextView) rootView.findViewById(R.id.naslov_knjige_id);
        autor_id_imeprezime = (TextView) rootView.findViewById(R.id.autor_id_imeprezime);
        naklada_id = (TextView) rootView.findViewById(R.id.naklada_id);
        godina_izdanja_id = (TextView) rootView.findViewById(R.id.godina_izdanja_id);
        broj_stranica_id = (TextView) rootView.findViewById(R.id.broj_stranica_id);
        klasifikacijski_broj_id = (TextView) rootView.findViewById(R.id.klasifikacijski_broj_id);

listaClanova = new ArrayList<>();
        listaClanova = Storage.ListCLanova();

        clan_broj_identifikacije = (EditText) rootView.findViewById(R.id.clan_broj_identifikacije);

        spremi = (Button) rootView.findViewById(R.id.spremi);
        nazad = (Button) rootView.findViewById(R.id.nazad);


        Bundle bundle = getArguments();
        knjiga = new Knjiga();
        knjiga = (Knjiga)getArguments().getSerializable("knjiga");


        setValuesOnForm(knjiga);

        asd = new AlertDialog.Builder(getActivity());


        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_view,new KnjigeFragment()).commit();
            }
        });


        spremi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clan_broj_identifikacije!= null && !clan_broj_identifikacije.getText().toString().isEmpty()){

                    for (int i = 0; i < listaClanova.size(); i++) {
                        if(listaClanova.get(i).getClan_broj().equals(clan_broj_identifikacije.getText().toString())){
                            clanZaProvjeru = new Clanovi();
                            clanZaProvjeru = listaClanova.get(i);
                            imaclan = true;
                            break;
                        }

                    }
                    if(imaclan){
                        showDIalogBox();
                    }else{
                        Toast.makeText(getActivity(), "Clan nije pronadjen", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "niste unijeli clanski broj!!!!!!!!", Toast.LENGTH_SHORT).show();

                }
            }
        });




        return rootView;
    }

boolean imaclan= false;




    private void showDIalogBox() {


        Log.d("ShowDialog", "showDIalogBox:  pokazan");
        LayoutInflater factory = LayoutInflater.from(getActivity());
        //text_entry is an Layout XML file containing two text field to display in alert dialog

        final View dialogBox = factory.inflate(R.layout.dialog_posudba_knjige, null);
        final TextView text = (TextView) dialogBox.findViewById(R.id.text_na_dialogu);
        final Button da = (Button) dialogBox.findViewById(R.id.da);
        final Button ne = (Button) dialogBox.findViewById(R.id.ne);

        asd.setView(dialogBox);
        final AlertDialog alertDialog = asd.create();
        Log.d("ShowDialog", "showDIalogBox:  Kreiran");

        alertDialog.show();

        ne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


        da.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Knjiga je uspjesno posudjena: " + clanZaProvjeru.getIme() + " " + clanZaProvjeru.getPrezime(), Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
                Date date = new Date();
                String TAG = "noviFragment"+date.getTime();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.content_view, new KnjigeFragment(), TAG).commit();
            }
        });


    }

    private void setValuesOnForm(Knjiga knjiga) {
        naslov_knjige_id.setText("Naziv: " + knjiga.getNaziv_knjige());
        autor_id_imeprezime.setText("Autor: " + knjiga.getAutor()); //= (TextView) rootView.findViewById(R.id.autor_id_imeprezime);
        naklada_id.setText("Naklada: " + knjiga.getNaklada()); //= (TextView) rootView.findViewById(R.id.naklada_id);
        godina_izdanja_id.setText("god. izd.: " + String.valueOf(knjiga.getGodina_izdanja())); //= (TextView) rootView.findViewById(R.id.godina_izdanja_id);
        broj_stranica_id.setText("Broj stranica: " + String.valueOf(knjiga.getBroj_stranica()));

        //= (TextView) rootView.findViewById(R.id.broj_stranica_id);
        for (int i = 0; i < Storage.listTipovi().size(); i++) {

            Log.d(TAG, "setValuesOnForm: "+ " ID: " + Storage.listTipovi().get(i).getTip_id());
            Log.d(TAG, "setValuesOnForm: "+ " Naziv: " + Storage.listTipovi().get(i).getTip_naziv());
            Log.d(TAG,"KNJIGA TIP: "+knjiga.getTip());
            if(knjiga.getTip() == Storage.listTipovi().get(i).getTip_id()){

                Log.d(TAG, "setValuesOnForm: "+ new Gson().toJson(Storage.listTipovi().get(i)));
                klasifikacijski_broj_id.setText("Tip: " + Storage.listTipovi().get(i).getTip_naziv());
            }else{
                Log.d(TAG, "setValuesOnForm: " + " nije pronadjenTip");
            }
        }



         //= (TextView) rootView.findViewById(R.id.klasifikacijski_broj_id);
    }
}
