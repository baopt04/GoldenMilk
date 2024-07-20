/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.SanPham;
/**
 *
 * @author Computer Bao
 */
public class SanPhamService {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
public List<SanPham> getList() {
    String sql = "SELECT \n" +
"    s.maSanPham,\n" +
"    s.tenSanPham,\n" +
"    l.tenLoaiSanPham,\n" +
"    h.tenHangSanPham,\n" +
"    s.trangThai,\n" +
"    s.mota\n" +            
"FROM \n" +
"    SanPham s\n" +
"    JOIN LoaiSanPham l ON s.id_loaiSanPham = l.id_loaiSanPham\n" +
"    JOIN HangSanPham h ON s.id_hangSanPham = h.id_hangSanPham";
    System.out.println(sql);
    List<SanPham> list = new ArrayList<>();
    try {
        Connection con = DBconnect.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            SanPham sanPham = new SanPham(rs.getString(1),
                   rs.getString(2),
                   rs.getString(3), 
                   rs.getString(4), 
                   rs.getString(6),
            rs.getString(5));
            list.add(sanPham);
        }
        return list;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    
}

    public int add(SanPham sanPham) {
 sql = "INSERT INTO SanPham (ID_loaiSanPham, ID_hangSanPham, maSanPham, tenSanPham, moTa, trangthai) values (? , ? , ? , ? , ? ,?)";
        try {
            int id_loai = getLoaiByIdName(sanPham.getId_loaiSanPham());
            int id_hang = getHangByIDName(sanPham.getId_hangSanPham());
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id_loai);
            ps.setObject(2, id_hang);
            ps.setObject(3, sanPham.getMaSanPham());
            ps.setObject(4, sanPham.getTenSanPham());
            ps.setObject(5, sanPham.getTrangThai());
            ps.setObject(6, sanPham.getGhiChu());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
        public int delete(String id) {
        sql = "delete SanPham where maSanPham  = ?  ";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
        
            public int update(SanPham sanPham, String id) {
          sql = "update SanPham set ID_hangSanPham = ? , ID_loaiSanPham = ? , tenSanPham = ? , moTa =? , trangthai = ? , maSanPham = ? where maSanPham = ?";    
        try {
         int id_loai = getLoaiByIdName(sanPham.getId_loaiSanPham());
            int id_hang = getHangByIDName(sanPham.getId_hangSanPham());
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_loai);
            ps.setInt(2, id_hang);
            ps.setObject(3, sanPham.getTenSanPham());
            ps.setObject(4, sanPham.getTrangThai());
            ps.setObject(5, sanPham.getGhiChu());
            ps.setObject(6, sanPham.getMaSanPham());
            ps.setObject(7, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private int getHangByIDName(String tenHang) {
        String sql = "SELECT ID_hangSanPham FROM HangSanPham WHERE tenHangSanPham = ?";
        try (Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenHang);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_hangSanPham");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
      private int getLoaiByIdName(String tenLoai) {
        String sql = "SELECT ID_loaiSanPham FROM LoaiSanPham WHERE tenLoaiSanPham = ?";
        try (Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenLoai);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_loaiSanPham");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
