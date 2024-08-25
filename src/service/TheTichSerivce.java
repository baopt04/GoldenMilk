/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import Interface.TheTichInter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.TheTich;
/**
 *
 * @author Computer Bao
 */
public class TheTichSerivce implements TheTichInter{
     Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";
<<<<<<< HEAD
    
      public boolean isTenSanPhamExists(String tenSanPham) {
        tenSanPham = tenSanPham.trim().toLowerCase();
    sql = "SELECT COUNT(*) FROM TheTich WHERE LOWER(tenthetich) = ?";
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
    public List<TheTich> getList(){
        List<TheTich> list = new ArrayList<>();
        String sql = "select mathetich , tenthetich from TheTich";
        try {
<<<<<<< HEAD
            con = DBConnect.getConnection();
=======
            con = DBconnect.getConnection();
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                TheTich theTich = new TheTich(rs.getString(1), rs.getString(2));
                list.add(theTich);
            }
            return list;
        } catch (Exception e) {
        e.printStackTrace();
        return null;
        }
    }

    @Override
    public int add(TheTich theTich) {
     sql = "Insert into TheTich (mathetich , tenthetich) values(? , ?)";
        try {
<<<<<<< HEAD
            con = DBConnect.getConnection();
=======
            con = DBconnect.getConnection();
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps = con.prepareStatement(sql);
            ps.setObject(1, theTich.getMaTheTich());
            ps.setObject(2, theTich.getTenTheTich());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }   
    }

    @Override
    public int delete(String id) {
             sql = "delete TheTich where mathetich  = ?  ";
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
    public int update(TheTich theTich, String id) {
           sql = "Update TheTich set tenthetich = ? ,mathetich = ?  where mathetich =?";
        try {
<<<<<<< HEAD
            con = DBConnect.getConnection();
=======
            con = DBconnect.getConnection();
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps = con.prepareStatement(sql);
            ps.setObject(1, theTich.getTenTheTich());
             ps.setObject(2, theTich.getMaTheTich());
            ps.setObject(3, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
<<<<<<< HEAD
       public boolean checkHv(String tt) {
     sql = "SELECT COUNT(*) FROM TheTich WHERE mathetich = ?";
    try {
        con = DBConnect.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, tt);
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
