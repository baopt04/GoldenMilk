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

    public List<HamLuongDuong> getList() {
        List<HamLuongDuong> list = new ArrayList<>();
        String sql = "select maHamLuongDuong , tenHamLuongDuong from HamLuongDuong";
        try {
            con = DBconnect.getConnection();
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
            con = DBconnect.getConnection();
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
    public int update(HamLuongDuong hamLuongDuong, String id) {
    sql = "Update HamLuongDuong set tenHamLuongDuong = ? ,maHamLuongDuong = ?  where maHamLuongDuong =?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hamLuongDuong.getTenHamLuong());
             ps.setObject(2, hamLuongDuong.getMaHamLuongDuong());
            ps.setObject(3, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
