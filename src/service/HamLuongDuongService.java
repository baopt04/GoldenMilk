/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Interface.HamLuongDuongInter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.HamLuongDuong;

/**
 *
 * @author Computer Bao
 */
public class HamLuongDuongService implements HamLuongDuongInter {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";

<<<<<<< HEAD
    public boolean isTenSanPhamExists(String tenSanPham) {
        tenSanPham = tenSanPham.trim().toLowerCase();
        sql = "SELECT COUNT(*) FROM HamLuongDuong WHERE LOWER(tenHamLuongDuong) = ?";
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
    public List<HamLuongDuong> getList() {
        List<HamLuongDuong> list = new ArrayList<>();
        String sql = "select maHamLuongDuong , tenHamLuongDuong from HamLuongDuong";
        try {
<<<<<<< HEAD
            con = DBConnect.getConnection();
=======
            con = DBconnect.getConnection();
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HamLuongDuong hamLuongDuong = new HamLuongDuong(rs.getString(1), rs.getString(2));
                list.add(hamLuongDuong);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int addDoiTuong(HamLuongDuong hamLuongDuong) {
        sql = "Insert into HamLuongDuong (maHamLuongDuong , tenHamLuongDuong) values(? , ?)";
        try {
<<<<<<< HEAD
            con = DBConnect.getConnection();
=======
            con = DBconnect.getConnection();
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps = con.prepareStatement(sql);
            ps.setObject(1, hamLuongDuong.getMaHamLuongDuong());
            ps.setObject(2, hamLuongDuong.getTenHamLuong());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        sql = "delete HamLuongDuong where maHamLuongDuong  = ?  ";
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
    public int update(HamLuongDuong hamLuongDuong, String id) {
<<<<<<< HEAD
        sql = "Update HamLuongDuong set tenHamLuongDuong = ? ,maHamLuongDuong = ?  where maHamLuongDuong =?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hamLuongDuong.getTenHamLuong());
            ps.setObject(2, hamLuongDuong.getMaHamLuongDuong());
=======
    sql = "Update HamLuongDuong set tenHamLuongDuong = ? ,maHamLuongDuong = ?  where maHamLuongDuong =?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hamLuongDuong.getTenHamLuong());
             ps.setObject(2, hamLuongDuong.getMaHamLuongDuong());
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps.setObject(3, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
<<<<<<< HEAD

    public boolean checkMaHLD(String hld) {
        sql = "SELECT COUNT(*) FROM HamLuongDuong WHERE maHamLuongDuong = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, hld);
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
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
}
