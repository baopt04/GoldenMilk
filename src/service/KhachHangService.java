
package service;
//import ConnectDatabase.DBConnect;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
//import com.sun.jdi.connect.spi.Connection;
import model.KhachHangMD;

public class KhachHangService {
     private static Connection con = null;
    //chuan bi thuc hien lenh
    private PreparedStatement pr = null;
    // tap ket qua truy van
    private ResultSet rs = null;
    //cau lenh sql
    private String sql = null;
    
    public KhachHangService (){
        con= DBConnect.getConnection();
    }
    public ArrayList<KhachHangMD> getAll() {
        //lay tat ca du lieu tu bang mylove trong sql server
        //Đổ vào list 
        sql = "select maKhachHang , hoTen, gioiTinh, soDT, email, diaChi from KhachHang";

        ArrayList list_ThuCung = new ArrayList<>();
        try {
            //  con = MVC_ThuCung.DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();//chay selec ra ket qua
            //tra ve 1 tap: rs
            while (rs.next()) {//chay tu dau den cuoi tap rs
                String MaKH = rs.getString("maKhachHang");
                String hoTen= rs.getString("hoTen");
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                String soDT = rs.getString("soDT");
                String email = rs.getString("email");
                String diaChi = rs.getString("diaChi");
                KhachHangMD ml = new KhachHangMD(MaKH, hoTen, gioiTinh, soDT, email, diaChi);
                list_ThuCung.add(ml);

            }//dong while
            return list_ThuCung;
        } catch (Exception e) {
            e.printStackTrace();//in ra loi
            return null;
        }

    }
    public int them(KhachHangMD kh) {
        sql = "INSERT INTO KhachHang (maKhachHang, hoTen,soDT,  email, gioiTinh, diaChi) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            pr = con.prepareStatement(sql);
            pr.setObject(1, kh.getMaKH());
            pr.setObject(2, kh.getHoTen());
            pr.setObject(5, kh.isGioiTinh());
            pr.setObject(3, kh.getSoDT());
            pr.setObject(4, kh.getEmail());
            pr.setObject(6, kh.getDiaChi());
            return pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int sua(String ma, KhachHangMD kh) {
        sql = "update KhachHang set  hoTen=?,soDT=?,email=?,gioiTinh=?,diaChi=? where maKhachHang=?";
        System.out.println(kh.toString());
        try {//sua duoc
            pr = con.prepareStatement(sql);
           // pr.setObject(1, kh.getMaKH());//so 1=? thu nhat= ho ten
            pr.setObject(1, kh.getHoTen());
            pr.setObject(2, kh.getSoDT());
            pr.setObject(3, kh.getEmail());
            pr.setObject(4, kh.isGioiTinh());
            pr.setObject(5, kh.getDiaChi());
            pr.setObject(6, ma);//id duoc truyen vao
            return pr.executeUpdate();

        } catch (Exception e) {//ko sua duoc
            e.printStackTrace();
            return 0;
        }
    }
        public KhachHangMD getKH_ToThemNhanhBanHang(String maKH) {//Mạnh Thêm
        sql = "SELECT maKhachHang, hoTen,soDT,  email, gioiTinh, diaChi FROM KhachHang WHERE maKhachHang = ?";
        KhachHangMD kh = null;
        try {
            con = DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            pr.setObject(1, maKH);
            rs = pr.executeQuery();
            while (rs.next()) {
                kh = new KhachHangMD();
             //   kh.setId(rs.getInt("ID"));
                kh.setMaKH(rs.getString("maKH"));
                kh.setHoTen(rs.getString("hoTen"));
            }
            return kh;
        } catch (SQLException e) {
            return null;
        }
    }
        public ArrayList<KhachHangMD> timKiem(String sDTCanTim) {

        sql = "select maKhachHang,hoTen, soDT,email,diaChi from KhachHang WHERE soDT like ?";
        ArrayList list_KhachHang = new ArrayList<>();
        try {
            //  con = MVC_ThuCung.DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            pr.setObject(1,'%'+ sDTCanTim+'%');
            rs = pr.executeQuery();//chay selec ra ket qua
            //tra ve 1 tap: rs
            while (rs.next()) {//chay tu dau den cuoi tap rs
                
                String maKH= rs.getString("maKhachHang");
                String hoTen= rs.getString("hoTen");
                String soDT = rs.getString("soDT");
                String email= rs.getString("email");
                String diaChi= rs.getString("diaChi");
                KhachHangMD ml = new KhachHangMD(maKH, hoTen, soDT, email, diaChi);
                list_KhachHang.add(ml);

            }//dong while
            return list_KhachHang;
        } catch (Exception e) {
            e.printStackTrace();//in ra loi
            return null;
        }
    }
        public int xoa(String  maKH) {
        sql = "delete from KhachHang where maKhachHang=?";
        try {
            //  con=DBConnect.getConnection();//luon co
            pr = con.prepareStatement(sql);//luon co
            pr.setObject(1, maKH);
            return pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
