package com.mertcanduldul.app;

public class ModalClass {
    private int urun_id;
    private String urun_adi;
    private String urun_fotograf;
    private String urun_aciklama;
    private int urun_fiyat;

    public ModalClass(int urun_id, String urun_adi, String urun_fotograf, String urun_aciklama, int urun_fiyat) {
        this.urun_id = urun_id;
        this.urun_adi = urun_adi;
        this.urun_fotograf = urun_fotograf;
        this.urun_aciklama = urun_aciklama;
        this.urun_fiyat = urun_fiyat;
    }

    public int getUrun_id() {
        return urun_id;
    }

    public void setUrun_id(int urun_id) {
        this.urun_id = urun_id;
    }

    public String getUrun_adi() {
        return urun_adi;
    }

    public void setUrun_adi(String urun_adi) {
        this.urun_adi = urun_adi;
    }

    public String getUrun_fotograf() {
        return urun_fotograf;
    }

    public void setUrun_fotograf(String urun_fotograf) {
        this.urun_fotograf = urun_fotograf;
    }

    public String getUrun_aciklama() {
        return urun_aciklama;
    }

    public void setUrun_aciklama(String urun_aciklama) {
        this.urun_aciklama = urun_aciklama;
    }

    public int getUrun_fiyat() {
        return urun_fiyat;
    }

    public void setUrun_fiyat(int urun_fiyat) {
        this.urun_fiyat = urun_fiyat;
    }
}

