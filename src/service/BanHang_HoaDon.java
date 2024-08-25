/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author OSC
 */
public class BanHang_HoaDon {
    private Connection conn;
    private String maNV;
    public BanHang_HoaDon(){
        this.conn = DBConnect.getConnection();
    }
    
    String trangThaiHoaDon = "Chờ thanh toán";
    public BanHang_HoaDon(String maNV) {
        this.maNV = maNV;
    }
    public void create(){
         String SQL = "INSERT INTO HoaDon (ID_khachHang, ID_nhanVien, ID_voucher,maNhanVienHD, giaTriThanhToan, trangThaiHoaDon, ngayTao) VALUES (NULL, NULL, NULL, ?, 0, N'Đang xử lý', GETDATE())";
        try {
            PreparedStatement ps = this.conn.prepareStatement(SQL);
            ps.setString(1, maNV);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
