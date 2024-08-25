
package service;

//import ConnectDatabase.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import model.HDCT_MD;
import model.HoaDonMD;
//import view.DBConnect1;


public class HoaDonService {
         private static Connection con = null;
    //chuan bi thuc hien lenh
    private PreparedStatement pr = null;
    // tap ket qua truy van
    private ResultSet rs = null;
    //cau lenh sql
    private String sql = null;
    
    public HoaDonService (){
        con= DBConnect.getConnection();
    } 
    
    public ArrayList<HoaDonMD> getAll() {
        //lay tat ca du lieu tu bang mylove trong sql server
        //Đổ vào list 
        sql = "select h.ID_hoaDon , k.hoTen, k.soDT, h.giaTriThanhToan, h.trangThaiHoaDon,h.ngayTao,n.hoTen as nguoiTao  from HoaDon h \n" +
"join KhachHang k on h.ID_khachHang=k.ID_khachHang \n" +
"join NhanVien n on h.ID_nhanVien=n.ID_nhanVien where h.trangThaiHoaDon=N'Đã thanh toán'";

        ArrayList list_HoaDon = new ArrayList<>();
        try {
            //  con = MVC_ThuCung.DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();//chay selec ra ket qua
            //tra ve 1 tap: rs
            while (rs.next()) {//chay tu dau den cuoi tap rs
                int idHD = rs.getInt("ID_hoaDon");
                String hoTen= rs.getString("hoTen");
                String soDT = rs.getString("soDT");
                float thanhTien = rs.getFloat("giaTriThanhToan");
                String trangThai = rs.getString("trangThaiHoaDon");
                Date ngayTao = rs.getDate("ngayTao");
                String nguoiTao = rs.getString("nguoiTao");
                HoaDonMD ml = new HoaDonMD(idHD, hoTen, soDT, thanhTien, trangThai, ngayTao, nguoiTao);
                list_HoaDon.add(ml);

            }//dong while
            return list_HoaDon;
        } catch (Exception e) {
            e.printStackTrace();//in ra loi
            return null;
        }

    }
     public ArrayList<HoaDonMD> timKiemHD_by_sdtKH(String sDTCanTim) {

        sql = "select h.ID_hoaDon , k.hoTen, k.soDT, h.giaTriThanhToan, h.trangThaiHoaDon,h.ngayTao,n.hoTen as nguoiTao  from HoaDon h \n" +
"join KhachHang k on h.ID_khachHang=k.ID_khachHang \n" +
"join NhanVien n on h.ID_nhanVien=n.ID_nhanVien where h.trangThaiHoaDon=N'Đã thanh toán' and k.soDT like ?";
        ArrayList list_HoaDon = new ArrayList<>();
        try {
            //  con = MVC_ThuCung.DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            pr.setObject(1,'%'+ sDTCanTim+'%');
            rs = pr.executeQuery();//chay selec ra ket qua
            //tra ve 1 tap: rs
            while (rs.next()) {//chay tu dau den cuoi tap rs
                int idHD = rs.getInt("ID_hoaDon");
                String hoTen= rs.getString("hoTen");
                String soDT = rs.getString("soDT");
                float thanhTien = rs.getFloat("giaTriThanhToan");
                String trangThai = rs.getString("trangThaiHoaDon");
                Date ngayTao = rs.getDate("ngayTao");
                String nguoiTao = rs.getString("nguoiTao");
                HoaDonMD ml = new HoaDonMD(idHD, hoTen, soDT, thanhTien, trangThai, ngayTao, nguoiTao);
                list_HoaDon.add(ml);

            }//dong while
            return list_HoaDon;
        } catch (Exception e) {
            e.printStackTrace();//in ra loi
            return null;
        }
    }
          public ArrayList<HoaDonMD> timKiemHD_by_NgTaoHoaDon(String tenNgTao) {

        sql = "select h.ID_hoaDon , k.hoTen, k.soDT, h.giaTriThanhToan, h.trangThaiHoaDon,h.ngayTao,n.hoTen as nguoiTao  from HoaDon h \n" +
"join KhachHang k on h.ID_khachHang=k.ID_khachHang \n" +
"join NhanVien n on h.ID_nhanVien=n.ID_nhanVien where h.trangThaiHoaDon=N'Đã thanh toán' and n.hoTen like ?";
        ArrayList list_HoaDon = new ArrayList<>();
        try {
            //  con = MVC_ThuCung.DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            pr.setObject(1,'%'+ tenNgTao+'%');
            rs = pr.executeQuery();//chay selec ra ket qua
            //tra ve 1 tap: rs
            while (rs.next()) {//chay tu dau den cuoi tap rs
                int idHD = rs.getInt("ID_hoaDon");
                String hoTen= rs.getString("hoTen");
                String soDT = rs.getString("soDT");
                float thanhTien = rs.getFloat("giaTriThanhToan");
                String trangThai = rs.getString("trangThaiHoaDon");
                Date ngayTao = rs.getDate("ngayTao");
                String nguoiTao = rs.getString("nguoiTao");
                HoaDonMD ml = new HoaDonMD(idHD, hoTen, soDT, thanhTien, trangThai, ngayTao, nguoiTao);
                list_HoaDon.add(ml);

            }//dong while
            return list_HoaDon;
        } catch (Exception e) {
            e.printStackTrace();//in ra loi
            return null;
        }
    }
         public ArrayList<HoaDonMD> timKiemHD_by_NgayTaoHoaDon(String ngayTaoCanTim) {

        sql = "select h.ID_hoaDon , k.hoTen, k.soDT, h.giaTriThanhToan, h.trangThaiHoaDon,h.ngayTao,n.hoTen as nguoiTao  from HoaDon h \n" +
"join KhachHang k on h.ID_khachHang=k.ID_khachHang \n" +
"join NhanVien n on h.ID_nhanVien=n.ID_nhanVien where h.trangThaiHoaDon=N'Đã thanh toán' and h.ngayTao like ?";
        ArrayList list_HoaDon = new ArrayList<>();
        try {
            //  con = MVC_ThuCung.DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            pr.setObject(1,'%'+ ngayTaoCanTim+'%');
            rs = pr.executeQuery();//chay selec ra ket qua
            //tra ve 1 tap: rs
            while (rs.next()) {//chay tu dau den cuoi tap rs
                int idHD = rs.getInt("ID_hoaDon");
                String hoTen= rs.getString("hoTen");
                String soDT = rs.getString("soDT");
                float thanhTien = rs.getFloat("giaTriThanhToan");
                String trangThai = rs.getString("trangThaiHoaDon");
                Date ngayTao = rs.getDate("ngayTao");
                String nguoiTao = rs.getString("nguoiTao");
                HoaDonMD ml = new HoaDonMD(idHD, hoTen, soDT, thanhTien, trangThai, ngayTao, nguoiTao);
                list_HoaDon.add(ml);

            }//dong while
            return list_HoaDon;
        } catch (Exception e) {
            e.printStackTrace();//in ra loi
            return null;
        }
    }
    
    public ArrayList<HoaDonMD> timKiemHD_by_idHD(String idHD_canTim) {

        sql = "select h.ID_hoaDon , k.hoTen, k.soDT, h.giaTriThanhToan, h.trangThaiHoaDon,h.ngayTao,n.hoTen as nguoiTao  from HoaDon h \n" +
"join KhachHang k on h.ID_khachHang=k.ID_khachHang \n" +
"join NhanVien n on h.ID_nhanVien=n.ID_nhanVien where h.trangThaiHoaDon=N'Đã thanh toán' and h.ID_hoaDon like ?";
        ArrayList list_HoaDon = new ArrayList<>();
        try {
            //  con = MVC_ThuCung.DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            pr.setObject(1,'%'+ idHD_canTim+'%');
            rs = pr.executeQuery();//chay selec ra ket qua
            //tra ve 1 tap: rs
            while (rs.next()) {//chay tu dau den cuoi tap rs
                int idHD = rs.getInt("ID_hoaDon");
                String hoTen= rs.getString("hoTen");
                String soDT = rs.getString("soDT");
                float thanhTien = rs.getFloat("giaTriThanhToan");
                String trangThai = rs.getString("trangThaiHoaDon");
                Date ngayTao = rs.getDate("ngayTao");
                String nguoiTao = rs.getString("nguoiTao");
                HoaDonMD ml = new HoaDonMD(idHD, hoTen, soDT, thanhTien, trangThai, ngayTao, nguoiTao);
                list_HoaDon.add(ml);

            }//dong while
            return list_HoaDon;
        } catch (Exception e) {
            e.printStackTrace();//in ra loi
            return null;
        }
    }
    public ArrayList<HDCT_MD> timKiem(int idHD) {

        sql = "select spct.maSanPhamChiTiet, sp.tenSanPham, hdct.soLuong, hdct.donGia, hdct.tongTien ,hdct.giaTriVoucher, hdct.giaTriThanhToan from HoaDonChiTiet hdct \n" +
"join SanPhamChiTiet spct on hdct.ID_sanPhamChiTiet=spct.ID_sanPhamChiTiet \n" +
"join SanPham sp on sp.ID_sanPham= spct.ID_sanPham\n" +
"join HoaDon hd on hd.ID_hoaDon=hdct.ID_hoaDon where hd.ID_hoaDon= " +idHD;
        
        ArrayList list_HDCT = new ArrayList<>();
        try {
            //  con = MVC_ThuCung.DBConnect.getConnection();
            pr = con.prepareStatement(sql);
//            pr.setObject(1,idHD);
           // System.out.println(sql);
            rs = pr.executeQuery();//chay selec ra ket qua
            //tra ve 1 tap: rs
            while (rs.next()) {//chay tu dau den cuoi tap rs
                

                String maSPCT= rs.getString("maSanPhamChiTiet");
            //    boolean gioiTinh = rs.getBoolean("gioiTinh");
                String tenSP = rs.getString("tenSanPham");
                int soLuong = rs.getInt("soLuong");
       //         String soDT = rs.getString("soDT");
                float donGia = rs.getFloat("donGia");
                float tongTien = rs.getFloat("tongTien");
                float giaTriVoucher = rs.getFloat("giaTriVoucher");
                float giaTriThanhToan = rs.getFloat("giaTriThanhToan");
                HDCT_MD ml = new HDCT_MD(maSPCT, tenSP, soLuong, donGia, tongTien, giaTriVoucher, giaTriThanhToan);
                list_HDCT.add(ml);

            }
            return list_HDCT;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<HDCT_MD> fillHDCT() {
        //lay tat ca du lieu tu bang mylove trong sql server
        //Đổ vào list 
        sql = "select spct.maSanPhamChiTiet, sp.tenSanPham, hdct.soLuong, hdct.donGia, hdct.tongTien ,hdct.giaTriVoucher, hdct.giaTriThanhToan from HoaDonChiTiet hdct \n" +
"join SanPhamChiTiet spct on hdct.ID_sanPhamChiTiet=spct.ID_sanPhamChiTiet \n" +
"join SanPham sp on sp.ID_sanPham= spct.ID_sanPham\n" +
"join HoaDon hd on hd.ID_hoaDon=hdct.ID_hoaDon";

        ArrayList list_HDCT = new ArrayList<>();
        try {
            //  con = MVC_ThuCung.DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();//chay selec ra ket qua
            //tra ve 1 tap: rs
            while (rs.next()) {//chay tu dau den cuoi tap rs
                String maSPCT= rs.getString("maSanPhamChiTiet");
            //    boolean gioiTinh = rs.getBoolean("gioiTinh");
                String tenSP = rs.getString("tenSanPham");
                int soLuong = rs.getInt("soLuong");
       //         String soDT = rs.getString("soDT");
                float donGia = rs.getFloat("donGia");
                float tongTien = rs.getFloat("tongTien");
                float giaTriVoucher = rs.getFloat("giaTriVoucher");
                float giaTriThanhToan = rs.getFloat("giaTriThanhToan");
                
                HDCT_MD ml = new HDCT_MD(maSPCT, tenSP, soLuong, donGia, tongTien, giaTriVoucher, giaTriThanhToan);
                list_HDCT.add(ml);

            }//dong while
            return list_HDCT;
        } catch (Exception e) {
            e.printStackTrace();//in ra loi
            return null;
        }

    }
    
}
