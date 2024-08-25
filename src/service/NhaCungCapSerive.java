/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Interface.NhaCungCapInter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.NhaCungCap;

/**
 *
 * @author Computer Bao
 */
public class NhaCungCapSerive implements NhaCungCapInter {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";
<<<<<<< HEAD
  public boolean isTenSanPhamExists(String tenSanPham) {
        tenSanPham = tenSanPham.trim().toLowerCase();
    sql = "SELECT COUNT(*) FROM NhaCungCap WHERE LOWER(tenNhaCungCap) = ?";
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
    public List<NhaCungCap> getList() {
        List<NhaCungCap> list = new ArrayList<>();
        String sql = "select maNhaCungCap , tenNhaCungCap , soDT , email  , diaChi from NhaCungCap";
        try {
<<<<<<< HEAD
            con = DBConnect.getConnection();
=======
            con = DBconnect.getConnection();
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhaCungCap nhaCungCap = new NhaCungCap(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
                list.add(nhaCungCap);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int add(NhaCungCap nhaCungCap) {
        sql = "Insert into NhaCungCap (maNhaCungCap , tenNhaCungCap , soDT ,email, diaChi ) values(? , ? , ? , ? , ?)";
        try {
<<<<<<< HEAD
            con = DBConnect.getConnection();
=======
            con = DBconnect.getConnection();
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps = con.prepareStatement(sql);
            ps.setObject(1, nhaCungCap.getMaNhaCungCap());
            ps.setObject(2, nhaCungCap.getTenNhaCungCap());
            ps.setObject(3, nhaCungCap.getSoDT());
            ps.setObject(4, nhaCungCap.getEmail());
            ps.setObject(5, nhaCungCap.getDiaChi());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        sql = "delete NhaCungCap where maNhaCungCap  = ?  ";
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
    public int update(NhaCungCap nhaCungCap, String id) {
        sql = "Update NhaCungCap set tenNhaCungCap = ? ,soDT = ? ,email = ? , diaChi = ? , maNhaCungCap = ?   where maNhaCungCap =?";
        try {
<<<<<<< HEAD
            con = DBConnect.getConnection();
=======
            con = DBconnect.getConnection();
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps = con.prepareStatement(sql);
            ps.setObject(1, nhaCungCap.getTenNhaCungCap());
            ps.setObject(2, nhaCungCap.getSoDT());
            ps.setObject(3, nhaCungCap.getEmail());
            ps.setObject(4, nhaCungCap.getDiaChi());
            ps.setObject(5, nhaCungCap.getMaNhaCungCap());
            ps.setObject(6, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
<<<<<<< HEAD
    public boolean checkNcc(String ncc) {
     sql = "SELECT COUNT(*) FROM NhaCungCap WHERE maNhaCungCap = ?";
    try {
        con = DBConnect.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, ncc);
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
