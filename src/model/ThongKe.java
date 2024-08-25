/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Computer Bao
 */
public class ThongKe {
    private Integer id_hoaDon;
    private Integer id_khachHang;
private float  giaTriThanhToan;
private String trangThaiThanhToan;
private Date ngayTao;

    public ThongKe() {
    }

    public ThongKe(Integer id_hoaDon, Integer id_khachHang, float giaTriThanhToan, String trangThaiThanhToan, Date ngayTao) {
        this.id_hoaDon = id_hoaDon;
        this.id_khachHang = id_khachHang;
        this.giaTriThanhToan = giaTriThanhToan;
        this.trangThaiThanhToan = trangThaiThanhToan;
        this.ngayTao = ngayTao;
    }

    public ThongKe(Integer id_hoaDon, float giaTriThanhToan, Date ngayTao) {
        this.id_hoaDon = id_hoaDon;
        this.giaTriThanhToan = giaTriThanhToan;
        this.ngayTao = ngayTao;
    }



    public Integer getId_hoaDon() {
        return id_hoaDon;
    }

    public void setId_hoaDon(Integer id_hoaDon) {
        this.id_hoaDon = id_hoaDon;
    }

    public Integer getId_khachHang() {
        return id_khachHang;
    }

    public void setId_khachHang(Integer id_khachHang) {
        this.id_khachHang = id_khachHang;
    }

    public float getGiaTriThanhToan() {
        return giaTriThanhToan;
    }

    public void setGiaTriThanhToan(float giaTriThanhToan) {
        this.giaTriThanhToan = giaTriThanhToan;
    }

    public String getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(String trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

}
