package com.mertcanduldul.app;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Kullanici {
    private String kullanici_id;
    private String kullanici_fullname;
    private String kullanici_adi;
    private String kullanici_sifre;


    public Kullanici() {

    }

    public Kullanici(String kullanici_id, String kullanici_sifre, String kullanici_adi) {
        this.kullanici_id = kullanici_id;
        this.kullanici_sifre = kullanici_sifre;
        this.kullanici_adi = kullanici_adi;
    }

    public String getKullanici_id() {
        return kullanici_id;
    }

    public void setKullanici_id(String kullanici_id) {
        this.kullanici_id = kullanici_id;
    }

    public String getKullanici_sifre() {
        return kullanici_sifre;
    }

    public void setKullanici_sifre(String kullanici_sifre) {
        this.kullanici_sifre = kullanici_sifre;
    }

    public String getKullanici_adi() {
        return kullanici_adi;
    }

    public void setKullanici_adi(String kullanici_adi) {
        this.kullanici_adi = kullanici_adi;
    }


    public String getKullanici_fullname() {
        return kullanici_fullname;
    }

    public void setKullanici_fullname(String kullanici_fullname) {
        this.kullanici_fullname = kullanici_fullname;
    }
}