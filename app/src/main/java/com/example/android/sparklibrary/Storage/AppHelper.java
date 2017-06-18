package com.example.android.sparklibrary.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.android.sparklibrary.Responsovi.LoginResponse;
import com.google.gson.Gson;


public class AppHelper {


    private static final AppHelper APP_HELPER = new AppHelper();


    private static final String SHARED_PREFERENCES = "shared_preferences";
    private static final String SOCIAL_LOGIN_TAG = "social_login";
    private static final String LOGIN_TAG = "login_data";
    private static final String DEFAULT_IMAGE = "http://www.freetouring.eu/img/no-avatar.jpg";



    private static final String KNJIGE_STORAGE = "knjige_storage";
    private static final String CLANOVI_STORAGE = "clanovi_storage";
    private static final String POSUDJENE_KNJIGE_STORAGE = "posudjene_knjige_storage";
    private static final String POSTAVKE_STORAGE= "postavke_storage";
    private static final String TIPOVI_STORAGE= "tipovi_storage";


    public static String getDefaultImage() {
        return DEFAULT_IMAGE;
    }

    private Context context;
    private Gson gson = new Gson();
    private SharedPreferences sharedPreferences;

    public static AppHelper getInstance() {
        return APP_HELPER;
    }
    public void initAppHelperWithContext(Context context) {
        this.context = context;
    }


    public void setLoginData(LoginResponse response) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
                .edit();
        editor.putString(LOGIN_TAG, gson.toJson(response));
        editor.commit();
    }

    public LoginResponse getLoginData() {
        SharedPreferences prefs = this.context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return gson.fromJson(prefs.getString(LOGIN_TAG, null), LoginResponse.class);
    }

    public void setKnjigeStorage(KnjigeStorage response) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
                .edit();
        editor.putString(KNJIGE_STORAGE, gson.toJson(response));
        editor.commit();
    }

    public KnjigeStorage getKnjigeStorage() {
        SharedPreferences prefs = this.context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return gson.fromJson(prefs.getString(KNJIGE_STORAGE, null), KnjigeStorage.class);
    }

    public void setClanoviStorage(ClanoviStorage response) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
                .edit();
        editor.putString(CLANOVI_STORAGE, gson.toJson(response));
        editor.commit();
    }

    public ClanoviStorage getClanoviStorage() {
        SharedPreferences prefs = this.context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return gson.fromJson(prefs.getString(CLANOVI_STORAGE, null), ClanoviStorage.class);
    }

    public void setPosudjeneKnjigeStorage(PosudjeneKnjigeStorage response) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
                .edit();
        editor.putString(POSUDJENE_KNJIGE_STORAGE, gson.toJson(response));
        editor.commit();
    }

    public PosudjeneKnjigeStorage getPosudjeneKnjigeStorage() {
        SharedPreferences prefs = this.context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return gson.fromJson(prefs.getString(POSUDJENE_KNJIGE_STORAGE, null), PosudjeneKnjigeStorage.class);
    }


    public void setPostavkeStorage(PostavkeStorage response) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
                .edit();
        editor.putString(POSTAVKE_STORAGE, gson.toJson(response));
        editor.commit();
    }

    public PostavkeStorage getPostavkeStorage() {
        SharedPreferences prefs = this.context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return gson.fromJson(prefs.getString(POSTAVKE_STORAGE, null), PostavkeStorage.class);
    }


    public void setTipoviStorage(TipoviStorage response) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
                .edit();
        editor.putString(TIPOVI_STORAGE, gson.toJson(response));
        editor.commit();
    }

    public TipoviStorage getTipoviStorage() {
        SharedPreferences prefs = this.context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return gson.fromJson(prefs.getString(TIPOVI_STORAGE, null), TipoviStorage.class);
    }





}