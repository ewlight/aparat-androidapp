package com.taracorpora.aparatapp;

public class ListpengumumanModel {
    private String nama;
    private String judul;
    private String tanggal;

    public ListpengumumanModel(String nama, String judul, String tanggal) {
        this.nama = nama;
        this.judul = judul;
        this.tanggal = tanggal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
