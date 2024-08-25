package model;

import java.sql.Date;

public class Voucher {
    private int id;
    private String maVoucher;
    private String tenVoucher;
    private float giaTriVoucher;
    private float giaTriApDungVoucher;
    private int soLuongVoucher;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String trangThai;
    private String ghiChu; // New field for notes

    // Getters and setters for all attributes
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public String getTenVoucher() {
        return tenVoucher;
    }

    public void setTenVoucher(String tenVoucher) {
        this.tenVoucher = tenVoucher;
    }

    public float getGiaTriVoucher() {
        return giaTriVoucher;
    }

    public void setGiaTriVoucher(float giaTriVoucher) {
        this.giaTriVoucher = giaTriVoucher;
    }

    public float getGiaTriApDungVoucher() {
        return giaTriApDungVoucher;
    }

    public void setGiaTriApDungVoucher(float giaTriApDungVoucher) {
        this.giaTriApDungVoucher = giaTriApDungVoucher;
    }

    public int getSoLuongVoucher() {
        return soLuongVoucher;
    }

    public void setSoLuongVoucher(int soLuongVoucher) {
        this.soLuongVoucher = soLuongVoucher;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Object[] toData(int stt) {
        return new Object[]{
            stt, maVoucher, tenVoucher, giaTriVoucher, giaTriApDungVoucher, 
            soLuongVoucher, ngayBatDau, ngayKetThuc, trangThai, ghiChu
        };
    }
}
