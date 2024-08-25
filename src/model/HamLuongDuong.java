/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Computer Bao
 */
public class HamLuongDuong {
    String maHamLuongDuong;
    String tenHamLuong;

    public HamLuongDuong() {
    }

    public HamLuongDuong(String maHamLuongDuong, String tenHamLuong) {
        this.maHamLuongDuong = maHamLuongDuong;
        this.tenHamLuong = tenHamLuong;
    }

    public String getMaHamLuongDuong() {
        return maHamLuongDuong;
    }

    public void setMaHamLuongDuong(String maHamLuongDuong) {
        this.maHamLuongDuong = maHamLuongDuong;
    }

    public String getTenHamLuong() {
        return tenHamLuong;
    }

    public void setTenHamLuong(String tenHamLuong) {
        this.tenHamLuong = tenHamLuong;
    }
     public Object[]toDataRowHLD(){
        return new Object[]{
            this.maHamLuongDuong,this.tenHamLuong
        };
    }
}
