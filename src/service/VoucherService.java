package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Voucher;

public class VoucherService {

    public List<Voucher> getAllVouchers() {
        List<Voucher> list = new ArrayList<>();
        String sql = "SELECT * FROM Voucher";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Voucher voucher = new Voucher();
                voucher.setId(rs.getInt("ID_voucher"));
                voucher.setMaVoucher(rs.getString("maVoucher"));
                voucher.setTenVoucher(rs.getString("tenVoucher"));
                voucher.setGiaTriVoucher(rs.getFloat("giaTriVoucher"));
                voucher.setGiaTriApDungVoucher(rs.getFloat("giaTriApDungVoucher"));
                voucher.setSoLuongVoucher(rs.getInt("soLuongVoucher"));
                voucher.setNgayBatDau(rs.getDate("ngayBatDau"));
                voucher.setNgayKetThuc(rs.getDate("ngayKetThuc"));
                voucher.setTrangThai(rs.getString("trangThai"));
                voucher.setGhiChu(rs.getString("ghiChu"));

                // Kiểm tra và cập nhật trạng thái nếu cần
                if (voucher.getNgayKetThuc().before(new java.util.Date())) {
                    voucher.setTrangThai("Ngừng hoạt động");
                    updateVoucherStatus(voucher.getId(), "Ngừng hoạt động");
                }

                list.add(voucher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Voucher> getAllVouchersSearch(String search) {
        List<Voucher> list = new ArrayList<>();
        String sql = "SELECT * FROM Voucher where maVoucher like ? or tenVoucher like ?";
        try (Connection con = DBConnect.getConnection()){
            PreparedStatement ps = con.prepareStatement(sql); 
            ps.setString(1, "%"+search+"%");     
            ps.setString(2, "%"+search+"%");
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Voucher voucher = new Voucher();
                voucher.setId(rs.getInt("ID_voucher"));
                voucher.setMaVoucher(rs.getString("maVoucher"));
                voucher.setTenVoucher(rs.getString("tenVoucher"));
                voucher.setGiaTriVoucher(rs.getFloat("giaTriVoucher"));
                voucher.setGiaTriApDungVoucher(rs.getFloat("giaTriApDungVoucher"));
                voucher.setSoLuongVoucher(rs.getInt("soLuongVoucher"));
                voucher.setNgayBatDau(rs.getDate("ngayBatDau"));
                voucher.setNgayKetThuc(rs.getDate("ngayKetThuc"));
                voucher.setTrangThai(rs.getString("trangThai"));
                voucher.setGhiChu(rs.getString("ghiChu"));

                // Kiểm tra và cập nhật trạng thái nếu cần
                if (voucher.getNgayKetThuc().before(new java.util.Date())) {
                    voucher.setTrangThai("Ngừng hoạt động");
                    updateVoucherStatus(voucher.getId(), "Ngừng hoạt động");
                }

                list.add(voucher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int updateVoucherStatus(int id, String newStatus) {
        String sql = "UPDATE Voucher SET trangThai = ? WHERE ID_voucher = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Voucher> searchVouchers(String tenVoucher, Date fromDate, Date toDate, String trangThai) {
        List<Voucher> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Voucher WHERE 1=1");

        if (tenVoucher != null && !tenVoucher.isEmpty()) {
            sql.append(" AND tenVoucher LIKE ?");
        }
        if (fromDate != null) {
            sql.append(" AND ngayBatDau >= ?");
        }
        if (toDate != null) {
            sql.append(" AND ngayKetThuc <= ?");
        }
        if (trangThai != null && !trangThai.equals("Tất cả")) {
            sql.append(" AND trangThai = ?");
        }

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (tenVoucher != null && !tenVoucher.isEmpty()) {
                ps.setString(paramIndex++, "%" + tenVoucher + "%");
            }
            if (fromDate != null) {
                ps.setDate(paramIndex++, fromDate);
            }
            if (toDate != null) {
                ps.setDate(paramIndex++, toDate);
            }
            if (trangThai != null && !trangThai.equals("Tất cả")) {
                ps.setString(paramIndex++, trangThai);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Voucher voucher = new Voucher();
                voucher.setId(rs.getInt("ID_voucher"));
                voucher.setMaVoucher(rs.getString("maVoucher"));
                voucher.setTenVoucher(rs.getString("tenVoucher"));
                voucher.setGiaTriVoucher(rs.getFloat("giaTriVoucher"));
                voucher.setGiaTriApDungVoucher(rs.getFloat("giaTriApDungVoucher"));
                voucher.setSoLuongVoucher(rs.getInt("soLuongVoucher"));
                voucher.setNgayBatDau(rs.getDate("ngayBatDau"));
                voucher.setNgayKetThuc(rs.getDate("ngayKetThuc"));
                voucher.setTrangThai(rs.getString("trangThai"));
                voucher.setGhiChu(rs.getString("ghiChu")); // New field for notes
                list.add(voucher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int addVoucher(Voucher voucher) {
        String sql = "INSERT INTO Voucher (maVoucher, tenVoucher, giaTriVoucher, giaTriApDungVoucher, soLuongVoucher, ngayBatDau, ngayKetThuc, trangThai, ghiChu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, voucher.getMaVoucher());
            ps.setString(2, voucher.getTenVoucher());
            ps.setFloat(3, voucher.getGiaTriVoucher());
            ps.setFloat(4, voucher.getGiaTriApDungVoucher());
            ps.setInt(5, voucher.getSoLuongVoucher());
            ps.setDate(6, voucher.getNgayBatDau());
            ps.setDate(7, voucher.getNgayKetThuc());
            ps.setString(8, voucher.getTrangThai());
            ps.setString(9, voucher.getGhiChu()); // New field for notes
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateVoucher(Voucher voucher) {
        String sql = "UPDATE Voucher SET maVoucher = ?, tenVoucher = ?, giaTriVoucher = ?, giaTriApDungVoucher = ?, soLuongVoucher = ?, ngayBatDau = ?, ngayKetThuc = ?, trangThai = ?, ghiChu = ? WHERE ID_voucher = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, voucher.getMaVoucher());
            ps.setString(2, voucher.getTenVoucher());
            ps.setFloat(3, voucher.getGiaTriVoucher());
            ps.setFloat(4, voucher.getGiaTriApDungVoucher());
            ps.setInt(5, voucher.getSoLuongVoucher());
            ps.setDate(6, voucher.getNgayBatDau());
            ps.setDate(7, voucher.getNgayKetThuc());
            ps.setString(8, voucher.getTrangThai());
            ps.setString(9, voucher.getGhiChu()); // New field for notes
            ps.setInt(10, voucher.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteVoucher(int id) {
        String sql = "DELETE FROM Voucher WHERE ID_voucher = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean isMaVoucherExists(String maVoucher) {
        String sql = "SELECT COUNT(*) FROM Voucher WHERE maVoucher = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maVoucher);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isTenVoucherExists(String tenVoucher) {
        String sql = "SELECT COUNT(*) FROM Voucher WHERE tenVoucher = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenVoucher);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isTenVoucherExistsUpdate(String tenVoucher, String idVoucher) {
        String sql = "SELECT COUNT(*) FROM Voucher WHERE tenVoucher = ? and ID_voucher != ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenVoucher);
            ps.setObject(2, idVoucher);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
