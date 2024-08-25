/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author OSC
 */
public class BanHang_SanPhamMD {
    private String maSP;
    private String tenSP;
    private String huongVi;
    private int soLuong;
    private float donGia;

    public BanHang_SanPhamMD() {
    }

    public BanHang_SanPhamMD(String maSP, String tenSP, String huongVi, int soLuong, float donGia) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.huongVi = huongVi;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getHuongVi() {
        return huongVi;
    }

    public void setHuongVi(String huongVi) {
        this.huongVi = huongVi;
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

    

    
}
