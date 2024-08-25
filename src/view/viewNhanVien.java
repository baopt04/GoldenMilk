/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.NhanVien;
import service.EmailUtil;
import service.NhanVienService;

/**
 *
 * @author OSC
 */
public class viewNhanVien extends javax.swing.JPanel {
    NhanVienService nhanVienService = new NhanVienService();
    DefaultTableModel tableModel = new DefaultTableModel();
    List<NhanVien> listDataNhanVien = new ArrayList<>();
    int index = -1;
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private ButtonGroup buttonGroup2 = new ButtonGroup();

    /**
     * Creates new form viewHoaDon
     */
    public viewNhanVien() {
        initComponents();
        load();
        jDateChooser1.setDateFormatString("dd-MM-yyyy");
        fillTable(nhanVienService.getList());
        clearInputFields();
        addFilterAction(); // Gọi phương thức này để thêm sự kiện lọc
    }

    public void clearInputFields() {
        txt_manv.setText("");
        txt_tennv.setText("");
        txt_diachi.setText("");
        txt_email.setText("");
        txttim.setText("");
        txt_sodienthoai.setText("");
        jDateChooser1.setDate(null);
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        jRadioNam.setSelected(true);
        jRadioNhanVien.setSelected(true);
        cbbTrangThai.setSelectedIndex(0); // Default to "Đang làm"
    }

    public static java.sql.Date convertToDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    public void load() {
        buttonGroup1.add(jRadioNam);
        buttonGroup1.add(jRadioNu);
        buttonGroup2.add(jRadioNhanVien);
        buttonGroup2.add(jRadioQuanLy);

        // Set default options for cbbTrangThai
        cbbTrangThai.addItem("Đang làm");
        cbbTrangThai.addItem("Nghỉ làm");

        // Set default options for cbbFilterTrangThai
        cbbFilterTrangThai.addItem("Tất cả");
        cbbFilterTrangThai.addItem("Đang làm");
        cbbFilterTrangThai.addItem("Nghỉ làm");
    }

    private int getSelectedChucVu() {
        if (jRadioNhanVien.isSelected()) {
            return 1;
        } else if (jRadioQuanLy.isSelected()) {
            return 2;
        }
        return -1;
    }

    private String generateRandomPassword(int length) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    public NhanVien readForm(boolean isNew) {
        String ma = txt_manv.getText();
        String ten = txt_tennv.getText();
        String sodt = txt_sodienthoai.getText();
        String email = txt_email.getText();
        String diachi = txt_diachi.getText();
        String gioiTinh = jRadioNam.isSelected() ? "0" : "1";
        Date ngaySinh = jDateChooser1.getDate();
        String matKhau = null;

        // Tạo mật khẩu mới nếu là thêm mới, nếu không sử dụng mật khẩu cũ
        if (isNew) {
            matKhau = generateRandomPassword(8);
        }

        String trangThai = cbbTrangThai.getSelectedItem().toString();

        return new NhanVien(getSelectedChucVu(), ma, ten, gioiTinh, convertToDate(ngaySinh), sodt, email, matKhau, diachi, trangThai);
    }

    public void fillTable(List<NhanVien> list) {
        listDataNhanVien = list; // Cập nhật danh sách hiện tại với kết quả tìm kiếm
        tableModel = (DefaultTableModel) tbltable.getModel();
        tableModel.setRowCount(0);
        int stt = 1;
        for (NhanVien nv : list) {
            String chucVu = (nv.getIdChucVu() == 1) ? "Nhân viên" : "Quản lý";
            tableModel.addRow(new Object[]{
                stt++,
                nv.getMaNhanVien(),
                chucVu,
                nv.getHoTen(),
                nv.getGioiTinh().equals("0")? "Nam": "Nữ",
                nv.getNgaySinh(),
                nv.getSoDT(),
                nv.getEmail(),
                nv.getDiaChi(),
                nv.getTrangThai()
            });
        }
    }

    public boolean checkData() {
        String regexPhone = "0\\d{9}";
        String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (txt_manv.getText().trim().isEmpty() || txt_tennv.getText().trim().isEmpty()
                || txt_diachi.getText().trim().isEmpty() || txt_email.getText().trim().isEmpty()
                || txt_sodienthoai.getText().trim().isEmpty() || (!jRadioNam.isSelected() && !jRadioNu.isSelected())
                || (!jRadioNhanVien.isSelected() && !jRadioQuanLy.isSelected()) || jDateChooser1.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
            return false;
        }
        if (!txt_sodienthoai.getText().trim().matches(regexPhone)) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại đúng định dạng");
            return false;
        }
        if (!txt_email.getText().trim().matches(regexEmail)) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập email đúng định dạng");
            return false;
        }
        return true;
    }

    public void showData(int index) {
        if (index >= 0 && index < listDataNhanVien.size()) {
            NhanVien nv = listDataNhanVien.get(index); // Lấy dữ liệu từ danh sách hiện tại
            txt_manv.setText(nv.getMaNhanVien());
            txt_tennv.setText(nv.getHoTen());
            txt_diachi.setText(nv.getDiaChi());
            txt_email.setText(nv.getEmail());
            txt_sodienthoai.setText(nv.getSoDT());
            jDateChooser1.setDate(nv.getNgaySinh());
            if (nv.getGioiTinh().equals("0")) {
                jRadioNam.setSelected(true);
            } else {
                jRadioNu.setSelected(true);
            }
            if (nv.getIdChucVu() == 1) {
                jRadioNhanVien.setSelected(true);
            } else {
                jRadioQuanLy.setSelected(true);
            }
            cbbTrangThai.setSelectedItem(nv.getTrangThai());
        }
    }

    private void sendEmailToNhanVien(String toEmail, String password) {
        String subject = "Thông tin tài khoản nhân viên";
        String body = "Mật khẩu của bạn là: " + password;
        EmailUtil.sendEmail(toEmail, subject, body);
    }

    private void addFilterAction() {
        cbbFilterTrangThai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedStatus = cbbFilterTrangThai.getSelectedItem().toString();
                if (selectedStatus.equals("Tất cả")) {
                    fillTable(nhanVienService.getList());
                } else {
                    fillTable(nhanVienService.filterByTrangThai(selectedStatus));
                }
            }
        });
    }
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel13 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_manv = new javax.swing.JTextField();
        txt_tennv = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_diachi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_sodienthoai = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jRadioNam = new javax.swing.JRadioButton();
        jRadioNu = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        jRadioNhanVien = new javax.swing.JRadioButton();
        jRadioQuanLy = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txttim = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltable = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        cbbTrangThai = new javax.swing.JComboBox<>();
        cbbFilterTrangThai = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setRequestFocusEnabled(false);

        jPanel13.setBackground(new java.awt.Color(66, 184, 234));

        jPanel1.setBackground(new java.awt.Color(66, 184, 234));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Quản lý nhân viên");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(582, 582, 582))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.setBackground(new java.awt.Color(66, 184, 234));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Mã nhân viên:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tên nhân viên:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Ngày sinh:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Địa chỉ :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Số điện thoại:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Email:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Giới tính:");

        buttonGroup1.add(jRadioNam);
        jRadioNam.setForeground(new java.awt.Color(255, 255, 255));
        jRadioNam.setText("Nam");
        jRadioNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioNamActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioNu);
        jRadioNu.setForeground(new java.awt.Color(255, 255, 255));
        jRadioNu.setText("Nữ");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Chức vụ:");

        buttonGroup3.add(jRadioNhanVien);
        jRadioNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        jRadioNhanVien.setText("Nhân viên");
        jRadioNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioNhanVienActionPerformed(evt);
            }
        });

        buttonGroup3.add(jRadioQuanLy);
        jRadioQuanLy.setForeground(new java.awt.Color(255, 255, 255));
        jRadioQuanLy.setText("Quản lý");

        jButton1.setText("Thêm");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Cập nhật");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Làm mới");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txttim.setText("Nhập mã hoặc tên cần tìm....");
        txttim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttimMouseClicked(evt);
            }
        });
        txttim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimKeyReleased(evt);
            }
        });

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
                "STT", "Mã nhân viên", "Chức vụ", "Họ tên", "Giới tính", "Ngày sinh", "SDT", "Email", "Địa chỉ", "Trạng thái"
            }
        ));
        tbltable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbltableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbltable);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Trạng thái:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(169, 169, 169)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_tennv, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                    .addComponent(txt_diachi)
                                    .addComponent(txt_manv)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jRadioNhanVien)
                                        .addGap(27, 27, 27)
                                        .addComponent(jRadioQuanLy))
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(219, 219, 219)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel13)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(205, 205, 205)
                                .addComponent(txttim, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(cbbFilterTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_email, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                    .addComponent(txt_sodienthoai)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jRadioNam)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioNu))
                                    .addComponent(cbbTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 367, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_manv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_sodienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tennv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)
                    .addComponent(jRadioNam)
                    .addComponent(jRadioNu))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(24, 24, 24)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(jRadioNhanVien)
                                .addComponent(jRadioQuanLy)))
                        .addComponent(jLabel13))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttim, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbFilterTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton3)
                            .addComponent(jButton4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioNamActionPerformed

    private void jRadioNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioNhanVienActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if (checkData()) {
            NhanVien doituong = this.readForm(true); // Sử dụng mật khẩu ngẫu nhiên
            if (nhanVienService.add(doituong) > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                clearInputFields();
                fillTable(nhanVienService.getList());
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm mới?");
        if (check != JOptionPane.YES_OPTION) {
            return;
        }
        if (checkData()) {
            for (NhanVien nhanVien : listDataNhanVien) {
                if (nhanVien.getMaNhanVien().equalsIgnoreCase(txt_manv.getText().trim())) {
                    JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại");
                    return;
                }
                if (nhanVien.getEmail().equalsIgnoreCase(txt_email.getText().trim())) {
                    JOptionPane.showMessageDialog(this, "Email nhân viên đã tồn tại");
                    return;
                }
                if (nhanVien.getSoDT().equalsIgnoreCase(txt_sodienthoai.getText().trim())) {
                    JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại");
                    return;
                }
            }
            NhanVien nv = readForm(true); 
            if (nhanVienService.add(nv) > 0) {
                // Gửi email sau khi thêm thành công
                try {
                    sendEmailToNhanVien(nv.getEmail(), nv.getMatKhau());
                    JOptionPane.showMessageDialog(this, "Thêm thành công và gửi email thành công");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công nhưng gửi email thất bại\nMật khẩu: " + nv.getMatKhau());
                }
                clearInputFields();
                fillTable(nhanVienService.getList());
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn cập nhật?");
        if (check != JOptionPane.YES_OPTION) {
            return;
        }
        index = tbltable.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn trường cần sửa");
        } else {
            if (!checkData()) {
                return;
            }
            NhanVien doiTuong = readForm(false); // Sử dụng mật khẩu từ form
            String id = tbltable.getValueAt(index, 1).toString();
            String sdt = tbltable.getValueAt(index, 6).toString();
            String email = tbltable.getValueAt(index, 7).toString();

            if (!doiTuong.getMaNhanVien().equalsIgnoreCase(id)) {
                for (NhanVien nhanVien : listDataNhanVien) {
                    if (nhanVien.getMaNhanVien().equalsIgnoreCase(txt_manv.getText().trim())) {
                        JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại");
                        return;
                    }
                }
            }
            if (!doiTuong.getEmail().equalsIgnoreCase(email)) {
                for (NhanVien nhanVien : listDataNhanVien) {
                    if (nhanVien.getEmail().equalsIgnoreCase(txt_email.getText().trim())) {
                        JOptionPane.showMessageDialog(this, "Email nhân viên đã tồn tại");
                        return;
                    }
                }
            }
            if (!doiTuong.getSoDT().equalsIgnoreCase(sdt)) {
                for (NhanVien nhanVien : listDataNhanVien) {
                    if (nhanVien.getSoDT().equalsIgnoreCase(txt_sodienthoai.getText().trim())) {
                        JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại");
                        return;
                    }
                }
            }
            if (nhanVienService.update(doiTuong, id) > 0) {
                JOptionPane.showMessageDialog(this, "Update thành công");
                fillTable(nhanVienService.getList());
                clearInputFields();
            } else {
                JOptionPane.showMessageDialog(this, "Update thất bại");
            }
        }
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int index = tbltable.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên muốn cập nhật");
        } else {
            String maNV = (String) tbltable.getValueAt(index, 1);
            NhanVien doituong = this.readForm(false); // Sử dụng mật khẩu hiện tại
            int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn cập nhật?");
            if (check == 0) {
                if (nhanVienService.update(doituong, maNV) > 0) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                    clearInputFields();
                    fillTable(nhanVienService.getList());
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
                }
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        load();
        fillTable(nhanVienService.getList());
        clearInputFields();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txttimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttimMouseClicked
        txttim.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_txttimMouseClicked

    private void txttimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimKeyReleased
  String search = txttim.getText().trim();
        if (search.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập mã hoặc tên cần tìm");
            return;
        }
        String pattern = "%" + search + "%";
        List<NhanVien> resultList = nhanVienService.searchNhanVien(pattern);
        if (resultList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy");
            fillTable(nhanVienService.getList());
        } else {
            fillTable(resultList);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txttimKeyReleased

    private void tbltableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbltableMouseClicked
        index = tbltable.getSelectedRow();
        this.showData(index);        // TODO add your handling code here:
    }//GEN-LAST:event_tbltableMouseClicked

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cbbFilterTrangThai;
    private javax.swing.JComboBox<String> cbbTrangThai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioNam;
    private javax.swing.JRadioButton jRadioNhanVien;
    private javax.swing.JRadioButton jRadioNu;
    private javax.swing.JRadioButton jRadioQuanLy;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbltable;
    private javax.swing.JTextField txt_diachi;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_manv;
    private javax.swing.JTextField txt_sodienthoai;
    private javax.swing.JTextField txt_tennv;
    private javax.swing.JTextField txttim;
    // End of variables declaration//GEN-END:variables
}
