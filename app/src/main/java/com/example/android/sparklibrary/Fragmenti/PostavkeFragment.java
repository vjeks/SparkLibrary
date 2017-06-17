package com.example.android.sparklibrary.Fragmenti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.android.sparklibrary.R;
import com.example.android.sparklibrary.Storage.AppHelper;
import com.example.android.sparklibrary.Storage.PostavkeStorage;

/**
 * Created by adissertovic on 17/06/17.
 */

public class PostavkeFragment extends Fragment {


    View rootView;

    CheckBox holo_dark_cb, holo_white_cb;

    PostavkeStorage postavkeStorage = new PostavkeStorage();
    Button spremi_postavke;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        rootView = inflater.inflate(R.layout.postavke_fragment, container, false);


        spremi_postavke = (Button)rootView.findViewById(R.id.spremi_postavke);
        holo_dark_cb = (CheckBox) rootView.findViewById(R.id.cb_holo_dark);
        holo_white_cb = (CheckBox) rootView.findViewById(R.id.cb_holo_white);

        postavkeStorage.setTema_broj(holo_dark_cb.isChecked());



        spremi_postavke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppHelper.getInstance().setPostavkeStorage(postavkeStorage);


                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_view, new KnjigeFragment()).commit();
            }
        });
        return rootView;
    }
}
