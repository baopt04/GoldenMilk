--DROP DATABASE DuAnBanSua

Create database DuAnBanSua
go
USE DuAnBanSua;
GO
CREATE TABLE LoaiSanPham (
    ID_loaiSanPham INT PRIMARY KEY identity,
	maLoaiSanPham nvarchar(50) ,
    tenLoaiSanPham NVARCHAR(100),
);

CREATE TABLE HangSanPham (
    ID_hangSanPham INT PRIMARY KEY identity,
	maHangSanPham nvarchar(50),
    tenHangSanPham NVARCHAR(100),
);

CREATE TABLE HuongVi (
    ID_huongVi INT PRIMARY KEY identity ,
	maHuongVi nvarchar(50) ,
    tenHuongVi NVARCHAR(100),

);

CREATE TABLE DoiTuongSuDung (
    ID_doiTuongSD INT PRIMARY KEY identity,
	maDoiTuongSuDung nvarchar(50) ,
    tenDoiTuongSD NVARCHAR(100),
   
);

CREATE TABLE NhaCungCap (
    ID_nhaCungCap INT PRIMARY KEY identity,
	maNhaCungCap nvarchar(50) , 
    tenNhaCungCap NVARCHAR(100),
    soDT NVARCHAR(20),
    email NVARCHAR(100),
    diaChi NVARCHAR(255),
);

CREATE TABLE KhachHang (
    ID_khachHang INT PRIMARY KEY identity, 
	maKhachHang nvarchar(50) ,
    hoTen NVARCHAR(100),
    gioiTinh NVARCHAR(10),
    soDT NVARCHAR(20),
    email NVARCHAR(100),
    diaChi NVARCHAR(255),
  
);

CREATE TABLE ChucVu (
    ID_chucVu INT PRIMARY KEY identity,
	maChucVu nvarchar(50) ,
    tenChucVu NVARCHAR(100),
 
);
CREATE TABLE LoSanXuat (
    ID_loSanXuat INT PRIMARY KEY identity,
    ID_nhaCungCap INT FOREIGN KEY REFERENCES NhaCungCap(ID_nhaCungCap),
	maLoSanXuat nvarchar(50) , 
	tenLoSanXuat nvarchar(50) ,
    ngayNhap DATE,
    soLuongNhap INT,
    giaNhap FLOAT,
    NSX DATE,
    HSD DATE,

);
Create table DonViTinh (
ID_donViTinh int primary key identity,
maDonViTinh nvarchar(50) , 
tenDonViTinh nvarchar(100)
)

Create table TheTich(
ID_theTich int primary key identity , 
maTheTich nvarchar(50) ,
tenTheTich nvarchar(100) 
)
Create table HamLuongDuong(
ID_hamLuongDuong int primary key identity , 
maHamLuongDuong nvarchar(50) ,
tenHamLuongDuong nvarchar(100) 
)
CREATE TABLE SanPham (
    ID_sanPham INT PRIMARY KEY identity,
    ID_loaiSanPham INT FOREIGN KEY REFERENCES LoaiSanPham(ID_loaiSanPham),
	ID_hangSanPham INT FOREIGN KEY REFERENCES HangSanPham(ID_hangSanPham),
   	maSanPham nvarchar(50) , 
    tenSanPham NVARCHAR(100), 
    moTa NVARCHAR(500),
   trangthai nvarchar(50) 
);

CREATE TABLE SanPhamChiTiet (
    ID_sanPhamChiTiet INT PRIMARY KEY identity,
    ID_sanPham INT FOREIGN KEY REFERENCES SanPham(ID_sanPham),
    ID_loSanXuat INT FOREIGN KEY REFERENCES LoSanXuat(ID_loSanXuat),
	ID_huongVi INT FOREIGN KEY REFERENCES HuongVi(ID_huongVi),
	ID_doiTuongSD INT FOREIGN KEY REFERENCES DoiTuongSuDung(ID_doiTuongSD),
--	 ID_nhaCungCap INT FOREIGN KEY REFERENCES NhaCungCap(ID_nhaCungCap),
	ID_donViTinh INT FOREIGN KEY REFERENCES DonViTinh(ID_donViTinh) , 
	ID_theTich INT FOREIGN KEY REFERENCES TheTich(ID_theTich),
	ID_hamLuongDuong INT FOREIGN KEY REFERENCES HamLuongDuong(ID_hamLuongDuong),
	maSanPhamChiTiet nvarchar(50) ,
    anh NVARCHAR(255),
    soLuongTon INT,
    donGia FLOAT,
    moTa NVARCHAR(500),
    QRCode NVARCHAR(255),
    trangThai BIT
);

CREATE TABLE NhanVien (
    ID_nhanVien INT PRIMARY KEY identity,
    ID_chucVu INT FOREIGN KEY REFERENCES ChucVu(ID_chucVu),
	maNhanVien nvarchar(50) ,
    hoTen NVARCHAR(100),
    gioiTinh NVARCHAR(10),
    ngaySinh DATE,
    soDT NVARCHAR(20),
    email NVARCHAR(100),
    matKhau NVARCHAR(255),
    diaChi NVARCHAR(255),
);

CREATE TABLE Voucher (
    ID_voucher INT PRIMARY KEY identity,
	maVoucher nvarchar(50) ,
	tenVoucher nvarchar(200) ,
    giaTriVoucher FLOAT,
    giaTriApDungVoucher FLOAT,
    soLuongVoucher INT,
    ngayBatDau DATE,
    ngayKetThuc DATE,
    ghiChu NVARCHAR(255),
   
);

CREATE TABLE HoaDon (
    ID_hoaDon INT PRIMARY KEY identity,
    ID_khachHang INT FOREIGN KEY REFERENCES KhachHang(ID_khachHang),
    ID_nhanVien INT FOREIGN KEY REFERENCES NhanVien(ID_nhanVien),
    ID_voucher INT FOREIGN KEY REFERENCES Voucher(ID_voucher),
    giaTriThanhToan FLOAT,
    trangThaiHoaDon NVARCHAR(50),
    ngayTao DATE,
);

CREATE TABLE HoaDonChiTiet (
    ID_hoaDonChiTiet INT PRIMARY KEY identity,
    ID_hoaDon INT FOREIGN KEY REFERENCES HoaDon(ID_hoaDon),
    ID_sanPhamChiTiet INT FOREIGN KEY REFERENCES SanPhamChiTiet(ID_sanPhamChiTiet),
    soLuong INT,
    donGia FLOAT,
    tongTien FLOAT,
    tienThue FLOAT,
    giaTriVoucher FLOAT,
    giaTriThanhToan FLOAT,
    ghiChu NVARCHAR(255),
    ngayTao DATE,
);

-- Thêm bản ghi vào bảng LoaiSanPham
INSERT INTO LoaiSanPham (maLoaiSanPham, tenLoaiSanPham) VALUES
('LSP01', N'Sữa bột'),
('LSP02', N'Sữa tươi'),
('LSP03', N'Sữa đặc'),
('LSP04', N'Sữa chua'),
('LSP05', N'Sữa hạt');

-- Thêm bản ghi vào bảng HangSanPham
INSERT INTO HangSanPham (maHangSanPham, tenHangSanPham) VALUES
('HSP01', 'Vinamilk'),
('HSP02', 'Nutifood'),
('HSP03', 'TH True Milk'),
('HSP04', 'Dutch Lady'),
('HSP05', 'Nestle');

-- Thêm bản ghi vào bảng HuongVi
INSERT INTO HuongVi (maHuongVi, tenHuongVi) VALUES
('HV01', N'Dâu'),
('HV02', N'Sô cô la'),
('HV03', N'Vani'),
('HV04', N'Trà xanh'),
('HV05', N'Khoai môn');

-- Thêm bản ghi vào bảng DoiTuongSuDung
INSERT INTO DoiTuongSuDung (maDoiTuongSuDung, tenDoiTuongSD) VALUES
('DTSD01', N'Trẻ em'),
('DTSD02', N'Người lớn'),
('DTSD03', N'Người già'),
('DTSD04', N'Phụ nữ mang thai'),
('DTSD05', N'Người vận động nhiều');

-- Thêm bản ghi vào bảng NhaCungCap
INSERT INTO NhaCungCap (maNhaCungCap, tenNhaCungCap, soDT, email, diaChi) VALUES
('NCC01', N'Nhà cung cấp A', '0123456789', 'nccA@example.com', N'Địa chỉ A'),
('NCC02', N'Nhà cung cấp B', '0123456788', 'nccB@example.com', N'Địa chỉ B'),
('NCC03', N'Nhà cung cấp C', '0123456787', 'nccC@example.com', N'Địa chỉ C'),
('NCC04', N'Nhà cung cấp D', '0123456786', 'nccD@example.com', N'Địa chỉ D'),
('NCC05', N'Nhà cung cấp E', '0123456785', 'nccE@example.com', N'Địa chỉ E');

-- Thêm bản ghi vào bảng KhachHang
INSERT INTO KhachHang (maKhachHang, hoTen, gioiTinh, soDT, email, diaChi) VALUES
('KH01', N'Nguyễn Văn A', 'Nam', '0123456789', 'kha@example.com', N'Địa chỉ KH A'),
('KH02', N'Trần Thị B', 'Nữ', '0123456788', 'khb@example.com', N'Địa chỉ KH B'),
('KH03', N'Lê Văn C', 'Nam', '0123456787', 'khc@example.com', N'Địa chỉ KH C'),
('KH04', N'Phạm Thị D', 'Nữ', '0123456786', 'khd@example.com', N'Địa chỉ KH D'),
('KH05', N'Đỗ Văn E', 'Nam', '0123456785', 'khe@example.com', N'Địa chỉ KH E');

-- Thêm bản ghi vào bảng ChucVu
INSERT INTO ChucVu (maChucVu, tenChucVu) VALUES
('CV01', 'Giám đốc'),
('CV02', 'Quản lý'),
('CV03', 'Nhân viên bán hàng'),
('CV04', 'Kế toán'),
('CV05', 'Nhân viên kho');

-- Thêm bản ghi vào bảng LoSanXuat
INSERT INTO LoSanXuat (ID_nhaCungCap, maLoSanXuat, tenLoSanXuat, ngayNhap, soLuongNhap, giaNhap, NSX, HSD) VALUES
(1, 'LSX01', N'Lô A', '2024-01-01', 1000, 50000, '2023-12-01', '2025-12-01'),
(2, 'LSX02', N'Lô B', '2024-02-01', 1500, 55000, '2023-11-01', '2025-11-01'),
(3, 'LSX03', N'Lô C', '2024-03-01', 1200, 52000, '2023-10-01', '2025-10-01'),
(4, 'LSX04', N'Lô D', '2024-04-01', 1300, 53000, '2023-09-01', '2025-09-01'),
(5, 'LSX05', N'Lô E', '2024-05-01', 1100, 51000, '2023-08-01', '2025-08-01');

-- Thêm bản ghi vào bảng DonViTinh
INSERT INTO DonViTinh (maDonViTinh, tenDonViTinh) VALUES
('DVT01', N'Hộp'),
('DVT02', N'Chai'),
('DVT03', N'Lon'),
('DVT04', N'Túi'),
('DVT05', N'Lốc');

-- Thêm bản ghi vào bảng TheTich
INSERT INTO TheTich (maTheTich, tenTheTich) VALUES
('TT01', '200ml'),
('TT02', '500ml'),
('TT03', '1L'),
('TT04', '1.5L'),
('TT05', '2L');

-- Thêm bản ghi vào bảng HamLuongDuong
INSERT INTO HamLuongDuong (maHamLuongDuong, tenHamLuongDuong) VALUES
('HLD01', N'Ít đường'),
('HLD02', N'Không đường'),
('HLD03', N'Vừa phải'),
('HLD04', N'Nhiều đường'),
('HLD05', N'Siêu ngọt');

-- Thêm bản ghi vào bảng SanPham
INSERT INTO SanPham (ID_loaiSanPham, ID_hangSanPham, maSanPham, tenSanPham, moTa, trangthai) VALUES
(1, 1, 'SP01', N'Sữa bột Vinamilk', N'Sữa bột dành cho trẻ em', N'Đang bán'),
(2, 2, 'SP02', N'Sữa tươi Nutifood', N'Sữa tươi chất lượng cao', N'Đang bán'),
(3, 3, 'SP03', N'Sữa đặc TH True Milk', N'Sữa đặc có đường', N'Đang bán'),
(4, 4, 'SP04', N'Sữa chua Dutch Lady', N'Sữa chua dâu', N'Đang bán'),
(5, 5, 'SP05', N'Sữa hạt Nestle', N'Sữa hạt dinh dưỡng', N'Đang bán');

-- Thêm bản ghi vào bảng SanPhamChiTiet
INSERT INTO SanPhamChiTiet (ID_sanPham, ID_loSanXuat, ID_huongVi, ID_doiTuongSD, ID_donViTinh, ID_theTich, ID_hamLuongDuong, maSanPhamChiTiet, anh, soLuongTon, donGia, moTa, QRCode, trangThai) VALUES
(1, 1, 1, 1, 1, 1, 1, 'SP01', 'anh1.jpg', 100, 50000, N'Sữa bột Vinamilk ít đường', 'QRCODE01', 1),
(2, 2, 2, 2, 2, 2, 2, 'SP02', 'anh2.jpg', 150, 55000, N'Sữa tươi Nutifood không đường', 'QRCODE02', 1),
(3, 3, 3, 3, 3, 3, 3, 'SP03', 'anh3.jpg', 120, 52000, N'Sữa đặc TH True Milk vừa phải', 'QRCODE03', 1),
(4, 4, 4, 4, 4, 4, 4, 'SP04', 'anh4.jpg', 130, 53000, N'Sữa chua Dutch Lady nhiều đường', 'QRCODE04', 1),
(5, 5, 5, 5, 5, 5, 5, 'SP05', 'anh5.jpg', 110, 51000, N'Sữa hạt Nestle siêu ngọt', 'QRCODE05', 1);

-- Thêm bản ghi vào bảng NhanVien
INSERT INTO NhanVien (ID_chucVu, maNhanVien, hoTen, gioiTinh, ngaySinh, soDT, email, matKhau, diaChi) VALUES
(1, 'NV01', N'Nguyễn Văn A', N'Nam', '1990-01-01', '0123456789', 'nva@example.com', 'password', N'Địa chỉ NV A'),
(2, 'NV02', N'Trần Thị B', N'Nữ', '1991-02-01', '0123456788', 'ttb@example.com', 'password', N'Địa chỉ NV B'),
(3, 'NV03', N'Lê Văn C', N'Nam', '1992-03-01', '0123456787', 'lvc@example.com', 'password', N'Địa chỉ NV C'),
(4, 'NV04', N'Phạm Thị D', N'Nữ', '1993-04-01', '0123456786', 'ptd@example.com', 'password', N'Địa chỉ NV D'),
(5, 'NV05', N'D', N'Nam', '1994-05-01', '0123456785', 'dve@example.com', '111', N'Địa chỉ NV E');

-- Thêm bản ghi vào bảng Voucher
INSERT INTO Voucher (maVoucher, tenVoucher, giaTriVoucher, giaTriApDungVoucher, soLuongVoucher, ngayBatDau, ngayKetThuc, ghiChu) VALUES
('V01',N'Ngày A', 10000, 50000, 100, '2024-01-01', '2024-12-31', 'Voucher 1'),
('V02',N'Ngày B', 20000, 100000, 200, '2024-02-01', '2024-11-30', 'Voucher 2'),
('V03',N'Ngày C', 15000, 75000, 150, '2024-03-01', '2024-10-31', 'Voucher 3'),
('V04',N'Ngày D', 25000, 125000, 250, '2024-04-01', '2024-09-30', 'Voucher 4'),
('V05',N'Ngày E', 5000, 25000, 50, '2024-05-01', '2024-08-31', 'Voucher 5');



select * from LoaiSanPham
select * from HangSanPham
select * from HuongVi
select * from DoiTuongSuDung
select * from NhaCungCap
select * from LoSanXuat
select * from HamLuongDuong
select * from SanPham
select * from SanPhamChiTiet
select * from KhachHang
select * from ChucVu
select * from NhanVien
select * from Voucher
select * from HoaDon
select * from HoaDonChiTiet	
select * from TheTich
select * from DonViTinh