/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import model.HangSanPham;

/**
 *
 * @author Computer Bao
 */
public interface HangInter {
    public int add(HangSanPham hangSanPham );
    public int delete(String id);
    public int update(HangSanPham hangSanPham , String id); 
}
