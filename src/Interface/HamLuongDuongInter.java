/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import java.util.List;
import model.HamLuongDuong;

/**
 *
 * @author Computer Bao
 */
public interface HamLuongDuongInter {
      public int addDoiTuong(HamLuongDuong hamLuongDuong );
    public int delete(String id);
    public int update(HamLuongDuong hamLuongDuong , String id);
}
