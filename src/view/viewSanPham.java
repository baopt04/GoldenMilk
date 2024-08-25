/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.microsoft.schemas.office.visio.x2012.main.CellType;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.DoiTuong;
import model.DonViTinh;
import model.HamLuongDuong;
import model.HangSanPham;
import model.HuongVi;
import model.LoSanXuat;
import model.LoaiSanPham;
import model.SanPham;
import model.SanPhamChiTiet;
import model.TheTich;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.DoiTuongService;
import service.DonViTinhService;
import service.HamLuongDuongService;
import service.HangService;
import service.HuongViSerivce;
import service.LoSanXuatService;
import service.LoaiSerive;
import service.NhaCungCapSerive;
import service.SanPhamChiTietSerivce;
import service.SanPhamService;
import service.TheTichSerivce;

/**
 *
 * @author OSC
 */
public class viewSanPham extends javax.swing.JPanel {

    SanPhamService sanPhamService = new SanPhamService();
    DefaultTableModel tblModel = new DefaultTableModel();
    SanPhamChiTietSerivce sanPhamChiTietSerivce = new SanPhamChiTietSerivce();
    LoaiSerive loaiSerive = new LoaiSerive();
    DoiTuongService doiTuongService = new DoiTuongService();
    DonViTinhService donViTinhService = new DonViTinhService();
    HamLuongDuongService hamLuongDuongService = new HamLuongDuongService();
    LoSanXuatService loSanXuatService = new LoSanXuatService();
    NhaCungCapSerive nhaCungCapSerive = new NhaCungCapSerive();
    TheTichSerivce theTichSerivce = new TheTichSerivce();
    HuongViSerivce huongViSerivce = new HuongViSerivce();
    HangService hangService = new HangService();

    String path;
    int index = -1;
    int id_sanPham;
    private static final String outputFolder = "D:\\QuanLySua2 (1)\\QuanLySua2\\QuanLySua\\QuanLyBanSua\\src\\qrcode";

    public viewSanPham() {
        initComponents();
        fillSanPham(sanPhamService.getList());
        fillSanPhamChiTiet(sanPhamChiTietSerivce.getList());
    }

    private static void genrateQRCode(String text, int width, int height, String fileName)
            throws WriterException, IOException {
        Path folderPath = FileSystems.getDefault().getPath(outputFolder);
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        QRCodeWriter qr = new QRCodeWriter();
        BitMatrix bm = qr.encode(text, BarcodeFormat.QR_CODE, width, height);
        String filePath = outputFolder + "\\" + fileName;
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bm, "PNG", path);
        System.out.println("QR Code saved to: " + path.toAbsolutePath());
    }

    void fillSanPham(List<SanPham> list) {
        fillHangSP();
        fillLoai();
        fillDoiTuong();
        int stt = 1;
        tblModel = (DefaultTableModel) tblsanpham.getModel();
        tblModel.setRowCount(0);
        for (SanPham sanPham : list) {
            tblModel.addRow(new Object[]{
                stt++,
                sanPham.getMaSanPham(),
                sanPham.getTenSanPham(),
                sanPham.getTenLoaiSanPham(),
                sanPham.getTenhangSanPham(),
                sanPham.getTenDoiTuongSD(),
                sanPham.getTrangThai(),
                sanPham.getGhiChu()
            });
        }

    }

    void fillSanPhamChiTiet(List<SanPhamChiTiet> list) {

        tblModel = (DefaultTableModel) tbltable.getModel();
        tblModel.setRowCount(0);
        int stt = 1;
        for (SanPhamChiTiet sanPhamChiTiet : list) {
            tblModel.addRow(new Object[]{
                stt++,
                sanPhamChiTiet.getMaSanPham(),
                sanPhamChiTiet.getTenSanPham(),
                sanPhamChiTiet.getTenLoSanXuat(),
                sanPhamChiTiet.getNhaCungCap(),
                sanPhamChiTiet.getTenHamLuongDuong(),
                sanPhamChiTiet.getTenHuongVi(),
                sanPhamChiTiet.getTenDonViTinh(),
                sanPhamChiTiet.getTenTheTich(),
                sanPhamChiTiet.getNSX(),
                sanPhamChiTiet.getHSD(),
                sanPhamChiTiet.getDonGia(),
                sanPhamChiTiet.getSoLuong()
            });

        }
        
        fillDonViTinh();
        fillHamLuong();
        fillHuongVi();
        fillLoSX();
        fillTheTich();
    }

    void fillLoai() {
        List<LoaiSanPham> loaiSanPhams = loaiSerive.getList();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (LoaiSanPham loaiSanPham : loaiSanPhams) {
            model.addElement(loaiSanPham.getTenLoaiSanPham());
        }
        cboloaisp.setModel(model);
    }

    void fillHangSP() {
        List<HangSanPham> hangSanPhams = hangService.getList();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (HangSanPham hangSanPham : hangSanPhams) {
            model.addElement(hangSanPham.getTenHangSanPham());
        }
        cbohangsp.setModel(model);
    }

    void fillHamLuong() {
        List<HamLuongDuong> hamLuongDuongs = hamLuongDuongService.getList();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (HamLuongDuong hamLuongDuong : hamLuongDuongs) {
            model.addElement(hamLuongDuong.getTenHamLuong());
        }
        cboluongduong.setModel(model);
    }

    void fillDonViTinh() {
        List<DonViTinh> donViTinhs = donViTinhService.getList();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (DonViTinh donViTinh : donViTinhs) {
            model.addElement(donViTinh.getTenDonVi());
        }
        cbodonvitinh.setModel(model);
    }

    void fillDoiTuong() {
        List<DoiTuong> doiTuongs = doiTuongService.getList();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (DoiTuong doiTuong : doiTuongs) {
            model.addElement(doiTuong.getTenDoiTuongSD());
        }
        cbodoituong.setModel(model);
    }

    void fillHuongVi() {
        List<HuongVi> huongVis = huongViSerivce.getList();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (HuongVi huongVi : huongVis) {
            model.addElement(huongVi.getTenHuongVi());
        }
        cbohuongvi.setModel(model);
    }

    void fillLoSX() {
        List<LoSanXuat> loSanXuats = loSanXuatService.getList();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (LoSanXuat loSanXuat : loSanXuats) {
            model.addElement(loSanXuat.getTenLoSanXuat());
        }
        cbolosx.setModel(model);
    }

    void fillTheTich() {
        List<TheTich> theTichs = theTichSerivce.getList();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (TheTich theTich : theTichs) {
            model.addElement(theTich.getTenTheTich());
        }
        cbott.setModel(model);
    }

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean checkData() {
        if (txtmasp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã");
            return false;
        }
        if (txttensp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên");
            return false;
        }
        if (txtghichu.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ghi chu");
            return false;
        }
        if (sanPhamService.isMaSanPhamExists(txtmasp.getText().trim())){
            JOptionPane.showMessageDialog(this, "Mã trùng vui lòng nhập lại");
            return false;
        }
        if (sanPhamService.isTenSanPhamExists(txttensp.getText().trim())){
            JOptionPane.showMessageDialog(this, "Tên trung vui lòng nhập lại");
            return false;
        }
        return true;
    }

    public SanPham readForm() {
        String ma, ten, tenHang, tenLoai, tenDoiTuongSD, moTa, trangThai;
        ma = txtmasp.getText();
        ten = txttensp.getText();
        moTa = txtghichu.getText();
        trangThai = rdoban.isSelected() ? "Đang bán" : "Ngừng bán";
        tenHang = (String) cbohangsp.getSelectedItem();
        tenLoai = (String) cboloaisp.getSelectedItem();
        tenDoiTuongSD = (String) cbodoituong.getSelectedItem();
        try {

            String fileName = ma + ".png";

            genrateQRCode(ma, 1250, 1250, fileName);
            System.out.println("QR Code generated successfully.");
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        return new SanPham(ma, ten, tenHang, tenLoai, tenDoiTuongSD, moTa, trangThai);
    }

    public SanPhamChiTiet read() {
        String tenHuongVi, tenLuongDuong, tenLoSanXua, tenDonViTinh,
                tenTheTich;
        tenHuongVi = (String) cbohuongvi.getSelectedItem();
        tenLuongDuong = (String) cboluongduong.getSelectedItem();
        tenLoSanXua = (String) cbolosx.getSelectedItem();
        tenDonViTinh = (String) cbodonvitinh.getSelectedItem();
        tenTheTich = (String) cbott.getSelectedItem();
        return new SanPhamChiTiet(0, tenLuongDuong, tenHuongVi, tenTheTich, tenLoSanXua, tenDonViTinh);
    }

    public SanPhamChiTiet read1() {
        String tenHuongVi, tenLuongDuong, tenLoSanXuat, tenDonViTinh,
                tenTheTich, tenmaSP, soLuong, dongia, anh;
        tenmaSP = txttensp1.getText();
        soLuong = txtsoluongton.getText();
        dongia = txtdongia.getText();
        anh = path;
        tenHuongVi = (String) cbohuongvi.getSelectedItem();
        tenLuongDuong = (String) cboluongduong.getSelectedItem();
        tenLoSanXuat = (String) cbolosx.getSelectedItem();
        tenDonViTinh = (String) cbodonvitinh.getSelectedItem();
        tenTheTich = (String) cbott.getSelectedItem();
        return new SanPhamChiTiet(tenmaSP, tenLuongDuong, tenHuongVi, tenTheTich, tenLoSanXuat, tenDonViTinh, dongia, soLuong, anh);
    }

    public SanPham getByMaSP(String maSP) {
        for (SanPham sp : sanPhamService.getList()) {
            if (sp.getMaSanPham().equals(maSP)) {
                System.out.println(sp);
                return sp;
            }
        }
        return null;
    }

    public SanPhamChiTiet getByMaSPCT(String maSP) {
        for (SanPhamChiTiet sp : sanPhamChiTietSerivce.getList()) {
            if (sp.getMaSanPham().equals(maSP)) {
                System.out.println(sp);
                return sp;
            }
        }
        return null;
    }

    public void showData(String maSP) {
        SanPham doiTuong = getByMaSP(maSP);
        if (doiTuong != null) {
            txtmasp.setText(doiTuong.getMaSanPham());
            txttensp.setText(doiTuong.getTenSanPham());
            txtghichu.setText(doiTuong.getGhiChu());
            if (doiTuong.getTrangThai().equals("Đang bán")) {
                rdoban.setSelected(true);
            } else {
                rdongungban.setSelected(true);
            }
            cbohangsp.setSelectedItem(doiTuong.getTenLoaiSanPham());
            cboloaisp.setSelectedItem(doiTuong.getTenhangSanPham());
            cbodoituong.setSelectedItem(doiTuong.getTenDoiTuongSD());
        } else {
            System.out.println("Không tìm thấy sản phẩm có mã " + maSP);
        }
    }

    public void showDataSPCT(String maSP) {
        txttensp1.setEnabled(false);
        SanPhamChiTiet spct = getByMaSPCT(maSP);
        id_sanPham = spct.getId_SPCT();
        txttensp1.setText(spct.getTenSanPham());
        cbolosx.setSelectedItem(spct.getTenLoSanXuat());
        cboluongduong.setSelectedItem(spct.getTenHamLuongDuong());
        cbohuongvi.setSelectedItem(spct.getTenHuongVi());
        cbodonvitinh.setSelectedItem(spct.getTenDonViTinh());
        cbott.setSelectedItem(spct.getTenTheTich());
//       selectComboBoxItem(cbodonvitinh, spct.getTenDonViTinh());
//        selectComboBoxItem(cbott, spct.getTenTheTich());
        txtdongia.setText(spct.getDonGia());
        txtsoluongton.setText(spct.getSoLuong());
        String imagePath = spct.getAnh();
        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                File imgFile = new File(imagePath);
                if (imgFile.exists()) {
                    BufferedImage bi = ImageIO.read(imgFile);
                    Image img = bi.getScaledInstance(155, 182, Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(img);
                    lbl_photo.setIcon(imageIcon);
                } else {
                    lbl_photo.setIcon(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                lbl_photo.setIcon(null);
            }
        } else {
            lbl_photo.setIcon(null);
        }

    }

    public boolean isAlpha(String str) {
        return str != null && str.matches("[a-zA-Z]+");
    }

    private void selectComboBoxItem(JComboBox<String> comboBox, String itemToSelect) {
        System.out.println("Item to select: '" + itemToSelect + "'");
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            String item = comboBox.getItemAt(i);
            System.out.println("Comparing with item: '" + item + "'");
            if (item.equals(itemToSelect)) {
                comboBox.setSelectedIndex(i);
                return;
            }
        }
        System.out.println("Item not found: '" + itemToSelect + "'");
    }

    public boolean check() {
        if (txtsoluongton.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng");
            return false;
        }
        if (isAlpha(txtsoluongton.getText())) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng là số ");
            return false;
        }
        if (txtdongia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn giá");
            return false;
        }
        if (isAlpha(txtdongia.getText())) {
            JOptionPane.showMessageDialog(this, "Vui đơn giá là số");
        }
        return true;
    }

    public String chooseImage() {
        JFileChooser chooser = new JFileChooser();
        int option = chooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            path = file.getAbsolutePath();
            try {
                Image bi = ImageIO.read(new File(path));
                Image img = bi.getScaledInstance(155, 182, Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(img);
                lbl_photo.setIcon(imageIcon);
                return path;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String chonDuongDan() {
        JFileChooser fileChooser = new JFileChooser();
        String filePath = null;
        // Thiết lập chỉ hiển thị hộp thoại lưu file
        fileChooser.setDialogTitle("Chọn đường dẫn để lưu file");

        // Hiển thị hộp thoại chọn đường dẫn lưu file
        int userSelection = fileChooser.showSaveDialog(null);

        // Kiểm tra nếu người dùng đã chọn một đường dẫn
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Lấy đường dẫn mà người dùng đã chọn
            File selectedFile = fileChooser.getSelectedFile();

            // Kiểm tra xem đường dẫn đã được chọn chưa
            if (selectedFile != null) {
                filePath = selectedFile.getAbsolutePath();
            }
        }

        return filePath;
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtmasp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txttensp = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbohangsp = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cboloaisp = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtghichu = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txttimkiem = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblsanpham = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        rdoban = new javax.swing.JRadioButton();
        rdongungban = new javax.swing.JRadioButton();
        jLabel28 = new javax.swing.JLabel();
        cbodoituong = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txttensp1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbohuongvi = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        cbodonvitinh = new javax.swing.JComboBox<>();
        cboluongduong = new javax.swing.JComboBox<>();
        cbolosx = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        cbott = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        txtsoluongton = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtdongia = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        btnanh = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbltable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        lbl_photo = new javax.swing.JLabel();
        txtgiamin = new javax.swing.JTextField();
        txtgiamax = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jPanel13.setBackground(new java.awt.Color(66, 184, 234));

        jPanel1.setBackground(new java.awt.Color(66, 184, 234));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Quản lý sản phẩm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(593, 593, 593)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.setBackground(new java.awt.Color(66, 184, 234));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mã sản phẩm:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tên sản phẩm:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Hãng sản phẩm:");

        cbohangsp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Loại sản phẩm:");

        cboloaisp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-15.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-refresh-15.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-15.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-refresh-15.png"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Trạng thái:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Ghi chú:");

        jButton1.setText("Thêm");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
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

        txttimkiem.setText("Tìm kiếm theo mã hoặc tên sản phẩm....");
        txttimkiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttimkiemMouseClicked(evt);
            }
        });
        txttimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiemKeyReleased(evt);
            }
        });

        jScrollPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane3MouseClicked(evt);
            }
        });

        tblsanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Hãng sản phẩm", "Loại sản phẩm", "Đối tượng", "Trạng Thái", "Ghi chú"
            }
        ));
        tblsanpham.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblsanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsanphamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblsanphamMouseEntered(evt);
            }
        });
        jScrollPane3.setViewportView(tblsanpham);

        jButton9.setText("Xuất Excel");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });

        buttonGroup1.add(rdoban);
        rdoban.setForeground(new java.awt.Color(255, 255, 255));
        rdoban.setSelected(true);
        rdoban.setText("Đang bán");

        buttonGroup1.add(rdongungban);
        rdongungban.setForeground(new java.awt.Color(255, 255, 255));
        rdongungban.setText("Ngừng bán");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Đối tượng:");

        cbodoituong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbodoituong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbodoituongActionPerformed(evt);
            }
        });

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-15.png"))); // NOI18N
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
        });

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-refresh-15.png"))); // NOI18N
        jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel37MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmasp, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttensp, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboloaisp, 0, 161, Short.MAX_VALUE)
                            .addComponent(cbohangsp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel28))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jButton9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbodoituong, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(txtghichu, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rdoban)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdongungban)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(533, 533, 533)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(586, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmasp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbohangsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbodoituong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtghichu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboloaisp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdoban)
                        .addComponent(rdongungban)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3)
                        .addComponent(jButton4)))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel2);

        jPanel3.setBackground(new java.awt.Color(66, 184, 234));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Tên sản phẩm:");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Hương vị:");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Lượng đường:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Lô sản xuất:");

        cbohuongvi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-15.png"))); // NOI18N
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-refresh-15.png"))); // NOI18N
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-15.png"))); // NOI18N
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-refresh-15.png"))); // NOI18N
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-15.png"))); // NOI18N
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-refresh-15.png"))); // NOI18N
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel26MouseClicked(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Đơn vị tính:");

        cbodonvitinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboluongduong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbolosx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Thể tích:");

        cbott.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Số lượng tồn:");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Đơn giá:");

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-15.png"))); // NOI18N
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel32MouseClicked(evt);
            }
        });

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-refresh-15.png"))); // NOI18N
        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel33MouseClicked(evt);
            }
        });

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-15.png"))); // NOI18N
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
        });

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-refresh-15.png"))); // NOI18N
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
        });

        btnanh.setText("Chọn");
        btnanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnanhMouseClicked(evt);
            }
        });

        jButton7.setText("Cập nhật");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Làm mới");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        txtsearch.setText("Tìm kiếm theo mã hoặc tên sản phẩm....");
        txtsearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtsearchMouseClicked(evt);
            }
        });
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
        });

        jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 500));
        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        tbltable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "MaSP", "Tên sản phẩm", "Lô sản xuất", "Nhà cung cấp", "Hàm lượng đường", "Hương vị ", "Đơn vị tính", "Thể tích", "NSX", "HSD", "Đơn giá ", "Số lượng"
            }
        ));
        tbltable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbltableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbltable);

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(lbl_photo, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbl_photo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        txtgiamax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtgiamaxKeyReleased(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Từ:");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Đến:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbohuongvi, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cboluongduong, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel29))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                                .addComponent(jLabel30))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(txttensp1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(cbolosx, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton7)
                        .addGap(35, 35, 35)
                        .addComponent(jButton8)
                        .addGap(62, 62, 62)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtsoluongton, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdongia, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbott, 0, 182, Short.MAX_VALUE)
                                    .addComponent(cbodonvitinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel32)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel33))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(130, 130, 130))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(46, 379, Short.MAX_VALUE)
                        .addComponent(btnanh)
                        .addGap(163, 163, 163))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(318, 318, 318)
                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtgiamin, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtgiamax, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(txttensp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel27)
                                .addComponent(cbodonvitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel29)
                                        .addComponent(cbott, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel30)
                                    .addComponent(txtsoluongton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtdongia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel16)
                                        .addComponent(cbohuongvi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel17)
                                        .addComponent(cboluongduong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel18)
                                        .addComponent(cbolosx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7)
                            .addComponent(jButton8)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnanh)))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtgiamin, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtgiamax, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sản phẩm chi tiết", jPanel3);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
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

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        HangView hangView = new  HangView();
hangView.setVisible(true);
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        fillHangSP();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        LoaiSanPhamView loaiSanPhamView = new LoaiSanPhamView();
        loaiSanPhamView.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        fillLoai();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel11MouseClicked
    private List<SanPham> currentList = new ArrayList<>();

    private void initializeTable() {
        currentList = sanPhamService.getList();

        fillSanPham(currentList);
    }

    void updateTableWithNewProduct(SanPham newProduct) {
        DefaultTableModel tblModel = (DefaultTableModel) tblsanpham.getModel();

        tblModel.insertRow(0, new Object[]{
            1,
            newProduct.getMaSanPham(),
            newProduct.getTenSanPham(),
            newProduct.getTenLoaiSanPham(),
            newProduct.getTenhangSanPham(),
            newProduct.getTenDoiTuongSD(),
            newProduct.getTrangThai(),
            newProduct.getGhiChu()
        });
        for (int i = 0; i < tblModel.getRowCount(); i++) {
            tblModel.setValueAt(i + 1, i, 0);
        }

    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if (checkData()) {
            SanPham doituong = this.readForm();
            SanPhamChiTiet spct = this.read();
            if (sanPhamService.addSP(doituong, spct) > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
//                List<SanPham> list = sanPhamService.getList();
                currentList.add(0, doituong);

                // Cập nhật bảng với danh sách hiện tại
                updateTableWithNewProduct(doituong);
                txtghichu.setText("");
                txtmasp.setText("");
                txttensp.setText("");

            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }

        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        index = tblsanpham.getSelectedRow();
        SanPham doiTuong = this.readForm();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn trường cần sửa");
        } else {
            String id = tblsanpham.getValueAt(index, 1).toString();
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhật?", "Xác nhận cập nhật", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                if (sanPhamService.update(doiTuong, id) > 0) {
                    JOptionPane.showMessageDialog(this, "Update thành công");
                    fillSanPham(sanPhamService.getList());
                    txtghichu.setText("");
                    txtmasp.setText("");
                    txttensp.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Update thất bại ");
                }
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        txtghichu.setText("");
        txtmasp.setText("");
        txttensp.setText("");
        fillHangSP();
        fillLoai();
        fillSanPham(sanPhamService.getList());// TODO add your handling code here:
    }//GEN-LAST:event_jButton4MouseClicked

    private void txttimkiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttimkiemMouseClicked
        txttimkiem.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_txttimkiemMouseClicked


    private void tblsanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsanphamMouseClicked

        if (evt.getClickCount() == 2) {
            System.out.println("double clilc ");
            String seach = txtmasp.getText();
            List<SanPhamChiTiet> list = sanPhamChiTietSerivce.timKiem(seach);
            fillSanPhamChiTiet(list);
            jTabbedPane1.setSelectedIndex(1);
        } else {
            index = tblsanpham.getSelectedRow();
            String maSP = (String) tblsanpham.getValueAt(index, 1);
            showData(maSP);

//            this.showData(index);
        }

    }//GEN-LAST:event_tblsanphamMouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        HuongViView huongViView = new HuongViView();
        huongViView.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        fillHuongVi();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        HamLuongDuongView hamLuongDuongView = new HamLuongDuongView();
        hamLuongDuongView.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel23MouseClicked

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
        fillHamLuong();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel24MouseClicked

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked
        LoSanXuatView loSanXuatView = new LoSanXuatView();
        loSanXuatView.setVisible(true);
        //hi hi = new hi();
        //hi.setVisible(true);
    }//GEN-LAST:event_jLabel25MouseClicked

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseClicked
        fillLoSX();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel26MouseClicked

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
        DonViTinhView donViTinhView = new DonViTinhView();
        donViTinhView.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel32MouseClicked

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        fillDonViTinh();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel33MouseClicked

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
        TheTichView theTichView = new TheTichView();
        theTichView.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel34MouseClicked

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
        fillTheTich();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel35MouseClicked

    private void btnanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnanhMouseClicked

        chooseImage();
    }//GEN-LAST:event_btnanhMouseClicked

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        String soLuong = txtsoluongton.getText();
        String donGia = txtdongia.getText();
        index = tbltable.getSelectedRow();
        SanPhamChiTiet doiTuong = this.read1();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn trường cần sửa");
        }
        if (!isNumeric(soLuong) || !isNumeric(donGia)) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập là số");
        } else {
            String id = tbltable.getValueAt(index, 1).toString();
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhật?", "Xác nhận cập nhật", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                if (sanPhamService.updateSPCT(doiTuong, id) > 0) {
                    JOptionPane.showMessageDialog(this, "Update thành công");
                    txtdongia.setText("");
                    txtsoluongton.setText("");
                    txttensp1.setText("");
                    lbl_photo.setIcon(null);
                    fillSanPhamChiTiet(sanPhamChiTietSerivce.getList());
                } else {
                    JOptionPane.showMessageDialog(this, "Update thất bại");
                }
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7MouseClicked

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        fillSanPhamChiTiet(sanPhamChiTietSerivce.getList());        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void txtsearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtsearchMouseClicked
        txtsearch.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchMouseClicked

    private void tbltableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbltableMouseClicked
//        index = tbltable.getSelectedRow();
//        this.showDataSPCT(index);  
        index = tbltable.getSelectedRow();
        String maSP = (String) tbltable.getValueAt(index, 1);
        showDataSPCT(maSP);
// TODO add your handling code here:
    }//GEN-LAST:event_tbltableMouseClicked

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Danh sách sản phẩm");
            sheet.setColumnWidth(0, 3000);
            sheet.setColumnWidth(1, 6000);
            sheet.setColumnWidth(2, 6000);
            sheet.setColumnWidth(3, 6000);
            sheet.setColumnWidth(4, 6000);
            sheet.setColumnWidth(5, 6000);
            sheet.setColumnWidth(6, 6000);
            sheet.setColumnWidth(7, 6000);
            sheet.setColumnWidth(8, 6000);
            sheet.setColumnWidth(9, 6000);
            sheet.setColumnWidth(10, 6000);
            sheet.setColumnWidth(11, 6000);
            sheet.setColumnWidth(12, 6000);
            sheet.setColumnWidth(13, 6000);
            sheet.setColumnWidth(14, 6000);
            sheet.setColumnWidth(15, 6000);
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(0);
            cell = row.createCell(0, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue("Danh sách sản phẩm");
            row = sheet.createRow(1);
            cell = row.createCell(0, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue("Mã sản phẩm");
            cell = row.createCell(1, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue("Tên sản phẩm");
            cell = row.createCell(2, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue("Loại sản phẩm");
            cell = row.createCell(3, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue("Hãng sản phẩm");
            cell = row.createCell(4, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue("Lô sản xuất");
            cell = row.createCell(5, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue("Nhà cung cấp");
            cell = row.createCell(6, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue("Đối tượng sử dụng");
            cell = row.createCell(7, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue("Hàm lượng đường");
            cell = row.createCell(8, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue("Hương vị ");
            cell = row.createCell(9, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue("Đơn vị tính");
            cell = row.createCell(10, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue("Thể tích");
            cell = row.createCell(11, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue("Đơn giá ");
            cell = row.createCell(12, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue("Số lượng");
            cell = row.createCell(13, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue("Mô tả");
            cell = row.createCell(14, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue("Trạng thái");
            int i = 0;
            for (SanPhamChiTiet cell1 : sanPhamChiTietSerivce.getAll()) {
                row = sheet.createRow(2 + i);
                cell = row.createCell(0, org.apache.poi.ss.usermodel.CellType.STRING);
                cell.setCellValue(cell1.getMaSanPham());
                cell = row.createCell(1, org.apache.poi.ss.usermodel.CellType.STRING);
                cell.setCellValue(cell1.getTenSanPham());
                cell = row.createCell(2, org.apache.poi.ss.usermodel.CellType.STRING);
                cell.setCellValue(cell1.getTenLoaiSP());
                cell = row.createCell(3, org.apache.poi.ss.usermodel.CellType.STRING);
                cell.setCellValue(cell1.getTenHangSP());
                cell = row.createCell(4, org.apache.poi.ss.usermodel.CellType.STRING);
                cell.setCellValue(cell1.getTenLoSanXuat());
                cell = row.createCell(5, org.apache.poi.ss.usermodel.CellType.STRING);
                cell.setCellValue(cell1.getNhaCungCap());
                cell = row.createCell(6, org.apache.poi.ss.usermodel.CellType.STRING);
                cell.setCellValue(cell1.getTenDoiTuongSD());
                cell = row.createCell(7, org.apache.poi.ss.usermodel.CellType.STRING);
                cell.setCellValue(cell1.getTenHamLuongDuong());
                cell = row.createCell(8, org.apache.poi.ss.usermodel.CellType.STRING);
                cell.setCellValue(cell1.getTenHuongVi());
                cell = row.createCell(9, org.apache.poi.ss.usermodel.CellType.STRING);
                cell.setCellValue(cell1.getTenDonViTinh());
                cell = row.createCell(10, org.apache.poi.ss.usermodel.CellType.STRING);
                cell.setCellValue(cell1.getTenTheTich());
                cell = row.createCell(11, org.apache.poi.ss.usermodel.CellType.STRING);
                cell.setCellValue(cell1.getDonGia());
                cell = row.createCell(12, org.apache.poi.ss.usermodel.CellType.STRING);
                cell.setCellValue(cell1.getSoLuong());
                cell = row.createCell(13, org.apache.poi.ss.usermodel.CellType.STRING);
                cell.setCellValue(cell1.getMoTaSP());
                cell = row.createCell(14, org.apache.poi.ss.usermodel.CellType.STRING);
                cell.setCellValue(cell1.getTrangThaiSP());
                i++;
            }
            File file = new File(chonDuongDan() + ".xlsx");
            try {
                FileOutputStream fos = new FileOutputStream(file);
                workbook.write(fos);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
            JOptionPane.showMessageDialog(this, "Đã xuất file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton9MouseClicked

    private void jScrollPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane3MouseClicked


    private void tblsanphamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsanphamMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblsanphamMouseEntered

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
        String seach = txtsearch.getText();
        List<SanPhamChiTiet> list = sanPhamChiTietSerivce.timKiem(seach);
        fillSanPhamChiTiet(list);
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchKeyReleased


    private void txttimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemKeyReleased
        String seach = txttimkiem.getText();
        List<SanPham> list = sanPhamService.timKiemSP(seach);
        fillSanPham(list);

    }//GEN-LAST:event_txttimkiemKeyReleased

    private void txtgiamaxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtgiamaxKeyReleased
        double giaMin = Double.parseDouble(txtgiamin.getText());
        double giaMax = Double.parseDouble(txtgiamax.getText());
        List<SanPhamChiTiet> list = sanPhamChiTietSerivce.timKiem1s(giaMin, giaMax);
        fillSanPhamChiTiet(list);        // TODO add your handling code here:
    }//GEN-LAST:event_txtgiamaxKeyReleased

    private void cbodoituongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbodoituongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbodoituongActionPerformed

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
        DoiTuongView dt = new DoiTuongView();
        dt.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_jLabel36MouseClicked

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
        fillDoiTuong();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel37MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnanh;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbodoituong;
    private javax.swing.JComboBox<String> cbodonvitinh;
    private javax.swing.JComboBox<String> cbohangsp;
    private javax.swing.JComboBox<String> cbohuongvi;
    private javax.swing.JComboBox<String> cboloaisp;
    private javax.swing.JComboBox<String> cbolosx;
    private javax.swing.JComboBox<String> cboluongduong;
    private javax.swing.JComboBox<String> cbott;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbl_photo;
    private javax.swing.JRadioButton rdoban;
    private javax.swing.JRadioButton rdongungban;
    private javax.swing.JTable tblsanpham;
    private javax.swing.JTable tbltable;
    private javax.swing.JTextField txtdongia;
    private javax.swing.JTextField txtghichu;
    private javax.swing.JTextField txtgiamax;
    private javax.swing.JTextField txtgiamin;
    private javax.swing.JTextField txtmasp;
    public javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtsoluongton;
    private javax.swing.JTextField txttensp;
    private javax.swing.JTextField txttensp1;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}
