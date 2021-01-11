package com.mertcanduldul.app;

public class Mesaj {
    private String id;
    private String fromKisi;
    private String toKisi;
    private String zaman;
    private String mesajicerik;

    public Mesaj() {
    }

    public Mesaj(String id, String fromKisi, String toKisi, String zaman, String mesajicerik) {
        this.id = id;
        this.fromKisi = fromKisi;
        this.toKisi = toKisi;
        this.zaman = zaman;
        this.mesajicerik = mesajicerik;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromKisi() {
        return fromKisi;
    }

    public void setFromKisi(String fromKisi) {
        this.fromKisi = fromKisi;
    }

    public String getToKisi() {
        return toKisi;
    }

    public void setToKisi(String toKisi) {
        this.toKisi = toKisi;
    }

    public String getZaman() {
        return zaman;
    }

    public void setZaman(String zaman) {
        this.zaman = zaman;
    }

    public String getMesajicerik() {
        return mesajicerik;
    }

    public void setMesajicerik(String mesajicerik) {
        this.mesajicerik = mesajicerik;
    }
}
