/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;
import service.BanHang;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;
import java.sql.Statement;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import model.BanHang_SanPhamMD;
import model.BanHang_GioHangMD;
import service.DBConnect;
import model.BanHang_Voicher;
import static view.DangNhap.maNV;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javax.swing.JLabel;
import service.BanHang_HoaDon;
import service.KhachHangService;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import model.HoaDonChiTiet;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.BanHang_HuongVi;
import model.KhachHangMD;
/**
 *
 * @author OSC
 */
public class viewBanHang extends javax.swing.JPanel implements ThreadFactory , Runnable {
    BanHang banHang_HuongVi = new BanHang();
    BanHang banHang_SanPham = new BanHang();
    BanHang banHang_Voicher = new BanHang();
    BanHang_HoaDon banHang_HoaDon = new BanHang_HoaDon();
    private KhachHangService sm= new KhachHangService();
    private Webcam webcam = null;
    private WebcamPanel panel = null;
    private Executor exception = Executors.newSingleThreadScheduledExecutor(this);
    /**
     * Creates new form viewBanHang
     */
    public viewBanHang() {
         ArrayList<BanHang_SanPhamMD> list =  banHang_SanPham.select();
        initComponents();
        loadTable(list);
        
        showComboData1();
        showComboData2();
        DangNhap dn = new DangNhap();
        txtTenKH.setEditable(false);
        txtSDTKH.setEditable(false);
        result_filed.setEnabled(true);
        
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(today);
        jlbNgayTao.setText(formattedDate);
        
        new Timer(1000, new ActionListener() {
            SimpleDateFormat fomart = new SimpleDateFormat("hh:mm:ss a");
            @Override
            public void actionPerformed(ActionEvent e) {
               jlbNgayTao1.setText(fomart.format(new Date()));
            }
        }).start();
         loadPendingInvoices();
    }
    String maNVV;
    String tenNV;
    public viewBanHang(String maNV, String tenNV) {
       this.maNVV = maNV;
       this.tenNV = tenNV;
       
       jlbMaNV.setText(this.maNVV);
       jlbMaNV1.setText(this.tenNV);
    }
    
private BanHang_SanPhamMD getProductByQRCode(String qrCode) {
    DefaultTableModel model = (DefaultTableModel) jblBanHang_SanPham.getModel();
    model.setRowCount(0); // Xóa tất cả các hàng hiện tại trong bảng

    BanHang_SanPhamMD product = null;
    String sql = "SELECT maSanPham, tenSanPham, tenHuongVi, soLuongTon, donGia " +
                 "FROM SanPham " +
                 "JOIN SanPhamChiTiet ON SanPham.ID_sanPham = SanPhamChiTiet.ID_sanPham " +
                 "JOIN HuongVi ON SanPhamChiTiet.ID_huongVi = HuongVi.ID_huongVi " +
                 "WHERE maSanPham = ?";

    try (Connection connection = DBConnect.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, qrCode);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                String maSanPham = resultSet.getString("maSanPham");
                String tenSanPham = resultSet.getString("tenSanPham");
                String tenHuongVi = resultSet.getString("tenHuongVi");
                int soLuongTon = resultSet.getInt("soLuongTon");
                double donGia = resultSet.getDouble("donGia");

                product = new BanHang_SanPhamMD(maSanPham, tenSanPham, tenHuongVi, soLuongTon, (float) donGia);
                model.addRow(new Object[]{maSanPham, tenSanPham, tenHuongVi, soLuongTon, donGia});
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm với mã QR: " + qrCode, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return product;
}
    private void initWebCam(){
    // Đóng webcam nếu nó đang mở
    if (webcam != null && webcam.isOpen()) {
        webcam.close();
    }

    Dimension size = WebcamResolution.QVGA.getSize();
    webcam = Webcam.getWebcams().get(0);
    webcam.setViewSize(size); // Thay đổi độ phân giải

    // Khởi tạo và cấu hình WebcamPanel
    panel = new WebcamPanel(webcam);
    panel.setPreferredSize(size);
    panel.setFPSDisplayed(true);
    
    // Xóa các thành phần hiện có và thêm panel mới vào jPanel2
    jPanel2.removeAll();
    jPanel2.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 241));
    jPanel2.revalidate(); // Cập nhật giao diện
    jPanel2.repaint(); // Vẽ lại giao diện
    
    // Bắt đầu quá trình quét mã QR
    exception.execute(this);
}
    @Override
    public void run() {
        do {            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(viewBanHang.class.getName()).log(Level.SEVERE, null, ex);
            }

            Result result = null;
            BufferedImage image = null;
            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException ex) {
                Logger.getLogger(viewBanHang.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (result != null) {
                result_filed.setText(result.getText());
                String qrCode = result.getText();
                BanHang_SanPhamMD product = getProductByQRCode(qrCode);
                if (product == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm với mã QR: " + qrCode, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                webcam.close();
                break;
            }
        } while (true);
    }
    
         @Override
    public Thread newThread(Runnable r){
        Thread t = new Thread(r , "My Thread");
        t.setDaemon(true);
        return t;
    }
    public void loadTable(ArrayList<BanHang_SanPhamMD> list) {
        DefaultTableModel model = (DefaultTableModel) jblBanHang_SanPham.getModel();
        model.setRowCount(0);
        for(BanHang_SanPhamMD sp: list) {
            Object[] thongTin = {sp.getMaSP(),sp.getTenSP(),sp.getHuongVi(), sp.getSoLuong(), sp.getDonGia()};
            model.addRow(thongTin);
        }
    }
    private void loadProducts() {
    DefaultTableModel model = (DefaultTableModel) jblBanHang_SanPham.getModel();
    String query = "Select maSanPham, tenSanPham,tenHuongVi, soLuongTon, donGia from SanPham join SanPhamChiTiet on SanPham.ID_sanPham = SanPhamChiTiet.ID_sanPham join HuongVi on SanPhamChiTiet.ID_huongVi = HuongVi.ID_huongVi where trangthai like N'Đang bán'";

    try (Connection conn = DBConnect.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {

        model.setRowCount(0); // Xóa các hàng hiện tại

        while (rs.next()) {
            String maSanPham = rs.getString("maSanPham");
            String tenSanPham = rs.getString("tenSanPham");
            String tenHuongVi = rs.getString("tenHuongVi");
            int soLuongTon = rs.getInt("soLuongTon");
            double donGia = rs.getDouble("donGia");

            model.addRow(new Object[]{maSanPham, tenSanPham,tenHuongVi, soLuongTon, donGia});
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    private void showComboData1() {
        ArrayList<BanHang_Voicher> data =  banHang_Voicher.voucher();
        for (BanHang_Voicher bv : data) {
            jComboBox2.addItem(bv.getTenVC());
        }
    }
    private void showComboData2() {
        ArrayList<BanHang_HuongVi> list = banHang_HuongVi.huongVi();
        for (BanHang_HuongVi bh : list) {
            jComboBox1.addItem(bh.getTenHV());
        }
    }
    
    private void saveToDatabase(String maSanPham, double donGia, int soLuong, double thanhTien) {
    int selectedHoaDonRow = jtbHoaDon.getSelectedRow();
    int idHoaDon = (int) jtbHoaDon.getValueAt(selectedHoaDonRow, 1);

    // Lấy ID_sanPhamChiTiet từ maSanPham
    int idSanPhamChiTiet = getSanPhamChiTietIdByMa(maSanPham);

    // Kiểm tra dữ liệu trước khi lưu
    if (idHoaDon == 0 || idSanPhamChiTiet == 0 || donGia <= 0) {
        JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if(soLuong <= 0){
        JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng lớn hơn 0.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String sql = "INSERT INTO HoaDonChiTiet (ID_hoaDon, ID_sanPhamChiTiet, soLuong, donGia, tongTien) VALUES (?, ?, ?, ?, ?)";

    try (Connection connection = DBConnect.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        // Cài đặt các tham số cho câu lệnh SQL
        statement.setInt(1, idHoaDon);
        statement.setInt(2, idSanPhamChiTiet);
        statement.setInt(3, soLuong);
        statement.setDouble(4, donGia);
        statement.setDouble(5, thanhTien);

        // Thực hiện câu lệnh
        int rowsAffected = statement.executeUpdate();

        

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi lưu dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
    private int getSanPhamChiTietIdByMa(String maSanPham) {
    String sql = "SELECT ID_sanPhamChiTiet FROM SanPhamChiTiet WHERE maSanPhamChiTiet = ?";
    try (Connection connection = DBConnect.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, maSanPham);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("ID_sanPhamChiTiet");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0; // Trả về 0 nếu không tìm thấy hoặc có lỗi
    }
    
    // Hàm để xóa sản phẩm khỏi giỏ hàng và cập nhật lại số lượng sản phẩm
    private void removeProductFromCart() {
        int selectedRow = jblBanHang_GioHang.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm trong giỏ hàng để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel cartModel = (DefaultTableModel) jblBanHang_GioHang.getModel();
        String maSanPham = cartModel.getValueAt(selectedRow, 0).toString();
        int soLuongTrongGio = Integer.parseInt(cartModel.getValueAt(selectedRow, 3).toString());

        // Cập nhật lại số lượng sản phẩm trong bảng sản phẩm
        updateProductQuantity(maSanPham, soLuongTrongGio);

        // Xóa sản phẩm khỏi giỏ hàng
        cartModel.removeRow(selectedRow);
    }
    // Hàm để cập nhật lại số lượng sản phẩm trong bảng sản phẩm
    private void updateProductQuantity(String maSanPham, int soLuongThemLai) {
        DefaultTableModel productModel = (DefaultTableModel) jblBanHang_SanPham.getModel();
        for (int i = 0; i < productModel.getRowCount(); i++) {
            String maSP = productModel.getValueAt(i, 0).toString();
            if (maSP.equals(maSanPham)) {
                int soLuongHienTai = Integer.parseInt(productModel.getValueAt(i, 2).toString());
                int soLuongMoi = soLuongHienTai + soLuongThemLai;
                productModel.setValueAt(soLuongMoi, i, 2);

                // Cập nhật vào cơ sở dữ liệu nếu cần thiết
                updateStock(maSanPham, soLuongMoi);

                break;
            }
        }
    }
    // Hàm để cập nhật số lượng tồn kho trong cơ sở dữ liệu
private void updateStock(String maSanPham, int newStock) {
    String sql = "UPDATE SanPhamChiTiet SET soLuongTon = ? WHERE maSanPhamChiTiet = ?";

    try (Connection connection = DBConnect.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, newStock);
        statement.setString(2, maSanPham);

        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Cập nhật tồn kho thành công.");
        } else {
            System.out.println("Không thể cập nhật tồn kho.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật tồn kho: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void loadHoaDon() {
    int selectedRow = jtbHoaDon.getSelectedRow();
    DefaultTableModel model = (DefaultTableModel) jtbHoaDon.getModel();
    model.setRowCount(0); // Xóa tất cả các hàng hiện tại trong bảng

    String sql = "SELECT ID_hoaDon, ID_khachHang, maNhanVienHD, trangThaiHoaDon, ngayTao FROM HoaDon WHERE trangThaiHoaDon = N'Chờ thanh toán'";

    try (Connection connection = DBConnect.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {
            int idHoaDon = resultSet.getInt("ID_hoaDon");
            int idKhachHang = resultSet.getInt("ID_khachHang");
            String maNhanVienHD = resultSet.getString("maNhanVienHD");
            String trangThaiHoaDon = resultSet.getString("trangThaiHoaDon");
            Date ngayTao = resultSet.getDate("ngayTao");

            // Lấy tên khách hàng từ ID khách hàng
            String tenKhachHang = getCustomerNameById(idKhachHang);

            // Thêm dữ liệu vào bảng hóa đơn
            model.addRow(new Object[]{model.getRowCount() + 1, idHoaDon, tenKhachHang, maNhanVienHD, trangThaiHoaDon, ngayTao});
            // Khôi phục trạng thái hàng được chọn nếu hàng vẫn tồn tại
        if (selectedRow != -1 && selectedRow < jtbHoaDon.getRowCount()) {
            jtbHoaDon.setRowSelectionInterval(selectedRow, selectedRow);
        }
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi tải danh sách hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
    private String getCustomerNameById(int idKhachHang) {
    String sql = "SELECT hoTen FROM KhachHang WHERE ID_khachHang = ?";

    try (Connection connection = DBConnect.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, idKhachHang);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getString("hoTen");
            } else {
                return "Không tìm thấy";
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return "Lỗi khi lấy tên khách hàng";
    }
}
    
    private void deleteHoaDonChiTiet(String maHoaDon, String maSanPham) {
    try (Connection connection = DBConnect.getConnection()) {
        // Lấy ID của sản phẩm từ mã sản phẩm
        String getIDSQL = "SELECT ID_sanPhamChiTiet FROM SanPhamChiTiet WHERE maSanPhamChiTiet = ?";
        String idSanPhamChiTiet = null;
        try (PreparedStatement psGetID = connection.prepareStatement(getIDSQL)) {
            psGetID.setString(1, maSanPham);
            try (ResultSet rsID = psGetID.executeQuery()) {
                if (rsID.next()) {
                    idSanPhamChiTiet = rsID.getString("ID_sanPhamChiTiet");
                }
            }
        }

        // Xóa thông tin từ hóa đơn chi tiết
        if (idSanPhamChiTiet != null) {
            String deleteSQL = "DELETE FROM HoaDonChiTiet WHERE ID_hoaDon = ? AND ID_sanPhamChiTiet = ?";
            try (PreparedStatement psDelete = connection.prepareStatement(deleteSQL)) {
                psDelete.setString(1, maHoaDon);
                psDelete.setString(2, idSanPhamChiTiet);
                psDelete.executeUpdate();
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi xóa sản phẩm khỏi hóa đơn chi tiết: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void updateStockAfterRemoval(String maSanPham, int soLuongXoa) {
    try (Connection connection = DBConnect.getConnection()) {
        // Lấy ID của sản phẩm từ mã sản phẩm
        String getIDSQL = "SELECT ID_sanPhamChiTiet FROM SanPhamChiTiet WHERE maSanPhamChiTiet = ?";
        String idSanPhamChiTiet = null;
        try (PreparedStatement psGetID = connection.prepareStatement(getIDSQL)) {
            psGetID.setString(1, maSanPham);
            try (ResultSet rsID = psGetID.executeQuery()) {
                if (rsID.next()) {
                    idSanPhamChiTiet = rsID.getString("ID_sanPhamChiTiet");
                }
            }
        }

        // Cập nhật số lượng tồn kho
        if (idSanPhamChiTiet != null) {
            String updateSQL = "UPDATE SanPhamChiTiet SET soLuongTon = soLuongTon + ? WHERE ID_sanPhamChiTiet = ?";
            try (PreparedStatement psUpdate = connection.prepareStatement(updateSQL)) {
                psUpdate.setInt(1, soLuongXoa);
                psUpdate.setString(2, idSanPhamChiTiet);
                psUpdate.executeUpdate();
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật số lượng tồn kho: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}

    private void updateProductTable(String maSanPham) {
    try (Connection connection = DBConnect.getConnection()) {
        String sql = "SELECT soLuongTon FROM SanPhamChiTiet WHERE maSanPhamChiTiet = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, maSanPham);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int newSoLuongTon = resultSet.getInt("soLuongTon");

                    // Cập nhật lại số lượng tồn kho trong jTable sản phẩm
                    DefaultTableModel modelSanPham = (DefaultTableModel) jblBanHang_SanPham.getModel();
                    for (int i = 0; i < modelSanPham.getRowCount(); i++) {
                        if (modelSanPham.getValueAt(i, 0).toString().equals(maSanPham)) {
                            modelSanPham.setValueAt(newSoLuongTon, i, 3); // Cột 3 là cột số lượng tồn kho
                            break;
                        }
                    }
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật số lượng tồn kho trên bảng sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
    private List<HoaDonChiTiet> getHoaDonChiTiet(int maHoaDon) {
    List<HoaDonChiTiet> chiTietList = new ArrayList<>();
    String sql = "SELECT s.maSanPham, hdonct.soLuong FROM HoaDonChiTiet hdonct " +
                 "JOIN SanPhamChiTiet spct ON hdonct.ID_sanPhamChiTiet = spct.ID_sanPhamChiTiet " +
                 "JOIN SanPham s ON spct.ID_sanPham = s.ID_sanPham " +
                 "WHERE hdonct.ID_hoaDon = ?";

    try (Connection connection = DBConnect.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, maHoaDon);

        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String maSanPham = resultSet.getString("maSanPham");
                int soLuong = resultSet.getInt("soLuong");
                chiTietList.add(new HoaDonChiTiet(maSanPham, soLuong));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi lấy chi tiết hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    return chiTietList;
}
    private void deleteHoaDon(int maHoaDon) {
    try (Connection connection = DBConnect.getConnection()) {
        // Xóa chi tiết hóa đơn
        String deleteChiTietSQL = "DELETE FROM HoaDonChiTiet WHERE ID_hoaDon = ?";
        try (PreparedStatement psDeleteChiTiet = connection.prepareStatement(deleteChiTietSQL)) {
            psDeleteChiTiet.setInt(1, maHoaDon);
            psDeleteChiTiet.executeUpdate();
        }

        // Xóa hóa đơn
        String deleteHoaDonSQL = "DELETE FROM HoaDon WHERE ID_hoaDon = ?";
        try (PreparedStatement psDeleteHoaDon = connection.prepareStatement(deleteHoaDonSQL)) {
            psDeleteHoaDon.setInt(1, maHoaDon);
            psDeleteHoaDon.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi xóa hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void deleteAllHoaDonChiTiet(String maHoaDon) {
    String sql = "DELETE FROM HoaDonChiTiet WHERE ID_hoaDon = ?";
    try (Connection connection = DBConnect.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, maHoaDon);
        statement.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi xóa chi tiết hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
    private List<HoaDonChiTiet> getProductsFromCart() {
    List<HoaDonChiTiet> productList = new ArrayList<>();
    DefaultTableModel modelGioHang = (DefaultTableModel) jblBanHang_GioHang.getModel();

    for (int i = 0; i < modelGioHang.getRowCount(); i++) {
        String maSanPham = modelGioHang.getValueAt(i, 0).toString();
        int soLuong = Integer.parseInt(modelGioHang.getValueAt(i, 3).toString());
        productList.add(new HoaDonChiTiet(maSanPham, soLuong));
    }

    return productList;
}
    
    private void loadSanPhamTable() {
    DefaultTableModel modelSanPham = (DefaultTableModel) jblBanHang_SanPham.getModel();
    modelSanPham.setRowCount(0);

    String sql = "Select maSanPham, tenSanPham,tenHuongVi, soLuongTon, donGia from SanPham join SanPhamChiTiet on SanPham.ID_sanPham = SanPhamChiTiet.ID_sanPham join HuongVi on SanPhamChiTiet.ID_huongVi = HuongVi.ID_huongVi";
    try (Connection connection = DBConnect.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet rs = statement.executeQuery()) {

        while (rs.next()) {
            String maSanPham = rs.getString("maSanPham");
            String tenSanPham = rs.getString("tenSanPham");
            String tenHuongVi = rs.getString("tenHuongVi");
            int soLuongTon = rs.getInt("soLuongTon");
            double donGia = rs.getDouble("donGia");

            modelSanPham.addRow(new Object[]{maSanPham, tenSanPham,tenHuongVi, soLuongTon, donGia});
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi tải danh sách sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void updateSelectedHoaDon() {
    int selectedRow = jtbHoaDon.getSelectedRow();
    if (selectedRow != -1) {
        // Lấy mã hóa đơn từ hàng được chọn
        String maHoaDon = jtbHoaDon.getValueAt(selectedRow, 2).toString(); // Giả sử mã hóa đơn ở cột thứ 2
        jlbMaHD.setText("HD" + maHoaDon);
    } else {
        jlbMaHD.setText("");
    }
}
    private void loadHoaDonChiTiet(int idHoaDon) {
    DefaultTableModel model = (DefaultTableModel) jblBanHang_GioHang.getModel();
    model.setRowCount(0); // Xóa tất cả các hàng hiện tại trong bảng chi tiết

    double tongTienHang = 0;
    String ghiChu = "";
    String sql = "SELECT s.maSanPham, s.tenSanPham, hdonct.soLuong, hdonct.donGia, hdonct.tongTien, hdonct.ghiChu " +
                 "FROM HoaDonChiTiet hdonct " +
                 "JOIN SanPhamChiTiet spct ON hdonct.ID_sanPhamChiTiet = spct.ID_sanPhamChiTiet " +
                 "JOIN SanPham s ON spct.ID_sanPham = s.ID_sanPham " +
                 "WHERE hdonct.ID_hoaDon = ?";

    try (Connection connection = DBConnect.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, idHoaDon);
        
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Lấy dữ liệu từ ResultSet
                String maSanPham = resultSet.getString("maSanPham");
                String tenSanPham = resultSet.getString("tenSanPham");
                int soLuong = resultSet.getInt("soLuong");
                double donGia = resultSet.getDouble("donGia");
                double thanhTien = resultSet.getDouble("tongTien");
                ghiChu = resultSet.getString("ghiChu");

                // Cộng dồn tổng tiền hàng
                tongTienHang += thanhTien;

                // Thêm dữ liệu vào bảng chi tiết
                model.addRow(new Object[]{maSanPham, tenSanPham, donGia, soLuong, thanhTien});
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi tải chi tiết hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    // Cập nhật tổng tiền hàng lên JLabel
    jLabel27.setText(tongTienHang + " VND");

    // Lấy thông tin voucher đã áp dụng
    String voucherSQL = "SELECT ID_voucher FROM HoaDon WHERE ID_hoaDon = ?";
    try (Connection connection = DBConnect.getConnection();
         PreparedStatement statement = connection.prepareStatement(voucherSQL)) {

        statement.setInt(1, idHoaDon);
        
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                String idVoucher = resultSet.getString("ID_voucher");
                jComboBox2.setSelectedItem(idVoucher); // Giả sử ID_voucher đã được thêm vào JComboBox trước đó
                applyVoucherAndUpdateTotal(); // Cập nhật tiền khách cần trả sau khi chọn voucher
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi tải thông tin voucher: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    // Cập nhật ghi chú lên JTextArea
    jTextField6.setText(ghiChu);
}

private void applyVoucherAndUpdateTotal() {
    // Lấy tổng tiền hàng từ JLabel
    String tongTienHangStr = jLabel27.getText().replaceAll("[^\\d.,]", ""); // Loại bỏ ký tự không phải số
    double tongTienHang;
    try {
        // Chuyển chuỗi số tiền về định dạng double
        tongTienHang = Double.parseDouble(tongTienHangStr.replaceAll(",", "."));
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Tổng tiền hàng không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Lấy giá trị giảm giá từ voucher đã chọn
    String selectedVoucher = (String) jComboBox2.getSelectedItem();
    double giamGia = getVoucherDiscount(selectedVoucher);
    
    // Tính tiền khách cần trả sau khi áp dụng thuế và voucher
    double tienKhachCanTra = tongTienHang +(tongTienHang* 0.1); // Áp dụng thuế 10%
    tienKhachCanTra -= giamGia; // Áp dụng khuyến mại từ voucher

    // Cập nhật tiền khách cần trả lên JLabel
    jLabel31.setText("" + formatCurrency(tienKhachCanTra));
}

private String formatCurrency(double amount) {
    // Chuyển số tiền thành chuỗi với dấu phân cách hàng nghìn và thêm đơn vị "VND"
    String formatted = String.format("%,.0f", amount);
    return formatted + " VND";
}

private double getVoucherDiscount(String voucher) {
    double discount = 0.0;
    String sql = "SELECT giaTriVoucher FROM Voucher WHERE tenVoucher = ?";
    try (Connection connection = DBConnect.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, voucher);
        
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                discount = resultSet.getDouble("giaTriVoucher");
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi tải giá trị giảm giá từ voucher: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    return discount;
}

private void loadPendingInvoices() {
        DefaultTableModel model = (DefaultTableModel) jtbHoaDon.getModel();
        model.setRowCount(0); // Xóa tất cả các hàng hiện tại trong bảng

        String sql = "SELECT ID_hoaDon, ID_khachHang, maNhanVienHD, trangThaiHoaDon, ngayTao " +
                 "FROM HoaDon WHERE trangThaiHoaDon = N'Chờ thanh toán'";

    try (Connection connection = DBConnect.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {
            int idHoaDon = resultSet.getInt("ID_hoaDon");
            int idKhachHang = resultSet.getInt("ID_khachHang"); // Lấy ID khách hàng
            String maNhanVienHD = resultSet.getString("maNhanVienHD");
            String trangThaiHoaDon = resultSet.getString("trangThaiHoaDon");
            Date ngayTao = resultSet.getDate("ngayTao");

            // Lấy tên khách hàng từ ID khách hàng
            String getCustomerNameSQL = "SELECT hoTen FROM KhachHang WHERE ID_khachHang = ?";
            String tenKhachHang = "Chưa xác định"; // Giá trị mặc định nếu không tìm thấy tên khách hàng
            try (PreparedStatement pstmtCustomerName = connection.prepareStatement(getCustomerNameSQL)) {
                pstmtCustomerName.setInt(1, idKhachHang);
                try (ResultSet rsCustomerName = pstmtCustomerName.executeQuery()) {
                    if (rsCustomerName.next()) {
                        tenKhachHang = rsCustomerName.getString("hoTen");
                    }
                }
            }

            // Thêm dữ liệu vào bảng hóa đơn
            model.addRow(new Object[]{model.getRowCount() + 1, idHoaDon, tenKhachHang, maNhanVienHD, trangThaiHoaDon, ngayTao});
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi tải danh sách hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }

private void loadPendingInvoicess() {
        DefaultTableModel model = (DefaultTableModel) jtbHoaDon.getModel();
        model.setRowCount(0); // Xóa tất cả các hàng hiện tại trong bảng

        String sql = "SELECT ID_hoaDon, ID_khachHang, maNhanVienHD, trangThaiHoaDon, ngayTao " +
                 "FROM HoaDon WHERE trangThaiHoaDon = N'Chờ thanh toán'";

    try (Connection connection = DBConnect.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {
            int idHoaDon = resultSet.getInt("ID_hoaDon");
            int idKhachHang = resultSet.getInt("ID_khachHang"); // Lấy ID khách hàng
            String maNhanVienHD = resultSet.getString("maNhanVienHD");
            String trangThaiHoaDon = resultSet.getString("trangThaiHoaDon");
            Date ngayTao = resultSet.getDate("ngayTao");

            // Lấy tên khách hàng từ ID khách hàng
            String getCustomerNameSQL = "SELECT hoTen FROM KhachHang WHERE ID_khachHang = ?";
            String tenKhachHang = "Chưa xác định"; // Giá trị mặc định nếu không tìm thấy tên khách hàng
            try (PreparedStatement pstmtCustomerName = connection.prepareStatement(getCustomerNameSQL)) {
                pstmtCustomerName.setInt(1, idKhachHang);
                try (ResultSet rsCustomerName = pstmtCustomerName.executeQuery()) {
                    if (rsCustomerName.next()) {
                        tenKhachHang = rsCustomerName.getString("hoTen");
                    }
                }
            }

            // Thêm dữ liệu vào bảng hóa đơn
            model.addRow(new Object[]{model.getRowCount() + 1, idHoaDon, tenKhachHang, maNhanVienHD, trangThaiHoaDon, ngayTao});
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi tải danh sách hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }


    private void updateHoaDon(int idHoaDon, Integer idKhachHang, int idNhanVien, Integer idVoucher, double giaTriThanhToan) {
    // Bắt đầu với câu lệnh SQL cơ bản
    StringBuilder sql = new StringBuilder("UPDATE HoaDon SET ");
    
    // Xây dựng danh sách các cột cần cập nhật
    List<String> columnsToUpdate = new ArrayList<>();
    if (idKhachHang != null) {
        columnsToUpdate.add("ID_khachHang = ?");
    }
    columnsToUpdate.add("ID_nhanVien = ?");
    if (idVoucher != null) {
        columnsToUpdate.add("ID_voucher = ?");
    }
    columnsToUpdate.add("giaTriThanhToan = ?");
    columnsToUpdate.add("trangThaiHoaDon = N'Đã thanh toán'");
    
    // Thêm các cột vào câu lệnh SQL
    sql.append(String.join(", ", columnsToUpdate));
    sql.append(" WHERE ID_hoaDon = ?");
    
    try (Connection connection = DBConnect.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {
        
        int paramIndex = 1;
        
        // Cài đặt các tham số cho câu lệnh SQL
        if (idKhachHang != null) {
            preparedStatement.setInt(paramIndex++, idKhachHang);
        }
        preparedStatement.setInt(paramIndex++, idNhanVien);
        if (idVoucher != null) {
            preparedStatement.setInt(paramIndex++, idVoucher);
        }
        preparedStatement.setDouble(paramIndex++, giaTriThanhToan);
        preparedStatement.setInt(paramIndex, idHoaDon);
        
        // Thực thi câu lệnh cập nhật
        int rowsAffected = preparedStatement.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Cập nhật hóa đơn thành công.");
        } else {
            System.out.println("Không tìm thấy hóa đơn để cập nhật.");
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    private void updateHoaDonChiTiet(int idHoaDon,String soDienThoai,double tienThue, double giaTriVoucher, double tienKhachCanTra, String ghiChu) {
    String sql = "UPDATE HoaDonChiTiet SET soDTKH = ?,tienThue = ?, giaTriVoucher = ?, giaTriThanhToan = ?, ghiChu = ?, ngayTao = GETDATE() WHERE ID_hoaDon = ?";
    
    try (Connection connection = DBConnect.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        // Thiết lập tham số cho câu lệnh SQL
        preparedStatement.setString(1, soDienThoai);
        preparedStatement.setDouble(2, tienThue);
        preparedStatement.setDouble(3, giaTriVoucher);
        preparedStatement.setDouble(4, tienKhachCanTra);
        preparedStatement.setString(5, ghiChu);
        preparedStatement.setLong(6, idHoaDon);
        
        // Thực thi câu lệnh cập nhật
        int rowsAffected = preparedStatement.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Cập nhật hóa đơn chi tiết thành công.");
        } else {
            System.out.println("Không tìm thấy hóa đơn chi tiết để cập nhật.");
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
private int getIdKhachHang(String soDienThoai) {
    String sql = "SELECT ID_khachHang FROM KhachHang WHERE soDT = ?";
    try (Connection connection = DBConnect.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setString(1, soDienThoai);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("ID_khachHang");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}
private int getIdNhanVien(String maNhanVien) {
    String sql = "SELECT ID_nhanVien FROM NhanVien WHERE maNhanVien = ?";
    try (Connection connection = DBConnect.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setString(1, maNhanVien);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("ID_nhanVien");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}
private int getIdVoicher(String selectedVoucher) {
    String sql = "SELECT ID_voucher FROM Voucher WHERE tenVoucher = ?";
    try (Connection connection = DBConnect.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setString(1, selectedVoucher);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("ID_voucher");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}

private void thanhToanHD(int maHD, String soDienThoai, String maNhanVien, String selectedVoucher, double giaTriThanhToan) {
    int idHoaDon = Integer.parseInt(jlbMaHD.getText());
    Integer idKhachHang = null;
    Integer idVoucher = null;
    
    // Kiểm tra số điện thoại và lấy idKhachHang nếu tồn tại
    if (!soDienThoai.isEmpty()) {
        List<KhachHangMD> khachHangList = sm.timKiem(soDienThoai);
        if (khachHangList != null && !khachHangList.isEmpty()) {
            idKhachHang = getIdKhachHang(soDienThoai);
        }
    }
    
    int idNhanVien = getIdNhanVien(maNhanVien);
    
    // Kiểm tra và lấy idVoucher nếu tồn tại
    if (selectedVoucher != null && !selectedVoucher.isEmpty()) {
        idVoucher = getIdVoicher(selectedVoucher);
    }
    
    // Cập nhật hóa đơn
    updateHoaDon(idHoaDon, idKhachHang, idNhanVien, idVoucher, giaTriThanhToan);
}
private void thanhToanHDCT(int maHD, String soDienThoai, double tienThue,double giamGia,double tienKhachCanTra,String ghiChu){
    int idHoaDon = Integer.parseInt(jlbMaHD.getText());
    updateHoaDonChiTiet(idHoaDon,soDienThoai,tienThue, giamGia, tienKhachCanTra, ghiChu);
}
private void resetHoaDonForm() {
    DefaultTableModel model = (DefaultTableModel) jblBanHang_GioHang.getModel();
    if (model != null) {
        model.setRowCount(0);
    }
    if (txtSDTKH != null) {
        txtSDTKH.setText("");
    }
    if (txtTenKH != null) {
        txtTenKH.setText("");
    }
    if (jlbMaHD != null) {
        jlbMaHD.setText("");
    }
    
    if (jLabel31 != null) {
        jLabel31.setText("0");
    }
    if (jLabel27 != null) {
        jLabel27.setText("0");
    }
    if (jTextField6 != null) {
        jTextField6.setText("");
    }
    // Reset các trường hoặc thành phần UI khác liên quan đến hóa đơn nếu cần thiết
}
private void updateCartInDatabase(String maSanPham, double donGia, int newQuantity, double newTotal) {
    int selectedHoaDonRow = jtbHoaDon.getSelectedRow();
    if (selectedHoaDonRow == -1) {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn trước.", "Lỗi", JOptionPane.WARNING_MESSAGE);
        return; // Dừng lại nếu chưa chọn hóa đơn
    }

    int idHoaDon = (int) jtbHoaDon.getValueAt(selectedHoaDonRow, 1);
    int idSanPhamChiTiet = getSanPhamChiTietIdByMa(maSanPham);

    if (idHoaDon == 0 || idSanPhamChiTiet == 0 || donGia <= 0 || newQuantity <= 0) {
        JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String sql = "UPDATE HoaDonChiTiet SET soLuong = ?, donGia = ?, tongTien = ? WHERE ID_hoaDon = ? AND ID_sanPhamChiTiet = ?";

    try (Connection connection = DBConnect.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, newQuantity);
        statement.setDouble(2, donGia);
        statement.setDouble(3, newTotal);
        statement.setInt(4, idHoaDon);
        statement.setInt(5, idSanPhamChiTiet);

        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Cập nhật giỏ hàng thành công.");
        } else {
            System.out.println("Không thể cập nhật giỏ hàng.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật giỏ hàng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
private void clearVoucherComboBox() {
    jComboBox2.removeAllItems(); // Xóa tất cả các mục trong hộp chọn voucher
    showComboData1();
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel12 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jblBanHang_GioHang = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtbHoaDon = new javax.swing.JTable();
        jbtTaoHD = new javax.swing.JButton();
        jbtXoaHoaDon = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jblBanHang_SanPham = new javax.swing.JTable();
        jButton13 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        result_filed = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSDTKH = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jlbMaNV = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jlbNgayTao = new javax.swing.JLabel();
        jlbNgayTao1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jlbMaNV1 = new javax.swing.JLabel();
        jlbMaHD = new javax.swing.JLabel();
        jlbMaHD1 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();

        jPanel12.setBackground(new java.awt.Color(66, 184, 234));

        jPanel19.setBackground(new java.awt.Color(66, 184, 234));
        jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel21.setBackground(new java.awt.Color(66, 184, 234));
        jPanel21.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Giỏ hàng");

        jblBanHang_GioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Đơn giá", "Số lượng", "Thành tiền"
            }
        ));
        jblBanHang_GioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jblBanHang_GioHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jblBanHang_GioHang);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/LamMoiGioHang.png"))); // NOI18N
        jButton10.setText("Làm mới giỏ hàng");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(255, 153, 153));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); // NOI18N
        jButton12.setText("Bỏ khỏi giỏ hàng");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 861, Short.MAX_VALUE)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel22.setBackground(new java.awt.Color(66, 184, 234));
        jPanel22.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Hóa đơn");

        jtbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hóa đơn", "Tên khách hàng", "Mã NV", "Trạng thái", "Ngày tạo"
            }
        ));
        jtbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbHoaDonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jtbHoaDonMouseEntered(evt);
            }
        });
        jScrollPane4.setViewportView(jtbHoaDon);

        jbtTaoHD.setBackground(new java.awt.Color(51, 255, 51));
        jbtTaoHD.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jbtTaoHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/TaoHoaDon.png"))); // NOI18N
        jbtTaoHD.setText("Tạo hóa đơn");
        jbtTaoHD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtTaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtTaoHDActionPerformed(evt);
            }
        });

        jbtXoaHoaDon.setBackground(new java.awt.Color(255, 153, 153));
        jbtXoaHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jbtXoaHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/BoKhoiGioHang.png"))); // NOI18N
        jbtXoaHoaDon.setText("Xóa hóa đơn");
        jbtXoaHoaDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtXoaHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtXoaHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtTaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtXoaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtTaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtXoaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel23.setBackground(new java.awt.Color(66, 184, 234));
        jPanel23.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Sản phẩm");

        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
        });

        jblBanHang_SanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Hương vị", "Số lượng tồn", "Đơn giá"
            }
        ));
        jblBanHang_SanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jblBanHang_SanPhamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jblBanHang_SanPhamMouseEntered(evt);
            }
        });
        jScrollPane3.setViewportView(jblBanHang_SanPham);

        jButton13.setBackground(new java.awt.Color(204, 204, 204));
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/QuetQR.png"))); // NOI18N
        jButton13.setText("Quét QR");
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton13MouseClicked(evt);
            }
        });
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Mã QR:");

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        result_filed.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                result_filedKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Tìm theo tên và mã SP :");
        jLabel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Lọc theo hương vị :");
        jLabel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hương vị" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/loadd.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel23Layout.createSequentialGroup()
                                        .addComponent(jButton13)
                                        .addGap(0, 52, Short.MAX_VALUE))
                                    .addComponent(result_filed, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14)
                                .addComponent(jLabel15)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1))
                        .addContainerGap())
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(result_filed)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton13)
                        .addGap(8, 8, 8))))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel20.setBackground(new java.awt.Color(66, 184, 234));
        jPanel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel24.setBackground(new java.awt.Color(66, 184, 234));
        jPanel24.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Hóa đơn");

        jPanel26.setBackground(new java.awt.Color(66, 184, 234));
        jPanel26.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Thông tin khách hàng");

        jLabel9.setText("SĐT:");

        jLabel10.setText("Họ tên:");

        jButton14.setBackground(new java.awt.Color(255, 255, 102));
        jButton14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/TimKiem.png"))); // NOI18N
        jButton14.setText("Tìm theo SĐT");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(102, 255, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/TaoHoaDon.png"))); // NOI18N
        jButton2.setText("Thêm khách hàng");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSDTKH)
                            .addComponent(txtTenKH)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(17, 17, 17)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(13, 13, 13)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel27.setBackground(new java.awt.Color(66, 184, 234));
        jPanel27.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Chi tiết hóa đơn");

        jLabel24.setText("Tổng tiền hàng:");

        jLabel25.setText("Voicher:");

        jLabel26.setText("Thuế:");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 51, 51));
        jLabel27.setText("0 ");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 51, 51));
        jLabel29.setText("10%");

        jLabel30.setText("Khách cần trả:");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 0));
        jLabel31.setText("0");

        jLabel35.setText("Ghi chú:");

        jComboBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox2MouseClicked(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã hóa đơn:");

        jLabel2.setText("Mã nhân viên:");

        jLabel3.setText("Ngày tạo");

        jlbMaNV.setBackground(new java.awt.Color(153, 204, 255));
        jlbMaNV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlbMaNV.setText("MaNV");

        jLabel11.setText("Tên nhân viên:");

        jlbNgayTao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlbNgayTao.setText("Days");

        jlbNgayTao1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlbNgayTao1.setText("Time");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("/");

        jlbMaNV1.setBackground(new java.awt.Color(153, 204, 255));
        jlbMaNV1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlbMaNV1.setText("TenNV");

        jlbMaHD.setBackground(new java.awt.Color(153, 204, 255));
        jlbMaHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlbMaHD.setText("Mã");

        jlbMaHD1.setBackground(new java.awt.Color(153, 204, 255));
        jlbMaHD1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlbMaHD1.setText("HD");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel26)
                            .addComponent(jLabel30)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel11)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel25))
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbMaNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel27Layout.createSequentialGroup()
                                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlbNgayTao1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel27))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jlbNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel27Layout.createSequentialGroup()
                                        .addComponent(jlbMaHD1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jlbMaHD))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(247, 247, 247))
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel23)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jlbMaHD)
                    .addComponent(jlbMaHD1))
                .addGap(24, 24, 24)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jlbMaNV))
                .addGap(26, 26, 26)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jlbMaNV1))
                .addGap(25, 25, 25)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jlbNgayTao1)
                    .addComponent(jLabel12)
                    .addComponent(jlbNgayTao))
                .addGap(25, 25, 25)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel27))
                .addGap(33, 33, 33)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel29))
                .addGap(23, 23, 23)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jButton15.setBackground(new java.awt.Color(0, 255, 0));
        jButton15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton15.setText("Thanh toán");
        jButton15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jblBanHang_SanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblBanHang_SanPhamMouseClicked
        int selectedHoaDonRow = jtbHoaDon.getSelectedRow();
    if (selectedHoaDonRow == -1) {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn trước.", "Lỗi", JOptionPane.WARNING_MESSAGE);
        return; // Dừng lại nếu chưa chọn hóa đơn
    }

    DefaultTableModel modelGioHang = (DefaultTableModel) jblBanHang_GioHang.getModel();

    // Kiểm tra và loại bỏ các hàng trống
    for (int i = modelGioHang.getRowCount() - 1; i >= 0; i--) {
        if (modelGioHang.getValueAt(i, 0) == null || modelGioHang.getValueAt(i, 0).toString().trim().isEmpty()) {
            modelGioHang.removeRow(i);
        }
    }

    int selectedRow = jblBanHang_SanPham.getSelectedRow();
    if (selectedRow != -1) {
        // Lấy dữ liệu từ hàng được chọn
        String maSanPham = jblBanHang_SanPham.getValueAt(selectedRow, 0).toString();
        String tenSanPham = jblBanHang_SanPham.getValueAt(selectedRow, 1).toString();
        int soLuongTon = Integer.parseInt(jblBanHang_SanPham.getValueAt(selectedRow, 3).toString());
        String donGiaStr = jblBanHang_SanPham.getValueAt(selectedRow, 4).toString();

        // Hiển thị hộp thoại nhập số lượng
        String soLuongStr = JOptionPane.showInputDialog("Nhập số lượng:");

        // Kiểm tra nếu người dùng nhấn Cancel hoặc nhập giá trị trống
        if (soLuongStr == null || soLuongStr.trim().isEmpty()) {
            return; // Người dùng đã nhấn Cancel hoặc để trống, không làm gì cả
        }

        try {
            // Chuyển đổi soLuong và donGia sang giá trị số
            int soLuong = Integer.parseInt(soLuongStr);
            double donGia = Double.parseDouble(donGiaStr);

            // Tính toán thanhTien
            double thanhTien = soLuong * donGia;

            // Kiểm tra số lượng tồn kho
            if (soLuong > soLuongTon) {
                JOptionPane.showMessageDialog(this, "Số lượng nhiều hơn số lượng tồn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
            boolean found = false;
            for (int i = 0; i < modelGioHang.getRowCount(); i++) {
                if (modelGioHang.getValueAt(i, 0).toString().equals(maSanPham)) {
                    // Cập nhật số lượng và thành tiền nếu sản phẩm đã có trong giỏ hàng
                    int currentQuantity = Integer.parseInt(modelGioHang.getValueAt(i, 3).toString());
                    int newQuantity = currentQuantity + soLuong;
                    double newTotal = newQuantity * donGia;
                    modelGioHang.setValueAt(newQuantity, i, 3);
                    modelGioHang.setValueAt(newTotal, i, 4);

                    // Cập nhật dữ liệu trong cơ sở dữ liệu
                    updateCartInDatabase(maSanPham, donGia, newQuantity, newTotal);

                    found = true;
                    break;
                }
            }

            // Nếu sản phẩm chưa có trong giỏ hàng, thêm mới
            if (!found) {
                modelGioHang.addRow(new Object[]{maSanPham, tenSanPham, donGia, soLuong, thanhTien});

                // Lưu vào cơ sở dữ liệu
                saveToDatabase(maSanPham, donGia, soLuong, thanhTien);
            }

            // Cập nhật số lượng tồn kho
            int newStock = soLuongTon - soLuong;
            updateStock(maSanPham, newStock);

            // Làm mới danh sách sản phẩm
            loadProducts();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm.", "Lỗi", JOptionPane.WARNING_MESSAGE);
    }
    
    int selectedRoww = jtbHoaDon.getSelectedRow();
    
    if (selectedRoww != -1) {
        // Lấy mã hóa đơn từ hàng được chọn (giả sử cột mã hóa đơn ở vị trí thứ 1)
        Object idHoaDonObj = jtbHoaDon.getValueAt(selectedRoww, 1);
        
        // Kiểm tra và chuyển đổi giá trị mã hóa đơn sang kiểu int
        if (idHoaDonObj instanceof Integer) {
            int idHoaDon = (Integer) idHoaDonObj;
            // Cập nhật JLabel với mã hóa đơn
            jlbMaHD.setText("" + idHoaDon);
            
            // Tải dữ liệu chi tiết hóa đơn
            try {
                loadHoaDonChiTiet(idHoaDon);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Lỗi khi tải chi tiết hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Mã hóa đơn không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn.", "Lỗi", JOptionPane.WARNING_MESSAGE);
    }
    jComboBox1.setSelectedIndex(0);
    loadProducts();
    }//GEN-LAST:event_jblBanHang_SanPhamMouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
         // Hiển thị hộp thoại xác nhận
    int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn làm mới giỏ hàng không?", "Xác nhận làm mới", JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        // Lấy mã hóa đơn hiện tại
        int selectedHoaDonRow = jtbHoaDon.getSelectedRow();
        if (selectedHoaDonRow != -1) {
            String maHoaDon = jtbHoaDon.getValueAt(selectedHoaDonRow, 1).toString();

            // Lấy danh sách sản phẩm trong giỏ hàng
            List<HoaDonChiTiet> productList = getProductsFromCart();

            // Xóa thông tin sản phẩm khỏi hóa đơn chi tiết trong cơ sở dữ liệu
            deleteAllHoaDonChiTiet(maHoaDon);

            // Cập nhật lại số lượng tồn kho
            for (HoaDonChiTiet product : productList) {
                updateStockAfterRemoval(product.getMaSanPham(), product.getSoLuong());
            }

            // Xóa tất cả các sản phẩm trong giỏ hàng
            DefaultTableModel modelGioHang = (DefaultTableModel) jblBanHang_GioHang.getModel();
            modelGioHang.setRowCount(0);

            // Cập nhật lại bảng sản phẩm
            loadSanPhamTable();
            
            jlbMaHD.setText("");
            jLabel27.setText("0");
            jLabel31.setText("0");
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn.", "Lỗi", JOptionPane.WARNING_MESSAGE);
        }
    }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        int selectedRow = jblBanHang_GioHang.getSelectedRow();
    if (selectedRow != -1) {
        // Lấy mã sản phẩm từ hàng được chọn trong giỏ hàng
        String maSanPham = jblBanHang_GioHang.getValueAt(selectedRow, 0).toString();
        int soLuongXoa = Integer.parseInt(jblBanHang_GioHang.getValueAt(selectedRow, 3).toString());

        // Lấy mã hóa đơn hiện tại
        int selectedHoaDonRow = jtbHoaDon.getSelectedRow();
        if (selectedHoaDonRow != -1) {
            String maHoaDon = jtbHoaDon.getValueAt(selectedHoaDonRow, 1).toString();

            // Xóa sản phẩm khỏi giỏ hàng
            DefaultTableModel modelGioHang = (DefaultTableModel) jblBanHang_GioHang.getModel();
            modelGioHang.removeRow(selectedRow);

            // Xóa thông tin sản phẩm khỏi hóa đơn chi tiết trong cơ sở dữ liệu
            deleteHoaDonChiTiet(maHoaDon, maSanPham);

            // Cập nhật lại số lượng tồn kho
            updateStockAfterRemoval(maSanPham, soLuongXoa);
            
            // Cập nhật lại số lượng tồn kho trên jTable sản phẩm
            updateProductTable(maSanPham);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn.", "Lỗi", JOptionPane.WARNING_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm trong giỏ hàng.", "Lỗi", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jbtTaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtTaoHDActionPerformed
    DefaultTableModel model = (DefaultTableModel) jtbHoaDon.getModel();
    int rowCount = model.getRowCount();

    // Kiểm tra số lượng hóa đơn đã đạt giới hạn tối đa chưa
    if (rowCount >= 10) {
        JOptionPane.showMessageDialog(null, "Đã đạt giới hạn tối đa 10 hóa đơn.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Lấy số điện thoại từ JLabel
    String sdtKH = txtSDTKH.getText().trim(); // Giả sử txtSDTKH là JTextField chứa số điện thoại của khách hàng

    // Tạo hóa đơn mới trong cơ sở dữ liệu
    String sql = "INSERT INTO HoaDon (ID_khachHang, ID_nhanVien, ID_voucher, maNhanVienHD, giaTriThanhToan, trangThaiHoaDon, ngayTao) " +
                 "VALUES (NULL, NULL, NULL, ?, 0, N'Chờ thanh toán', GETDATE())";

    try (Connection connection = DBConnect.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        // Đặt giá trị cho maNhanVienHD
        preparedStatement.setString(1, maNV);

        // Thực hiện câu lệnh SQL
        int affectedRows = preparedStatement.executeUpdate();

        // Kiểm tra số lượng hàng bị ảnh hưởng để xác nhận việc chèn thành công
        if (affectedRows > 0) {
            // Lấy ID của hóa đơn mới tạo
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int hoaDonID = generatedKeys.getInt(1);

                    // Lấy thông tin giá trị hóa đơn và ngày tạo từ cơ sở dữ liệu
                    String getInvoiceDetailsSQL = "SELECT giaTriThanhToan, ngayTao FROM HoaDon WHERE ID_hoaDon = ?";
                    try (PreparedStatement pstmt = connection.prepareStatement(getInvoiceDetailsSQL)) {
                        pstmt.setInt(1, hoaDonID);
                        try (ResultSet rs = pstmt.executeQuery()) {
                            if (rs.next()) {
                                double giaTriThanhToan = rs.getDouble("giaTriThanhToan");
                                Date ngayTao = rs.getDate("ngayTao");

                                // Lấy ID khách hàng từ số điện thoại
                                String getCustomerIDSQL = "SELECT ID_khachHang FROM KhachHang WHERE soDT = ?";
                                int customerID = -1; // Giá trị mặc định nếu không tìm thấy ID khách hàng
                                try (PreparedStatement pstmtCustomerID = connection.prepareStatement(getCustomerIDSQL)) {
                                    pstmtCustomerID.setString(1, sdtKH);
                                    try (ResultSet rsCustomerID = pstmtCustomerID.executeQuery()) {
                                        if (rsCustomerID.next()) {
                                            customerID = rsCustomerID.getInt("ID_khachHang");
                                        }
                                    }
                                }

                                // Lấy tên khách hàng từ ID khách hàng
                                String getCustomerNameSQL = "SELECT hoTen FROM KhachHang WHERE ID_khachHang = ?";
                                String customerName = "Chưa xác định"; // Giá trị mặc định nếu không tìm thấy tên khách hàng
                                try (PreparedStatement pstmtCustomerName = connection.prepareStatement(getCustomerNameSQL)) {
                                    pstmtCustomerName.setInt(1, customerID);
                                    try (ResultSet rsCustomerName = pstmtCustomerName.executeQuery()) {
                                        if (rsCustomerName.next()) {
                                            customerName = rsCustomerName.getString("hoTen");
                                        }
                                    }
                                }

                                // Thêm dòng mới vào bảng hóa đơn trong giao diện người dùng
                                Object[] rowData = {rowCount + 1, hoaDonID, customerName, maNV, "Chờ thanh toán", giaTriThanhToan, ngayTao};
                                model.addRow(rowData);
                            }
                        }
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không thể tạo hóa đơn mới.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi tạo hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    loadHoaDon();
    }//GEN-LAST:event_jbtTaoHDActionPerformed

    private void jbtXoaHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtXoaHoaDonActionPerformed
        // TODO add your handling code here:
        int selectedRow = jtbHoaDon.getSelectedRow();
    if (selectedRow != -1) {
        // Hiển thị hộp thoại xác nhận
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa hóa đơn này không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            // Lấy mã hóa đơn từ hàng được chọn
            int maHoaDon = Integer.parseInt(jtbHoaDon.getValueAt(selectedRow, 1).toString());

            // Lấy thông tin các sản phẩm trong hóa đơn chi tiết
            List<HoaDonChiTiet> chiTietList = getHoaDonChiTiet(maHoaDon);

            // Cập nhật lại số lượng tồn kho của các sản phẩm và cập nhật jTable sản phẩm
            for (HoaDonChiTiet chiTiet : chiTietList) {
                updateStockAfterRemoval(chiTiet.getMaSanPham(), chiTiet.getSoLuong());
                updateProductTable(chiTiet.getMaSanPham());
            }

            // Xóa hóa đơn và chi tiết hóa đơn khỏi cơ sở dữ liệu
            deleteHoaDon(maHoaDon);

            // Xóa hóa đơn khỏi jTable
            DefaultTableModel model = (DefaultTableModel) jtbHoaDon.getModel();
            model.removeRow(selectedRow);

            // Xóa hết sản phẩm ra khỏi giỏ hàng
            DefaultTableModel modelGioHang = (DefaultTableModel) jblBanHang_GioHang.getModel();
            modelGioHang.setRowCount(0);
            
            txtSDTKH.setText("");
            txtTenKH.setText("");
            jlbMaHD.setText("");
            jLabel27.setText("0");
            jLabel31.setText("0");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn.", "Lỗi", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_jbtXoaHoaDonActionPerformed

    private void jblBanHang_SanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblBanHang_SanPhamMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jblBanHang_SanPhamMouseEntered
private int selectedHoaDonID = -1;
    private void jtbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbHoaDonMouseClicked
         int selectedRow = jtbHoaDon.getSelectedRow();
    
    if (selectedRow != -1) {
        // Lấy mã hóa đơn từ hàng được chọn (giả sử cột mã hóa đơn ở vị trí thứ 1)
        Object idHoaDonObj = jtbHoaDon.getValueAt(selectedRow, 1);
        
        if (idHoaDonObj instanceof Integer) {
            int idHoaDon = (Integer) idHoaDonObj;
            // Cập nhật JLabel với mã hóa đơn
            jlbMaHD.setText("" + idHoaDon);
            
            // Tải dữ liệu chi tiết hóa đơn
            try {
                loadHoaDonChiTiet(idHoaDon);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Lỗi khi tải chi tiết hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            // Lấy ID khách hàng từ hóa đơn
            int idKhachHang = getCustomerIdByInvoiceId(idHoaDon);
            
            if (idKhachHang != -1) {
                // Lấy thông tin khách hàng
                KhachHangMD customer = getCustomerById(idKhachHang);
                
                if (customer != null) {
                    // Cập nhật JLabel với tên và số điện thoại khách hàng
                    txtTenKH.setText(customer.getHoTen());
                    txtSDTKH.setText(customer.getSoDT());
                } else {
                    txtTenKH.setText("Chưa xác định");
                    txtSDTKH.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Hóa đơn không có ID khách hàng hợp lệ.");
                txtTenKH.setText("Khách vãng lai");
                txtSDTKH.setText("0000000000");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Mã hóa đơn không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn.", "Lỗi", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_jtbHoaDonMouseClicked
private KhachHangMD getCustomerById(int idKhachHang) {
    String sql = "SELECT hoTen, soDT FROM KhachHang WHERE ID_khachHang = ?";
    try (Connection connection = DBConnect.getConnection();
         PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setInt(1, idKhachHang);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String hoTen = rs.getString("hoTen");
                String soDT = rs.getString("soDT");
                return new KhachHangMD(hoTen, soDT); // Constructor with id, name, and phone number
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
private int getCustomerIdByInvoiceId(int idHoaDon) {
    String sql = "SELECT ID_khachHang FROM HoaDon WHERE ID_hoaDon = ?";
    try (Connection connection = DBConnect.getConnection();
         PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setInt(1, idHoaDon);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("ID_khachHang");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1;
}
    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
    // Kiểm tra nếu sự kiện được kích hoạt không phải do việc khởi tạo hoặc reset
    if (evt.getSource() == jComboBox2) {
        int selectedHoaDonRow = jtbHoaDon.getSelectedRow();
        
        if (selectedHoaDonRow == -1) {
            
        } else {
            applyVoucherAndUpdateTotal();
        }
    }    
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        String sDT = JOptionPane.showInputDialog(this, "Mời nhập SDT cần tìm:", "Tìm kiếm khách hàng theo SDT", JOptionPane.INFORMATION_MESSAGE);

    if (sDT != null && !sDT.trim().isEmpty()) {
        int idKhachHang = getCustomerIdByPhoneNumber(sDT);

        if (idKhachHang != -1) {
            // Tìm kiếm khách hàng theo số điện thoại
            List<KhachHangMD> kh = sm.timKiem(sDT);
            
            if (kh != null && !kh.isEmpty()) {
                // Giả sử kết quả đầu tiên là kết quả mong muốn
                KhachHangMD foundPerson = kh.get(0);

                // Điền thông tin vào txtTen và txtSDT
                txtTenKH.setText(foundPerson.getHoTen());
                txtSDTKH.setText(foundPerson.getSoDT());
                
                // Cập nhật ID khách hàng trong bảng hóa đơn
                int selectedRow = jtbHoaDon.getSelectedRow();
                if (selectedRow != -1) {
                    int idHoaDon = (Integer) jtbHoaDon.getValueAt(selectedRow, 1);
                    updateCustomerInInvoice(idHoaDon, idKhachHang);
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng với số điện thoại đã nhập.");
                txtTenKH.setText("Khách vãng lai");
                txtSDTKH.setText("0000000000");
                
                // Cập nhật ID khách hàng là khách vãng lai trong bảng hóa đơn
                int selectedRow = jtbHoaDon.getSelectedRow();
                if (selectedRow != -1) {
                    int idHoaDon = (Integer) jtbHoaDon.getValueAt(selectedRow, 1);
                    // Giả sử ID khách vãng lai là 0 hoặc một giá trị khác mà bạn định nghĩa
                    updateCustomerInInvoice(idHoaDon, 0); // Hoặc sử dụng ID khác của khách vãng lai
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng với số điện thoại đã nhập.");
            txtTenKH.setText("Khách vãng lai");
            txtSDTKH.setText("0000000000");
            
            // Cập nhật ID khách hàng là khách vãng lai trong bảng hóa đơn
            int selectedRow = jtbHoaDon.getSelectedRow();
            if (selectedRow != -1) {
                int idHoaDon = (Integer) jtbHoaDon.getValueAt(selectedRow, 1);
                // Giả sử ID khách vãng lai là 0 hoặc một giá trị khác mà bạn định nghĩa
                updateCustomerInInvoice(idHoaDon, 1); // Hoặc sử dụng ID khác của khách vãng lai
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn.");
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ.");
    }
    }//GEN-LAST:event_jButton14ActionPerformed
    private int getCustomerIdByPhoneNumber(String phoneNumber) {
    String sql = "SELECT ID_khachHang FROM KhachHang WHERE soDT = ?";
    
    try (Connection connection = DBConnect.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        
        statement.setString(1, phoneNumber);
        
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("ID_khachHang");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi lấy ID khách hàng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    
    return -1; // Trả về -1 nếu không tìm thấy khách hàng
}
    private void updateCustomerInInvoice(int idHoaDon, int idKhachHang) {
    String sql = "UPDATE HoaDon SET ID_khachHang = ? WHERE ID_hoaDon = ?";

    try (Connection connection = DBConnect.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, idKhachHang);
        statement.setInt(2, idHoaDon);

        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Cập nhật thông tin hóa đơn thành công.");
            // Làm mới bảng hóa đơn để cập nhật tên khách hàng
            loadHoaDon();
        } else {
            JOptionPane.showMessageDialog(this, "Không thể cập nhật thông tin hóa đơn.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật thông tin hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new viewThemKhachHang().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // Thu thập thông tin từ giao diện người dùng
        int selectedHoaDonRow = jtbHoaDon.getSelectedRow();
        if (selectedHoaDonRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn trước.", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return; // Dừng lại nếu chưa chọn hóa đơn
        }
        if (txtSDTKH.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin khách hàng.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        DefaultTableModel modelGioHang = (DefaultTableModel) jblBanHang_GioHang.getModel();
        if (modelGioHang.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Giỏ hàng của hóa đơn không có sản phẩm nào.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return; // Dừng lại nếu giỏ hàng trống
        }
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thanh toán?", "Xác nhận thanh toán", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int maHD = Integer.parseInt(jlbMaHD.getText());
                String soDienThoai = txtSDTKH.getText().trim();
                String maNhanVien = jlbMaNV.getText().trim();
                String selectedVoucher = (String) jComboBox2.getSelectedItem();
                String ghiChu = jTextField6.getText().trim();

                // Lấy tổng tiền hàng từ jLabel và chuyển sang định dạng double
                String tongTienHangStr = jLabel27.getText().replaceAll("[^\\d.,]", ""); // Loại bỏ ký tự không phải số
                double tongTienHang;
                try {
                    // Chuyển chuỗi số tiền về định dạng double
                    tongTienHang = Double.parseDouble(tongTienHangStr.replaceAll(",", "."));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Tổng tiền hàng không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Lấy giá trị giảm giá từ voucher đã chọn
                double giamGia = getVoucherDiscount(selectedVoucher);
                double tienThue = tongTienHang * 0.1;
                // Tính tiền khách cần trả sau khi áp dụng thuế và voucher
                double tienKhachCanTra = tongTienHang +(tongTienHang* 0.1); // Áp dụng thuế 10%
                tienKhachCanTra -= giamGia; // Áp dụng khuyến mại từ voucher

                // Gọi phương thức thanh toán
                thanhToanHD(maHD, soDienThoai, maNhanVien, selectedVoucher, tienKhachCanTra);
                thanhToanHDCT(maHD, soDienThoai, tienThue, giamGia, tienKhachCanTra, ghiChu);
                JOptionPane.showMessageDialog(this, "Thanh toán thành công.");
                loadHoaDon();
                resetHoaDonForm();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi cập nhật hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jtbHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbHoaDonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jtbHoaDonMouseEntered

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased
        // TODO add your handling code here:
    String searchQuery = jTextField8.getText().trim();

    if (searchQuery.isEmpty()) {
        // Nếu trường tìm kiếm trống, thông báo lỗi
        JOptionPane.showMessageDialog(null, "Vui lòng nhập mã sản phẩm hoặc tên sản phẩm để tìm.", "Lỗi", JOptionPane.WARNING_MESSAGE);
        return;
    }

    DefaultTableModel model = (DefaultTableModel) jblBanHang_SanPham.getModel();
    model.setRowCount(0); // Xóa tất cả các hàng hiện tại trong bảng

    // Câu lệnh SQL để tìm kiếm theo mã sản phẩm hoặc tên sản phẩm
    String sql = "SELECT maSanPham, tenSanPham, tenHuongVi, soLuongTon, donGia " +
                 "FROM SanPham " +
                 "JOIN SanPhamChiTiet ON SanPham.ID_sanPham = SanPhamChiTiet.ID_sanPham " +
                 "JOIN HuongVi ON SanPhamChiTiet.ID_huongVi = HuongVi.ID_huongVi " +
                 "WHERE maSanPham LIKE ? OR tenSanPham LIKE ?";

    try (Connection connection = DBConnect.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        // Thiết lập giá trị tham số cho câu lệnh SQL
        String searchPattern = "%" + searchQuery + "%"; // Tìm kiếm với mẫu chứa chuỗi tìm kiếm
        statement.setString(1, searchPattern);
        statement.setString(2, searchPattern);

        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String maSanPham = resultSet.getString("maSanPham");
                String tenSanPham = resultSet.getString("tenSanPham");
                String tenHuongVi = resultSet.getString("tenHuongVi");
                int soLuongTon = resultSet.getInt("soLuongTon");
                double donGia = resultSet.getDouble("donGia");

                model.addRow(new Object[]{maSanPham, tenSanPham, tenHuongVi, soLuongTon, donGia});
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jTextField8KeyReleased

    private void jButton13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseClicked
        initWebCam();//initWebCam();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13MouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
         String tenHV = (String) jComboBox1.getSelectedItem();
    DefaultTableModel model = (DefaultTableModel) jblBanHang_SanPham.getModel();
    model.setRowCount(0); // Xóa tất cả các hàng hiện tại trong bảng

    // Câu lệnh SQL với tham số cho tenHuongVi
    String sql = "SELECT maSanPham, tenSanPham, tenHuongVi, soLuongTon, donGia " +
                 "FROM SanPham " +
                 "JOIN SanPhamChiTiet ON SanPham.ID_sanPham = SanPhamChiTiet.ID_sanPham " +
                 "JOIN HuongVi ON SanPhamChiTiet.ID_huongVi = HuongVi.ID_huongVi " +
                 "WHERE tenHuongVi = ?";

    try (Connection connection = DBConnect.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        // Đặt tham số cho câu lệnh SQL
        statement.setString(1, tenHV);
        
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String maSanPham = resultSet.getString("maSanPham");
                String tenSanPham = resultSet.getString("tenSanPham");
                String tenHuongVi = resultSet.getString("tenHuongVi");
                int soLuongTon = resultSet.getInt("soLuongTon");
                double donGia = resultSet.getDouble("donGia");

                // Thêm dữ liệu vào bảng
                model.addRow(new Object[]{maSanPham, tenSanPham, tenHuongVi, soLuongTon, donGia});
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
     
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jblBanHang_GioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblBanHang_GioHangMouseClicked
       int selectedHoaDonRow = jtbHoaDon.getSelectedRow();
        String maHoaDon = jtbHoaDon.getValueAt(selectedHoaDonRow, 1).toString();
        int selectedRow = jblBanHang_GioHang.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm trong giỏ hàng.", "Lỗi", JOptionPane.WARNING_MESSAGE);
        return;
    }

    DefaultTableModel modelGioHang = (DefaultTableModel) jblBanHang_GioHang.getModel();
    
    // Lấy thông tin sản phẩm được chọn từ giỏ hàng
    String maSanPham = modelGioHang.getValueAt(selectedRow, 0).toString();
    String tenSanPham = modelGioHang.getValueAt(selectedRow, 1).toString();
    int currentQuantity = Integer.parseInt(modelGioHang.getValueAt(selectedRow, 3).toString());
    double currentDonGia = Double.parseDouble(modelGioHang.getValueAt(selectedRow, 2).toString());

    // Hiển thị hộp thoại nhập số lượng mới
    String soLuongStr = JOptionPane.showInputDialog("Nhập số lượng mới:", currentQuantity);

    if (soLuongStr == null || soLuongStr.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        // Chuyển đổi số lượng sang giá trị số
        int newQuantity = Integer.parseInt(soLuongStr);

        // Nếu số lượng mới là 0, xóa sản phẩm khỏi giỏ hàng và cơ sở dữ liệu
        if (newQuantity == 0) {
            // Xóa sản phẩm khỏi giỏ hàng
            modelGioHang.removeRow(selectedRow);

            // Xóa sản phẩm khỏi cơ sở dữ liệu
            deleteHoaDonChiTiet(maHoaDon, maSanPham);

            // Cập nhật số lượng tồn kho
            String sqlStockCheck = "SELECT soLuongTon FROM SanPhamChiTiet WHERE maSanPhamChiTiet = ?";
            int soLuongTon = 0;

            try (Connection connection = DBConnect.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sqlStockCheck)) {
                stmt.setString(1, maSanPham);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        soLuongTon = rs.getInt("soLuongTon");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi kiểm tra số lượng tồn kho: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cập nhật số lượng tồn kho
            int newStock = soLuongTon + currentQuantity; // Trả số lượng hiện tại về kho
            updateStock(maSanPham, newStock);

            // Làm mới danh sách sản phẩm
            loadProducts();

        } else {
            // Tính toán thành tiền mới
            double newTotal = newQuantity * currentDonGia;

            // Kiểm tra số lượng tồn kho
            String sqlStockCheck = "SELECT soLuongTon FROM SanPhamChiTiet WHERE maSanPhamChiTiet = ?";
            int soLuongTon = 0;

            try (Connection connection = DBConnect.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sqlStockCheck)) {
                stmt.setString(1, maSanPham);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        soLuongTon = rs.getInt("soLuongTon");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi kiểm tra số lượng tồn kho: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (newQuantity > soLuongTon) {
                JOptionPane.showMessageDialog(this, "Số lượng mới nhiều hơn số lượng tồn kho.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cập nhật giỏ hàng
            modelGioHang.setValueAt(newQuantity, selectedRow, 3);
            modelGioHang.setValueAt(newTotal, selectedRow, 4);

            // Cập nhật thông tin trong cơ sở dữ liệu
            updateCartInDatabase(maSanPham, currentDonGia, newQuantity, newTotal);

            // Cập nhật số lượng tồn kho
            int newStock = soLuongTon + currentQuantity - newQuantity; // Đưa số lượng hiện tại về kho và trừ đi số lượng mới
            updateStock(maSanPham, newStock);

            // Làm mới danh sách sản phẩm
            loadProducts();
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    int selectedRoww = jtbHoaDon.getSelectedRow();

    if (selectedRoww != -1) {
        // Lấy mã hóa đơn từ hàng được chọn (giả sử cột mã hóa đơn ở vị trí thứ 1)
        Object idHoaDonObj = jtbHoaDon.getValueAt(selectedRoww, 1);
        
        // Kiểm tra và chuyển đổi giá trị mã hóa đơn sang kiểu int
        if (idHoaDonObj instanceof Integer) {
            int idHoaDon = (Integer) idHoaDonObj;
            // Cập nhật JLabel với mã hóa đơn
            jlbMaHD.setText("" + idHoaDon);
            
            // Tải dữ liệu chi tiết hóa đơn
            try {
                loadHoaDonChiTiet(idHoaDon);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Lỗi khi tải chi tiết hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Mã hóa đơn không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn.", "Lỗi", JOptionPane.WARNING_MESSAGE);
    }
}

// Phương thức xóa sản phẩm khỏi cơ sở dữ liệu
private void deleteProductFromCart(String maSanPham) {
    String sqlDelete = "DELETE FROM GioHang WHERE maSanPham = ?";
    try (Connection connection = DBConnect.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sqlDelete)) {
        stmt.setString(1, maSanPham);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi xóa sản phẩm khỏi giỏ hàng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jblBanHang_GioHangMouseClicked

  
    private void result_filedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_result_filedKeyReleased
        // TODO add your handling code here:
        // TODO add your handling code here:
        String maQR = result_filed.getText().trim();

        DefaultTableModel model = (DefaultTableModel) jblBanHang_SanPham.getModel();
        model.setRowCount(0); // Xóa tất cả các hàng hiện tại trong bảng

        String sql = "Select maSanPham, tenSanPham,tenHuongVi, soLuongTon, donGia from SanPham join SanPhamChiTiet on SanPham.ID_sanPham = SanPhamChiTiet.ID_sanPham join HuongVi on SanPhamChiTiet.ID_huongVi = HuongVi.ID_huongVi where maSanPham = ?";
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1,maQR);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String maSanPham = resultSet.getString("maSanPham");
                    String tenSanPham = resultSet.getString("tenSanPham");
                    String tenHuongVi = resultSet.getString("tenHuongVi");
                    int soLuongTon = resultSet.getInt("soLuongTon");
                    double donGia = resultSet.getDouble("donGia");

            model.addRow(new Object[]{maSanPham, tenSanPham,tenHuongVi, soLuongTon, donGia});
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_result_filedKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        loadProducts();
        clearVoucherComboBox();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox2MouseClicked
        // TODO add your handling code here:
            // TODO add your handling code here:
         int selectedHoaDonRow = jtbHoaDon.getSelectedRow();
    if (selectedHoaDonRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn trước.", "Lỗi", JOptionPane.WARNING_MESSAGE);
        return;
    }else{
        applyVoucherAndUpdateTotal();
    }
    }//GEN-LAST:event_jComboBox2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTable jblBanHang_GioHang;
    private javax.swing.JTable jblBanHang_SanPham;
    private javax.swing.JButton jbtTaoHD;
    private javax.swing.JButton jbtXoaHoaDon;
    public static javax.swing.JLabel jlbMaHD;
    public static javax.swing.JLabel jlbMaHD1;
    public static javax.swing.JLabel jlbMaNV;
    public static javax.swing.JLabel jlbMaNV1;
    public static javax.swing.JLabel jlbNgayTao;
    public static javax.swing.JLabel jlbNgayTao1;
    private javax.swing.JTable jtbHoaDon;
    private javax.swing.JTextField result_filed;
    private javax.swing.JTextField txtSDTKH;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
    

}
