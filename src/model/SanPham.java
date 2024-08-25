/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author Computer Bao
 */
public class SanPham {

    int id;
    String maSanPham;
    String tenSanPham;
    String tenhangSanPham;
    String tenLoaiSanPham;
    String tenDoiTuongSD;
    String ghiChu;
    String trangThai;

    public String getTenDoiTuongSD() {
        return tenDoiTuongSD;
    }

    public void setTenDoiTuongSD(String tenDoiTuongSD) {
        this.tenDoiTuongSD = tenDoiTuongSD;
    }

    
    public String getTenhangSanPham() {
        return tenhangSanPham;
    }

    public void setTenhangSanPham(String tenhangSanPham) {
        this.tenhangSanPham = tenhangSanPham;
    }

    public String getTenLoaiSanPham() {
        return tenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

    public SanPham() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

 

    public SanPham(String maSanPham, String tenSanPham, String tenhangSanPham, String tenLoaiSanPham, String tenDoiTuongSD, String ghiChu, String trangThai) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.tenhangSanPham = tenhangSanPham;
        this.tenLoaiSanPham = tenLoaiSanPham;
        this.tenDoiTuongSD = tenDoiTuongSD;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }
    
    

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    Integer stt = 1;

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public Object[] toData() {
        return new Object[]{
            this.maSanPham,
            this.tenSanPham,
            this.tenLoaiSanPham,
            this.tenhangSanPham,
            this.trangThai,
            this.ghiChu
        };
    }
}
