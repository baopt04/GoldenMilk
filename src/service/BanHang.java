 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import model.BanHang_HuongVi;
import model.BanHang_Voicher;
import model.BanHang_GioHangMD;
import model.BanHang_SanPhamMD;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author OSC
 */
public class BanHang {
    private Connection conn;
    
    public BanHang(){
        this.conn = DBConnect.getConnection();
    }
    
    public ArrayList<BanHang_SanPhamMD> select(){
        String SQL = "Select maSanPham, tenSanPham,tenHuongVi, soLuongTon, donGia from SanPham join SanPhamChiTiet on SanPham.ID_sanPham = SanPhamChiTiet.ID_sanPham join HuongVi on SanPhamChiTiet.ID_huongVi = HuongVi.ID_huongVi";
        ArrayList<BanHang_SanPhamMD> list = new ArrayList<>();
        try {conn = DBConnect.getConnection();
            PreparedStatement ps = this.conn.prepareStatement(SQL);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while(rs.next()){
                String maSP = rs.getString("maSanPham");
                String tenSP = rs.getString("tenSanPham");
                String huongVi = rs.getString("tenHuongVi");
                int soLuong = rs.getInt("soLuongTon");
                float donGia = rs.getFloat("donGia");
                list.add(new BanHang_SanPhamMD(maSP, tenSP, huongVi, soLuong, donGia));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<BanHang_Voicher> voucher(){
        String SQL = "select tenVoucher from Voucher where trangThai like N'Hoạt động'";
        ArrayList<BanHang_Voicher> listVoucher = new ArrayList<>();
        try {conn = DBConnect.getConnection();
            PreparedStatement ps = this.conn.prepareStatement(SQL);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while(rs.next()){
                String tenVC = rs.getString("tenVoucher");
                listVoucher.add(new BanHang_Voicher(tenVC));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listVoucher;
    }
    public ArrayList<BanHang_HuongVi> huongVi(){
        String SQL = "SELECT tenHuongVi FROM HuongVi";
        ArrayList<BanHang_HuongVi> listHuongVi = new ArrayList<>();
        try {conn = DBConnect.getConnection();
            PreparedStatement ps = this.conn.prepareStatement(SQL);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while(rs.next()){
                String tenHV = rs.getString("tenHuongVi");
                listHuongVi.add(new BanHang_HuongVi(tenHV));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHuongVi;
    }
    public ArrayList<BanHang_SanPhamMD> find(){
        String SQL = "Select maSanPham, tenSanPham,tenHuongVi, soLuongTon, donGia from SanPham join SanPhamChiTiet on SanPham.ID_sanPham = SanPhamChiTiet.ID_sanPham join HuongVi on SanPhamChiTiet.ID_huongVi = HuongVi.ID_huongVi where tenSanPham = ?";
        ArrayList<BanHang_SanPhamMD> list = new ArrayList<>();
        try {conn = DBConnect.getConnection();
            PreparedStatement ps = this.conn.prepareStatement(SQL);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while(rs.next()){
                String maSP = rs.getString("maSanPham");
                String tenSP = rs.getString("tenSanPham");
                String huongVi = rs.getString("tenHuongVi");
                int soLuong = rs.getInt("soLuongTon");
                float donGia = rs.getFloat("donGia");
                list.add(new BanHang_SanPhamMD(maSP, tenSP, huongVi, soLuong, donGia));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
