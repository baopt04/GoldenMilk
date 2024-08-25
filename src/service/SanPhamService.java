/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
<<<<<<< HEAD

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import model.SanPham;
import model.SanPhamChiTiet;

=======
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.SanPham;
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
/**
 *
 * @author Computer Bao
 */
public class SanPhamService {
<<<<<<< HEAD

=======
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
<<<<<<< HEAD

    public List<SanPham> getList() {
        sql = "SELECT \n"
           + "    s.maSanPham, \n"
           + "    s.tenSanPham, \n"
           + "    l.tenLoaiSanPham, \n"
           + "    h.tenHangSanPham, \n"
           + "    d.tenDoiTuongSD, \n"
           + "    s.trangthai, \n"
           + "    s.moTa AS mota \n"
           + "FROM \n"
           + "    SanPham s \n"
           + "    JOIN LoaiSanPham l ON s.ID_loaiSanPham = l.ID_loaiSanPham \n"
           + "    JOIN HangSanPham h ON s.ID_hangSanPham = h.ID_hangSanPham \n"
           + "    JOIN DoiTuongSuDung d ON s.ID_doiTuongSD = d.ID_doiTuongSD";

        List<SanPham> list = new ArrayList<>();
        try {
            Connection con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(7),
                        rs.getString(6));
                list.add(sanPham);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    public List<SanPham> getList1() {
      sql = "SELECT \n"
           + "    s.maSanPham, \n"
           + "    s.tenSanPham, \n"
           + "    l.tenLoaiSanPham, \n"
           + "    h.tenHangSanPham, \n"
           + "    d.tenDoiTuongSD, \n"
           + "    s.trangthai, \n"
           + "    s.moTa AS mota \n"
           + "FROM \n"
           + "    SanPham s \n"
           + "    JOIN LoaiSanPham l ON s.ID_loaiSanPham = l.ID_loaiSanPham \n"
           + "    JOIN HangSanPham h ON s.ID_hangSanPham = h.ID_hangSanPham \n"
           + "    JOIN DoiTuongSuDung d ON s.ID_doiTuongSD = d.ID_doiTuongSD";
    List<SanPham> list = new ArrayList<>();
    try {
        Connection con = DBConnect.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            SanPham sanPham = new SanPham(rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(7),
                    rs.getString(6));
=======
public List<SanPham> getList() {
    String sql = "SELECT \n" +
"    s.maSanPham,\n" +
"    s.tenSanPham,\n" +
"    l.tenLoaiSanPham,\n" +
"    h.tenHangSanPham,\n" +
"    s.trangThai,\n" +
"    s.mota\n" +            
"FROM \n" +
"    SanPham s\n" +
"    JOIN LoaiSanPham l ON s.id_loaiSanPham = l.id_loaiSanPham\n" +
"    JOIN HangSanPham h ON s.id_hangSanPham = h.id_hangSanPham";
    System.out.println(sql);
    List<SanPham> list = new ArrayList<>();
    try {
        Connection con = DBconnect.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            SanPham sanPham = new SanPham(rs.getString(1),
                   rs.getString(2),
                   rs.getString(3), 
                   rs.getString(4), 
                   rs.getString(6),
            rs.getString(5));
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            list.add(sanPham);
        }
        return list;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
<<<<<<< HEAD
}

  public boolean isMaSanPhamExists(String maSanPham) {
      maSanPham = maSanPham.trim().toLowerCase();
    sql = "SELECT COUNT(*) FROM SanPham WHERE LOWER(maSanPham) = ?";
    try (Connection conn = DBConnect.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, maSanPham);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
  public boolean isTenSanPhamExists(String tenSanPham) {
        tenSanPham = tenSanPham.trim().toLowerCase();
    sql = "SELECT COUNT(*) FROM SanPham WHERE LOWER(tenSanPham) = ?";
    try (Connection conn = DBConnect.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, tenSanPham);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

public String getLastSanPhamChiTietCode() {
    String sql = "SELECT TOP 1 maSanPhamChiTiet FROM SanPhamChiTiet ORDER BY maSanPhamChiTiet DESC";
    try {
        con = DBConnect.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("maSanPhamChiTiet");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } 
    return null;
}
public String generateNewSanPhamChiTietCode() {
    String lastCode = getLastSanPhamChiTietCode();
    if (lastCode != null && lastCode.startsWith("SP")) {
        String prefix = "SP";
        String numberPart = lastCode.substring(prefix.length());
        int lastNumber = Integer.parseInt(numberPart);
        int newNumber = lastNumber + 1;
        return prefix + String.format("%02d", newNumber); 
    } else {
        return "SP06"; 
    }
}

   

    public int addSP(SanPham sanPham, SanPhamChiTiet sanPhamChiTiet) {
        String sqlSanPham = "INSERT INTO SanPham (ID_loaiSanPham, ID_hangSanPham,ID_doiTuongSD, maSanPham, tenSanPham, moTa, trangthai) VALUES (?,?, ?, ?, ?, ?, ?)";
        String sqlSanPhamChiTiet = "INSERT INTO SanPhamChiTiet (ID_sanPham, ID_loSanXuat, ID_huongVi, ID_donViTinh, ID_theTich, ID_hamLuongDuong,maSanPhamChiTiet, soLuongTon, donGia) VALUES ( ?,?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            int id_loai = getLoaiByIdName(sanPham.getTenLoaiSanPham());
            int id_hang = getHangByIDName(sanPham.getTenhangSanPham());
            int id_doiTuongSd = getDoiTuongSDByIdName(sanPham.getTenDoiTuongSD());
            int id_loSX = getLoSXByIdName(sanPhamChiTiet.getTenLoSanXuat());
            int id_huongVi = getHuongViByIdName(sanPhamChiTiet.getTenHuongVi());
            int id_donViTinh = getDonViTinhByIdName(sanPhamChiTiet.getTenDonViTinh());
            int id_theTich = getDonViTinhByIdName(sanPhamChiTiet.getTenDonViTinh());
            int id_hamLuong = getHamLuongDuongByIdName(sanPhamChiTiet.getTenHamLuongDuong());
            con = DBConnect.getConnection();

            ps = con.prepareStatement(sqlSanPham, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, id_loai);
            ps.setObject(2, id_hang);
            ps.setObject(3, id_doiTuongSd);
            ps.setObject(4, sanPham.getMaSanPham());
            ps.setObject(5, sanPham.getTenSanPham());
            ps.setObject(6, sanPham.getGhiChu());
            ps.setObject(7, sanPham.getTrangThai());
            int reu = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idSanPham = rs.getInt(1);
                 String newMaSanPhamChiTiet = generateNewSanPhamChiTietCode();
                ps = con.prepareStatement(sqlSanPhamChiTiet);
                ps.setObject(1, idSanPham);
                ps.setObject(2, id_loSX);
                ps.setObject(3, id_huongVi);
                ps.setObject(4, id_donViTinh);
                ps.setObject(5, id_theTich);
                ps.setObject(6, id_hamLuong);
                ps.setObject(7, newMaSanPhamChiTiet);
                ps.setObject(8, sanPhamChiTiet.getSoLuong());
                ps.setObject(9, sanPhamChiTiet.getDonGia());
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 1;
    }

    public int delete(String id) {
        sql = "delete SanPham where maSanPham  = ?  ";
        try {
            con = DBConnect.getConnection();
=======
    
}

    public int add(SanPham sanPham) {
 sql = "INSERT INTO SanPham (ID_loaiSanPham, ID_hangSanPham, maSanPham, tenSanPham, moTa, trangthai) values (? , ? , ? , ? , ? ,?)";
        try {
            int id_loai = getLoaiByIdName(sanPham.getId_loaiSanPham());
            int id_hang = getHangByIDName(sanPham.getId_hangSanPham());
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id_loai);
            ps.setObject(2, id_hang);
            ps.setObject(3, sanPham.getMaSanPham());
            ps.setObject(4, sanPham.getTenSanPham());
            ps.setObject(5, sanPham.getTrangThai());
            ps.setObject(6, sanPham.getGhiChu());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
        public int delete(String id) {
        sql = "delete SanPham where maSanPham  = ?  ";
        try {
            con = DBconnect.getConnection();
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
<<<<<<< HEAD

public int update(SanPham sanPham, String id) {
    sql = "UPDATE SanPham SET "
            + "ID_hangSanPham = ?, "
            + "ID_loaiSanPham = ?, "
            + "ID_doiTuongSD = ?,"
            + "tenSanPham = ?, "
            + "moTa = ?, "
            + "trangthai = ?, "
            + "maSanPham = ? "
            + "WHERE maSanPham = ?";
    try {
        int id_loai = getLoaiByIdName(sanPham.getTenLoaiSanPham());
        int id_hang = getHangByIDName(sanPham.getTenhangSanPham());
        int id_doiTuongSd = getDoiTuongSDByIdName(sanPham.getTenDoiTuongSD());
        con = DBConnect.getConnection();
        ps = con.prepareStatement(sql);

        // Sắp xếp lại chỉ mục tham số
        ps.setInt(1, id_hang);
        ps.setInt(2, id_loai);
        ps.setInt(3, id_doiTuongSd);
        ps.setString(4, sanPham.getTenSanPham());
        ps.setString(5, sanPham.getGhiChu());     
        ps.setString(6, sanPham.getTrangThai());   
        ps.setString(7, sanPham.getMaSanPham());   
        ps.setString(8, id);                      

        return ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}


    public int updateSPCT(SanPhamChiTiet spct, String id) {
       sql = "UPDATE spct "
                + "SET spct.ID_loSanXuat = ?, "
                + "    spct.ID_huongVi = ?, "
                + "    spct.ID_donViTinh = ?, "
                + "    spct.ID_theTich = ?, "
                + "    spct.ID_hamLuongDuong = ?, "
               +"spct.anh = ?,"
                + "    spct.soLuongTon = ? ,"
               + "spct.dongia = ?"
                + "FROM SanPhamChiTiet spct "
                + "INNER JOIN SanPham sp ON spct.ID_sanPham = sp.ID_sanPham "
                + "WHERE sp.maSanPham = ?";
        try {
            int id_loSX = getLoSXByIdName(spct.getTenLoSanXuat());
            int id_huongVi = getHuongViByIdName(spct.getTenHuongVi());
            int id_donViTinh = getDonViTinhByIdName(spct.getTenDonViTinh());
            int id_theTich = getTheTichByIdName(spct.getTenTheTich());
            int id_hamLuong = getHamLuongDuongByIdName(spct.getTenHamLuongDuong());
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setObject(1, id_loSX);
            ps.setObject(2, id_huongVi);
            ps.setObject(3, id_donViTinh);
            ps.setObject(4, id_theTich);
            ps.setObject(5, id_hamLuong);
            ps.setObject(6, spct.getAnh());
            ps.setObject(7, spct.getSoLuong());
            ps.setObject(8 ,spct.getDonGia());
            ps.setObject(9, id);
            ps.executeUpdate();
=======
        
            public int update(SanPham sanPham, String id) {
          sql = "update SanPham set ID_hangSanPham = ? , ID_loaiSanPham = ? , tenSanPham = ? , moTa =? , trangthai = ? , maSanPham = ? where maSanPham = ?";    
        try {
         int id_loai = getLoaiByIdName(sanPham.getId_loaiSanPham());
            int id_hang = getHangByIDName(sanPham.getId_hangSanPham());
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_loai);
            ps.setInt(2, id_hang);
            ps.setObject(3, sanPham.getTenSanPham());
            ps.setObject(4, sanPham.getTrangThai());
            ps.setObject(5, sanPham.getGhiChu());
            ps.setObject(6, sanPham.getMaSanPham());
            ps.setObject(7, id);
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

<<<<<<< HEAD
    public List<SanPham> timKiemSP(String ma) {
      sql = "SELECT \n"
           + "    sp.maSanPham,\n"
           + "    sp.tenSanPham,\n"
           + "    hsp.tenHangSanPham,\n"
           + "    lsp.tenLoaiSanPham,\n"
           + "    d.tenDoiTuongSD,\n"       
           + "    sp.moTa,\n"
           + "    sp.trangthai\n"
           + "FROM \n"
           + "    SanPham sp\n"
           + "JOIN \n"
           + "    LoaiSanPham lsp ON sp.ID_loaiSanPham = lsp.ID_loaiSanPham\n"
           + "JOIN \n"
           + "    HangSanPham hsp ON sp.ID_hangSanPham = hsp.ID_hangSanPham\n"
           + "JOIN \n"
           + "    DoiTuongSuDung d ON sp.ID_doiTuongSD = d.ID_doiTuongSD\n"
           + "WHERE \n"
           + "    sp.maSanPham LIKE ? OR \n"
           + "    sp.tenSanPham LIKE ? ";

        List<SanPham> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + ma + "%");
            ps.setString(2, "%" + ma + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7));

                list.add(sanPham);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private int getHangByIDName(String tenHang) {
        String sql = "SELECT ID_hangSanPham FROM HangSanPham WHERE tenHangSanPham = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
=======
    private int getHangByIDName(String tenHang) {
        String sql = "SELECT ID_hangSanPham FROM HangSanPham WHERE tenHangSanPham = ?";
        try (Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps.setString(1, tenHang);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_hangSanPham");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
<<<<<<< HEAD

    private int getLoaiByIdName(String tenLoai) {
        String sql = "SELECT ID_loaiSanPham FROM LoaiSanPham WHERE tenLoaiSanPham = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
=======
      private int getLoaiByIdName(String tenLoai) {
        String sql = "SELECT ID_loaiSanPham FROM LoaiSanPham WHERE tenLoaiSanPham = ?";
        try (Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
            ps.setString(1, tenLoai);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_loaiSanPham");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
<<<<<<< HEAD

    private int getLoSXByIdName(String tenLoai) {
        String sql = "SELECT ID_loSanXuat FROM LoSanXuat WHERE tenLoSanXuat = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenLoai);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_loSanXuat");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int getHamLuongDuongByIdName(String tenLoai) {
        String sql = "SELECT ID_hamLuongDuong FROM HamLuongDuong WHERE tenHamLuongDuong = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenLoai);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_hamLuongDuong");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int getTheTichByIdName(String tenLoai) {
        String sql = "SELECT ID_theTich FROM TheTich WHERE tenTheTich = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenLoai);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_theTich");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int getHuongViByIdName(String tenLoai) {
        String sql = "SELECT ID_huongVi FROM HuongVi WHERE tenHuongVi = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenLoai);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_huongVi");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int getDoiTuongSDByIdName(String tenLoai) {
        String sql = "SELECT ID_doiTuongSD FROM DoiTuongSuDung WHERE tenDoiTuongSD = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenLoai);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_doiTuongSD");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int getDonViTinhByIdName(String tenLoai) {
        String sql = "SELECT ID_donViTinh FROM DonViTinh WHERE tenDonViTinh = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenLoai);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_donViTinh");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
=======
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
}
