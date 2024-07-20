/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import Interface.HangInter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.HangSanPham;
/**
 *
 * @author Computer Bao
 */
public class HangService implements HangInter{
   Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";
    public List<HangSanPham> getList(){
        List<HangSanPham> list = new ArrayList<>();
        String sql = "select maHangSanPham , tenHangSanPham from HangSanPham";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                HangSanPham hangSanPham = new HangSanPham(rs.getString(1), rs.getString(2));
                list.add(hangSanPham);
            }
            return list;
        } catch (Exception e) {
        e.printStackTrace();
        return null;
        }
    }  

    @Override
    public int add(HangSanPham hangSanPham) {
        sql = "Insert into HangSanPham (maHangSanPham , tenHangSanPham) values(? , ?)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hangSanPham.getMaHangSanPham());
            ps.setObject(2, hangSanPham.getTenHangSanPham());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(String id) {
   sql = "delete HangSanPham where maHangSanPham  = ?  ";
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
    public int update(HangSanPham hangSanPham, String id) {
sql = "Update HangSanPham set tenHangSanPham = ? ,maHangSanPham = ?  where maHangSanPham =?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hangSanPham.getTenHangSanPham());
            ps.setObject(2, hangSanPham.getMaHangSanPham());
            ps.setObject(3, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
} 
