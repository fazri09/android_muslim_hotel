package com.example.muslimhotel.model;

public class AuthorsHotel {
    private String tempat;
    private String review;
    private String gambarHotel;
    private String nmHotel;
    private String scoreHotel;

    public AuthorsHotel(String tempat, String review, String gambarHotel, String nmHotel, String scoreHotel) {
        this.tempat = tempat;
        this.review = review;
        this.gambarHotel = gambarHotel;
        this.nmHotel = nmHotel;
        this.scoreHotel = scoreHotel;
    }

    public AuthorsHotel() {

    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
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

    public String getScoreHotel() {
        return scoreHotel;
    }

    public void setScoreHotel(String scoreHotel) {
        this.scoreHotel = scoreHotel;
    }
}
