package com.example.muslimhotel.model;

public class HotelDiscover {
    private String nmHotel;
    private int gambarHotel;
    private String deskripsiHotel;
    private String tempatHotel;

    public HotelDiscover(String nmHotel, int gambarHotel, String deskripsiHotel, String tempatHotel) {
        this.nmHotel = nmHotel;
        this.gambarHotel = gambarHotel;
        this.deskripsiHotel = deskripsiHotel;
        this.tempatHotel = tempatHotel;
    }

    public String getNmHotel() {
        return nmHotel;
    }

    public void setNmHotel(String nmHotel) {
        this.nmHotel = nmHotel;
    }

    public int getGambarHotel() {
        return gambarHotel;
    }

    public void setGambarHotel(int gambarHotel) {
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
