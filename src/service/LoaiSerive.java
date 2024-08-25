/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import Interface.LoaiInter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.LoaiSanPham;
/**
 *
 * @author Computer Bao
 */
public class LoaiSerive  implements LoaiInter{
     Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";
<<<<<<< HEAD
    
      public boolean isTenSanPhamExists(String tenSanPham) {
        tenSanPham = tenSanPham.trim().toLowerCase();
    sql = "SELECT COUNT(*) FROM LoaiSanPham WHERE LOWER(tenLoaiSanPham) = ?";
    try (Connection conn = DBConnect.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, tenSanPham);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
=======
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
    public List<LoaiSanPham> getList(){
        List<LoaiSanPham> list = new ArrayList<>();
        String sql = "select maLoaiSanPham , tenLoaiSanPham from LoaiSanPham";
        try {
<<<<<<< HEAD
            con = DBConnect.getConnection();
=======
            con = DBconnect.getConnection();
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                LoaiSanPham loaiSanPham = new LoaiSanPham(rs.getString(1), rs.getString(2));
                list.add(loaiSanPham);
            }
            return list;
        } catch (Exception e) {
        e.printStackTrace();
        return null;
        }
    }

    @Override
    public int add(LoaiSanPham loaiSanPham) {
           sql = "Insert into LoaiSanPham (maLoaiSanPham , tenLoaiSanPham) values(? , ?)";
        try {
<<<<<<< HEAD
            con = DBConnect.getConnection();
=======
            con = DBconnect.getConnection();
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps = con.prepareStatement(sql);
            ps.setObject(1, loaiSanPham.getMaLoaiSanPham());
            ps.setObject(2, loaiSanPham.getTenLoaiSanPham());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(String id) {
 sql = "delete LoaiSanPham where maLoaiSanPham  = ?  ";
        try {
<<<<<<< HEAD
            con = DBConnect.getConnection();
=======
            con = DBconnect.getConnection();
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(LoaiSanPham loaiSanPham, String id) {
       sql = "Update LoaiSanPham set tenLoaiSanPham = ? ,maLoaiSanPham = ?  where maLoaiSanPham =?";
        try {
<<<<<<< HEAD
            con = DBConnect.getConnection();
=======
            con = DBconnect.getConnection();
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps = con.prepareStatement(sql);
            ps.setObject(1, loaiSanPham.getTenLoaiSanPham());
             ps.setObject(2, loaiSanPham.getMaLoaiSanPham());
            ps.setObject(3, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
<<<<<<< HEAD
        public boolean checkHv(String loai) {
     sql = "SELECT COUNT(*) FROM LoaiSanPham WHERE maLoaiSanPham = ?";
    try {
        con = DBConnect.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, loai);
        rs = ps.executeQuery();
        
        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0; 
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false; 
}
=======
    
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
}
