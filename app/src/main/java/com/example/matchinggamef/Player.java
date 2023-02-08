package com.example.matchinggamef;

public class Player {
    String tenNguoiChoi;
    int tongSoLanLat;

    public Player() {
    }

    public Player(String tenNguoiChoi, int tongSoLanLat) {
        this.tenNguoiChoi = tenNguoiChoi;
        this.tongSoLanLat = tongSoLanLat;
    }

    public String getTenNguoiChoi() {
        return tenNguoiChoi;
    }

    public void setTenNguoiChoi(String tenNguoiChoi) {
        this.tenNguoiChoi = tenNguoiChoi;
    }

    public int getTongSoLanLat() {
        return tongSoLanLat;
    }

    public void setTongSoLanLat(int tongSoLanLat) {
        this.tongSoLanLat = tongSoLanLat;
    }
}
