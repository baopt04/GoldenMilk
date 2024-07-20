/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import Interface.HuongViInter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.HuongVi;
/**
 *
 * @author Computer Bao
 */
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
                HuongVi huongVi = new HuongVi(rs.getString(1), rs.getString(2));
                list.add(huongVi);
            }
            return list;
        } catch (Exception e) {
        e.printStackTrace();
        return null;
        }
    }

    @Override
    public int add(HuongVi huongVi) {
 sql = "Insert into HuongVi (maHuongVi , tenHuongVi) values(? , ?)";
        try {
            con = DBconnect.getConnection();
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
     sql = "delete HuongVi where maHuongVi  = ?  ";
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

    @Override
    public int update(HuongVi huongVi, String id) {
      sql = "Update HuongVi set tenHuongVi = ? ,maHuongVi = ?  where maHuongVi =?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, huongVi.getTenHuongVi());
             ps.setObject(2, huongVi.getMaHuongVi());
            ps.setObject(3, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }   
    }
    
}
