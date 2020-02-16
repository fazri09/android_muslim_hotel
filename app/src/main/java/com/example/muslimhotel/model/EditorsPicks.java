package com.example.muslimhotel.model;

public class EditorsPicks {
    private String tempat;
    private int review;
    private int gambarHotel;

    public EditorsPicks(String tempat, int review, int gambarHotel) {
        this.tempat = tempat;
        this.review = review;
        this.gambarHotel = gambarHotel;
    }

    public String getTempat() {
        return tempat;
    }

    public int getReview() {
        return review;
    }

    public int getGambarHotel() {
        return gambarHotel;
    }
}
