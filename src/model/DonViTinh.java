/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Computer Bao
 */
public class DonViTinh {
    private String maDonViTinh;
    private String tenDonVi;

    public DonViTinh() {
    }

    public DonViTinh(String maDonViTinh, String tenDonVi) {
        this.maDonViTinh = maDonViTinh;
        this.tenDonVi = tenDonVi;
    }

    public String getMaDonViTinh() {
        return maDonViTinh;
    }

    public void setMaDonViTinh(String maDonViTinh) {
        this.maDonViTinh = maDonViTinh;
    }

    public String getTenDonVi() {
        return tenDonVi;
    }

    public void setTenDonVi(String tenDonVi) {
        this.tenDonVi = tenDonVi;
    }
 public Object[]toDataRowDVT(){
        return new Object[]{
            this.maDonViTinh,this.tenDonVi
        };
    }
  
          
}
