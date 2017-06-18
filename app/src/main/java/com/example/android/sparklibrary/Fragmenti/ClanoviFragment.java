package com.example.android.sparklibrary.Fragmenti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.sparklibrary.Adapteri.ClanoviAdapter;
import com.example.android.sparklibrary.Klase.Clanovi;
import com.example.android.sparklibrary.R;
import com.example.android.sparklibrary.Storage.AppHelper;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;


public class ClanoviFragment extends Fragment {

    private static final String TAG = "ClanoviFragment";

    View rootView;
    ListView clanoviListView;
    List<Clanovi> clanoviList;
    ClanoviAdapter clanoviAdapter;
    //TextView no_members;

    FloatingActionButton fab;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.clanovi_fragment_layout, container, false);

        clanoviListView = (ListView) rootView.findViewById(R.id.clanoviList);
        //no_members = (TextView) rootView.findViewById(R.id.no_members);
        //no_members.setVisibility(GONE);


        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();


                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_view, new ClanoviUnos()).addToBackStack(new ClanoviFragment().getClass().getName()).commit();

            }
        });
clanoviListView.setOnItemClickListener(new onListItemClicked());

        SetSetClanovi();
        return rootView;
    }


    private class onListItemClicked implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Log.d(TAG, "onItemClick: " + "kliknuto na clana liste");
            Clanovi item = new Clanovi();
            item = clanoviList.get(i);
            Bundle bundle = new Bundle();
            bundle.putSerializable("clan", item);

            PregledClana pregledClana = new PregledClana();
            pregledClana.setArguments(bundle);
            java.util.Date date = new java.util.Date();
            String TAG = "noviFragment" + date.getTime();
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(rootView.getClass().getName()).
                    add(R.id.content_view, pregledClana, TAG).commit();

        }
    }

    public void SetSetClanovi() {
        clanoviList = new ArrayList<>();

        if (AppHelper.getInstance().getClanoviStorage() != null) {
            if (AppHelper.getInstance().getClanoviStorage().getListaCLanova() != null) {
                if (AppHelper.getInstance().getClanoviStorage().getListaCLanova().size() > 0) {
                    clanoviList = AppHelper.getInstance().getClanoviStorage().getListaCLanova();
                    clanoviAdapter = new ClanoviAdapter(getActivity(), clanoviList);
                    clanoviListView.setAdapter(clanoviAdapter);
                } else {
                    Log.d(TAG, "SetSetClanovi: " + "getListaCLanova je 0");
                    clanoviListView.setVisibility(GONE);
                    //no_members.setVisibility(View.VISIBLE);

                }
            } else {
                Log.d(TAG, "SetSetClanovi: " + "getListaCLanova je prazan");
                clanoviListView.setVisibility(GONE);
                //no_members.setVisibility(View.VISIBLE);
            }
        } else {
            clanoviListView.setVisibility(GONE);
            //no_members.setVisibility(View.VISIBLE);
            Log.d(TAG, "SetSetClanovi: " + "GetClanoviStorage je prazan");
        }
    }

}
