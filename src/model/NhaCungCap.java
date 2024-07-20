/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Computer Bao
 */
public class NhaCungCap {
    String maNhaCungCap;
    String tenNhaCungCap;
    String soDT;
    String email;
    String diaChi;

    public NhaCungCap(String maNhaCungCap, String tenNhaCungCap, String soDT, String email, String diaChi) {
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.soDT = soDT;
        this.email = email;
        this.diaChi = diaChi;
    }

    public NhaCungCap() {
    }

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

 public Object[]toDataRowNCC(){
        return new Object[]{
            this.maNhaCungCap,this.tenNhaCungCap , 
            this.email ,this.soDT , this.diaChi
        };
    }
}
