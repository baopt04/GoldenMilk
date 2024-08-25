/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Voucher;
import service.VoucherService;

/**
 *
 * @author OSC
 */
public class viewKhuyenMai extends javax.swing.JPanel {

    private VoucherService voucherService;
    private ButtonGroup radioGroup;
    private String idVoucher = null;
    private Integer selectedRow = -1;

    /**
     * Creates new form viewThongKe
     */
    public viewKhuyenMai() {
        initComponents();
        voucherService = new VoucherService();
        setupRadioGroup();
        loadData();
        setupListeners();
        jDateBatDau.setDateFormatString("dd-MM-yyyy");
        jDateKetThuc.setDateFormatString("dd-MM-yyyy");
    }

    private void setupRadioGroup() {
        radioGroup = new ButtonGroup();
        radioGroup.add(RadioCon);
        radioGroup.add(RadioDaKetThuc);
    }

    private void setupListeners() {
        txt_timkiemtheoten.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filterData();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filterData();
            }

            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filterData();
            }
        });

        cbbTimKiemFrom.addPropertyChangeListener("date", new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                filterData();
            }
        });

        cbbTimKiemTo.addPropertyChangeListener("date", new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                filterData();
            }
        });

        cbbLocTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterData();
            }
        });
    }

    private void loadData() {
        DefaultTableModel model = (DefaultTableModel) tbltable.getModel();
        model.setRowCount(0); // Xóa các hàng hiện tại
        model.setColumnCount(0); // Xóa các cột hiện tại

        // Đặt tiêu đề cho các cột
        model.setColumnIdentifiers(new Object[]{
            "STT", "Mã voucher", "Tên", "Ghi chú", "Giá trị voucher",
            "Giá trị áp dụng", "Số lượng", "Ngày bắt đầu",
            "Ngày kết thúc", "Trạng thái"
        });

        List<Voucher> list = voucherService.getAllVouchers();
        for (Voucher voucher : list) {
            model.addRow(new Object[]{
                voucher.getId(), voucher.getMaVoucher(), voucher.getTenVoucher(), voucher.getGhiChu(),
                voucher.getGiaTriVoucher(), voucher.getGiaTriApDungVoucher(),
                voucher.getSoLuongVoucher(), voucher.getNgayBatDau(),
                voucher.getNgayKetThuc(), voucher.getTrangThai()
            });
        }
    }

    private void loadDataSearch(List<Voucher> list ) {
        DefaultTableModel model = (DefaultTableModel) tbltable.getModel();
        model.setRowCount(0); // Xóa các hàng hiện tại
        model.setColumnCount(0); // Xóa các cột hiện tại

        // Đặt tiêu đề cho các cột
        model.setColumnIdentifiers(new Object[]{
            "STT", "Mã voucher", "Tên", "Ghi chú", "Giá trị voucher",
            "Giá trị áp dụng", "Số lượng", "Ngày bắt đầu",
            "Ngày kết thúc", "Trạng thái"
        });

        for (Voucher voucher : list) {
            model.addRow(new Object[]{
                voucher.getId(), voucher.getMaVoucher(), voucher.getTenVoucher(), voucher.getGhiChu(),
                voucher.getGiaTriVoucher(), voucher.getGiaTriApDungVoucher(),
                voucher.getSoLuongVoucher(), voucher.getNgayBatDau(),
                voucher.getNgayKetThuc(), voucher.getTrangThai()
            });
        }
    }

    private void filterData() {
        String tenVoucher = txt_timkiemtheoten.getText();
        Date fromDate = cbbTimKiemFrom.getDate() != null ? new java.sql.Date(cbbTimKiemFrom.getDate().getTime()) : null;
        Date toDate = cbbTimKiemTo.getDate() != null ? new java.sql.Date(cbbTimKiemTo.getDate().getTime()) : null;
        String trangThai = (String) cbbLocTrangThai.getSelectedItem();

        if ("Tất cả".equals(trangThai)) {
            trangThai = null;
        }

        List<Voucher> filteredList = voucherService.searchVouchers(tenVoucher, fromDate, toDate, trangThai);
        DefaultTableModel model = (DefaultTableModel) tbltable.getModel();
        model.setRowCount(0);
        for (Voucher voucher : filteredList) {
            model.addRow(new Object[]{
                voucher.getId(), voucher.getMaVoucher(), voucher.getTenVoucher(), voucher.getGhiChu(),
                voucher.getGiaTriVoucher(), voucher.getGiaTriApDungVoucher(),
                voucher.getSoLuongVoucher(), voucher.getNgayBatDau(),
                voucher.getNgayKetThuc(), voucher.getTrangThai()
            });
        }
    }

    private boolean validateFields() {
        // Kiểm tra các trường không được bỏ trống
        if (txt_mavoucher.getText().isEmpty() || txt_ten.getText().isEmpty()
                || txt_giatrivoucher.getText().isEmpty() || txt_GiaTriApDung.getText().isEmpty()
                || jSpinnerSoLuong.getValue() == null || jDateBatDau.getDate() == null
                || jDateKetThuc.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin.");
            return false;
        }

        // Kiểm tra giá trị của các trường số
        try {
            float giaTriVoucher = Float.parseFloat(txt_giatrivoucher.getText());
            float giaTriApDung = Float.parseFloat(txt_GiaTriApDung.getText());
            int soLuong = (Integer) jSpinnerSoLuong.getValue();

            if (giaTriVoucher < 0 || giaTriApDung < 0 || soLuong < 0) {
                JOptionPane.showMessageDialog(this, "Giá trị voucher, giá trị áp dụng, và số lượng không được là số âm.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá trị phải là số hợp lệ.");
            return false;
        }

        // Kiểm tra ngày bắt đầu phải lớn hơn hoặc bằng ngày hiện tại
        java.util.Date currentDate = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String startDateStr = sdf.format(jDateBatDau.getDate());
        String date = sdf.format(currentDate);
        if (!startDateStr.equals(date)) {
            if (jDateBatDau.getDate().before(currentDate)) {
                JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải lớn hơn hoặc bằng ngày hiện tại.");
                return false;
            }
        }

        // Kiểm tra ngày bắt đầu phải trước ngày kết thúc
        if (jDateBatDau.getDate().after(jDateKetThuc.getDate())) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc.");
            return false;
        }

        // Kiểm tra độ dài tên voucher
        String tenVoucher = txt_ten.getText();
        if (tenVoucher.length() < 5) { // Ví dụ: tên voucher phải có ít nhất 5 ký tự
            JOptionPane.showMessageDialog(this, "Tên voucher phải có ít nhất 5 ký tự.");
            return false;
        }

        // Kiểm tra tên voucher không chứa ký tự đặc biệt
        //        if (!tenVoucher.matches("[a-zA-Z0-9 ]+")) { // Chỉ cho phép chữ cái, số và khoảng trắng
        //            JOptionPane.showMessageDialog(this, "Tên voucher không được chứa ký tự đặc biệt.");
        //            return false;
        //        }
        // Kiểm tra trùng lặp tên voucher
        if (voucherService.isTenVoucherExists(tenVoucher)) {
            JOptionPane.showMessageDialog(this, "Tên voucher đã tồn tại. Vui lòng nhập tên khác.");
            return false;
        }

        return true;
    }

    private boolean validateFieldsUpdate() {
        // Kiểm tra các trường không được bỏ trống
        if (txt_mavoucher.getText().isEmpty() || txt_ten.getText().isEmpty()
                || txt_giatrivoucher.getText().isEmpty() || txt_GiaTriApDung.getText().isEmpty()
                || jSpinnerSoLuong.getValue() == null || jDateBatDau.getDate() == null
                || jDateKetThuc.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin.");
            return false;
        }

        // Kiểm tra giá trị của các trường số
        try {
            float giaTriVoucher = Float.parseFloat(txt_giatrivoucher.getText());
            float giaTriApDung = Float.parseFloat(txt_GiaTriApDung.getText());
            int soLuong = (Integer) jSpinnerSoLuong.getValue();

            if (giaTriVoucher < 0 || giaTriApDung < 0 || soLuong < 0) {
                JOptionPane.showMessageDialog(this, "Giá trị voucher, giá trị áp dụng, và số lượng không được là số âm.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá trị phải là số hợp lệ.");
            return false;
        }

        if (selectedRow >= 0) {
            java.util.Date currentDate = new java.util.Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String ngayBatDauObj = tbltable.getValueAt(selectedRow, 7).toString();
            String ngayKetThucObj = tbltable.getValueAt(selectedRow, 8).toString();
            // Kiểm tra ngày bắt đầu phải lớn hơn hoặc bằng ngày hiện tại
            String startDateStr = sdf.format(jDateBatDau.getDate());
            String endDateStr = sdf.format(jDateKetThuc.getDate());
            System.out.println("startDateStr" + startDateStr);
            System.out.println("ngayBatDauObj" + ngayBatDauObj);

            if (!ngayBatDauObj.equals(startDateStr)) {
                String date = sdf.format(currentDate);
                if (!startDateStr.equals(date)) {
                    if (jDateBatDau.getDate().before(currentDate)) {
                        JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải lớn hơn hoặc bằng ngày hiện tại.");
                        return false;
                    }
                }

            }
            if (!ngayKetThucObj.equals(endDateStr)) {
                // Kiểm tra ngày bắt đầu phải trước ngày kết thúc
                if (jDateBatDau.getDate().after(jDateKetThuc.getDate())) {
                    JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc.");
                    return false;
                }
            }

        }

        // Kiểm tra độ dài tên voucher
        String tenVoucher = txt_ten.getText();
        if (tenVoucher.length() < 5) { // Ví dụ: tên voucher phải có ít nhất 5 ký tự
            JOptionPane.showMessageDialog(this, "Tên voucher phải có ít nhất 5 ký tự.");
            return false;
        }

        // Kiểm tra tên voucher không chứa ký tự đặc biệt
        //        if (!tenVoucher.matches("[a-zA-Z0-9 ]+")) { // Chỉ cho phép chữ cái, số và khoảng trắng
        //            JOptionPane.showMessageDialog(this, "Tên voucher không được chứa ký tự đặc biệt.");
        //            return false;
        //        }
        // Kiểm tra trùng lặp tên voucher
        if (idVoucher != null) {
            if (voucherService.isTenVoucherExistsUpdate(tenVoucher, idVoucher)) {
                JOptionPane.showMessageDialog(this, "Tên voucher đã tồn tại. Vui lòng nhập tên khác.");
                return false;
            }
        }

        return true;
    }

    private void insertVoucher() {
        if (!validateFields()) {
            System.out.println("Validation failed for insertVoucher");
            return;
        }

        int confirmation = JOptionPane.showOptionDialog(this,
                "Bạn có chắc chắn muốn thêm voucher này?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Yes", "No"},
                "Yes");
        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            String maVoucher = txt_mavoucher.getText();
            if (voucherService.isMaVoucherExists(maVoucher)) {
                JOptionPane.showMessageDialog(this, "Mã voucher đã tồn tại. Vui lòng nhập mã khác.");
                return;
            }

            Voucher voucher = new Voucher();
            voucher.setMaVoucher(maVoucher);
            voucher.setTenVoucher(txt_ten.getText());
            voucher.setGiaTriVoucher(Float.parseFloat(txt_giatrivoucher.getText()));
            voucher.setGiaTriApDungVoucher(Float.parseFloat(txt_GiaTriApDung.getText()));
            voucher.setSoLuongVoucher((Integer) jSpinnerSoLuong.getValue());
            voucher.setNgayBatDau(new java.sql.Date(jDateBatDau.getDate().getTime()));
            voucher.setNgayKetThuc(new java.sql.Date(jDateKetThuc.getDate().getTime()));
            voucher.setTrangThai(RadioCon.isSelected() ? "Hoạt động" : "Ngừng hoạt động");
            voucher.setGhiChu(txt_ghichu.getText());

            int result = voucherService.addVoucher(voucher);
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Thêm voucher thành công");
                loadData();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm voucher thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ: " + e.getMessage());
        }
    }

    private void updateVoucher() {
        if (!validateFieldsUpdate()) {
            System.out.println("Validation failed for updateVoucher");
            return;
        }

        int confirmation = JOptionPane.showOptionDialog(this,
                "Bạn có chắc chắn muốn cập nhật voucher này?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Yes", "No"},
                "Yes");
        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            Voucher voucher = new Voucher();
            voucher.setMaVoucher(txt_mavoucher.getText());
            voucher.setTenVoucher(txt_ten.getText());
            voucher.setGiaTriVoucher(Float.parseFloat(txt_giatrivoucher.getText()));
            voucher.setGiaTriApDungVoucher(Float.parseFloat(txt_GiaTriApDung.getText()));
            voucher.setSoLuongVoucher((Integer) jSpinnerSoLuong.getValue());
            voucher.setNgayBatDau(new java.sql.Date(jDateBatDau.getDate().getTime()));
            voucher.setNgayKetThuc(new java.sql.Date(jDateKetThuc.getDate().getTime()));
            voucher.setTrangThai(RadioCon.isSelected() ? "Hoạt động" : "Ngừng hoạt động");
            voucher.setGhiChu(txt_ghichu.getText());

            int selectedRow = tbltable.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) tbltable.getValueAt(selectedRow, 0);
                voucher.setId(id);

                int result = voucherService.updateVoucher(voucher);
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Cập nhật voucher thành công");
                    loadData();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật voucher thất bại");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Chưa chọn voucher");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ: " + e.getMessage());
        }
    }

    private void deleteVoucher() {
        int selectedRow = tbltable.getSelectedRow();
        if (selectedRow >= 0) {
            int confirmation = JOptionPane.showOptionDialog(this,
                    "Bạn có chắc chắn muốn xóa voucher này?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Yes", "No"},
                    "Yes");
            if (confirmation != JOptionPane.YES_OPTION) {
                return;
            }

            int id = (int) tbltable.getValueAt(selectedRow, 0);
            int result = voucherService.deleteVoucher(id);
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Xóa voucher thành công");
                loadData();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa voucher thất bại");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chưa chọn voucher");
        }
    }

    // Xóa các trường nhập liệu
    private void clearFields() {
        txt_mavoucher.setText("");
        txt_ten.setText("");
        txt_giatrivoucher.setText("");
        txt_GiaTriApDung.setText("");
        jSpinnerSoLuong.setValue(0);
        jDateBatDau.setDate(null);
        jDateKetThuc.setDate(null);
        radioGroup.clearSelection();
        cbbLocTrangThai.setSelectedIndex(0);
        txt_timkiemtheoten.setText("");
        cbbTimKiemFrom.setDate(null);
        cbbTimKiemTo.setDate(null);
        txt_ghichu.setText(""); // Clear the new notes field
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // </editor-fold>
//    private void tbltableMouseClicked(java.awt.event.MouseEvent evt) {                                      
//        int selectedRow = tbltable.getSelectedRow();
//        if (selectedRow >= 0) {
//            Object idObj = tbltable.getValueAt(selectedRow, 0);
//            Object maVoucherObj = tbltable.getValueAt(selectedRow, 1);
//            Object tenVoucherObj = tbltable.getValueAt(selectedRow, 2);
//            Object ghiChuObj = tbltable.getValueAt(selectedRow, 3);
//            Object giaTriVoucherObj = tbltable.getValueAt(selectedRow, 4);
//            Object giaTriApDungVoucherObj = tbltable.getValueAt(selectedRow, 5);
//            Object soLuongObj = tbltable.getValueAt(selectedRow, 6);
//            Object ngayBatDauObj = tbltable.getValueAt(selectedRow, 7);
//            Object ngayKetThucObj = tbltable.getValueAt(selectedRow, 8);
//            Object trangThaiObj = tbltable.getValueAt(selectedRow, 9);
//
//            txt_mavoucher.setText(maVoucherObj != null ? maVoucherObj.toString() : "");
//            txt_ten.setText(tenVoucherObj != null ? tenVoucherObj.toString() : "");
//            txt_giatrivoucher.setText(giaTriVoucherObj != null ? giaTriVoucherObj.toString() : "");
//            txt_GiaTriApDung.setText(giaTriApDungVoucherObj != null ? giaTriApDungVoucherObj.toString() : "");
//            txt_ghichu.setText(ghiChuObj != null ? ghiChuObj.toString() : "");
//
//            if (soLuongObj instanceof Number) {
//                int soLuong = ((Number) soLuongObj).intValue();
//                jSpinnerSoLuong.setValue(soLuong);
//            } else {
//                try {
//                    int soLuong = Integer.parseInt(soLuongObj.toString());
//                    jSpinnerSoLuong.setValue(soLuong);
//                } catch (NumberFormatException e) {
//                    jSpinnerSoLuong.setValue(0); // Đặt giá trị mặc định nếu không hợp lệ
//                    JOptionPane.showMessageDialog(this, "Giá trị số lượng không hợp lệ: " + soLuongObj);
//                }
//            }
//
//            if (ngayBatDauObj != null) {
//                jDateBatDau.setDate(Date.valueOf(ngayBatDauObj.toString()));
//            } else {
//                jDateBatDau.setDate(null);
//            }
//
//            if (ngayKetThucObj != null) {
//                jDateKetThuc.setDate(Date.valueOf(ngayKetThucObj.toString()));
//            } else {
//                jDateKetThuc.setDate(null);
//            }
//
//            if (trangThaiObj != null && trangThaiObj.toString().equals("Hoạt động")) {
//                RadioCon.setSelected(true);
//            } else {
//                RadioDaKetThuc.setSelected(true);
//            }
//        }
//    }                                     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel16 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_mavoucher = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_ten = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_giatrivoucher = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jDateBatDau = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jDateKetThuc = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        txt_GiaTriApDung = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jSpinnerSoLuong = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        txt_ghichu = new javax.swing.JTextField();
        txt_timkiemtheoten = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbbTimKiemFrom = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        cbbTimKiemTo = new com.toedter.calendar.JDateChooser();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        RadioCon = new javax.swing.JRadioButton();
        RadioDaKetThuc = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        cbbLocTrangThai = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltable = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1400, 760));

        jPanel16.setBackground(new java.awt.Color(66, 184, 234));

        jPanel1.setBackground(new java.awt.Color(66, 184, 234));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Quản lý khuyến mãi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(593, 593, 593)
                .addComponent(jLabel1)
                .addContainerGap(857, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.setBackground(new java.awt.Color(66, 184, 234));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mã voucher:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tên voucher:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Giá trị voucher:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Thời gian bắt đầu:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Thời gian kết thúc:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Giá trị áp dụng:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Số lượng:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Mô tả:");

        txt_timkiemtheoten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timkiemtheotenActionPerformed(evt);
            }
        });
        txt_timkiemtheoten.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timkiemtheotenKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tìm theo thời gian:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("from:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("to:");

        btnThem.setText("Thêm");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
        });
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCapNhatMouseClicked(evt);
            }
        });
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoiMouseClicked(evt);
            }
        });
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Trạng thái:");

        buttonGroup1.add(RadioCon);
        RadioCon.setForeground(new java.awt.Color(255, 255, 255));
        RadioCon.setText("Hoạt động");

        buttonGroup1.add(RadioDaKetThuc);
        RadioDaKetThuc.setForeground(new java.awt.Color(255, 255, 255));
        RadioDaKetThuc.setText("Dừng hoạt động");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Trạng thái:");

        cbbLocTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Hoạt động", "Ngừng hoạt động" }));

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tbltable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã voucher", "Tên", "Mô tả", "Giá trị voucher", "Giá trị áp dụng", "Số lượng", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"
            }
        ));
        tbltable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbltableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbltable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 118, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_mavoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txt_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbTimKiemFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_giatrivoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jSpinnerSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(RadioCon)
                                        .addGap(18, 18, 18)
                                        .addComponent(RadioDaKetThuc)
                                        .addGap(16, 16, 16)))))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(cbbTimKiemTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(jDateBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel10))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jDateKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_GiaTriApDung)
                                        .addComponent(txt_ghichu))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(btnThem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCapNhat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLamMoi))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_timkiemtheoten, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbLocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 213, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txt_mavoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(jDateBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txt_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(jDateKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_giatrivoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_GiaTriApDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jSpinnerSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(txt_ghichu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(RadioCon)
                                    .addComponent(RadioDaKetThuc))
                                .addGap(37, 37, 37))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnLamMoi)
                                    .addComponent(btnXoa)
                                    .addComponent(btnCapNhat)
                                    .addComponent(btnThem))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbbTimKiemFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbbTimKiemTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_timkiemtheoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15)
                                .addComponent(cbbLocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel11)))
                .addGap(48, 48, 48)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 1377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked

    }//GEN-LAST:event_btnThemMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        insertVoucher();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCapNhatMouseClicked

    }//GEN-LAST:event_btnCapNhatMouseClicked

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        updateVoucher();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        deleteVoucher();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLamMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiMouseClicked
        btnLamMoiActionPerformed(null);
    }//GEN-LAST:event_btnLamMoiMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        clearFields();
        loadData();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tbltableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbltableMouseClicked
        selectedRow = tbltable.getSelectedRow();
        if (selectedRow >= 0) {
            Object idObj = tbltable.getValueAt(selectedRow, 0);
            Object maVoucherObj = tbltable.getValueAt(selectedRow, 1);
            Object tenVoucherObj = tbltable.getValueAt(selectedRow, 2);
            Object ghiChuObj = tbltable.getValueAt(selectedRow, 3);
            Object giaTriVoucherObj = tbltable.getValueAt(selectedRow, 4);
            Object giaTriApDungVoucherObj = tbltable.getValueAt(selectedRow, 5);
            Object soLuongObj = tbltable.getValueAt(selectedRow, 6);
            Object ngayBatDauObj = tbltable.getValueAt(selectedRow, 7);
            Object ngayKetThucObj = tbltable.getValueAt(selectedRow, 8);
            Object trangThaiObj = tbltable.getValueAt(selectedRow, 9);
            idVoucher = idObj != null ? idObj.toString() : null;
            txt_mavoucher.setText(maVoucherObj != null ? maVoucherObj.toString() : "");
            txt_ten.setText(tenVoucherObj != null ? tenVoucherObj.toString() : "");
            txt_giatrivoucher.setText(giaTriVoucherObj != null ? giaTriVoucherObj.toString() : "");
            txt_GiaTriApDung.setText(giaTriApDungVoucherObj != null ? giaTriApDungVoucherObj.toString() : "");
            txt_ghichu.setText(ghiChuObj != null ? ghiChuObj.toString() : "");

            if (soLuongObj instanceof Number) {
                int soLuong = ((Number) soLuongObj).intValue();
                jSpinnerSoLuong.setValue(soLuong);
            } else {
                try {
                    int soLuong = Integer.parseInt(soLuongObj.toString());
                    jSpinnerSoLuong.setValue(soLuong);
                } catch (NumberFormatException e) {
                    jSpinnerSoLuong.setValue(0); // Đặt giá trị mặc định nếu không hợp lệ
                    JOptionPane.showMessageDialog(this, "Giá trị số lượng không hợp lệ: " + soLuongObj);
                }
            }

            if (ngayBatDauObj != null) {
                jDateBatDau.setDate(Date.valueOf(ngayBatDauObj.toString()));
            } else {
                jDateBatDau.setDate(null);
            }

            if (ngayKetThucObj != null) {
                jDateKetThuc.setDate(Date.valueOf(ngayKetThucObj.toString()));
            } else {
                jDateKetThuc.setDate(null);
            }

            if (trangThaiObj != null && trangThaiObj.toString().equals("Hoạt động")) {
                RadioCon.setSelected(true);
            } else {
                RadioDaKetThuc.setSelected(true);
            }
        }
    }//GEN-LAST:event_tbltableMouseClicked

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void txt_timkiemtheotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timkiemtheotenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timkiemtheotenActionPerformed

    private void txt_timkiemtheotenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timkiemtheotenKeyReleased
      loadDataSearch(voucherService.getAllVouchersSearch(txt_timkiemtheoten.getText()));
    }//GEN-LAST:event_txt_timkiemtheotenKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RadioCon;
    private javax.swing.JRadioButton RadioDaKetThuc;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbLocTrangThai;
    private com.toedter.calendar.JDateChooser cbbTimKiemFrom;
    private com.toedter.calendar.JDateChooser cbbTimKiemTo;
    private com.toedter.calendar.JDateChooser jDateBatDau;
    private com.toedter.calendar.JDateChooser jDateKetThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerSoLuong;
    private javax.swing.JTable tbltable;
    private javax.swing.JTextField txt_GiaTriApDung;
    private javax.swing.JTextField txt_ghichu;
    private javax.swing.JTextField txt_giatrivoucher;
    private javax.swing.JTextField txt_mavoucher;
    private javax.swing.JTextField txt_ten;
    private javax.swing.JTextField txt_timkiemtheoten;
    // End of variables declaration//GEN-END:variables
}
