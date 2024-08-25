/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class EmployeeDAO {
    public static String maNV;
    public static String fullName;
    public static String phoneNumber;
    public static String email;
    public static String gender;
    public static Date birthDate;
    public static String address;

    public EmployeeDAO() {
    }

    public EmployeeDAO(String maNV,String fullName, String phoneNumber, String email, String gender, Date birthDate, String address) {
        EmployeeDAO.maNV = maNV;
        EmployeeDAO.fullName = fullName;
        EmployeeDAO.phoneNumber = phoneNumber;
        EmployeeDAO.email = email;
        EmployeeDAO.gender = gender;
        EmployeeDAO.birthDate = birthDate;
        EmployeeDAO.address = address;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        EmployeeDAO.maNV = maNV;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        EmployeeDAO.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        EmployeeDAO.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        EmployeeDAO.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        EmployeeDAO.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        EmployeeDAO.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        EmployeeDAO.address = address;
    }

   
}

