package com.example.android.sparklibrary.Fragmenti;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.android.sparklibrary.MainActivity;
import com.example.android.sparklibrary.R;
import com.example.android.sparklibrary.Storage.AppHelper;
import com.example.android.sparklibrary.Storage.PostavkeStorage;


public class PostavkeFragment extends Fragment {


    View rootView;

    CheckBox holo_dark_cb, holo_white_cb;

    PostavkeStorage postavkeStorage = new PostavkeStorage();
    Button spremi_postavke;
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        rootView = inflater.inflate(R.layout.postavke_fragment, container, false);


        spremi_postavke = (Button)rootView.findViewById(R.id.spremi_postavke);
        holo_dark_cb = (CheckBox) rootView.findViewById(R.id.cb_holo_dark);
        holo_white_cb = (CheckBox) rootView.findViewById(R.id.cb_holo_white);

        if(AppHelper.getInstance().getPostavkeStorage()!= null){
            if(AppHelper.getInstance().getPostavkeStorage().getTema_broj()){
                holo_dark_cb.setChecked(AppHelper.getInstance().getPostavkeStorage().getTema_broj());
                holo_white_cb.setChecked(!AppHelper.getInstance().getPostavkeStorage().getTema_broj());
            }
        }




        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.GONE);



        holo_white_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    holo_dark_cb.setChecked(!isChecked);
                }
            }
        });

        holo_dark_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    holo_white_cb.setChecked(!isChecked);
                }
            }
        });


        spremi_postavke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postavkeStorage.setTema_broj(holo_dark_cb.isChecked());
                AppHelper.getInstance().setPostavkeStorage(postavkeStorage);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();

                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_view, new KnjigeFragment()).commit();
            }
        });
        return rootView;
    }
}
