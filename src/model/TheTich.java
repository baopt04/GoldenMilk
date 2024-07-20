/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Computer Bao
 */
public class TheTich {
    String maTheTich;
    String tenTheTich;

    public TheTich() {
    }

    public String getMaTheTich() {
        return maTheTich;
    }

    public void setMaTheTich(String maTheTich) {
        this.maTheTich = maTheTich;
    }

    public String getTenTheTich() {
        return tenTheTich;
    }

    public void setTenTheTich(String tenTheTich) {
        this.tenTheTich = tenTheTich;
    }

    public TheTich(String maTheTich, String tenTheTich) {
        this.maTheTich = maTheTich;
        this.tenTheTich = tenTheTich;
    }
 public Object[]toDataRowTT(){
        return new Object[]{
            this.maTheTich,this.tenTheTich
        };
    }

    
}
