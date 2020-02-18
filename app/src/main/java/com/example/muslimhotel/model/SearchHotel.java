package com.example.muslimhotel.model;

public class SearchHotel {
    private String idHotel;
    private String nmHotel;
    private String kotaHotel;
    private String scoreHotel;
    private String gambarHotel;
    private String tglAwalHotel;
    private String tglAkhirHotel;
    private String jPeopleTersedia;
    private String jKamarHotel;
    private String deskripsi;
    private String hargaHotel;
    public String reaview;

    public SearchHotel(String idHotel, String nmHotel, String kotaHotel, String scoreHotel, String gambarHotel, String tglAwalHotel, String tglAkhirHotel, String jPeopleTersedia, String jKamarHotel, String deskripsi, String hargaHotel, String reaview) {
        this.idHotel = idHotel;
        this.nmHotel = nmHotel;
        this.kotaHotel = kotaHotel;
        this.scoreHotel = scoreHotel;
        this.gambarHotel = gambarHotel;
        this.tglAwalHotel = tglAwalHotel;
        this.tglAkhirHotel = tglAkhirHotel;
        this.jPeopleTersedia = jPeopleTersedia;
        this.jKamarHotel = jKamarHotel;
        this.deskripsi = deskripsi;
        this.hargaHotel = hargaHotel;
        this.reaview = reaview;
    }

    public SearchHotel() {

    }

    public String getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(String idHotel) {
        this.idHotel = idHotel;
    }

    public String getNmHotel() {
        return nmHotel;
    }

    public void setNmHotel(String nmHotel) {
        this.nmHotel = nmHotel;
    }

    public String getKotaHotel() {
        return kotaHotel;
    }

    public void setKotaHotel(String kotaHotel) {
        this.kotaHotel = kotaHotel;
    }

    public String getScoreHotel() {
        return scoreHotel;
    }

    public void setScoreHotel(String scoreHotel) {
        this.scoreHotel = scoreHotel;
    }

    public String getGambarHotel() {
        return gambarHotel;
    }

    public void setGambarHotel(String gambarHotel) {
        this.gambarHotel = gambarHotel;
    }

    public String getTglAwalHotel() {
        return tglAwalHotel;
    }

    public void setTglAwalHotel(String tglAwalHotel) {
        this.tglAwalHotel = tglAwalHotel;
    }

    public String getTglAkhirHotel() {
        return tglAkhirHotel;
    }

    public void setTglAkhirHotel(String tglAkhirHotel) {
        this.tglAkhirHotel = tglAkhirHotel;
    }

    public String getjPeopleTersedia() {
        return jPeopleTersedia;
    }

    public void setjPeopleTersedia(String jPeopleTersedia) {
        this.jPeopleTersedia = jPeopleTersedia;
    }

    public String getjKamarHotel() {
        return jKamarHotel;
    }

    public void setjKamarHotel(String jKamarHotel) {
        this.jKamarHotel = jKamarHotel;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getHargaHotel() {
        return hargaHotel;
    }

    public void setHargaHotel(String hargaHotel) {
        this.hargaHotel = hargaHotel;
    }

    public String getReaview() {
        return reaview;
    }

    public void setReaview(String reaview) {
        this.reaview = reaview;
    }
}
