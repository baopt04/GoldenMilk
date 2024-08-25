/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
<<<<<<< HEAD

=======
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.SanPhamChiTiet;
<<<<<<< HEAD

=======
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
/**
 *
 * @author Computer Bao
 */
public class SanPhamChiTietSerivce {
<<<<<<< HEAD

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<SanPhamChiTiet> getList() {
  sql = "SELECT " +
                         "    sp.maSanPham, " +
                         "    sp.tenSanPham, " +
                         "    ncc.tenNhaCungCap, " +
                         "    lsx.tenLoSanXuat, " +
                         "    hl.tenHamLuongDuong, " +
                         "    hv.tenHuongVi, " +
                         "    dvt.tenDonViTinh, " +
                         "    tt.tenTheTich, " +
                         "    lsx.NSX, " +
                         "    lsx.HSD, " +
                         "    spct.donGia, " +
                         "    spct.soLuongTon, " +
                         "    spct.anh " +
                         "FROM " +
                         "    SanPhamChiTiet spct " +
                         "JOIN " +
                         "    SanPham sp ON spct.ID_sanPham = sp.ID_sanPham " +
                         "JOIN " +
                         "    LoSanXuat lsx ON spct.ID_loSanXuat = lsx.ID_loSanXuat " +
                         "JOIN " +
                         "    HuongVi hv ON spct.ID_huongVi = hv.ID_huongVi " +
                         "JOIN " +
                         "    DonViTinh dvt ON spct.ID_donViTinh = dvt.ID_donViTinh " +
                         "JOIN " +
                         "    TheTich tt ON spct.ID_theTich = tt.ID_theTich " +
                         "JOIN " +
                         "    HamLuongDuong hl ON spct.ID_hamLuongDuong = hl.ID_hamLuongDuong " +
                         "JOIN " +
                         "    NhaCungCap ncc ON lsx.ID_nhaCungCap = ncc.ID_nhaCungCap";
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                       rs.getString(4) ,
                        rs.getString(7),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13));
                list.add(sanPhamChiTiet);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SanPhamChiTiet> timKiem(String ma) {
        sql = "SELECT \n"
                + "    sp.maSanPham,\n"
                + "    sp.tenSanPham,\n"
                + "    lsx.tenLoSanXuat,\n"
                + "    nsx.tenNhaCungCap,\n"
                + "    hl.tenHamLuongDuong,\n"
                + "    hv.tenHuongVi,\n"
                + "    dvt.tenDonViTinh,\n"
                + "    tt.tenTheTich,\n"
                +"lsx.NSX,\n"
                +"lsx.HSD,\n"
                + "    spct.donGia,\n"
                + "    spct.soLuongTon,\n"
                + "    spct.anh\n"
                + "FROM \n"
                + "    SanPhamChiTiet spct\n"
                + "JOIN \n"
                + "    SanPham sp ON spct.ID_sanPham = sp.ID_sanPham\n"
                + "JOIN \n"
                + "    LoSanXuat lsx ON spct.ID_loSanXuat = lsx.ID_loSanXuat\n"
                + "JOIN \n"
                + "    NhaCungCap nsx ON lsx.ID_nhaCungCap = nsx.ID_nhaCungCap\n"
                + "JOIN \n"
                + "    HuongVi hv ON spct.ID_huongVi = hv.ID_huongVi\n"
                + "JOIN \n"
                + "    DonViTinh dvt ON spct.ID_donViTinh = dvt.ID_donViTinh\n"
                + "JOIN \n"
                + "    TheTich tt ON spct.ID_theTich = tt.ID_theTich\n"
                + "JOIN \n"
                + "    HamLuongDuong hl ON spct.ID_hamLuongDuong = hl.ID_hamLuongDuong\n"
                + "WHERE \n"
                + "    sp.maSanPham LIKE ? OR sp.tenSanPham LIKE ?;";

        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + ma + "%");
            ps.setString(2, "%" + ma + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet(rs.getString(1),
                        rs.getString(2),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(3),
                        rs.getString(7),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13));
                list.add(sanPhamChiTiet);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<SanPhamChiTiet> timKiem1s(double giaMin, double giaMax) {
        // Câu lệnh SQL đã được cập nhật để tìm kiếm theo khoảng giá
        sql = "SELECT \n"
                + "    sp.maSanPham,\n"
                + "    sp.tenSanPham,\n"
                + "    lsx.tenLoSanXuat,\n"
                + "    nsx.tenNhaCungCap,\n"
                + "    hl.tenHamLuongDuong,\n"
                + "    hv.tenHuongVi,\n"
                + "    dvt.tenDonViTinh,\n"
                + "    tt.tenTheTich,\n"
                +"lsx.NSX,\n"
                +"lsx.HSD,\n"
                + "    spct.donGia,\n"
                + "    spct.soLuongTon,\n"
                + "    spct.anh\n"
                + "FROM \n"
                + "    SanPhamChiTiet spct\n"
                + "JOIN \n"
                + "    SanPham sp ON spct.ID_sanPham = sp.ID_sanPham\n"
                + "JOIN \n"
                + "    LoSanXuat lsx ON spct.ID_loSanXuat = lsx.ID_loSanXuat\n"
                + "JOIN \n"
                + "    NhaCungCap nsx ON lsx.ID_nhaCungCap = nsx.ID_nhaCungCap\n"
                + "JOIN \n"
                + "    HuongVi hv ON spct.ID_huongVi = hv.ID_huongVi\n"
                + "JOIN \n"
                + "    DonViTinh dvt ON spct.ID_donViTinh = dvt.ID_donViTinh\n"
                + "JOIN \n"
                + "    TheTich tt ON spct.ID_theTich = tt.ID_theTich\n"
                + "JOIN \n"
                + "    HamLuongDuong hl ON spct.ID_hamLuongDuong = hl.ID_hamLuongDuong\n"
                + "WHERE \n"
                + "    spct.donGia BETWEEN ? AND ?;";

        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, giaMin);
            ps.setDouble(2, giaMax);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet(rs.getString(1),
                        rs.getString(2),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(3),
                        rs.getString(7),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13));
                list.add(sanPhamChiTiet);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SanPhamChiTiet> getAll() {
        sql = "SELECT \n"
                + "    sp.maSanPham,\n"
                + "    sp.tenSanPham,\n"
                + "    lsp.tenLoaiSanPham,\n"
                + "    hsp.tenHangSanPham,\n"
                + "    lsx.tenLoSanXuat,\n"
                + "    nsx.tenNhaCungCap,\n"
                + "    dtsd.tenDoiTuongSD,\n"
                + "    hl.tenHamLuongDuong,\n"
                + "    hv.tenHuongVi,\n"
                + "    dvt.tenDonViTinh,\n"
                + "    tt.tenTheTich,\n"
                + "    spct.donGia,\n"
                + "    spct.soLuongTon,\n"
                + "    sp.moTa AS moTaSanPham,\n"
                + "    sp.trangthai AS trangThaiSanPham\n"
                + "FROM \n"
                + "    SanPham sp\n"
                + "JOIN \n"
                + "    LoaiSanPham lsp ON sp.ID_loaiSanPham = lsp.ID_loaiSanPham\n"
                + "JOIN \n"
                + "    HangSanPham hsp ON sp.ID_hangSanPham = hsp.ID_hangSanPham\n"
                + "JOIN \n"
                + "    SanPhamChiTiet spct ON sp.ID_sanPham = spct.ID_sanPham\n"
                + "JOIN \n"
                + "    LoSanXuat lsx ON spct.ID_loSanXuat = lsx.ID_loSanXuat\n"
                + "JOIN \n"
                + "    NhaCungCap nsx ON lsx.ID_nhaCungCap = nsx.ID_nhaCungCap\n"
                + "JOIN \n"
                + "    HuongVi hv ON spct.ID_huongVi = hv.ID_huongVi\n"
                + "JOIN \n"
                + "    DonViTinh dvt ON spct.ID_donViTinh = dvt.ID_donViTinh\n"
                + "JOIN \n"
                + "    TheTich tt ON spct.ID_theTich = tt.ID_theTich\n"
                + "JOIN \n"
                + "    HamLuongDuong hl ON spct.ID_hamLuongDuong = hl.ID_hamLuongDuong\n"
                + "JOIN \n"
                + "    DoiTuongSuDung dtsd ON sp.ID_doiTuongSD = dtsd.ID_doiTuongSD;";
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            Connection con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet(rs.getString(1),
                        rs.getString(2),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(11),
                        rs.getString(5),
                        rs.getString(10),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(14),
                        rs.getString(15));

//                SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet(rs.getString(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getString(4),
//                        rs.getString(5),
//                        rs.getString(6),
//                        rs.getString(7),
//                        rs.getString(8),
//                        rs.getString(9),
//                        rs.getString(10),
//                        rs.getString(11),
//                        rs.getString(13),
//                        rs.getString(14),
//                        rs.getString(15)
//                );
                list.add(sanPhamChiTiet);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
=======
   Connection con = null;
   PreparedStatement ps = null;
   ResultSet rs = null;
   String sql = null;
   public List<SanPhamChiTiet> getList(){
       String sql = "	SELECT \n" +
"    sp.maSanPham,\n" +
"    sp.tenSanPham,\n" +
"    lsp.tenLoaiSanPham,\n" +
"	 lsx.tenLoSanXuat,\n" +
"	ncc.tenNhaCungCap,\n" +
"    dtsd.tenDoiTuongSD,\n" +
"	hl.tenHamLuongDuong,\n" +
"	hv.tenHuongVi,\n" +
"	dvt.tenDonViTinh,\n" +
"	tt.tenTheTich,\n" +
"	hsp.tenHangSanPham,\n" +
"    spct.QRCode,\n" +
"	spct.donGia,\n" +
"	spct.soLuongTon,\n" +
"	  spct.moTa,\n" +
"    spct.anh\n" +
"   \n" +
"FROM \n" +
"    SanPhamChiTiet spct\n" +
"JOIN \n" +
"    SanPham sp ON spct.ID_sanPham = sp.ID_sanPham\n" +
"JOIN\n" +
"	HangSanPham hsp ON sp.ID_hangSanPham = hsp.ID_hangSanPham\n" +
"JOIN\n" +
"    LoaiSanPham lsp ON sp.ID_loaiSanPham = lsp.ID_loaiSanPham  \n" +
"JOIN \n" +
"    LoSanXuat lsx ON spct.ID_loSanXuat = lsx.ID_loSanXuat\n" +
"JOIN \n" +
"    HuongVi hv ON spct.ID_huongVi = hv.ID_huongVi\n" +
"JOIN \n" +
"    DoiTuongSuDung dtsd ON spct.ID_doiTuongSD = dtsd.ID_doiTuongSD\n" +
"JOIN \n" +
"    DonViTinh dvt ON spct.ID_donViTinh = dvt.ID_donViTinh\n" +
"JOIN \n" +
"NhaCungCap ncc ON dvt.ID_donViTinh = ncc.ID_nhaCungCap \n" +
"JOIN\n" +
"    TheTich tt ON spct.ID_theTich = tt.ID_theTich\n" +
"	JOIN\n" +
"	HamLuongDuong hl ON spct.ID_theTich = hl.ID_hamLuongDuong;";
       List<SanPhamChiTiet> list = new ArrayList<>();
       try {
           Connection con = DBconnect.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
          SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet(rs.getString(1), rs.getString(2),
                  rs.getString(3), rs.getString(4), rs.getString(5), 
                  rs.getString(6), rs.getString(7), rs.getString(8),
                  rs.getString(9), rs.getString(10), rs.getString(11),
                  rs.getString(12), rs.getString(13), rs.getString(14), 
                  rs.getString(15), rs.getString(16));
               list.add(sanPhamChiTiet);
           }
           return list;
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }
    public List<SanPhamChiTiet> timKiem(String ma) {
    String sql = "SELECT \n" +
        "    sp.maSanPham,\n" +
        "    sp.tenSanPham,\n" +
        "    lsp.tenLoaiSanPham,\n" +
        "    lsx.tenLoSanXuat,\n" +
        "    nsx.tenNhaCungCap,\n" + // đổi từ `ncc.tenNhaCungCap` sang `nsx.tenNhaSanXuat`
        "    dtsd.tenDoiTuongSD,\n" +
        "    hl.tenHamLuongDuong,\n" +
        "    hv.tenHuongVi,\n" +
        "    dvt.tenDonViTinh,\n" +
        "    tt.tenTheTich,\n" +
        "    hsp.tenHangSanPham,\n" +
        "    spct.QRCode,\n" +
        "    spct.donGia,\n" +
        "    spct.soLuongTon,\n" +
        "    spct.moTa,\n" +
        "    spct.anh\n" +
        "FROM \n" +
        "    SanPhamChiTiet spct\n" +
        "JOIN \n" +
        "    SanPham sp ON spct.ID_sanPham = sp.ID_sanPham\n" +
        "JOIN\n" +
        "    HangSanPham hsp ON sp.ID_hangSanPham = hsp.ID_hangSanPham\n" +
        "JOIN\n" +
        "    LoaiSanPham lsp ON sp.ID_loaiSanPham = lsp.ID_loaiSanPham\n" +
        "JOIN \n" +
        "    LoSanXuat lsx ON spct.ID_loSanXuat = lsx.ID_loSanXuat\n" +
        "JOIN \n" +
        "    NhaCungCap nsx ON lsx.ID_nhaCungCap = nsx.ID_nhaCungCap\n" + // đổi từ `NhaCungCap ncc` sang `NhaSanXuat nsx`
        "JOIN \n" +
        "    HuongVi hv ON spct.ID_huongVi = hv.ID_huongVi\n" +
        "JOIN \n" +
        "    DoiTuongSuDung dtsd ON spct.ID_doiTuongSD = dtsd.ID_doiTuongSD\n" +
        "JOIN \n" +
        "    DonViTinh dvt ON spct.ID_donViTinh = dvt.ID_donViTinh\n" +
        "JOIN \n" +
        "    TheTich tt ON spct.ID_theTich = tt.ID_theTich\n" +
        "JOIN\n" +
        "    HamLuongDuong hl ON spct.ID_hamLuongDuong = hl.ID_hamLuongDuong\n" +
        "WHERE \n" +
        "    sp.maSanPham LIKE ? OR sp.tenSanPham LIKE ?;";
    List<SanPhamChiTiet> list = new ArrayList<>();
    try {
        con = DBconnect.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1,"%" + ma + "%");
        ps.setString(2,"%" + ma + "%");
        rs = ps.executeQuery();
        while (rs.next()) {
              SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet(rs.getString(1), rs.getString(2),
                  rs.getString(3), rs.getString(4), rs.getString(5), 
                  rs.getString(6), rs.getString(7), rs.getString(8),
                  rs.getString(9), rs.getString(10), rs.getString(11),
                  rs.getString(12), rs.getString(13), rs.getString(14), 
                  rs.getString(15), rs.getString(16));
               list.add(sanPhamChiTiet);
        }
        return list;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
}
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
