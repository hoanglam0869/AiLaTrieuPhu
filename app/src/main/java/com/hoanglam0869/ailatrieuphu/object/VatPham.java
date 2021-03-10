package com.hoanglam0869.ailatrieuphu.object;

public class VatPham {
    private int id;
    private String tenVatPham;
    private byte[] hinhVatPham;
    private int giaVatPham;
    private int slVatPham;
    private String loaiThe;
    private int capThe;
    private String moTaVatPham;

    public VatPham(int id, String tenVatPham, byte[] hinhVatPham, int giaVatPham, int slVatPham, String loaiThe, int capThe, String moTaVatPham) {
        this.id = id;
        this.tenVatPham = tenVatPham;
        this.hinhVatPham = hinhVatPham;
        this.giaVatPham = giaVatPham;
        this.slVatPham = slVatPham;
        this.loaiThe = loaiThe;
        this.capThe = capThe;
        this.moTaVatPham = moTaVatPham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenVatPham() {
        return tenVatPham;
    }

    public void setTenVatPham(String tenVatPham) {
        this.tenVatPham = tenVatPham;
    }

    public byte[] getHinhVatPham() {
        return hinhVatPham;
    }

    public void setHinhVatPham(byte[] hinhVatPham) {
        this.hinhVatPham = hinhVatPham;
    }

    public int getGiaVatPham() {
        return giaVatPham;
    }

    public void setGiaVatPham(int giaVatPham) {
        this.giaVatPham = giaVatPham;
    }

    public int getSlVatPham() {
        return slVatPham;
    }

    public void setSlVatPham(int slVatPham) {
        this.slVatPham = slVatPham;
    }

    public String getLoaiThe() {
        return loaiThe;
    }

    public void setLoaiThe(String loaiThe) {
        this.loaiThe = loaiThe;
    }

    public int getCapThe() {
        return capThe;
    }

    public void setCapThe(int capThe) {
        this.capThe = capThe;
    }

    public String getMoTaVatPham() {
        return moTaVatPham;
    }

    public void setMoTaVatPham(String moTaVatPham) {
        this.moTaVatPham = moTaVatPham;
    }
}
