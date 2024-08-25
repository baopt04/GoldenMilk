
package model;

import java.util.Date;


public class HoaDonMD {
    private int idHD;
    private String tenKH;
    private String SDT;
    private float thanhTien;
    private String trangThai;
    private Date ngayTao;
    private String nguoiTao;

    public HoaDonMD() {
    }

    public HoaDonMD(int idHD, String tenKH, String SDT, float thanhTien, String trangThai, Date ngayTao, String nguoiTao) {
        this.idHD = idHD;
        this.tenKH = tenKH;
        this.SDT = SDT;
        this.thanhTien = thanhTien;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.nguoiTao = nguoiTao;
    }

    public int getidHD() {
        return idHD;
    }

    public void setidHD(int idHD) {
        this.idHD = idHD;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }
    
         public Object[]toDataRow(){
        return new Object[]{
            this.idHD,this.tenKH,this.SDT,this.thanhTien,this.trangThai,this.ngayTao,this.nguoiTao
        };
    }
}
