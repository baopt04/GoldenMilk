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
public class LoSanXuat {
    String maLoSanXuat;
    String tenLoSanXuat;
    String ngayNhap;
    String soLuongNhap;
    String giaNhap;
    String NSX;
    String HSD;
    String tenNhaCungCap;

    public LoSanXuat() {
    }

    public LoSanXuat(String maLoSanXuat, String tenLoSanXuat, String ngayNhap, String soLuongNhap, String giaNhap, String NSX, String HSD, String tenNhaCungCap) {
        this.maLoSanXuat = maLoSanXuat;
        this.tenLoSanXuat = tenLoSanXuat;
        this.ngayNhap = ngayNhap;
        this.soLuongNhap = soLuongNhap;
        this.giaNhap = giaNhap;
        this.NSX = NSX;
        this.HSD = HSD;
        this.tenNhaCungCap = tenNhaCungCap;
    }



    public String getMaLoSanXuat() {
        return maLoSanXuat;
    }

    public void setMaLoSanXuat(String maLoSanXuat) {
        this.maLoSanXuat = maLoSanXuat;
    }

    public String getTenLoSanXuat() {
        return tenLoSanXuat;
    }

    public void setTenLoSanXuat(String tenLoSanXuat) {
        this.tenLoSanXuat = tenLoSanXuat;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

 

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setSoLuongNhap(String soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }

    public String getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(String giaNhap) {
        this.giaNhap = giaNhap;
    }

    public String getNSX() {
        return NSX;
    }

    public void setNSX(String NSX) {
        this.NSX = NSX;
    }

    public String getHSD() {
        return HSD;
    }

    public void setHSD(String HSD) {
        this.HSD = HSD;
    }

    public Object[] toDataRowLSX() {
        return new Object[]{
            this.maLoSanXuat,this.tenLoSanXuat , 
            this.ngayNhap , this.soLuongNhap , this.giaNhap,
            this.NSX , this.HSD , this.tenNhaCungCap
        };
    }
}
