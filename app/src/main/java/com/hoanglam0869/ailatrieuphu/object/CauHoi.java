package com.hoanglam0869.ailatrieuphu.object;

import java.util.ArrayList;

public class CauHoi {
    private String noiDung, dapAnDung;
    private ArrayList<String> arrDapAnSai;

    public CauHoi() {
    }

    public CauHoi(String noiDung, String dapAnDung, ArrayList<String> arrDapAnSai) {
        this.noiDung = noiDung;
        this.dapAnDung = dapAnDung;
        this.arrDapAnSai = arrDapAnSai;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getDapAnDung() {
        return dapAnDung;
    }

    public void setDapAnDung(String dapAnDung) {
        this.dapAnDung = dapAnDung;
    }

    public ArrayList<String> getArrDapAnSai() {
        return arrDapAnSai;
    }

    public void setArrDapAnSai(ArrayList<String> arrDapAnSai) {
        this.arrDapAnSai = arrDapAnSai;
    }
}
