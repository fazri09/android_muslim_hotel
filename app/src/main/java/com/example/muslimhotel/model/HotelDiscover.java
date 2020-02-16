package com.example.muslimhotel.model;

public class HotelDiscover {
    private String nmHotel;
    private String gambarHotel;
    private String deskripsiHotel;
    private String tempatHotel;

    public HotelDiscover(String nmHotel, String gambarHotel, String deskripsiHotel, String tempatHotel) {
        this.nmHotel = nmHotel;
        this.gambarHotel = gambarHotel;
        this.deskripsiHotel = deskripsiHotel;
        this.tempatHotel = tempatHotel;
    }

    public HotelDiscover() {

    }

    public String getNmHotel() {
        return nmHotel;
    }

    public void setNmHotel(String nmHotel) {
        this.nmHotel = nmHotel;
    }

    public String getGambarHotel() {
        return gambarHotel;
    }

    public void setGambarHotel(String gambarHotel) {
        this.gambarHotel = gambarHotel;
    }

    public String getDeskripsiHotel() {
        return deskripsiHotel;
    }

    public void setDeskripsiHotel(String deskripsiHotel) {
        this.deskripsiHotel = deskripsiHotel;
    }

    public String getTempatHotel() {
        return tempatHotel;
    }

    public void setTempatHotel(String tempatHotel) {
        this.tempatHotel = tempatHotel;
    }
}
