/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Interface.HangInter;
import Interface.LoSanXuatInter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.LoSanXuat;

/**
 *
 * @author Computer Bao
 */
public class LoSanXuatService implements LoSanXuatInter {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";

    public List<LoSanXuat> getList() {
        List<LoSanXuat> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    lsx.maLoSanXuat,\n"
                + "    lsx.tenLoSanXuat,\n"
                + "    lsx.ngayNhap,\n"
                + "    lsx.soLuongNhap,\n"
                + "    lsx.giaNhap,\n"
                + "    lsx.NSX,\n"
                + "    lsx.HSD,\n"
                + "    ncc.tenNhaCungCap\n"
                + "FROM \n"
                + "    LoSanXuat lsx\n"
                + "JOIN \n"
                + "    NhaCungCap ncc ON lsx.ID_nhaCungCap = ncc.ID_nhaCungCap;";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                LoSanXuat loSanXuat = new LoSanXuat(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8));
                list.add(loSanXuat);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
public int add(LoSanXuat loSanXuat) {
    String sql = "INSERT INTO LoSanXuat (maLoSanXuat, tenLoSanXuat, ngayNhap, soLuongNhap, giaNhap, NSX, HSD, ID_nhaCungCap) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try {
        con = DBconnect.getConnection();
        ps = con.prepareStatement(sql);
        
        // Chuyển đổi chuỗi ngày tháng sang java.sql.Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date ngayNhap = new Date(dateFormat.parse(loSanXuat.getNgayNhap()).getTime());
        Date NSX = new Date(dateFormat.parse(loSanXuat.getNSX()).getTime());
        Date HSD = new Date(dateFormat.parse(loSanXuat.getHSD()).getTime());
        
        // Lấy ID_nhaCungCap từ tên nhà cung cấp
        int idNhaCungCap = getNhaCungCapIdByName(loSanXuat.getTenNhaCungCap());
        if (idNhaCungCap == -1) {
            // Handle the case when the ID is not found
            throw new Exception("Nhà cung cấp không tồn tại");
        }
        
        ps.setString(1, loSanXuat.getMaLoSanXuat());
        ps.setString(2, loSanXuat.getTenLoSanXuat());
        ps.setDate(3, ngayNhap);
        ps.setString(4, loSanXuat.getSoLuongNhap());
        ps.setString(5, loSanXuat.getGiaNhap());
        ps.setDate(6, NSX);
        ps.setDate(7, HSD);
        ps.setInt(8, idNhaCungCap);
        
        return ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}



    private int getNhaCungCapIdByName(String tenNhaCungCap) {
        String sql = "SELECT ID_nhaCungCap FROM NhaCungCap WHERE tenNhaCungCap = ?";
        try (Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenNhaCungCap);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_nhaCungCap");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int delete(String id) {
        sql = "delete LoSanXuat where maLoSanXuat  = ?  ";
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
    public int update(LoSanXuat loSanXuat, String id) {
        sql = "Update LoSanXuat set tenLoSanXuat = ? ,soLuongNhap = ? , giaNhap = ?, NSX = ? ,HSD = ? , ID_nhaCungCap = ? ,maLoSanXuat = ?   where maLoSanXuat =?";
        try {
                  int idNhaCungCap = getNhaCungCapIdByName(loSanXuat.getTenNhaCungCap());
        if (idNhaCungCap == -1) {
            throw new Exception("Nhà cung cấp không tồn tại");
        }
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date NSX = new Date(dateFormat.parse(loSanXuat.getNSX()).getTime());
        Date HSD = new Date(dateFormat.parse(loSanXuat.getHSD()).getTime());
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, loSanXuat.getTenLoSanXuat());
            ps.setObject(2, loSanXuat.getSoLuongNhap());
            ps.setObject(3, loSanXuat.getGiaNhap());
            ps.setObject(4, NSX);
            ps.setObject(5, HSD);
            ps.setObject(6, idNhaCungCap);
            ps.setObject(7, loSanXuat.getMaLoSanXuat());
            ps.setObject(8, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
