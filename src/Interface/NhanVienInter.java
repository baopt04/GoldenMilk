/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import model.NhanVien;
import java.util.List;

/**
 * Interface for NhanVien service operations
 * 
 */
public interface NhanVienInter {
    public int add(NhanVien nhanVien);
    public int delete(String id);
    public int update(NhanVien nhanVien, String id);
    public List<NhanVien> getList();
}
