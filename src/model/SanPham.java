/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
/**
 *
 * @author Computer Bao
 */
public class SanPham {
<<<<<<< HEAD

    int id;
    String maSanPham;
    String tenSanPham;
    String tenhangSanPham;
    String tenLoaiSanPham;
    String tenDoiTuongSD;
    String ghiChu;
    String trangThai;

    public String getTenDoiTuongSD() {
        return tenDoiTuongSD;
    }

    public void setTenDoiTuongSD(String tenDoiTuongSD) {
        this.tenDoiTuongSD = tenDoiTuongSD;
    }

    
    public String getTenhangSanPham() {
        return tenhangSanPham;
    }

    public void setTenhangSanPham(String tenhangSanPham) {
        this.tenhangSanPham = tenhangSanPham;
    }

    public String getTenLoaiSanPham() {
        return tenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

    public SanPham() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

 

    public SanPham(String maSanPham, String tenSanPham, String tenhangSanPham, String tenLoaiSanPham, String tenDoiTuongSD, String ghiChu, String trangThai) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.tenhangSanPham = tenhangSanPham;
        this.tenLoaiSanPham = tenLoaiSanPham;
        this.tenDoiTuongSD = tenDoiTuongSD;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }
    
    
=======
    String maSanPham;
      String tenSanPham;
    String id_hangSanPham;
    String id_loaiSanPham;
    String ghiChu;
    String trangThai;

    public SanPham() {
    }

    public SanPham(String maSanPham, String tenSanPham, String id_hangSanPham, String id_loaiSanPham, String ghiChu, String trangThai) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.id_hangSanPham = id_hangSanPham;
        this.id_loaiSanPham = id_loaiSanPham;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

<<<<<<< HEAD
=======
    public String getId_hangSanPham() {
        return id_hangSanPham;
    }

    public void setId_hangSanPham(String id_hangSanPham) {
        this.id_hangSanPham = id_hangSanPham;
    }

    public String getId_loaiSanPham() {
        return id_loaiSanPham;
    }

    public void setId_loaiSanPham(String id_loaiSanPham) {
        this.id_loaiSanPham = id_loaiSanPham;
    }

>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

<<<<<<< HEAD
    Integer stt = 1;
=======
Integer stt = 1;
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

<<<<<<< HEAD
    public Object[] toData() {
        return new Object[]{
            this.maSanPham,
            this.tenSanPham,
            this.tenLoaiSanPham,
            this.tenhangSanPham,
            this.trangThai,
            this.ghiChu
        };
    }
=======
   public Object[] toData() {
    return new Object[]{
        this.maSanPham,
        this.tenSanPham,
        this.id_loaiSanPham,
        this.id_hangSanPham,
        this.trangThai,
        this.ghiChu
    };
   }
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
}
