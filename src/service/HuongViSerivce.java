/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
<<<<<<< HEAD

=======
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
import Interface.HuongViInter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.HuongVi;
<<<<<<< HEAD

=======
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
/**
 *
 * @author Computer Bao
 */
<<<<<<< HEAD
public class HuongViSerivce implements HuongViInter {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";

    public boolean isTenSanPhamExists(String tenSanPham) {
        tenSanPham = tenSanPham.trim().toLowerCase();
        sql = "SELECT COUNT(*) FROM HuongVi WHERE LOWER(tenHuongVi) = ?";
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

    public List<HuongVi> getList() {
        List<HuongVi> list = new ArrayList<>();
        String sql = "select maHuongVi , tenHuongVi from HuongVi";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
=======
public class HuongViSerivce implements HuongViInter{
     Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";
    public List<HuongVi> getList(){
        List<HuongVi> list = new ArrayList<>();
        String sql = "select maHuongVi , tenHuongVi from HuongVi";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
                HuongVi huongVi = new HuongVi(rs.getString(1), rs.getString(2));
                list.add(huongVi);
            }
            return list;
        } catch (Exception e) {
<<<<<<< HEAD
            e.printStackTrace();
            return null;
=======
        e.printStackTrace();
        return null;
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
        }
    }

    @Override
    public int add(HuongVi huongVi) {
<<<<<<< HEAD
        sql = "Insert into HuongVi (maHuongVi , tenHuongVi) values(? , ?)";
        try {
            con = DBConnect.getConnection();
=======
 sql = "Insert into HuongVi (maHuongVi , tenHuongVi) values(? , ?)";
        try {
            con = DBconnect.getConnection();
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps = con.prepareStatement(sql);
            ps.setObject(1, huongVi.getMaHuongVi());
            ps.setObject(2, huongVi.getTenHuongVi());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(String id) {
<<<<<<< HEAD
        sql = "delete HuongVi where maHuongVi  = ?  ";
        try {
            con = DBConnect.getConnection();
=======
     sql = "delete HuongVi where maHuongVi  = ?  ";
        try {
            con = DBconnect.getConnection();
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
<<<<<<< HEAD
        }
=======
        }  
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
    }

    @Override
    public int update(HuongVi huongVi, String id) {
<<<<<<< HEAD
        sql = "Update HuongVi set tenHuongVi = ? ,maHuongVi = ?  where maHuongVi =?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, huongVi.getTenHuongVi());
            ps.setObject(2, huongVi.getMaHuongVi());
=======
      sql = "Update HuongVi set tenHuongVi = ? ,maHuongVi = ?  where maHuongVi =?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, huongVi.getTenHuongVi());
             ps.setObject(2, huongVi.getMaHuongVi());
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps.setObject(3, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
<<<<<<< HEAD
        }
    }

    public boolean checkHv(String hv) {
        sql = "SELECT COUNT(*) FROM HuongVi WHERE maHuongVi = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, hv);
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
        }   
    }
    
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
}
