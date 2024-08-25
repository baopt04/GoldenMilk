/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Interface.DoiTuongInterFace;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.DoiTuong;
<<<<<<< HEAD
//import service.DBconnect;
=======
import service.DBconnect;
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b

/**
 *
 * @author Computer Bao
 */
public class DoiTuongService implements DoiTuongInterFace {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";
<<<<<<< HEAD
    
  public boolean isTenSanPhamExists(String tenSanPham) {
        tenSanPham = tenSanPham.trim().toLowerCase();
    sql = "SELECT COUNT(*) FROM DoiTuongSuDung WHERE LOWER(tenDoiTuongSD) = ?";
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
    public List<DoiTuong> getList() {
        List<DoiTuong> list = new ArrayList<>();
        String sql = "select madoituongsudung , tenDoiTuongSD from DoiTuongSuDung";
        try {
<<<<<<< HEAD
            con = DBConnect.getConnection();
=======
            con = DBconnect.getConnection();
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DoiTuong doiTuong = new DoiTuong(rs.getString(1), rs.getString(2));
                list.add(doiTuong);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int addDoiTuong(DoiTuong doiTuong) {
        sql = "Insert into DoiTuongSuDung (maDoiTuongSuDung , tenDoiTuongSD) values(? , ?)";
        try {
<<<<<<< HEAD
            con = DBConnect.getConnection();
=======
            con = DBconnect.getConnection();
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps = con.prepareStatement(sql);
            ps.setObject(1, doiTuong.getMaDoiTuong());
            ps.setObject(2, doiTuong.getTenDoiTuongSD());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(DoiTuong doiTuong, String id) {
        sql = "	Update DoiTuongSuDung set tenDoiTuongSD = ? ,maDoiTuongSuDung = ?  where maDoiTuongSuDung =?";
        try {
<<<<<<< HEAD
            con = DBConnect.getConnection();
=======
            con = DBconnect.getConnection();
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps = con.prepareStatement(sql);
            ps.setObject(1, doiTuong.getTenDoiTuongSD());
             ps.setObject(2, doiTuong.getMaDoiTuong());
            ps.setObject(3, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        sql = "delete DoiTuongSuDung where maDoiTuongSuDung  = ?  ";
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
    public List<DoiTuong> timKiem(String ma) {
     sql = "SELECT maDoiTuongSuDung, tenDoiTuongSD FROM DoiTuongSuDung WHERE maDoiTuongSuDung LIKE ? OR tenDoiTuongSD LIKE ?";
    List<DoiTuong> list = new ArrayList<>();
    try {
<<<<<<< HEAD
        con = DBConnect.getConnection();
=======
        con = DBconnect.getConnection();
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
        ps = con.prepareStatement(sql);
        ps.setString(1, ma);
        ps.setString(2, ma);
        rs = ps.executeQuery();
        while (rs.next()) {
            DoiTuong doiTuong = new DoiTuong(rs.getString(1), 
                    rs.getString(2));
            list.add(doiTuong);
        }
        return list;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
<<<<<<< HEAD
public boolean checkMaDT(String maDoiTuong) {
     sql = "SELECT COUNT(*) FROM doituongsudung WHERE maDoiTuongSuDung = ?";
    try {
        con = DBConnect.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, maDoiTuong);
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
