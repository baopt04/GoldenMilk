/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import java.util.List;
import model.DonViTinh;

/**
 *
 * @author Computer Bao
 */
public interface DonViTinhInter {
    public int addDoiTuong(DonViTinh donViTinh );
    public int delete(String id);
    public int update(DonViTinh donViTinh , String id);
    public List<DonViTinh> timKiem(String ma);
}
