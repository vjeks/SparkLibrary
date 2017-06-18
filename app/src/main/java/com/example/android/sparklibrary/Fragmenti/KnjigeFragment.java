package com.example.android.sparklibrary.Fragmenti;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.sparklibrary.Adapteri.KnjigeListAdapter;
import com.example.android.sparklibrary.Klase.Knjiga;
import com.example.android.sparklibrary.R;
import com.example.android.sparklibrary.Storage.AppHelper;
import com.example.android.sparklibrary.Storage.KnjigeStorage;
import com.example.android.sparklibrary.Storage.Storage;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class KnjigeFragment extends Fragment {

    public static String TAG = "KnjigeFragment";

    View rootView;
    ListView knjigeList;
    List<Knjiga> knjigaListe;
    KnjigeListAdapter knjigeListAdapter;


    Spinner klasifikacije_spinner;

    List<Knjiga> spinnerKnjigeFilter;
    FloatingActionButton fab;
    private AlertDialog.Builder asd;

    Knjiga item;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.knjiga_fragment_layout, container, false);


        knjigeList = (ListView) rootView.findViewById(R.id.knjigeList);
        spinnerKnjigeFilter = new ArrayList<>();
        klasifikacije_spinner = (Spinner) rootView.findViewById(R.id.klasifikacije_spinner);
        item = new Knjiga();
        asd = new AlertDialog.Builder(getActivity());

        //TODO treba instancirati spinner

        List<String> tipovi = new ArrayList<>();
        tipovi.add("Svi tipovi");


        for (int i = 0; i < Storage.listTipoviStringovi().size(); i++) {
            tipovi.add(Storage.listTipoviStringovi().get(i));
        }
        ArrayAdapter<String> spinnerCategories = new ArrayAdapter<String>(getActivity(), R.layout.route_preview_sp_item_cat_lang_search_text1,
                tipovi);
        spinnerCategories.setDropDownViewResource(R.layout.spinner_dropdown_item);

        klasifikacije_spinner.setAdapter(spinnerCategories);

        klasifikacije_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (klasifikacije_spinner.getSelectedItemPosition() == 0) {
                    SetKnjige();
                } else {
                    presentKnjige(position);
                }
                Toast.makeText(getActivity(), "pozicija je" + position, Toast.LENGTH_SHORT).show();
                //presentKnjige(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        knjigeList.setOnItemClickListener(new onListItemClicked());
        knjigeList.setOnItemLongClickListener(new onListItemLongClickListener());

        SetKnjige();


        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();


                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_view, new KnjigaUnos()).addToBackStack(new KnjigeFragment().getClass().getName()).commit();

            }
        });
        return rootView;
    }


    private class onListItemClicked implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Knjiga item = new Knjiga();
            item = knjigaListe.get(i);


            Bundle bundle = new Bundle();
            bundle.putSerializable("knjiga", item);

            KnjigaPregled knjigaPregled = new KnjigaPregled();
            knjigaPregled.setArguments(bundle);
            java.util.Date date = new java.util.Date();
            String TAG = "noviFragment" + date.getTime();
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(rootView.getClass().getName()).
                    add(R.id.content_view, knjigaPregled, TAG).commit();

        }
    }

    private void presentKnjige(int position) {

        spinnerKnjigeFilter.clear();
        for (int i = 0; i < Storage.listTipoviStringovi().size(); i++) {
            if (knjigaListe.get(i).getTip() == position) {
                Log.d("ima knjiga", "*************************");
                spinnerKnjigeFilter.add(knjigaListe.get(i));
            }
        }
        knjigeListAdapter = new KnjigeListAdapter(getActivity(), spinnerKnjigeFilter);
        knjigeListAdapter.notifyDataSetChanged();
        knjigeList.setAdapter(knjigeListAdapter);
    }


    public void SetKnjige() {
        knjigaListe = new ArrayList<>();

        if (AppHelper.getInstance().getKnjigeStorage() != null) {
            if (AppHelper.getInstance().getKnjigeStorage().getListaKnjiga() != null) {
                if (AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().size() > 0) {
                    knjigaListe = AppHelper.getInstance().getKnjigeStorage().getListaKnjiga();
                    knjigeListAdapter = new KnjigeListAdapter(getActivity(), knjigaListe);
                    knjigeList.setAdapter(knjigeListAdapter);
                }
            }
        }

//        knjigaListe = Storage.GetAllKnjige();
//
//        Knjiga knjiga1 = new Knjiga(1,"Druzba pere kvrzice", "treb cpic","bajke1");
//        Knjiga knjiga2 = new Knjiga(2,"HCIe", "uyert cpic","bajk2e");
//        Knjiga knjiga3 = new Knjiga(3,"Programiranje2", "pozdrav cpic","bajke3");
//        Knjiga knjiga4 = new Knjiga(4,"Programiranje23", "Konj cpic","bajke4");
//        Knjiga knjiga5 = new Knjiga(5,"IST", "branko USer","bajke5");
//        Knjiga knjiga6 = new Knjiga(6,"Programiranje26", "SeL cpic","bajke6");
//        Knjiga knjiga7 = new Knjiga(7,"Programir", "Supic cpic","bajke7");
//        Knjiga knjiga8 = new Knjiga(8,"Programir123", "Supic cpic","bajke8");
//        Knjiga knjiga9 = new Knjiga(9,"Programir1234", "branko Supic","bajke9");
//        Knjiga knjiga10 = new Knjiga(10,"uvod u programiranje 333", "branko Supic","bajke10");
//
//
//        knjigaListe.add(knjiga1);
//        knjigaListe.add(knjiga2);
//        knjigaListe.add(knjiga3);
//        knjigaListe.add(knjiga4);
//        knjigaListe.add(knjiga5);
//        knjigaListe.add(knjiga6);
//        knjigaListe.add(knjiga7);
//        knjigaListe.add(knjiga8);
//        knjigaListe.add(knjiga9);
//        knjigaListe.add(knjiga10);


    }

    private class onListItemLongClickListener implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

//treba pozvati dialog sa pitanjem da se obrise knjiga ili da se modifikuje;

            item = new Knjiga();
            item = knjigaListe.get(position);

            showDIalogBox();


//            Knjiga item = new Knjiga();
//            item = knjigaListe.get(position);
//
//
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("knjiga", item);
//
//            KnjigaPregled knjigaPregled = new KnjigaPregled();
//            knjigaPregled.setArguments(bundle);
//            java.util.Date date = new java.util.Date();
//            String TAG = "noviFragment" + date.getTime();
//            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(rootView.getClass().getName()).
//                    add(R.id.content_view, knjigaPregled, TAG).commit();
            return true;
        }
    }

    private void showDIalogBox() {

        Log.d(TAG, "showDIalogBox: " + new Gson().toJson(item));

        Log.d("ShowDialog", "showDIalogBox:  pokazan");
        LayoutInflater factory = LayoutInflater.from(getActivity());
        //text_entry is an Layout XML file containing two text field to display in alert dialog

        final View dialogBox = factory.inflate(R.layout.dialog_long_klik_listener, null);
        final TextView text = (TextView) dialogBox.findViewById(R.id.text_na_dialogu);
        final Button da = (Button) dialogBox.findViewById(R.id.uredi);
        final Button ne = (Button) dialogBox.findViewById(R.id.obrisi);

        asd.setView(dialogBox);
        final AlertDialog alertDialog = asd.create();
        Log.d("ShowDialog", "showDIalogBox:  Kreiran");

        alertDialog.show();

        ne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Knjiga> knjigeList = new ArrayList<>();
                KnjigeStorage knjigeStorage = new KnjigeStorage();

                if (AppHelper.getInstance().getKnjigeStorage() != null) {
                    if (AppHelper.getInstance().getKnjigeStorage().getListaKnjiga() != null) {
                        if (AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().size() > 0) {

                            knjigeList = AppHelper.getInstance().getKnjigeStorage().getListaKnjiga();
                            Log.d(TAG, "onCLick" + new Gson().toJson(knjigeList) );

                            for (int i = 0; i < AppHelper.getInstance().getKnjigeStorage().getListaKnjiga().size(); i++) {
                                if (knjigeList.get(i).getID() == item.getID()){
                                    Log.d(TAG, "onClick: IMA KNJIGEEEE");
                                    Log.d(TAG, "onClick: " + new Gson().toJson(knjigeList.get(i)));

                                    knjigeList.remove(i);

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
        da.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
                UrediKnjiguFragment urediKnjiguFragment = new UrediKnjiguFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("knjiga", item);
                urediKnjiguFragment.setArguments(bundle1);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_view, urediKnjiguFragment).commit();
            }
        });


    }

}
