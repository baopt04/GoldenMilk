
package model;


public class KhachHangMD {
    private int id;
    private String maKH;
    private String hoTen;
    private boolean  gioiTinh;
    private String soDT;
    private String email;
    private String diaChi;

    public KhachHangMD() {
    }

    public KhachHangMD(String maKH, String hoTen, String soDT) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.soDT = soDT;
    }

    public KhachHangMD(String hoTen, String soDT) {
        this.hoTen = hoTen;
        this.soDT = soDT;
    }

    public KhachHangMD(int id, String maKH, String hoTen, boolean gioiTinh, String soDT, String email, String diaChi) {
        this.id = id;
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.soDT = soDT;
        this.email = email;
        this.diaChi = diaChi;
    }

    public KhachHangMD(String maKH, String hoTen, String soDT, String email, String diaChi) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.soDT = soDT;
        this.email = email;
        this.diaChi = diaChi;
    }

    public KhachHangMD(String maKH, String hoTen, boolean gioiTinh, String soDT, String email, String diaChi) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.soDT = soDT;
        this.email = email;
        this.diaChi = diaChi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    
         public Object[]toDataRow(){
        return new Object[]{
            this.maKH,this.hoTen,this.gioiTinh?"Nam":"Nữ",this.soDT,this.email,this.diaChi
        };
    }

    @Override
    public String toString() {
        return "KhachHangMD{" + "id=" + id + ", maKH=" + maKH + ", hoTen=" + hoTen + ", gioiTinh=" + gioiTinh + ", soDT=" + soDT + ", email=" + email + ", diaChi=" + diaChi + '}';
    }
}
