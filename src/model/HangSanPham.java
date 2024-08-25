/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Computer Bao
 */
public class HangSanPham {
    String maHangSanPham;
    String tenHangSanPham;

    public HangSanPham(String maHangSanPham, String tenHangSanPham) {
        this.maHangSanPham = maHangSanPham;
        this.tenHangSanPham = tenHangSanPham;
    }

    public HangSanPham() {
    }

    public String getMaHangSanPham() {
        return maHangSanPham;
    }

    public void setMaHangSanPham(String maHangSanPham) {
        this.maHangSanPham = maHangSanPham;
    }

    public String getTenHangSanPham() {
        return tenHangSanPham;
    }

    public void setTenHangSanPham(String tenHangSanPham) {
        this.tenHangSanPham = tenHangSanPham;
    }
 public Object[]toDataRowHSP(){
        return new Object[]{
            this.maHangSanPham,this.tenHangSanPham
        };
    }
   
    
}
