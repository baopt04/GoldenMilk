/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author OSC
 */
public class UserSession {
    private static String maNV;
    private static String tenNV;
    private static String role;

    public static String getMaNV() {
        return maNV;
    }

    public static void setMaNV(String maNV) {
        UserSession.maNV = maNV;
    }

    public static String getTenNV() {
        return tenNV;
    }

    public static void setTenNV(String tenNV) {
        UserSession.tenNV = tenNV;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        UserSession.role = role;
    }
}
