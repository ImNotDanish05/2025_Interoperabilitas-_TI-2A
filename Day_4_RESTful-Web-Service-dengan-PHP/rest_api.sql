-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 15, 2025 at 03:43 PM
-- Server version: 8.0.30
-- PHP Version: 8.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rest_api`
--

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `price` float NOT NULL,
  `quantity` int NOT NULL,
  `seller` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `product_name`, `price`, `quantity`, `seller`) VALUES
(1, 'danish05_Keyboard', 75000, 50, 'PT. Techno Nusantara'),
(2, 'danish05_Mouse', 37500, 80, 'PT. Gadgetindo'),
(3, 'danish05_Monitor 24 Inch', 850000, 20, 'PT. Vision Tek'),
(4, 'danish05_Headset Gaming', 250000, 35, 'PT. Audio Blast'),
(5, 'danish05_Webcam HD', 150000, 40, 'PT. Streamers ID'),
(6, 'danish05_Microphone Condenser', 300000, 25, 'PT. SoundTech'),
(7, 'danish05_USB Hub 3.0', 50000, 60, 'PT. Hubster'),
(8, 'danish05_External Hard Disk 1TB', 500000, 15, 'PT. DataStore'),
(9, 'danish05_Mechanical Keyboard', 600000, 30, 'PT. MechaBoard'),
(10, 'danish05_Gaming Chair', 1250000, 10, 'PT. Comfort Gamer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
