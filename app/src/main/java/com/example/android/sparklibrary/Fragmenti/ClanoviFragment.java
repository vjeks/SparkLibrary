package com.example.android.sparklibrary.Fragmenti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.sparklibrary.Adapteri.ClanoviAdapter;
import com.example.android.sparklibrary.Klase.Clanovi;
import com.example.android.sparklibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 14.6.2017..
 */

public class ClanoviFragment extends Fragment {

    View rootView;
    ListView clanoviListView;
    List<Clanovi> clanoviList;
    ClanoviAdapter clanoviAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.clanovi_fragment_layout, container,false);
        SetSetClanovi();

        clanoviListView =(ListView) rootView.findViewById(R.id.clanoviList);
        clanoviAdapter = new ClanoviAdapter(getActivity(),clanoviList);
        clanoviListView.setAdapter(clanoviAdapter);

        return  rootView;
    }

    public void SetSetClanovi(){
        clanoviList = new ArrayList<>();

//        Clanovi knjiga1 = new Clanovi(1,"Vjekoslav", "Buric");
//        Clanovi knjiga2 = new Clanovi(2,"Vjekoslav", "BuricBuric");
//        Clanovi knjiga3 = new Clanovi(3,"Vjekoslav", "Buric");
//        Clanovi knjiga4 = new Clanovi(4,"Vjekoslav", "Buric");
//        Clanovi knjiga5 = new Clanovi(5,"Vjekoslav", "Buric");
//        Clanovi knjiga6 = new Clanovi(6,"VjekoslavVjekoslav", "BuricBuricBuric");
//        Clanovi knjiga7 = new Clanovi(7,"Vjekoslav", "Buric");
//        Clanovi knjiga8 = new Clanovi(8,"Vjekoslav", "Buric");
//
//        clanoviList.add(knjiga1);
//        clanoviList.add(knjiga2);
//        clanoviList.add(knjiga3);
//        clanoviList.add(knjiga4);
//        clanoviList.add(knjiga5);
//        clanoviList.add(knjiga6);
//        clanoviList.add(knjiga7);
//        clanoviList.add(knjiga8);
    }

}
