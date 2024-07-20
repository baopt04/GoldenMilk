/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Computer Bao
 */
public class SanPham {
    String maSanPham;
      String tenSanPham;
    String id_hangSanPham;
    String id_loaiSanPham;
    String ghiChu;
    String trangThai;

    public SanPham() {
    }

    public SanPham(String maSanPham, String tenSanPham, String id_hangSanPham, String id_loaiSanPham, String ghiChu, String trangThai) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.id_hangSanPham = id_hangSanPham;
        this.id_loaiSanPham = id_loaiSanPham;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getId_hangSanPham() {
        return id_hangSanPham;
    }

    public void setId_hangSanPham(String id_hangSanPham) {
        this.id_hangSanPham = id_hangSanPham;
    }

    public String getId_loaiSanPham() {
        return id_loaiSanPham;
    }

    public void setId_loaiSanPham(String id_loaiSanPham) {
        this.id_loaiSanPham = id_loaiSanPham;
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
        this.id_loaiSanPham,
        this.id_hangSanPham,
        this.trangThai,
        this.ghiChu
    };
   }
}
