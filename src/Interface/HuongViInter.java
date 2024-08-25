/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import model.HuongVi;

/**
 *
 * @author Computer Bao
 */
public interface HuongViInter {
    public int add(HuongVi huongVi );
    public int delete(String id);
    public int update(HuongVi huongVi  , String id);
}
