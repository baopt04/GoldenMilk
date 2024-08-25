package service;

import Interface.NhanVienInter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ChucVu;
import model.NhanVien;

/**
 * Service class for NhanVien
 * 
 */
public class NhanVienService implements NhanVienInter {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";

    @Override
    public List<NhanVien> getList() {
        List<NhanVien> list = new ArrayList<>();
        sql = "SELECT ID_nhanVien, ID_chucVu, maNhanVien, hoTen, gioiTinh, ngaySinh, soDT, email, matKhau, diaChi, trangThai FROM NhanVien";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien(
                        rs.getInt("ID_chucVu"),
                        rs.getString("maNhanVien"),
                        rs.getString("hoTen"),
                        rs.getString("gioiTinh"),
                        rs.getDate("ngaySinh"),
                        rs.getString("soDT"),
                        rs.getString("email"),
                        rs.getString("matKhau"),
                        rs.getString("diaChi"),
                        rs.getString("trangThai")
                );
                list.add(nhanVien);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }

    @Override
    public int add(NhanVien nhanVien) {
        sql = "INSERT INTO NhanVien (ID_chucVu, maNhanVien, hoTen, gioiTinh, ngaySinh, soDT, email, matKhau, diaChi, trangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, nhanVien.getIdChucVu());
            ps.setString(2, nhanVien.getMaNhanVien());
            ps.setString(3, nhanVien.getHoTen());
            ps.setString(4, nhanVien.getGioiTinh());
            ps.setDate(5, new java.sql.Date(nhanVien.getNgaySinh().getTime()));
            ps.setString(6, nhanVien.getSoDT());
            ps.setString(7, nhanVien.getEmail());
            ps.setString(8, nhanVien.getMatKhau());
            ps.setString(9, nhanVien.getDiaChi());
            ps.setString(10, nhanVien.getTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeConnection();
        }
    }

    @Override
    public int delete(String id) {
        sql = "DELETE FROM NhanVien WHERE maNhanVien = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeConnection();
        }
    }

    @Override
    public int update(NhanVien nhanVien, String id) {
        sql = "UPDATE NhanVien SET ID_chucVu = ?, maNhanVien = ?, hoTen = ?, gioiTinh = ?, ngaySinh = ?, soDT = ?, email = ?, matKhau = ?, diaChi = ?, trangThai = ? WHERE maNhanVien = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, nhanVien.getIdChucVu());
            ps.setString(2, nhanVien.getMaNhanVien());
            ps.setString(3, nhanVien.getHoTen());
            ps.setString(4, nhanVien.getGioiTinh());
            ps.setDate(5, new java.sql.Date(nhanVien.getNgaySinh().getTime()));
            ps.setString(6, nhanVien.getSoDT());
            ps.setString(7, nhanVien.getEmail());
            ps.setString(8, nhanVien.getMatKhau());
            ps.setString(9, nhanVien.getDiaChi());
            ps.setString(10, nhanVien.getTrangThai());
            ps.setString(11, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeConnection();
        }
    }

    public List<ChucVu> getAllChucVu() {
        List<ChucVu> list = new ArrayList<>();
        sql = "SELECT ID_chucVu, maChucVu, tenChucVu FROM ChucVu";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChucVu chucVu = new ChucVu(
                        rs.getInt("ID_chucVu"),
                        rs.getString("maChucVu"),
                        rs.getString("tenChucVu")
                );
                list.add(chucVu);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }

    public List<NhanVien> searchNhanVien(String keyword) {
        List<NhanVien> list = new ArrayList<>();
        sql = "SELECT ID_nhanVien, ID_chucVu, maNhanVien, hoTen, gioiTinh, ngaySinh, soDT, email, matKhau, diaChi, trangThai FROM NhanVien WHERE maNhanVien LIKE ? OR hoTen LIKE ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien(
                        rs.getInt("ID_chucVu"),
                        rs.getString("maNhanVien"),
                        rs.getString("hoTen"),
                        rs.getString("gioiTinh"),
                        rs.getDate("ngaySinh"),
                        rs.getString("soDT"),
                        rs.getString("email"),
                        rs.getString("matKhau"),
                        rs.getString("diaChi"),
                        rs.getString("trangThai")
                );
                list.add(nhanVien);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }

    public List<NhanVien> filterByTrangThai(String trangThai) {
        List<NhanVien> list = new ArrayList<>();
        sql = "SELECT ID_nhanVien, ID_chucVu, maNhanVien, hoTen, gioiTinh, ngaySinh, soDT, email, matKhau, diaChi, trangThai FROM NhanVien WHERE trangThai = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, trangThai);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien(
                        rs.getInt("ID_chucVu"),
                        rs.getString("maNhanVien"),
                        rs.getString("hoTen"),
                        rs.getString("gioiTinh"),
                        rs.getDate("ngaySinh"),
                        rs.getString("soDT"),
                        rs.getString("email"),
                        rs.getString("matKhau"),
                        rs.getString("diaChi"),
                        rs.getString("trangThai")
                );
                list.add(nhanVien);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }

    private void closeConnection() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
