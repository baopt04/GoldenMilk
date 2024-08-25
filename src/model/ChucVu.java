package model;

/**
 * Entity class for ChucVu
 * 
 */
public class ChucVu {
    private int idChucVu;
    private String maChucVu;
    private String tenChucVu;

    // Constructors
    public ChucVu() {
    }

    public ChucVu(int idChucVu, String maChucVu, String tenChucVu) {
        this.idChucVu = idChucVu;
        this.maChucVu = maChucVu;
        this.tenChucVu = tenChucVu;
    }

    // Getter and Setter methods
    public int getIdChucVu() {
        return idChucVu;
    }

    public void setIdChucVu(int idChucVu) {
        this.idChucVu = idChucVu;
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    // Method to convert to data row (if needed)
    public Object[] toDataRowChucVu() {
        return new Object[]{
            this.idChucVu, this.maChucVu, this.tenChucVu
        };
    }

    @Override
    public String toString() {
        return tenChucVu;
    }
    
}
