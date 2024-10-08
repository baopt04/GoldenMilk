/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Interface.DonViTinhInter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.DonViTinh;

/**
 *
 * @author Computer Bao
 */
<<<<<<< HEAD
public class DonViTinhService implements DonViTinhInter {
=======
public class DonViTinhService implements DonViTinhInter{
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";

<<<<<<< HEAD
    public boolean isTenSanPhamExists(String tenSanPham) {
        tenSanPham = tenSanPham.trim().toLowerCase();
        sql = "SELECT COUNT(*) FROM DonViTinh WHERE LOWER(tenDonViTinh) = ?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
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
    public List<DonViTinh> getList() {
        List<DonViTinh> list = new ArrayList<>();
        String sql = "select maDonViTinh , tenDonViTinh from DonViTinh";
        try {
<<<<<<< HEAD
            con = DBConnect.getConnection();
=======
            con = DBconnect.getConnection();
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DonViTinh donViTinh = new DonViTinh(rs.getString(1), rs.getString(2));
                list.add(donViTinh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int addDoiTuong(DonViTinh donViTinh) {
        sql = "Insert into DonViTinh (maDonViTinh , tenDonViTinh) values(? , ?)";
        try {
<<<<<<< HEAD
            con = DBConnect.getConnection();
=======
            con = DBconnect.getConnection();
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps = con.prepareStatement(sql);
            ps.setObject(1, donViTinh.getMaDonViTinh());
            ps.setObject(2, donViTinh.getTenDonVi());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(String id) {
<<<<<<< HEAD
        sql = "delete DonViTinh where maDonViTinh  = ?  ";
        try {
            con = DBConnect.getConnection();
=======
 sql = "delete DonViTinh where maDonViTinh  = ?  ";
        try {
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
    public int update(DonViTinh donViTinh, String id) {
        sql = "	Update DonViTinh set tenDonViTinh = ? ,maDonViTinh = ?  where maDonViTinh =?";
        try {
<<<<<<< HEAD
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, donViTinh.getTenDonVi());
            ps.setObject(2, donViTinh.getMaDonViTinh());
=======
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, donViTinh.getTenDonVi());
             ps.setObject(2, donViTinh.getMaDonViTinh());
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps.setObject(3, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<DonViTinh> timKiem(String ma) {
<<<<<<< HEAD
        sql = "SELECT maDonViTinh, tenDonViTinh FROM DonViTinh WHERE maDonViTinh LIKE ? OR tenDonViTinh LIKE ?";
        List<DonViTinh> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ps.setString(2, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                DonViTinh donViTinh = new DonViTinh(rs.getString(1),
                        rs.getString(2));
                list.add(donViTinh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkMaDVT(String dvt) {
        sql = "SELECT COUNT(*) FROM DonViTinh WHERE maDonViTinh = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, dvt);
            rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Trả về true nếu tồn tại, false nếu không tồn tại
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi xảy ra
    }

=======
 sql = "SELECT maDonViTinh, tenDonViTinh FROM DonViTinh WHERE maDonViTinh LIKE ? OR tenDonViTinh LIKE ?";
    List<DonViTinh> list = new ArrayList<>();
    try {
        con = DBconnect.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, ma);
        ps.setString(2, ma);
        rs = ps.executeQuery();
        while (rs.next()) {
            DonViTinh donViTinh = new DonViTinh(rs.getString(1), 
                    rs.getString(2));
            list.add(donViTinh);
        }
        return list;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    }
    
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
}
