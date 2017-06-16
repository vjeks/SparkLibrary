package com.example.android.sparklibrary;

import com.example.android.sparklibrary.Klase.Autor;
import com.example.android.sparklibrary.Klase.Clanovi;
import com.example.android.sparklibrary.Klase.Knjiga;
import com.example.android.sparklibrary.Klase.TipKnjige;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adissertovic on 15/06/17.
 */

public class Storage {

    public static List<Knjiga> listaKnjiga;
    public static List<Clanovi> listaClanova;
    public static List<TipKnjige> KlasifikacijaList;
    public static List<Autor> ListaAutora;





    public static List<Clanovi> ListCLanova(){

        listaClanova = new ArrayList<>();
        Clanovi clan1 = new Clanovi(1,"Vjekoslav","Buric","Mostar 88000","123456789","1");
        Clanovi clan2 = new Clanovi(2,"Vjekoslav","Buric","Mostar 88000","123456789","2");
        Clanovi clan3 = new Clanovi(3,"Vjekoslav","Buric","Mostar 88000","123456789","3");
        Clanovi clan4 = new Clanovi(4,"Vjekoslav","Buric","Mostar 88000","123456789","4");
        Clanovi clan5 = new Clanovi(5,"Vjekoslav","Buric","Mostar 88000","123456789","5");
        Clanovi clan6 = new Clanovi(6,"Vjekoslav","Buric","Mostar 88000","123456789","6");
        Clanovi clan7 = new Clanovi(7,"Vjekoslav","Buric","Mostar 88000","123456789","7");
        Clanovi clan8 = new Clanovi(8,"Vjekoslav","Buric","Mostar 88000","123456789","8");
        Clanovi clan9 = new Clanovi(9,"Vjekoslav","Buric","Mostar 88000","123456789","9");
        Clanovi clan19 = new Clanovi(10,"Vjekoslav","Buric","Mostar 88000","123456789","10");
        listaClanova.add(clan1);
        listaClanova.add(clan2);
        listaClanova.add(clan3);
        listaClanova.add(clan4);
        listaClanova.add(clan5);
        listaClanova.add(clan6);
        listaClanova.add(clan7);
        listaClanova.add(clan8);
        listaClanova.add(clan9);


        return listaClanova;

    }


    public static List<Knjiga> GetAllKnjige(){

        List<Knjiga> knjige = new ArrayList<>();
        listaKnjiga = new ArrayList<>();

        Knjiga knjiga1 = new Knjiga(1,"Uvod u programiranje",
                ListaAutora.get(0),"Svjetlost",
                1905,123,KlasifikacijaList.get(0).getTip_naziv(),true,KlasifikacijaList.get(0).getTip_id());
        Knjiga knjiga2 = new Knjiga(2,"Uvod u programiranje",
                ListaAutora.get(0),"Svjetlost1",
                1905,123,KlasifikacijaList.get(0).getTip_naziv(),false,KlasifikacijaList.get(0).getTip_id());
        Knjiga knjiga3 = new Knjiga(3,"Uvod u ",
                ListaAutora.get(0),"Svjetlost2",
                1905,123,KlasifikacijaList.get(0).getTip_naziv(),true,KlasifikacijaList.get(1).getTip_id());
        Knjiga knjiga4 = new Knjiga(4,"Uvod u ",
                ListaAutora.get(0),"Svjetlost3",
                1905,123,KlasifikacijaList.get(0).getTip_naziv(),false,KlasifikacijaList.get(1).getTip_id());
        Knjiga knjiga5 = new Knjiga(5,"Uvod u ",
                ListaAutora.get(0),"Svjetlost",
                1905,123,KlasifikacijaList.get(0).getTip_naziv(),true,KlasifikacijaList.get(2).getTip_id());
        Knjiga knjiga6 = new Knjiga(6,"Uvod u programiranje",
                ListaAutora.get(0),"Svjetlost",
                1905,123,KlasifikacijaList.get(0).getTip_naziv(),false,KlasifikacijaList.get(2).getTip_id());
        Knjiga knjiga7 = new Knjiga(7," u programiranje",
                ListaAutora.get(0),"Svjetlost",
                1905,123,KlasifikacijaList.get(0).getTip_naziv(),true,KlasifikacijaList.get(1).getTip_id());
        Knjiga knjiga8 = new Knjiga(8,"Uvod  programiranje",
                ListaAutora.get(0),"Svjetlost",
                1905,123,KlasifikacijaList.get(0).getTip_naziv(),false,KlasifikacijaList.get(2).getTip_id());
        Knjiga knjiga9 = new Knjiga(9,"Uvod  programiranje",
                ListaAutora.get(0),"Svjetlost",
                1905,123,KlasifikacijaList.get(0).getTip_naziv(),true,KlasifikacijaList.get(2).getTip_id());
        Knjiga knjiga10 = new Knjiga(10,"Uvod 123 programiranje",
                ListaAutora.get(0),"Svjetlost",
                1905,123,KlasifikacijaList.get(0).getTip_naziv(),false,KlasifikacijaList.get(1).getTip_id());


        knjige.add(knjiga1);
        knjige.add(knjiga2);
        knjige.add(knjiga3);
        knjige.add(knjiga4);
        knjige.add(knjiga5);
        knjige.add(knjiga6);
        knjige.add(knjiga7);
        knjige.add(knjiga8);
        knjige.add(knjiga9);
        knjige.add(knjiga10);

        listaKnjiga = knjige;
        return listaKnjiga;
    }

    public static List<TipKnjige> listTipovi(){

        TipKnjige tip1 = new TipKnjige(1,"Tehnologija");
        TipKnjige tip2 = new TipKnjige(1,"Književnost");
        TipKnjige tip3 = new TipKnjige(1,"Književnost 23");
        TipKnjige tip4 = new TipKnjige(1,"Književnost 3");

        KlasifikacijaList = new ArrayList<>();
        KlasifikacijaList.add(tip1);
        KlasifikacijaList.add(tip2);
        KlasifikacijaList.add(tip3);
        KlasifikacijaList.add(tip4);


        return KlasifikacijaList;
    }


    public static List<String> listTipoviStringovi(){


        List<String> listStringova = new ArrayList<>();


        for (  int i  =0; i <listTipovi().size(); i++){
            listStringova.add(i,listTipovi().get(i).getTip_naziv());
        }
        return listStringova;
    }


    public static List<Autor> listAutora(){



        Autor tip1 = new Autor(1,"Autor 1 ","Prezime");
        Autor tip2 = new Autor(1,"Književnost","Prezime");
        Autor tip3 = new Autor(1,"Književnost 23","Prezime");
        Autor tip4 = new Autor(1,"Književnost 3","Prezime");

        ListaAutora = new ArrayList<>();
        ListaAutora.add(tip1);
        ListaAutora.add(tip2);
        ListaAutora.add(tip3);
        ListaAutora.add(tip4);


        return ListaAutora;
    }















}
