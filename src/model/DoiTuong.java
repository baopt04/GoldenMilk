/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Computer Bao
 */
public class DoiTuong {
    String maDoiTuong;
    String tenDoiTuongSD;

    public DoiTuong() {
    }

    public DoiTuong(String maDoiTuong, String tenDoiTuongSD) {
        this.maDoiTuong = maDoiTuong;
        this.tenDoiTuongSD = tenDoiTuongSD;
    }

    public String getMaDoiTuong() {
        return maDoiTuong;
    }

    public void setMaDoiTuong(String maDoiTuong) {
        this.maDoiTuong = maDoiTuong;
    }

    public String getTenDoiTuongSD() {
        return tenDoiTuongSD;
    }

    public void setTenDoiTuongSD(String tenDoiTuongSD) {
        this.tenDoiTuongSD = tenDoiTuongSD;
    }
     public Object[]toDataRowDT(){
        return new Object[]{
            this.maDoiTuong,this.tenDoiTuongSD
        };
    }
}
