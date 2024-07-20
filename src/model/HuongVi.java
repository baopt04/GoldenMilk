/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Computer Bao
 */
public class HuongVi {
    String maHuongVi;
    String tenHuongVi;

    public HuongVi() {
    }

    public String getMaHuongVi() {
        return maHuongVi;
    }

    public void setMaHuongVi(String maHuongVi) {
        this.maHuongVi = maHuongVi;
    }

    public String getTenHuongVi() {
        return tenHuongVi;
    }

    public void setTenHuongVi(String tenHuongVi) {
        this.tenHuongVi = tenHuongVi;
    }

    public HuongVi(String maHuongVi, String tenHuongVi) {
        this.maHuongVi = maHuongVi;
        this.tenHuongVi = tenHuongVi;
    }
 public Object[]toDataRowHV(){
        return new Object[]{
            this.maHuongVi,this.tenHuongVi
        };
    }
   
    
}
