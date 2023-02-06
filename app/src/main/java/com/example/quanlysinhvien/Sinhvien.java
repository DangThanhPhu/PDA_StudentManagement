package com.example.quanlysinhvien;

public class Sinhvien {
    public int masv;
    public  String tensv, sdtsv;

    public Sinhvien(int masv, String tensv, String sdtsv) {
        this.masv = masv;
        this.tensv = tensv;
        this.sdtsv = sdtsv;
    }

    public int getMasv() {
        return masv;
    }

    public void setMasv(int masv) {
        this.masv = masv;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getSdtsv() {
        return sdtsv;
    }

    public void setSdtsv(String sdtsv) {
        this.sdtsv = sdtsv;
    }
}
