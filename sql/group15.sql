-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 16, 2016 at 03:49 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `group15`
--

-- --------------------------------------------------------

--
-- Table structure for table `don_vi`
--

CREATE TABLE IF NOT EXISTS `don_vi` (
  `id` int(11) NOT NULL,
  `ten` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tai_khoan`
--

CREATE TABLE IF NOT EXISTS `tai_khoan` (
  `id` int(11) NOT NULL,
  `ten_dang_nhap` varchar(100) NOT NULL,
  `mat_khau` char(32) NOT NULL,
  `kieu_nguoi_dung` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tai_khoan`
--

INSERT INTO `tai_khoan` (`id`, `ten_dang_nhap`, `mat_khau`, `kieu_nguoi_dung`) VALUES
(0, 'quanly', '202cb962ac59075b964b07152d234b70', 0),
(1, 'donvi1', '202cb962ac59075b964b07152d234b70', 1),
(2, 'donvi2', '202cb962ac59075b964b07152d234b70', 1),
(3, 'donvi3', '202cb962ac59075b964b07152d234b70', 1);

-- --------------------------------------------------------

--
-- Table structure for table `thiet_bi`
--

CREATE TABLE IF NOT EXISTS `thiet_bi` (
  `id` int(11) NOT NULL,
  `ten_thiet_bi` varchar(100) NOT NULL,
  `thong_so_ky_thuat` varchar(255) NOT NULL,
  `tinh_trang` varchar(100) NOT NULL,
  `nha_san_xuat` varchar(100) NOT NULL,
  `so_luong` int(11) NOT NULL,
  `gia_don_chiec` int(11) NOT NULL,
  `ngay_nhap` datetime NOT NULL,
  `ngay_chinh_sua` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `vat_tu`
--

CREATE TABLE IF NOT EXISTS `vat_tu` (
  `id` int(11) NOT NULL,
  `ten_vat_tu` varchar(100) NOT NULL,
  `so_luong` float NOT NULL,
  `don_vi_tinh` varchar(30) NOT NULL,
  `gia` int(11) NOT NULL,
  `nha_cung_cap` varchar(100) NOT NULL,
  `ngay_nhap` datetime NOT NULL,
  `ngay_chinh_sua` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `vat_tu_duoc_cap`
--

CREATE TABLE IF NOT EXISTS `vat_tu_duoc_cap` (
  `id` int(11) NOT NULL,
  `don_vi_id` int(11) NOT NULL,
  `vat_tu_id` int(11) NOT NULL,
  `so_luong_cap` float NOT NULL,
  `thoi_gian_cap` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `vat_tu_tieu_thu`
--

CREATE TABLE IF NOT EXISTS `vat_tu_tieu_thu` (
  `id` int(11) NOT NULL,
  `vat_tu_duoc_cap_id` int(11) NOT NULL,
  `so_luong_su_dung` varchar(255) NOT NULL,
  `ngay_su_dung` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `vat_tu_yeu_cau`
--

CREATE TABLE IF NOT EXISTS `vat_tu_yeu_cau` (
  `id` int(11) NOT NULL,
  `don_vi_id` int(11) NOT NULL,
  `ten_vat_tu` varchar(255) NOT NULL,
  `mo_ta` text,
  `so_luong` float NOT NULL,
  `don_vi_tinh` varchar(10) NOT NULL,
  `yeu_cau_khac` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `don_vi`
--
ALTER TABLE `don_vi`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tai_khoan`
--
ALTER TABLE `tai_khoan`
 ADD PRIMARY KEY (`id`), ADD KEY `id` (`id`);

--
-- Indexes for table `thiet_bi`
--
ALTER TABLE `thiet_bi`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vat_tu`
--
ALTER TABLE `vat_tu`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vat_tu_duoc_cap`
--
ALTER TABLE `vat_tu_duoc_cap`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_vat_tu_id` (`vat_tu_id`), ADD KEY `fk_don_vi_id` (`don_vi_id`);

--
-- Indexes for table `vat_tu_tieu_thu`
--
ALTER TABLE `vat_tu_tieu_thu`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_vat_tu_duoc_cap_id` (`vat_tu_duoc_cap_id`);

--
-- Indexes for table `vat_tu_yeu_cau`
--
ALTER TABLE `vat_tu_yeu_cau`
 ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `vat_tu_duoc_cap`
--
ALTER TABLE `vat_tu_duoc_cap`
ADD CONSTRAINT `fk_don_vi_id` FOREIGN KEY (`don_vi_id`) REFERENCES `don_vi` (`id`),
ADD CONSTRAINT `fk_vat_tu_id` FOREIGN KEY (`vat_tu_id`) REFERENCES `vat_tu` (`id`);

--
-- Constraints for table `vat_tu_tieu_thu`
--
ALTER TABLE `vat_tu_tieu_thu`
ADD CONSTRAINT `fk_vat_tu_duoc_cap_id` FOREIGN KEY (`vat_tu_duoc_cap_id`) REFERENCES `vat_tu_duoc_cap` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
