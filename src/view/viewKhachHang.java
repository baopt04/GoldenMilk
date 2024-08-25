/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.KhachHangMD;
import service.KhachHangService;
import service.KhachHangService;

/**
 *
 * @author OSC
 */
public class viewKhachHang extends javax.swing.JPanel {
        private KhachHangService sm= new KhachHangService();
private DefaultTableModel mol= new DefaultTableModel();
private  int i=-1;
   
    /**
     * Creates new form viewHoaDon
     */
    public viewKhachHang() {
        initComponents();
                this.fillTable(sm.getAll());

    }
   void fillTable(ArrayList<KhachHangMD> list){
        mol=(DefaultTableModel)tblKhachHang.getModel();
        mol.setRowCount(0);// xoa du lieu cu trong bang
        //them dong trong bang
        
        for(KhachHangMD x:list){
            mol.addRow(x.toDataRow());
        }
    }
      KhachHangMD readForm(){
        //doc du lieu tu form de them/ update
        //test du lieu ko duoc trong, chieu cao can nang phai la so
        
        String ma, ten, sdt, email, diaChi;
        boolean gioiTinh;

        ma = txtMaKH.getText().trim();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập mã");
            txtMaKH.requestFocus();
            return null;
        }
        // Kiểm tra mã bắt đầu bằng "KH"
        if (!isValidMa(ma)) {
            JOptionPane.showMessageDialog(this, "Mã khách hàng phải bắt đầu bằng 'KH'");
            txtMaKH.requestFocus();
            return null;
        }

        // Kiểm tra mã không trùng
        if (isMaTrung(ma)) {
            JOptionPane.showMessageDialog(this, "Mã khách hàng đã tồn tại");
            txtMaKH.requestFocus();
            return null;
        }

        ten = txtTenKH.getText().trim();
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập họ tên");
            txtTenKH.requestFocus();
            return null;
        }

        sdt = txtSDT.getText().trim();
        if (sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập số điện thoại");
            txtSDT.requestFocus();
            return null;
        }

        // Kiểm tra số điện thoại đúng định dạng và không trùng
        if (!isValidSDT(sdt)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
            txtSDT.requestFocus();
            return null;
        }
        if (isSDTTrung(sdt)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại");
            txtSDT.requestFocus();
            return null;
        }

        email = txtEmail.getText().trim();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập email");
            txtEmail.requestFocus();
            return null;
        }

        // Kiểm tra email đúng định dạng
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ");
            txtEmail.requestFocus();
            return null;
        }
        if (isEmailTrung(email)) {
            JOptionPane.showMessageDialog(this, "Email đã tồn tại");
            txtEmail.requestFocus();
            return null;
        }

        diaChi = txtDC.getText().trim();
        if (diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập địa chỉ");
            txtDC.requestFocus();
            return null;
        }
        

        gioiTinh = rdoNam.isSelected();

        return new KhachHangMD(ma, ten, gioiTinh, sdt, email, diaChi);
        
    }
        private boolean isMaTrung(String ma) {
        // Kiểm tra mã khách hàng có tồn tại hay không trong database
        // Giả sử sm.getAll() trả về danh sách tất cả các khách hàng
        List<KhachHangMD> list = sm.getAll();
        for (KhachHangMD kh : list) {
            if (kh.getMaKH().equals(ma)) {
                return true;
            }
        }
        return false;
    }
             private boolean isValidMa(String ma) {
        // Kiểm tra mã bắt đầu bằng KH
        String regex = "^KH.*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ma);
        return matcher.matches();
    }
     private boolean isSDTTrung(String sdt) {
        // Kiểm tra số điện thoại có tồn tại hay không trong database
        List<KhachHangMD> list = sm.getAll();
        for (KhachHangMD kh : list) {
            if (kh.getSoDT().equals(sdt)) {
                return true;
            }
        }
        return false;
    }
     private boolean isValidSDT(String sdt) {
        // Kiểm tra định dạng số điện thoại (ví dụ: 10 chữ số)
        String regex = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sdt);
        return matcher.matches();
    }
      private boolean isEmailTrung(String email) {
        // Kiểm tra số điện thoại có tồn tại hay không trong database
        List<KhachHangMD> list = sm.getAll();
        for (KhachHangMD kh : list) {
            if (kh.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    private boolean isValidEmail(String email) {
        // Kiểm tra định dạng email
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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
        jPanel13 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDC = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua1 = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtTimSDT = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();

        jPanel13.setBackground(new java.awt.Color(66, 184, 234));

        jPanel1.setBackground(new java.awt.Color(66, 184, 234));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Quản lý khách hàng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(651, 651, 651)
                .addComponent(jLabel1)
                .addContainerGap(508, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.setBackground(new java.awt.Color(66, 184, 234));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Mã khách hàng:");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Họ tên:");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Số điện thoại:");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Email:");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Địa chỉ:");

        txtDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDCActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Giới tính:");

        buttonGroup1.add(rdoNam);
        rdoNam.setForeground(new java.awt.Color(255, 255, 255));
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");
        rdoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNamActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoNu);
        rdoNu.setForeground(new java.awt.Color(255, 255, 255));
        rdoNu.setText("Nữ");
        rdoNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNuActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua1.setText("Xóa");
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });

        btnSua.setText("Chỉnh sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(66, 184, 234));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Mời nhập SDT khách hàng :");

        txtTimSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimSDTActionPerformed(evt);
            }
        });
        txtTimSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimSDTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimSDTKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã KH", "Họ tên", "Giới tính", "Số điện thoại", "Email", "Địa chỉ"
            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(472, 472, 472)
                                .addComponent(jLabel7)
                                .addGap(21, 21, 21)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(385, 385, 385)
                                .addComponent(jLabel9)
                                .addGap(31, 31, 31)
                                .addComponent(rdoNam)
                                .addGap(52, 52, 52)
                                .addComponent(rdoNu))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(343, 343, 343)
                                .addComponent(btnThem)
                                .addGap(18, 18, 18)
                                .addComponent(btnSua1)
                                .addGap(18, 18, 18)
                                .addComponent(btnSua)
                                .addGap(18, 18, 18)
                                .addComponent(btnLamMoi)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                            .addComponent(txtSDT)
                            .addComponent(txtMaKH))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDC, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(438, 438, 438))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(txtDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu)
                    .addComponent(jLabel9))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua)
                    .addComponent(btnLamMoi))
                .addGap(16, 16, 16)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void txtDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDCActionPerformed

    private void rdoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNamActionPerformed

    private void rdoNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNuActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if(this.readForm()!=null){// them duoc
            int chon= JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không");
            if(chon==0){ //co them
                sm.them(this.readForm());//them vao database
                this.fillTable(sm.getAll());
                JOptionPane.showMessageDialog(this, "Thêm thành công");

            }
            else// ko chon them duoc
            JOptionPane.showMessageDialog(this, "Bạn không chọn thêm");
        }
//        else
//        JOptionPane.showMessageDialog(this, "Mời nhập đầy đủ thông tin khách hàng");

    }//GEN-LAST:event_btnThemActionPerformed
   KhachHangMD readForm2(String currentMa, String currentSDT, String currentEmail) {
    // Đọc dữ liệu từ form để thêm/ update
    String ma, ten, sdt, email, diaChi;
    boolean gioiTinh;

    ma = txtMaKH.getText().trim();
    if (ma.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Chưa nhập mã");
        txtMaKH.requestFocus();
        return null;
    }
    // Kiểm tra mã bắt đầu bằng "KH"
    if (!isValidMa(ma)) {
        JOptionPane.showMessageDialog(this, "Mã khách hàng phải bắt đầu bằng 'KH'");
        txtMaKH.requestFocus();
        return null;
    }

    // Kiểm tra mã không trùng, bỏ qua kiểm tra nếu mã không thay đổi
    if (!ma.equals(currentMa) && isMaTrung(ma)) {
        JOptionPane.showMessageDialog(this, "Mã khách hàng đã tồn tại");
        txtMaKH.requestFocus();
        return null;
    }

    ten = txtTenKH.getText().trim();
    if (ten.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Chưa nhập họ tên");
        txtTenKH.requestFocus();
        return null;
    }

    sdt = txtSDT.getText().trim();
    if (sdt.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Chưa nhập số điện thoại");
        txtSDT.requestFocus();
        return null;
    }

    // Kiểm tra số điện thoại đúng định dạng và không trùng, bỏ qua kiểm tra nếu số điện thoại không thay đổi
    if (!isValidSDT(sdt)) {
        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
        txtSDT.requestFocus();
        return null;
    }
    if (!sdt.equals(currentSDT) && isSDTTrung(sdt)) {
        JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại");
        txtSDT.requestFocus();
        return null;
    }

    email = txtEmail.getText().trim();
    if (email.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Chưa nhập email");
        txtEmail.requestFocus();
        return null;
    }

    // Kiểm tra email đúng định dạng
    if (!isValidEmail(email)) {
        JOptionPane.showMessageDialog(this, "Email không hợp lệ");
        txtEmail.requestFocus();
        return null;
    }
    if (!email.equals(currentEmail) && isEmailTrung(email)) {
        JOptionPane.showMessageDialog(this, "Email đã tồn tại");
        txtEmail.requestFocus();
        return null;
    }

    diaChi = txtDC.getText().trim();
    if (diaChi.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Chưa nhập địa chỉ");
        txtDC.requestFocus();
        return null;
    }

    gioiTinh = rdoNam.isSelected();

    return new KhachHangMD(ma, ten, gioiTinh, sdt, email, diaChi);
}
    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        // TODO add your handling code here:
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa?");
        if (hoi == JOptionPane.YES_OPTION) {
            int i = tblKhachHang.getSelectedRow();
            if (i >= 0) {
                String  maKH = tblKhachHang.getValueAt(i, 0).toString();
                if (sm.xoa(maKH) > 0) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    this.fillTable(sm.getAll());
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa");
            }
        }
        this.clearForm();
    }//GEN-LAST:event_btnSua1ActionPerformed
     void searchPhone(String sdt) {
        RowFilter rf = null;
        mol = (DefaultTableModel) tblKhachHang.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(mol);
        tblKhachHang.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(sdt, 3));
    }
        private void clearForm() {
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtDC.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtTimSDT.setText("");
        this.searchPhone("");
    }

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int i = tblKhachHang.getSelectedRow();

        // Lấy mã và số điện thoại hiện tại từ bảng
        String currentMa = tblKhachHang.getValueAt(i, 0).toString();
        String currentSDT = tblKhachHang.getValueAt(i, 3).toString();
        String currentEmail = tblKhachHang.getValueAt(i, 4).toString();

        // Đọc dữ liệu đã sửa từ form
      int confirm = JOptionPane.showConfirmDialog(
            this, 
            "Bạn có muốn sửa không?", 
            "Xác nhận sửa", 
            JOptionPane.YES_NO_OPTION
        );

        // Kiểm tra kết quả xác nhận
        if (confirm == JOptionPane.YES_OPTION) {
            // Người dùng chọn "Yes", thực hiện hành động sửa
            KhachHangMD khachHang = this.readForm2(currentMa, currentSDT, currentEmail);
            if (khachHang != null) {
                if (sm.sua(currentMa, khachHang) > 0) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                    this.fillTable(sm.getAll());
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thất bại");
                }
            }
        } else {
            // Người dùng chọn "No", không thực hiện hành động
            JOptionPane.showMessageDialog(this, "Hành động sửa bị hủy.");
        }
    

        this.clearForm();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        this.clearForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtTimSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimSDTActionPerformed

    private void txtTimSDTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimSDTKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTimSDTKeyPressed

    private void txtTimSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimSDTKeyReleased
        // TODO add your handling code here:
        searchPhone(txtTimSDT.getText());
    }//GEN-LAST:event_txtTimSDTKeyReleased

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        // TODO add your handling code here:

        i= tblKhachHang.getSelectedRow();
        String  gioiTinh;
        // lay du lieu tu bang day len form
        txtMaKH.setText(tblKhachHang.getValueAt(i, 0).toString());
        txtTenKH.setText(tblKhachHang.getValueAt(i, 1).toString());
        txtSDT.setText(tblKhachHang.getValueAt(i, 3).toString());
        txtEmail.setText(tblKhachHang.getValueAt(i, 4).toString());
        //nam=tblMylove.getValueAt(i, 2).toString();
        //cbbNamsinh.setSelectedItem(nam);
        gioiTinh= tblKhachHang.getValueAt(i, 2).toString();
        if(gioiTinh.equalsIgnoreCase("Nam")){
            rdoNam.setSelected(true);
        } else{
            rdoNu.setSelected(true);
        }
        txtDC.setText(tblKhachHang.getValueAt(i, 5).toString());
    }//GEN-LAST:event_tblKhachHangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtDC;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimSDT;
    // End of variables declaration//GEN-END:variables
}
