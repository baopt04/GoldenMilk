/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import java.util.List;
import model.DoiTuong;

/**
 *
 * @author Computer Bao
 */
public interface DoiTuongInterFace {
    public int addDoiTuong(DoiTuong doiTuong );
    public int delete(String id);
    public int update(DoiTuong doiTuong , String id);
    public List<DoiTuong> timKiem(String ma);
}
