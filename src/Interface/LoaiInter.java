/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import model.LoaiSanPham;

/**
 *
 * @author Computer Bao
 */
public interface LoaiInter {
       public int add( LoaiSanPham loaiSanPham );
    public int delete(String id);
    public int update(LoaiSanPham loaiSanPham  , String id);
}
