/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.sql.*;
/**
 *
 * @author Computer Bao
 */
public class LoginService {
    Connection con= null;
    PreparedStatement ps = null;
    ResultSet rs=  null;
    String sql = null;
    public  boolean checkLogin(String email , String password){
        sql = "select * from nhanvien where email = ? and matkhau = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while(rs.next()){
             return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
