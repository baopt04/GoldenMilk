/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import model.NhaCungCap;

/**
 *
 * @author Computer Bao
 */
public interface NhaCungCapInter {
     public int add(NhaCungCap nhaCungCap );
    public int delete(String id);
    public int update(NhaCungCap nhaCungCap  , String id);
}
