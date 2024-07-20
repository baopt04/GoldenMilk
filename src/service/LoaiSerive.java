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
    public List<LoaiSanPham> getList(){
        List<LoaiSanPham> list = new ArrayList<>();
        String sql = "select maLoaiSanPham , tenLoaiSanPham from LoaiSanPham";
        try {
            con = DBconnect.getConnection();
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
            con = DBconnect.getConnection();
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
    public int update(LoaiSanPham loaiSanPham, String id) {
       sql = "Update LoaiSanPham set tenLoaiSanPham = ? ,maLoaiSanPham = ?  where maLoaiSanPham =?";
        try {
            con = DBconnect.getConnection();
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
    
}
