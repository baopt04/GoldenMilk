/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Computer Bao
 */
public class SanPhamChiTiet {
<<<<<<< HEAD

    int id_SPCT;
    Integer id_sanPham;
    String maSanPham;
    String tenSanPham;
    String nhaCungCap;
    String tenDoiTuongSD;
    String tenHamLuongDuong;
    String tenHuongVi;
    String tenTheTich;
    String tenLoSanXuat;
    String tenDonViTinh;
    String NSX;
    String HSD;
    String maSanPhamChiTiet;
    String qrCode;
    String donGia;
    String soLuong;
    String anh;
    String tenLoaiSP;
    String tenHangSP;
    String moTaSP;
    String trangThaiSP;

    public SanPhamChiTiet(String maSanPham, String tenSanPham, String nhaCungCap, String tenDoiTuongSD, String tenHamLuongDuong, String tenHuongVi, String tenTheTich, String tenLoSanXuat, String tenDonViTinh, String donGia, String soLuong, String tenLoaiSP, String tenHangSP, String moTaSP, String trangThaiSP) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
=======
//    String id_sanPham;
//    String id_loSanXuat;
//    String id_nhaCungCap;
//    String id_huongVi;
//    String id_doituongSD;
//    String id_donViTinh;
//    String id_theTich;
//    String id_hamLuongDuong;
//    String anh;
//    String soLuongTon;
//    String donGia;
//    String moTa;
//    String qrCode;
//    String trangThai;
       String maSanPham;
       String tenSanPham;
       String tenLoaiSanPham;
       String tenLoSanXuat;
       String nhaCungCap;
       String tenDoiTuongSD;
       String tenHamLuongDuong;
       String tenHuongVi;
       String tenKhoiluong;
       String tenDonViTinh;
       String tenHangSanPham;
       String qrCode;
       String donGia;
       String soLuong;
       String ghiChu;
       String anh;
    public SanPhamChiTiet() {
    }

//    public SanPhamChiTiet(String id_sanPham, String id_loSanXuat, String id_nhaCungCap, String id_huongVi, String id_doituongSD, String id_donViTinh, String id_theTich, String id_hamLuongDuong, String anh, String soLuongTon, String donGia, String moTa, String qrCode, String trangThai) {
//        this.id_sanPham = id_sanPham;
//        this.id_loSanXuat = id_loSanXuat;
//        this.id_nhaCungCap = id_nhaCungCap;
//        this.id_huongVi = id_huongVi;
//        this.id_doituongSD = id_doituongSD;
//        this.id_donViTinh = id_donViTinh;
//        this.id_theTich = id_theTich;
//        this.id_hamLuongDuong = id_hamLuongDuong;
//        this.anh = anh;
//        this.soLuongTon = soLuongTon;
//        this.donGia = donGia;
//        this.moTa = moTa;
//        this.qrCode = qrCode;
//        this.trangThai = trangThai;
//    }

    public SanPhamChiTiet(String maSanPham, String tenSanPham, String tenLoaiSanPham, String tenLoSanXuat, String nhaCungCap, String tenDoiTuongSD, String tenHamLuongDuong, String tenHuongVi, String tenKhoiluong, String tenDonViTinh, String tenHangSanPham, String qrCode, String donGia, String soLuong, String ghiChu, String anh) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.tenLoaiSanPham = tenLoaiSanPham;
        this.tenLoSanXuat = tenLoSanXuat;
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
        this.nhaCungCap = nhaCungCap;
        this.tenDoiTuongSD = tenDoiTuongSD;
        this.tenHamLuongDuong = tenHamLuongDuong;
        this.tenHuongVi = tenHuongVi;
<<<<<<< HEAD
        this.tenTheTich = tenTheTich;
        this.tenLoSanXuat = tenLoSanXuat;
        this.tenDonViTinh = tenDonViTinh;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.tenLoaiSP = tenLoaiSP;
        this.tenHangSP = tenHangSP;
        this.moTaSP = moTaSP;
        this.trangThaiSP = trangThaiSP;
    }

    public SanPhamChiTiet(String maSanPham, String tenSanPham, String nhaCungCap, String tenHamLuongDuong, String tenHuongVi, String tenTheTich, String tenLoSanXuat, String tenDonViTinh, String donGia, String soLuong, String tenLoaiSP, String tenHangSP, String moTaSP, String trangThaiSP) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.nhaCungCap = nhaCungCap;
        this.tenHamLuongDuong = tenHamLuongDuong;
        this.tenHuongVi = tenHuongVi;
        this.tenTheTich = tenTheTich;
        this.tenLoSanXuat = tenLoSanXuat;
        this.tenDonViTinh = tenDonViTinh;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.tenLoaiSP = tenLoaiSP;
        this.tenHangSP = tenHangSP;
        this.moTaSP = moTaSP;
        this.trangThaiSP = trangThaiSP;
    }

    public SanPhamChiTiet(String maSanPham, String tenSanPham, String nhaCungCap, String tenHamLuongDuong, String tenHuongVi, String tenTheTich, String tenLoSanXuat, String tenDonViTinh , String NSX , String HSD, String donGia, String soLuong, String anh) {
        this.maSanPham = maSanPham;//zzx
        this.tenSanPham = tenSanPham;
        this.nhaCungCap = nhaCungCap;
        this.tenHamLuongDuong = tenHamLuongDuong;
        this.tenHuongVi = tenHuongVi;
        this.tenTheTich = tenTheTich;
        this.tenLoSanXuat = tenLoSanXuat;
        this.tenDonViTinh = tenDonViTinh;
        this.NSX = NSX;
        this.HSD = HSD;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.anh = anh;
    }

    public SanPhamChiTiet(String tenSanPham, String nhaCungCap, String tenHamLuongDuong, String tenHuongVi, String tenTheTich, String tenLoSanXuat, String tenDonViTinh, String donGia, String soLuong, String anh) {
        this.tenSanPham = tenSanPham;
        this.nhaCungCap = nhaCungCap;
        this.tenHamLuongDuong = tenHamLuongDuong;
        this.tenHuongVi = tenHuongVi;
        this.tenTheTich = tenTheTich;
        this.tenLoSanXuat = tenLoSanXuat;
        this.tenDonViTinh = tenDonViTinh;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.anh = anh;
    }

    public SanPhamChiTiet(String tenSanPham, String tenHamLuongDuong, String tenHuongVi, String tenTheTich, String tenLoSanXuat, String tenDonViTinh, String donGia, String soLuong, String anh) {
        this.tenSanPham = tenSanPham;
        this.tenHamLuongDuong = tenHamLuongDuong;
        this.tenHuongVi = tenHuongVi;
        this.tenTheTich = tenTheTich;
        this.tenLoSanXuat = tenLoSanXuat;
        this.tenDonViTinh = tenDonViTinh;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.anh = anh;
    }

    public SanPhamChiTiet(Integer id_sanPham, String tenHamLuongDuong, String tenHuongVi, String tenTheTich, String tenLoSanXuat, String tenDonViTinh) {
        this.id_sanPham = id_sanPham;
        this.tenHamLuongDuong = tenHamLuongDuong;
        this.tenHuongVi = tenHuongVi;
        this.tenTheTich = tenTheTich;
        this.tenLoSanXuat = tenLoSanXuat;
        this.tenDonViTinh = tenDonViTinh;
    }

    public String getNSX() {
        return NSX;
    }

    public void setNSX(String NSX) {
        this.NSX = NSX;
    }

    public String getHSD() {
        return HSD;
    }

    public void setHSD(String HSD) {
        this.HSD = HSD;
    }

    
    public String getTenLoaiSP() {
        return tenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        this.tenLoaiSP = tenLoaiSP;
    }

    public String getTenHangSP() {
        return tenHangSP;
    }

    public void setTenHangSP(String tenHangSP) {
        this.tenHangSP = tenHangSP;
    }

    public String getTenDoiTuongSD() {
        return tenDoiTuongSD;
    }

    public String getMaSanPhamChiTiet() {
        return maSanPhamChiTiet;
    }

    public void setMaSanPhamChiTiet(String maSanPhamChiTiet) {
        this.maSanPhamChiTiet = maSanPhamChiTiet;
    }

    public void setTenDoiTuongSD(String tenDoiTuongSD) {
        this.tenDoiTuongSD = tenDoiTuongSD;
    }

    public String getMoTaSP() {
        return moTaSP;
    }

    public void setMoTaSP(String moTaSP) {
        this.moTaSP = moTaSP;
    }

    public String getTrangThaiSP() {
        return trangThaiSP;
    }

    public void setTrangThaiSP(String trangThaiSP) {
        this.trangThaiSP = trangThaiSP;
    }

    public int getId_SPCT() {
        return id_SPCT;
    }

    public void setId_SPCT(int id_SPCT) {
        this.id_SPCT = id_SPCT;
    }

    public Integer getId_sanPham() {
        return id_sanPham;
    }

    public void setId_sanPham(Integer id_sanPham) {
        this.id_sanPham = id_sanPham;
    }

=======
        this.tenKhoiluong = tenKhoiluong;
        this.tenDonViTinh = tenDonViTinh;
        this.tenHangSanPham = tenHangSanPham;
        this.qrCode = qrCode;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
        this.anh = anh;
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

<<<<<<< HEAD
=======
    public String getTenLoaiSanPham() {
        return tenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

    public String getTenLoSanXuat() {
        return tenLoSanXuat;
    }

    public void setTenLoSanXuat(String tenLoSanXuat) {
        this.tenLoSanXuat = tenLoSanXuat;
    }

>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

<<<<<<< HEAD
=======
    public String getTenDoiTuongSD() {
        return tenDoiTuongSD;
    }

    public void setTenDoiTuongSD(String tenDoiTuongSD) {
        this.tenDoiTuongSD = tenDoiTuongSD;
    }

>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
    public String getTenHamLuongDuong() {
        return tenHamLuongDuong;
    }

    public void setTenHamLuongDuong(String tenHamLuongDuong) {
        this.tenHamLuongDuong = tenHamLuongDuong;
    }

    public String getTenHuongVi() {
        return tenHuongVi;
    }

    public void setTenHuongVi(String tenHuongVi) {
        this.tenHuongVi = tenHuongVi;
    }

<<<<<<< HEAD
    public String getTenTheTich() {
        return tenTheTich;
    }

    public void setTenTheTich(String tenTheTich) {
        this.tenTheTich = tenTheTich;
=======
    public String getTenKhoiluong() {
        return tenKhoiluong;
    }

    public void setTenKhoiluong(String tenKhoiluong) {
        this.tenKhoiluong = tenKhoiluong;
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
    }

    public String getTenDonViTinh() {
        return tenDonViTinh;
    }

    public void setTenDonViTinh(String tenDonViTinh) {
        this.tenDonViTinh = tenDonViTinh;
    }

<<<<<<< HEAD
=======
    public String getTenHangSanPham() {
        return tenHangSanPham;
    }

    public void setTenHangSanPham(String tenHangSanPham) {
        this.tenHangSanPham = tenHangSanPham;
    }

>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

<<<<<<< HEAD
=======
    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
<<<<<<< HEAD

    }

    public Object[] toData() {
        return new Object[]{
            this.maSanPham, this.tenSanPham,
            this.nhaCungCap, this.tenLoSanXuat,
            this.tenHamLuongDuong,
            this.tenHuongVi, this.tenTheTich, this.tenDonViTinh,
            this.qrCode, this.donGia,
            this.soLuong
        };
    }

    public String getTenLoSanXuat() {
        return tenLoSanXuat;
    }

    public void setTenLoSanXuat(String tenLoSanXuat) {
        this.tenLoSanXuat = tenLoSanXuat;
    }
=======
    }
    

//    public String getId_sanPham() {
//        return id_sanPham;
//    }
//
//    public void setId_sanPham(String id_sanPham) {
//        this.id_sanPham = id_sanPham;
//    }
//
//    public String getId_loSanXuat() {
//        return id_loSanXuat;
//    }
//
//    public void setId_loSanXuat(String id_loSanXuat) {
//        this.id_loSanXuat = id_loSanXuat;
//    }
//
//    public String getId_nhaCungCap() {
//        return id_nhaCungCap;
//    }
//
//    public void setId_nhaCungCap(String id_nhaCungCap) {
//        this.id_nhaCungCap = id_nhaCungCap;
//    }
//
//    public String getId_huongVi() {
//        return id_huongVi;
//    }
//
//    public void setId_huongVi(String id_huongVi) {
//        this.id_huongVi = id_huongVi;
//    }
//
//    public String getId_doituongSD() {
//        return id_doituongSD;
//    }
//
//    public void setId_doituongSD(String id_doituongSD) {
//        this.id_doituongSD = id_doituongSD;
//    }
//
//    public String getId_donViTinh() {
//        return id_donViTinh;
//    }
//
//    public void setId_donViTinh(String id_donViTinh) {
//        this.id_donViTinh = id_donViTinh;
//    }
//
//    public String getId_theTich() {
//        return id_theTich;
//    }
//
//    public void setId_theTich(String id_theTich) {
//        this.id_theTich = id_theTich;
//    }
//
//    public String getId_hamLuongDuong() {
//        return id_hamLuongDuong;
//    }
//
//    public void setId_hamLuongDuong(String id_hamLuongDuong) {
//        this.id_hamLuongDuong = id_hamLuongDuong;
//    }
//
//    public String getAnh() {
//        return anh;
//    }
//
//    public void setAnh(String anh) {
//        this.anh = anh;
//    }
//
//    public String getSoLuongTon() {
//        return soLuongTon;
//    }
//
//    public void setSoLuongTon(String soLuongTon) {
//        this.soLuongTon = soLuongTon;
//    }
//
//    public String getDonGia() {
//        return donGia;
//    }
//
//    public void setDonGia(String donGia) {
//        this.donGia = donGia;
//    }
//
//    public String getMoTa() {
//        return moTa;
//    }
//
//    public void setMoTa(String moTa) {
//        this.moTa = moTa;
//    }
//
//    public String getQrCode() {
//        return qrCode;
//    }
//
//    public void setQrCode(String qrCode) {
//        this.qrCode = qrCode;
//    }
//
//    public String getTrangThai() {
//        return trangThai;
//    }
//
//    public void setTrangThai(String trangThai) {
//        this.trangThai = trangThai;
//    }

    
    public Object[] toData(){
    return new Object[]{
         this.maSanPham , this.tenSanPham , this.tenLoaiSanPham ,this.tenLoSanXuat
             , this.nhaCungCap , this.tenDoiTuongSD , this.tenHamLuongDuong ,
            this.tenHuongVi , this.tenKhoiluong , this.tenDonViTinh ,
            this.tenHangSanPham , this.qrCode , this.donGia ,
            this.soLuong
            ,this.ghiChu , this.anh
    };
}
>>>>>>> b63760df799d11f838fc10d576440d2801f77e3b
}
