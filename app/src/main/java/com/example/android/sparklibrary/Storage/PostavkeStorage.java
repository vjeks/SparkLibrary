package com.example.android.sparklibrary.Storage;

public class PostavkeStorage {

    boolean tema_broj;

    public PostavkeStorage(boolean tema_broj) {
        this.tema_broj = tema_broj;
    }

    public PostavkeStorage() {
    }

    public boolean getTema_broj() {
        return tema_broj;
    }

    public void setTema_broj(boolean tema_broj) {
        this.tema_broj = tema_broj;
    }
}
