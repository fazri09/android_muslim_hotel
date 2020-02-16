package com.example.muslimhotel.model;

public class AuthorsHotel {
    private String tempat;
    private int review;
    private String gambarHotel;
    private String nmHotel;

    public AuthorsHotel(String tempat, int review, String gambarHotel, String nmHotel) {
        this.tempat = tempat;
        this.review = review;
        this.gambarHotel = gambarHotel;
        this.nmHotel = nmHotel;
    }

    public AuthorsHotel() {

    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public String getGambarHotel() {
        return gambarHotel;
    }

    public void setGambarHotel(String gambarHotel) {
        this.gambarHotel = gambarHotel;
    }

    public String getNmHotel() {
        return nmHotel;
    }

    public void setNmHotel(String nmHotel) {
        this.nmHotel = nmHotel;
    }
}
