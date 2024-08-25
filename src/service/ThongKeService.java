/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.HoaDonMD;
import model.ThongKe;
/**
 *
 * @author Computer Bao
 */
public class ThongKeService {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";
     public ThongKe getThongKe() {
    ThongKe thongKe = new ThongKe();
    String sql = "SELECT SUM(giaTriThanhToan) AS TongGiaTriThanhToan FROM HoaDon";

    try (Connection con = DBConnect.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            thongKe.setGiaTriThanhToan(rs.getFloat(1));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return thongKe;
}
  public int getTongSoLuongHoaDonsDaThanhToan() {
        int soLuong = 0;
         sql = "SELECT COUNT(*) AS SoLuongHoaDonDaThanhToan FROM HoaDon WHERE trangThaiHoaDon = N'Đã thanh toán'";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                soLuong = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return soLuong;
    }
    public int getTongSoLuongHoaDonsChuaThanhToan() {
        int soLuong = 0;
         sql = "SELECT COUNT(*) AS SoLuongHoaDonDaThanhToan FROM HoaDon WHERE trangThaiHoaDon = N'Chờ thanh toán'";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                soLuong = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return soLuong;
    }
     public int getTongSoLuongKhachHang() {
        int soLuong = 0;
        String sql = "SELECT COUNT(*) AS TongSoLuongKhachHang FROM KhachHang";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                soLuong = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return soLuong;
    }
         public List<ThongKe> getDanhSachHoaDons() {
        List<ThongKe> thongKes = new ArrayList<>();
         sql = "SELECT ID_hoaDon, ngayTao, giaTriThanhToan FROM HoaDon WHERE trangThaiHoaDon = N'Đã thanh toán'";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ThongKe thongKe = new ThongKe();
                thongKe.setId_hoaDon(rs.getInt("ID_hoaDon"));
                thongKe.setNgayTao(rs.getDate("ngayTao"));
                thongKe.setGiaTriThanhToan(rs.getFloat("giaTriThanhToan"));
                thongKes.add(thongKe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return thongKes;
    }
            public Map<String, Double> getDoanhThuData() {
        Map<String, Double> doanhThuMap = new HashMap<>();

        try (
             Connection  con = DBConnect.getConnection();
             Statement stmt = con.createStatement()) {

            String query = "SELECT ngayTao, giaTriThanhToan FROM HoaDon";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String ngayTao = rs.getDate("ngayTao").toString();
                double giaTriThanhToan = rs.getDouble("giaTriThanhToan");
                doanhThuMap.put(ngayTao, giaTriThanhToan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doanhThuMap;
    }
          public List<ThongKe> getHoaDonByYear(int year) {
        List<ThongKe> thongKes = new ArrayList<>();
        
        String sql = "SELECT * FROM HoaDon WHERE YEAR(ngayTao) = ?";
        
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, year);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("ID_hoaDon");
                Float giaTriThanhToan = resultSet.getFloat("giaTriThanhToan");
                Date ngayTao = resultSet.getDate("ngayTao");
                
                // Tạo đối tượng ThongKe và thêm vào danh sách
                ThongKe thongKe = new ThongKe(id, giaTriThanhToan, ngayTao);
                thongKes.add(thongKe);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return thongKes;
    }
              public List<ThongKe> getHoaDonByMonth(int month) {
        List<ThongKe> thongKes = new ArrayList<>();
        
        String sql = "SELECT * FROM HoaDon WHERE MONTH(ngayTao) = ?";
        
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, month);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("ID_hoaDon");
                Float giaTriThanhToan = resultSet.getFloat("giaTriThanhToan");
                Date ngayTao = resultSet.getDate("ngayTao");
                
                // Tạo đối tượng ThongKe và thêm vào danh sách
                ThongKe thongKe = new ThongKe(id, giaTriThanhToan, ngayTao);
                thongKes.add(thongKe);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return thongKes;
    }
               public List<ThongKe> getHoaDonByDateRange(java.util.Date startDate, java.util.Date endDate) {
        List<ThongKe> thongKes = new ArrayList<>();

        String sql = "SELECT * FROM HoaDon WHERE ngayTao BETWEEN ? AND ?";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Chuyển đổi java.util.Date thành java.sql.Date
            statement.setDate(1, new java.sql.Date(startDate.getTime()));
            statement.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID_hoaDon");
                Float giaTriThanhToan = resultSet.getFloat("giaTriThanhToan");
                Date ngayTao = resultSet.getDate("ngayTao");

                ThongKe thongKe = new ThongKe(id, giaTriThanhToan, ngayTao);
                thongKes.add(thongKe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return thongKes;
    }
} 

