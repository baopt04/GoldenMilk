/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import model.Employee;

public class EmployeeService {

    public Employee login(String username, String password) {
        Connection con = DBConnect.getConnection();
        String sql = "select * from NhanVien where maNhanVien = ? and matKhau = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Nếu đăng nhập thành công, trả về đối tượng Employee
                return new Employee(
                    rs.getString("fullName"),
                    rs.getString("phoneNumber"),
                    rs.getString("email"),
                    rs.getString("gender"),
                    rs.getDate("birthDate"),
                    rs.getString("address")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy
    }
    

    public boolean updateEmployee(Employee employee) {
        Connection conn = DBConnect.getConnection();
        String query = "UPDATE NhanVien SET hoTen = ?, soDT = ?, email = ?, gioiTinh = ?, ngaySinh = ?, diaChi = ? WHERE maNhanVien = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, employee.getFullName());
            ps.setString(2, employee.getPhoneNumber());
            ps.setString(3, employee.getEmail());
            ps.setString(4, employee.getGender());
            ps.setDate(5, new java.sql.Date(employee.getBirthDate().getTime()));
            ps.setString(6, employee.getAddress());
            ps.setString(7, employee.getMaNV());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean validateEmail(String email) {
        // Kiểm tra định dạng email hợp lệ
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(emailRegex, email);
    }

    public boolean validatePhone(String phone) {
        // Kiểm tra định dạng số điện thoại hợp lệ (chỉ số, độ dài từ 10-11 số)
        String phoneRegex = "^\\d{10,11}$";
        return Pattern.matches(phoneRegex, phone);
    }

    public boolean isEmailOrPhoneExists(String email, String phone, String username) {
        System.out.println("email: " + email);
        System.out.println("phone: " + phone);
        System.out.println("username: " + username);
        Connection con = DBConnect.getConnection();
        String sql = "SELECT * FROM NhanVien WHERE (email = ? OR soDT = ?) AND maNhanVien != ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, phone);
            ps.setString(3, username);
            ResultSet rs = ps.executeQuery();
            return rs.next(); 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
//    public boolean isEmailExists(String email, String username) {
//    Connection con = DBConnect.getConnection();
//    String sql = "SELECT * FROM NhanVien WHERE email = ? AND maNhanVien != ?";
//    try {
//        PreparedStatement ps = con.prepareStatement(sql);
//        ps.setString(1, email);
//        ps.setString(2, username);
//        ResultSet rs = ps.executeQuery();
//        return rs.next();
//    } catch (SQLException ex) {
//        ex.printStackTrace();
//    }
//    return false;
//}
//
//    public boolean isPhoneExists(String phone, String username) {
//    Connection con = DBConnect.getConnection();
//    String sql = "SELECT * FROM NhanVien WHERE soDT = ? AND maNhanVien != ?";
//    try {
//        PreparedStatement ps = con.prepareStatement(sql);
//        ps.setString(1, phone);
//        ps.setString(2, username);
//        ResultSet rs = ps.executeQuery();
//        return rs.next();
//    } catch (SQLException ex) {
//        ex.printStackTrace();
//    }
//    return false;
//}

    public boolean isPasswordExists(String matkhau, String username) {
        Connection con = DBConnect.getConnection();
        String sql = "SELECT * FROM NhanVien WHERE matkhau = ? AND maNhanVien = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, matkhau);
            ps.setString(2, username);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Trả về true nếu tìm thấy tài khoản và mật khau đã tồn tại
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean updatePasswordEmployee(String matkhau, String username) {
        Connection conn = DBConnect.getConnection();
        String query = "UPDATE NhanVien SET matkhau = ? WHERE maNhanVien = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, matkhau);
            ps.setString(2, username);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

