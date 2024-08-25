/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class HDCT_MD {
    private int idSPCT;
    private String maSPCT;
    private String tenSP;
    private int soLuong;
    private float donGia;
    private float tongTien;
    private float giaTriVoucher;
    private float giaTriThanhToan;
    public HDCT_MD() {
    }

    public HDCT_MD(int idSPCT, String maSPCT, String tenSP, int soLuong, float donGia, float tongTien) {
        this.idSPCT = idSPCT;
        this.maSPCT = maSPCT;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tongTien = tongTien;
    }

    public HDCT_MD(String maSPCT, String tenSP, int soLuong, float donGia, float tongTien) {
        this.maSPCT = maSPCT;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tongTien = tongTien;
    }

    public HDCT_MD(String maSPCT, String tenSP, int soLuong, float donGia, float tongTien, float giaTriVoucher, float giaTriThanhToan) {
        this.maSPCT = maSPCT;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tongTien = tongTien;
        this.giaTriVoucher = giaTriVoucher;
        this.giaTriThanhToan = giaTriThanhToan;
    }
    
    public int getIdSPCT() {
        return idSPCT;
    }

    public void setIdSPCT(int idSPCT) {
        this.idSPCT = idSPCT;
    }

    public String getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(String maSPCT) {
        this.maSPCT = maSPCT;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }
    
         public Object[]toDataRow(){
        return new Object[]{
            this.maSPCT,this.tenSP,this.soLuong,this.donGia,this.tongTien,this.giaTriVoucher, this.giaTriThanhToan
        };
    }
}
