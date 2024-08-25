/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author OSC
 */
public class HoaDonChiTiet {
    private String maSanPham;
    private int soLuong;

    public HoaDonChiTiet(String maSanPham, int soLuong) {
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }
}
