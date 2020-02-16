package com.example.muslimhotel.model;

public class EditorsPicks {
    private String iidHotel;
    private String tempat;
    private String review;
    private String gambarHotel;
    private String scorelHotel;
    private String nmHotel;
    private String almatHotel;

    public EditorsPicks(String iidHotel, String tempat, String review, String gambarHotel, String scorelHotel, String nmHotel, String almatHotel) {
        this.iidHotel = iidHotel;
        this.tempat = tempat;
        this.review = review;
        this.gambarHotel = gambarHotel;
        this.scorelHotel = scorelHotel;
        this.nmHotel = nmHotel;
        this.almatHotel = almatHotel;
    }

    public EditorsPicks() {

    }

    public String getIidHotel() {
        return iidHotel;
    }

    public void setIidHotel(String iidHotel) {
        this.iidHotel = iidHotel;
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

    public String getScorelHotel() {
        return scorelHotel;
    }

    public void setScorelHotel(String scorelHotel) {
        this.scorelHotel = scorelHotel;
    }

    public String getNmHotel() {
        return nmHotel;
    }

    public void setNmHotel(String nmHotel) {
        this.nmHotel = nmHotel;
    }

    public String getAlmatHotel() {
        return almatHotel;
    }

    public void setAlmatHotel(String almatHotel) {
        this.almatHotel = almatHotel;
    }
}
