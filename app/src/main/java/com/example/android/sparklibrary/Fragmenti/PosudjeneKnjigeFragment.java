package com.example.android.sparklibrary.Fragmenti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.sparklibrary.Adapteri.PosudjeneKnjigeAdapterList;
import com.example.android.sparklibrary.Klase.PosudjeneKnjige;
import com.example.android.sparklibrary.R;
import com.example.android.sparklibrary.Storage.AppHelper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adissertovic on 17/06/17.
 */

public class PosudjeneKnjigeFragment extends Fragment {

    View rootView;
    ListView posudjene_knjige_lv;
    PosudjeneKnjigeAdapterList posudjeneKnjigeAdapterList;

    List<PosudjeneKnjige> posudjeneKnjigeLista;

    private static final String TAG = "PosudjeneKnjige";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        rootView = inflater.inflate(R.layout.posudjene_knjige_fragment, container, false);

        posudjene_knjige_lv = (ListView) rootView.findViewById(R.id.posudjene_knjige_lv);


        posudjeneKnjigeLista = new ArrayList<>();


        SetValuesOnForm();




        return rootView;
    }

    private void SetValuesOnForm() {


        Log.d(TAG, "SetValuesOnForm: " + new Gson().toJson(AppHelper.getInstance().getPosudjeneKnjigeStorage().getPosudjeneKnjigeStorageList()));

        if(AppHelper.getInstance().getPosudjeneKnjigeStorage()!= null){
            if(AppHelper.getInstance().getPosudjeneKnjigeStorage().getPosudjeneKnjigeStorageList()!= null){
                if (AppHelper.getInstance().getPosudjeneKnjigeStorage().getPosudjeneKnjigeStorageList().size()>0) {

                    posudjeneKnjigeLista = AppHelper.getInstance().getPosudjeneKnjigeStorage().getPosudjeneKnjigeStorageList();
//                    Log.d(TAG, "SetValuesOnForm: " + new Gson().toJson(AppHelper.getInstance().getPosudjeneKnjigeStorage().getPosudjeneKnjigeStorageList()));

                    posudjeneKnjigeAdapterList = new PosudjeneKnjigeAdapterList(getActivity(),posudjeneKnjigeLista);
                    posudjene_knjige_lv.setAdapter(posudjeneKnjigeAdapterList);
                }
            }
        }



        posudjene_knjige_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                PosudjeneKnjige knjiga = new PosudjeneKnjige();
                knjiga = (PosudjeneKnjige) posudjeneKnjigeLista.get(position);

                Bundle bundle = new Bundle();
                bundle.putSerializable("posudjenaKnjiga", knjiga);


                PosudjenaKnjigaFragment knjigaFragment = new PosudjenaKnjigaFragment();
                knjigaFragment.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(rootView.getClass().getName()).add(R.id.content_view,knjigaFragment,TAG).commit();



            }
        });


    }
}
