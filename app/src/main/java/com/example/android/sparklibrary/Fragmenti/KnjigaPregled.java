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
import com.example.android.sparklibrary.Klase.PosudjeneKnjige;
import com.example.android.sparklibrary.R;
import com.example.android.sparklibrary.Storage.AppHelper;
import com.example.android.sparklibrary.Storage.KnjigeStorage;
import com.example.android.sparklibrary.Storage.PosudjeneKnjigeStorage;
import com.example.android.sparklibrary.Storage.Storage;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by adissertovic on 15/06/17.
 */

public class KnjigaPregled extends Fragment {


    public static final String TAG = "KnjigaPregled";

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

        Log.d(TAG, "onCreateView: " + "KNJIGA JSON *******   "+new Gson().toJson(knjiga));


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

                    for (int i = 0; i < AppHelper.getInstance().getClanoviStorage().getListaCLanova().size(); i++) {
                        if(AppHelper.getInstance().getClanoviStorage().getListaCLanova().get(i).getID()==Integer.parseInt(clan_broj_identifikacije.getText().toString())){
                            clanZaProvjeru = new Clanovi();
                            clanZaProvjeru = AppHelper.getInstance().getClanoviStorage().getListaCLanova().get(i);
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


        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        final String formattedDate = df.format(c.getTime());


        Log.d(TAG, "Prije klika na da ili ne");
        Log.d(TAG, "Knjiga info: " + new Gson().toJson(knjiga));
        String zauzetaKnjiga = new Gson().toJson(knjiga);


        Knjiga knjiga1 = new Gson().fromJson(zauzetaKnjiga,Knjiga.class);
        Log.d(TAG, "Knjiga1 info: " + new Gson().toJson(knjiga1));

        da.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Knjiga je uspjesno posudjena: " + clanZaProvjeru.getIme() + " " + clanZaProvjeru.getPrezime(), Toast.LENGTH_SHORT).show();
                //TODO dodati clana u storage i zapamtiti;

                PosudjeneKnjige posudjenaKnjigaUnos = new PosudjeneKnjige();
                List<PosudjeneKnjige> posudjeneKnjigeList= new ArrayList<>();
                PosudjeneKnjigeStorage posudjeneKnjigeStorage= new PosudjeneKnjigeStorage();

                if(AppHelper.getInstance().getPosudjeneKnjigeStorage() != null){
                    if(AppHelper.getInstance().getPosudjeneKnjigeStorage().getPosudjeneKnjigeStorageList()!=null){
                        if (AppHelper.getInstance().getPosudjeneKnjigeStorage().getPosudjeneKnjigeStorageList().size()>0){
                            posudjeneKnjigeStorage = AppHelper.getInstance().getPosudjeneKnjigeStorage();
                            posudjeneKnjigeList = posudjeneKnjigeStorage.getPosudjeneKnjigeStorageList();

                            posudjenaKnjigaUnos.setKnjiga_id(knjiga.getID());
                            posudjenaKnjigaUnos.setClan_id(clanZaProvjeru.getID());
                            posudjenaKnjigaUnos.setDatum(formattedDate);


                            posudjeneKnjigeList.add(posudjenaKnjigaUnos);

                            posudjeneKnjigeStorage.setPosudjeneKnjigeStorageList(posudjeneKnjigeList);
                            AppHelper.getInstance().setPosudjeneKnjigeStorage(posudjeneKnjigeStorage);
                            Log.d(TAG, "unesiKnjigu: ");
                        }else{
                            posudjenaKnjigaUnos.setKnjiga_id(knjiga.getID());
                            posudjenaKnjigaUnos.setClan_id(clanZaProvjeru.getID());
                            posudjenaKnjigaUnos.setDatum(formattedDate);
                            posudjeneKnjigeList.add(posudjenaKnjigaUnos);
                            posudjeneKnjigeStorage.setPosudjeneKnjigeStorageList(posudjeneKnjigeList);
                            AppHelper.getInstance().setPosudjeneKnjigeStorage(posudjeneKnjigeStorage);
                        }
                    }else{
                        posudjenaKnjigaUnos.setKnjiga_id(knjiga.getID());
                        posudjenaKnjigaUnos.setClan_id(clanZaProvjeru.getID());
                        posudjenaKnjigaUnos.setDatum(formattedDate);
                        posudjeneKnjigeList.add(posudjenaKnjigaUnos);
                        posudjeneKnjigeStorage.setPosudjeneKnjigeStorageList(posudjeneKnjigeList);
                        AppHelper.getInstance().setPosudjeneKnjigeStorage(posudjeneKnjigeStorage);
                    }

                }else {
                    posudjenaKnjigaUnos.setKnjiga_id(knjiga.getID());
                    posudjenaKnjigaUnos.setClan_id(clanZaProvjeru.getID());
                    posudjenaKnjigaUnos.setDatum(formattedDate);
                    posudjeneKnjigeList.add(posudjenaKnjigaUnos);
                    posudjeneKnjigeStorage.setPosudjeneKnjigeStorageList(posudjeneKnjigeList);
                    AppHelper.getInstance().setPosudjeneKnjigeStorage(posudjeneKnjigeStorage);
                }

                List<Knjiga> knjige = new ArrayList<Knjiga>();
                KnjigeStorage knjigeStorage = new KnjigeStorage();
                knjige = AppHelper.getInstance().getKnjigeStorage().getListaKnjiga();

                for (int i = 0; i < knjige.size(); i++) {
                    if(knjige.get(i).getID() == knjiga.getID()){
                        knjige.get(i).setDostupnost(false);
                    }

                }
                knjigeStorage.setListaKnjiga(knjige);
                AppHelper.getInstance().setKnjigeStorage(knjigeStorage);


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
