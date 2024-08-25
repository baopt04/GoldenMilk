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
        this.nhaCungCap = nhaCungCap;
        this.tenDoiTuongSD = tenDoiTuongSD;
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

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

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

    public String getTenTheTich() {
        return tenTheTich;
    }

    public void setTenTheTich(String tenTheTich) {
        this.tenTheTich = tenTheTich;
    }

    public String getTenDonViTinh() {
        return tenDonViTinh;
    }

    public void setTenDonViTinh(String tenDonViTinh) {
        this.tenDonViTinh = tenDonViTinh;
    }

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

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;

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
}
