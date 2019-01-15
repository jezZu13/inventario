-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 09-06-2018 a las 09:15:05
-- Versión del servidor: 5.7.21
-- Versión de PHP: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `qzr440`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dispositivo`
--

DROP TABLE IF EXISTS `dispositivo`;
CREATE TABLE IF NOT EXISTS `dispositivo` (
  `id_disp` int(11) NOT NULL AUTO_INCREMENT,
  `identificador_disp` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `sn_disp` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `fecha_alta_disp` date DEFAULT NULL,
  `fecha_baja_disp` date DEFAULT NULL,
  `MAC_disp` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `observaciones_disp` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `id_producto` int(11) NOT NULL,
  `id_ped` int(11) NOT NULL,
  `id_ori_eq` int(11) NOT NULL,
  PRIMARY KEY (`id_disp`),
  KEY `id_producto` (`id_producto`),
  KEY `id_producto_2` (`id_producto`),
  KEY `id_producto_3` (`id_producto`),
  KEY `id_ped` (`id_ped`),
  KEY `id_ori_eq` (`id_ori_eq`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `dispositivo`
--

INSERT INTO `dispositivo` (`id_disp`, `identificador_disp`, `sn_disp`, `fecha_alta_disp`, `fecha_baja_disp`, `MAC_disp`, `observaciones_disp`, `id_producto`, `id_ped`, `id_ori_eq`) VALUES
(1, 'LAB-18', '30295K1', '2009-06-01', NULL, '00:22:5F:AF:B6:22', 'PIV Intel Celeron 900 2.2 Ghz 2Gb RAM 160 Gb HD', 1, 2, 1),
(2, 'LAB-13', '', '2009-06-01', '2016-05-27', '00:22:5F:AF:B6:F6', 'PIV Intel Celeron 900 2.2 Ghz 2Gb RAM 160 Gb HD', 1, 2, 1),
(3, '', '', '2009-09-01', '2016-10-24', '', 'PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 2, 3, 2),
(4, '', '', '2009-09-01', '2016-09-16', '', 'PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 2, 3, 2),
(5, '', '', '2005-09-01', NULL, '', '\"(\"\"heredada\"\" de Antoni Gª)\"', 3, 4, 1),
(6, '', '', '2005-09-01', '2016-05-16', '', 'Armario Madalena', 4, 4, 1),
(7, '', '', '2010-03-01', NULL, '', 'XP PIV 2.8 Ghz 1Gb RAM 80Gb HD NIC 10/100Mb', 2, 3, 2),
(8, '', '', '2010-03-01', NULL, '', 'XP PIV 2.8 Ghz 1Gb RAM 80Gb HD NIC 10/100Mb', 2, 3, 2),
(9, '', '', '2010-03-01', '2016-09-16', '', 'PIV 2.8 Ghz 1Gb RAM 80Gb HD NIC 10/100Mb', 2, 3, 2),
(10, '', '', '2010-03-01', NULL, '', 'XP PIV 2.8 Ghz 1Gb RAM 80Gb HD NIC 10/100Mb', 2, 3, 2),
(11, '325-05', '', '2010-03-01', NULL, '', 'PIV 2.8 Ghz 1Gb RAM 80Gb HD NIC 10/100Mb', 2, 3, 2),
(12, '', '', '2010-03-01', NULL, '', '', 5, 3, 2),
(13, 'Primer 325-08', '', '2010-03-01', NULL, '', 'PIV 2.8 Ghz 1Gb RAM 80Gb HD NIC 10/100Mb', 2, 3, 2),
(14, '', '', '2010-03-01', NULL, '', '', 5, 3, 2),
(15, '', '', '2010-03-01', NULL, '', 'LEVEL ONE OFFICE SWITCH FSW-1621 16 PUERTOS', 6, 5, 1),
(16, '128-01 - VIEJO PC-29 LABORATORIO', '', '2008-09-01', NULL, '94:0C:6D:C4:D9:B2', 'PIV 2.8 Ghz 1Gb RAM 120 Gb HD NIC 10/100Mb', 7, 6, 2),
(17, '324-04', '', '2008-09-01', NULL, '74:EA:3A:E2:B9:B1', 'PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(18, '128-28 - VIEJO 333-04 (profesor)', '', '2008-09-01', NULL, '94:0C:6D:C4:E9:2E', 'XP PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb avast8', 7, 6, 2),
(19, '128-14 - VIEJO 324-03', '', '2008-09-01', NULL, '', 'PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(20, '128-22 - VIEJO 334-04', '', '2008-09-01', NULL, '74:EA:3A:E2:C0:64', 'XP PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(21, '322-04', '', '2008-09-01', NULL, '74:EA:3A:E2:AF:7E', 'XP PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(22, '333-02', '', '2008-09-01', NULL, '74:EA:3A:E2:A2:77', 'XP PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(23, '128-02', '', '2008-09-01', NULL, '74:EA:3A:CA:53:9A', 'XP PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(24, '325-06', '', '2008-09-01', NULL, '74:EA:3A:E2:A3:15', 'W7 PIV 2.8 Ghz 1Gb RAM 40 Gb HD NIC 10/100Mb NIC Wifi TP-Link W7 tuneado passwifi no visible', 7, 6, 2),
(25, 'viejo 322-03', '', '2008-09-01', NULL, '74:EA:3A:E2:B2:8A', 'PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(26, '128-07 - VIEJO 323-04', '', '2008-09-01', NULL, '74:EA:3A:CA:47:15', 'XP PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(27, '128-19 - VIEJO 323-03', '', '2008-09-01', NULL, '74:EA:3A:CA:4A:98', 'XP PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(28, '323-02', '', '2008-09-01', NULL, '', 'PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(29, '323-01 (profesor)', '', '2008-09-01', NULL, '74:EA:3A:CA:4E:B6', 'XP PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb avast8', 7, 6, 2),
(30, '128-15 - VIEJO 322-02', '', '2008-09-01', NULL, '74:EA:3A:E2:AF:32', 'XP PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(31, '', '', '2008-09-01', NULL, '', 'PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(32, '128-24 - VIEJO 334-03', '', '2008-09-01', NULL, '74:EA:3A:E2:BD:B3', 'XP PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(33, '334-02', '', '2008-09-01', NULL, '74:EA:3A:E2:A3:07', 'PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(34, '', '', '2008-09-01', NULL, '', 'PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(35, '128-04 - VIEJO 324-01', '', '2008-09-01', NULL, '74:EA:3A:E2:B8:2E', 'PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(36, '', '', '2008-09-01', NULL, '', 'PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(37, '333-03', '', '2007-09-01', NULL, '74:EA:3A:E2:BA:75', 'XP PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb avast8', 7, 6, 2),
(38, '333-02', '', '2007-09-01', '2012-09-21', '', 'PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(39, '345-02', '', '2008-09-01', NULL, '74:ea:3a:ca:54:2e', 'XP PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb 512 Mb RAM TP-Link', 7, 6, 2),
(40, '331-03', '', '2008-09-01', NULL, '94:0C:6D:C4:D9:72', 'XP PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(41, ' 128-21 - VIEJO 332-03', '', '2007-09-01', NULL, '94:0C:6D:C4:CE:0C', 'XP PIV 2.8 Ghz 1Gb RAM 80Wifi TP/Link TL-WN350G', 7, 6, 2),
(42, '332-01', '', '2007-09-01', '2012-10-16', '', 'PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(43, '332-02', '', '2007-09-01', NULL, '94:0C:6D:C4:E9:6C', 'XP PIV 2.8 Ghz 1Gb RAM 80Wifi TP/Link TL-WN350G', 7, 6, 2),
(44, '332-04', '', '2007-09-01', NULL, '94:0C:6D:C4:E8:F4', 'XP PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb Wifi TP/Link TL-WN350G', 7, 6, 2),
(45, '128-20 - VIEJO 331-01', '', '2007-09-01', NULL, '94:0C:6D:C4:D0:2B', 'XP PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(46, '331-03', '', '2007-09-01', '2012-09-21', '', 'PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(47, '128-27 - VIEJO 331-04 (Profesor)', '', '2007-09-01', NULL, '94:0C:6D:C4:D9:87', 'XP PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(48, '128-17 - VIEJO 331-02', '', '2008-09-01', NULL, '94:0C:6D:C4:E3:2C', 'XP PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb avast8', 7, 6, 2),
(49, '128-06 - VIEJO BIBLIO-02', '', '2007-09-01', NULL, '74:EA:3A:CA:41:76', 'XP PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(50, 'BIBLIO-04', '', '2007-09-01', NULL, '74:EA:3A:CA:44:54', 'XP PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(51, '128-03 - VIEJO BIBLIO-03', '', '2007-09-01', NULL, '74:EA:3A:CA:4C:8A', 'XP PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(52, 'BIBLIO-01', '', '2008-09-01', '2012-09-21', '', 'PIV 2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 7, 6, 2),
(53, '', '', '2013-10-05', NULL, '', 'Proyector BENQ MX661 DLP', 8, 7, 1),
(54, '', '', '2013-10-05', NULL, '', 'Pantalla Desplegable', 9, 7, 1),
(55, '', '', '2013-10-05', NULL, '', 'ALTAVOCES', 10, 7, 1),
(56, '', '1282135001558', '2014-01-08', NULL, '98:3B:16:47:38:F1', 'bq Edison 2 Quad-Core Android 4.2.2 S/N: 1282135001558 TABLET-XXX', 11, 8, 1),
(57, '', '1282134901377', '2014-01-08', NULL, '98:3B:16:45:53:50', 'bq Edison 2 Quad-Core Android 4.2.2 S/N: 1282134901377 TABLET-XXX', 11, 8, 1),
(58, '', '1282134901154', '2014-01-08', NULL, '98:3B:16:47:10:FC', 'bq Edison 2 Quad-Core Android 4.2.2 S/N: 1282134901154 TABLET-XXX', 11, 8, 1),
(59, '', '1282134900280', '2014-01-08', NULL, '98:3B:16:46:11:3E', 'bq Edison 2 Quad-Core Android 4.2.2 S/N: 1282134900280 TABLET-XXX', 11, 8, 1),
(60, '', '1282135001705', '2014-01-08', NULL, '98:3B:16:45:7E:32', 'bq Edison 2 Quad-Core Android 4.2.2 S/N: 1282135001705 TABLET-XXX', 11, 8, 1),
(61, '', '', '2014-01-15', NULL, '', 'Router DRAYTEK VIGOR 3200 Balanceo de Carga 4 líneas VDSL', 12, 9, 1),
(62, '', 'P-870H-51QV2', '2014-01-15', NULL, '', 'Router ZYXEL VDSL P-870H-51QV2 - IP externa 212.85.40.108', 13, 9, 1),
(63, '', 'VMG1312-B', '2014-01-15', NULL, '', 'Router ZYXEL VDSL VMG1312-B  - IP externa 212.85.40.109', 13, 9, 1),
(64, '', '', '2010-09-01', NULL, 'a8:0c:0d:bc:4f:1e', 'CISCO ANTENA w/RP-TNC Connector 2.4 Ghz, 12 dBi', 14, 10, 1),
(65, '', '', '2016-09-09', NULL, '', '\"TFT 19\"\" DELL/HP\"', 29, 3, 2),
(66, '', '', '2016-09-09', NULL, '', '\"TFT 19\"\" DELL/HP\"', 29, 3, 2),
(67, '', '', '2016-09-09', NULL, '', '\"TFT 19\"\" DELL/HP\"', 29, 3, 2),
(68, 'AULADUAL-01', '', '2016-11-17', NULL, 'ec:08:6b:08:20:d7', 'W7Pro 64bits SP1 i5 3.2Ghz 8Gb RAM 250Gb HD DVDRW', 17, 3, 2),
(69, 'AULADUAL-02', '', '2016-11-17', NULL, 'c4:6e:1f:1f:9f:74', 'W7Pro 64bits SP1 i5 3.2Ghz 8Gb RAM 250Gb HD DVDRW', 17, 3, 2),
(70, '', '', '2016-09-30', NULL, '', '\"TFT 22\"\" DELL\"', 18, 3, 2),
(71, '', '', '2016-09-30', NULL, '', '\"TFT 22\"\" DELL\"', 18, 3, 2),
(72, '', '', '2015-09-23', NULL, '', 'Switch CISCO Small Business SG200-50 Port 10/100/1000 Smart Switch S/N DNI????????', 19, 11, 1),
(73, '', '', '2016-09-01', NULL, '', 'Switch CISCO Small Business SG200-50 Port 10/100/1000 Smart Switch S/N DNI????????', 19, 11, 1),
(74, '', '', '1900-01-01', NULL, '', 'HP Deskjet 3637 - cartuchos 302', 20, 1, 1),
(75, '', '', '2017-09-11', NULL, '', 'W10Pro 64bits SP1 i5 3.2Ghz 8Gb RAM 250Gb HD DVDRW', 17, 3, 2),
(76, '', '', '2017-09-11', NULL, '', 'W10Pro 64bits SP1 i5 3.2Ghz 8Gb RAM 250Gb HD DVDRW', 17, 3, 2),
(77, '', '', '2017-09-11', NULL, '', 'W10Pro 64bits SP1 i5 3.2Ghz 8Gb RAM 250Gb HD DVDRW', 17, 3, 2),
(78, '', '', '2017-09-11', NULL, '', 'W10Pro 64bits SP1 i5 3.2Ghz 8Gb RAM 250Gb HD DVDRW', 17, 3, 2),
(79, 'E1-106', 'dsfdag-NK35', '2017-06-15', NULL, '0c:8d:db:65:ef:a7', 'AP Meraki MR33 Cloud Managed AP #SERIE: dsfdag-NK35', 21, 11, 1),
(80, '', '', '2017-11-02', NULL, '', 'ARMARIO PARA PORTÁTILES', 22, 7, 1),
(81, '', '', '2017-10-16', NULL, '', 'HD EXTERNO WD MY PASSPORT 1TB USB 3.0', 23, 12, 1),
(82, '', '', '2017-10-16', NULL, '', 'HD EXTERNO SEAGATE EXPANSION 3TB USB 3.0', 24, 12, 1),
(83, '', '', '2018-01-11', NULL, '', 'W10Pro 64bits SP1 i5 3.2Ghz 8Gb RAM 250Gb HD DVDRW', 17, 3, 2),
(84, '', '', '2018-03-05', NULL, '', 'W10Pro 64bits SP1 i5 2400S  8Gb RAM 250Gb HD DVDRW', 25, 3, 2),
(85, '', '', '2018-03-05', NULL, '', 'W10Pro 64bits SP1 i5 2400S  8Gb RAM 250Gb HD DVDRW', 25, 3, 2),
(86, '', '', '2018-03-05', NULL, '', '\"TFT 17\"\"\"', 26, 3, 2),
(87, '', '', '2018-03-05', NULL, '', '\"TFT 17\"\"\"', 26, 3, 2),
(88, '', '', '2018-03-05', NULL, '', '\"TFT 17\"\"\"', 26, 3, 2),
(89, '', '', '2017-09-01', NULL, '', 'Switch UBIQUITI 24', 27, 11, 1),
(90, '', '', '2017-09-01', NULL, '', 'Switch UBIQUITI 24', 27, 11, 1),
(91, '', '', '2017-09-01', NULL, '', 'Switch UBIQUITI 24', 27, 11, 1),
(92, '', '', '2018-04-10', NULL, '', 'Switch UBIQUITI 48 with SFP+', 28, 11, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `edificio`
--

DROP TABLE IF EXISTS `edificio`;
CREATE TABLE IF NOT EXISTS `edificio` (
  `id_edif` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_edif` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion_edif` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`id_edif`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `edificio`
--

INSERT INTO `edificio` (`id_edif`, `nombre_edif`, `descripcion_edif`) VALUES
(1, 'A-1', 'Infantil-primaria'),
(2, 'B-2', 'Bachillerato-4ºESO-Deportes-Enfermeria'),
(3, 'C-3', 'Ciclos-ESO'),
(4, 'Ajeno al centro', NULL),
(5, 'Privado', 'Equipos privados de profesores');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

DROP TABLE IF EXISTS `estado`;
CREATE TABLE IF NOT EXISTS `estado` (
  `id_estado` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_estado` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion_estado` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`id_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `estado`
--

INSERT INTO `estado` (`id_estado`, `nombre_estado`, `descripcion_estado`) VALUES
(1, 'Descatalogado', 'No se permite la modificación y/o eliminación de este estado puesto que es usado por el sistema.'),
(2, 'En stock', 'No se permite la modificación y/o eliminación de este estado puesto que es usado por el sistema.'),
(3, 'Funcionando', NULL),
(4, 'Para piezas', NULL),
(5, 'En reparación', ''),
(6, 'Robado', 'Equipo robado de nuestras instalaciones');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_dispositivo`
--

DROP TABLE IF EXISTS `estado_dispositivo`;
CREATE TABLE IF NOT EXISTS `estado_dispositivo` (
  `id_est_disp` int(11) NOT NULL AUTO_INCREMENT,
  `id_disp` int(11) NOT NULL,
  `id_estado` int(11) NOT NULL,
  `fecha_inicio_est_disp` date DEFAULT NULL,
  `fecha_fin_est_disp` date DEFAULT NULL,
  PRIMARY KEY (`id_est_disp`),
  KEY `estado_dispositivo_ibfk_1` (`id_disp`),
  KEY `id_estado` (`id_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `estado_dispositivo`
--

INSERT INTO `estado_dispositivo` (`id_est_disp`, `id_disp`, `id_estado`, `fecha_inicio_est_disp`, `fecha_fin_est_disp`) VALUES
(1, 1, 3, '2009-06-01', NULL),
(2, 2, 3, '2009-06-01', '2016-05-27'),
(3, 2, 4, '2016-05-27', NULL),
(4, 3, 3, '2009-09-01', '2016-10-24'),
(5, 3, 4, '2016-10-24', NULL),
(6, 4, 3, '2009-09-01', '2016-09-16'),
(7, 4, 4, '2016-09-16', NULL),
(8, 5, 2, '2005-09-01', NULL),
(9, 6, 3, '2005-09-01', '2016-05-16'),
(10, 6, 4, '2016-05-16', NULL),
(11, 7, 3, '2010-03-01', NULL),
(12, 8, 4, '2010-03-01', NULL),
(13, 9, 3, '2010-03-01', '2016-09-16'),
(14, 9, 4, '2016-09-16', NULL),
(15, 10, 4, '2010-03-01', NULL),
(16, 11, 4, '2010-03-01', NULL),
(17, 12, 4, '2010-03-01', NULL),
(18, 13, 4, '2010-03-01', NULL),
(19, 14, 4, '2010-03-01', NULL),
(20, 15, 3, '2010-03-01', NULL),
(21, 16, 4, '2008-09-01', NULL),
(22, 17, 4, '2008-09-01', NULL),
(23, 18, 4, '2008-09-01', NULL),
(24, 19, 4, '2008-09-01', NULL),
(25, 20, 4, '2008-09-01', NULL),
(26, 21, 4, '2008-09-01', NULL),
(27, 22, 4, '2008-09-01', NULL),
(28, 23, 4, '2008-09-01', NULL),
(29, 24, 4, '2008-09-01', NULL),
(30, 25, 3, '2008-09-01', NULL),
(31, 26, 4, '2008-09-01', NULL),
(32, 27, 4, '2008-09-01', NULL),
(33, 28, 4, '2008-09-01', NULL),
(34, 29, 4, '2008-09-01', NULL),
(35, 30, 4, '2008-09-01', NULL),
(36, 31, 4, '2008-09-01', NULL),
(37, 32, 4, '2008-09-01', NULL),
(38, 33, 4, '2008-09-01', NULL),
(39, 34, 4, '2008-09-01', NULL),
(40, 35, 4, '2008-09-01', NULL),
(41, 36, 4, '2008-09-01', NULL),
(42, 37, 4, '2007-09-01', NULL),
(43, 38, 4, '2007-09-01', NULL),
(44, 39, 4, '2008-09-01', NULL),
(45, 40, 4, '2008-09-01', NULL),
(46, 41, 4, '2007-09-01', NULL),
(47, 42, 4, '2007-09-01', NULL),
(48, 43, 4, '2007-09-01', NULL),
(49, 44, 4, '2007-09-01', NULL),
(50, 45, 4, '2007-09-01', NULL),
(51, 46, 4, '2007-09-01', NULL),
(52, 47, 4, '2007-09-01', NULL),
(53, 48, 4, '2008-09-01', NULL),
(54, 49, 4, '2007-09-01', NULL),
(55, 50, 4, '2007-09-01', NULL),
(56, 51, 4, '2007-09-01', NULL),
(57, 52, 4, '2008-09-01', NULL),
(58, 53, 3, '2013-10-05', NULL),
(59, 54, 3, '2013-10-05', NULL),
(60, 55, 3, '2013-10-05', NULL),
(61, 56, 2, '2014-01-08', NULL),
(62, 57, 2, '2014-01-08', NULL),
(63, 58, 2, '2014-01-08', NULL),
(64, 59, 2, '2014-01-08', NULL),
(65, 60, 2, '2014-01-08', NULL),
(66, 61, 3, '2014-01-15', NULL),
(67, 62, 3, '2014-01-15', NULL),
(68, 63, 3, '2014-01-15', NULL),
(69, 64, 3, '2010-09-01', NULL),
(70, 65, 3, '2016-09-09', NULL),
(71, 66, 3, '2016-09-09', NULL),
(72, 67, 3, '2016-09-09', NULL),
(73, 68, 2, '2016-11-17', NULL),
(74, 69, 2, '2016-11-17', NULL),
(75, 70, 2, '2016-09-30', NULL),
(76, 71, 2, '2016-09-30', NULL),
(77, 72, 3, '2015-09-23', NULL),
(78, 73, 3, '2016-09-01', NULL),
(79, 74, 3, '1900-01-01', NULL),
(80, 75, 3, '2017-09-11', NULL),
(81, 76, 3, '2017-09-11', NULL),
(82, 77, 3, '2017-09-11', NULL),
(83, 78, 3, '2017-09-11', NULL),
(84, 79, 3, '2017-06-15', NULL),
(85, 80, 3, '2017-11-02', NULL),
(86, 81, 3, '2017-10-16', NULL),
(87, 82, 3, '2017-10-16', NULL),
(88, 83, 3, '2018-01-11', NULL),
(89, 84, 3, '2018-03-05', NULL),
(90, 85, 3, '2018-03-05', NULL),
(91, 86, 3, '2018-03-05', NULL),
(92, 87, 3, '2018-03-05', NULL),
(93, 88, 3, '2018-03-05', NULL),
(94, 89, 3, '2017-09-01', NULL),
(95, 90, 3, '2017-09-01', NULL),
(96, 91, 3, '2017-09-01', NULL),
(97, 92, 3, '2018-04-10', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `familia_producto`
--

DROP TABLE IF EXISTS `familia_producto`;
CREATE TABLE IF NOT EXISTS `familia_producto` (
  `id_familia_prod` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_familia_prod` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion_familia_prod` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`id_familia_prod`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `familia_producto`
--

INSERT INTO `familia_producto` (`id_familia_prod`, `nombre_familia_prod`, `descripcion_familia_prod`) VALUES
(1, 'Access Point', '\n'),
(2, 'Altavoces', '\n'),
(3, 'Antena Exterior', '\n'),
(4, 'Antena Interior', '\n'),
(5, 'AP Edificio', '\n'),
(6, 'Armario-Portátiles', '\n'),
(7, 'Desconocido', '\n'),
(8, 'Duplicador Teclado', '\n'),
(9, 'Ebook', '\n'),
(10, 'Escaner', '\n'),
(11, 'Grabador DVD', '\n'),
(12, 'HD Backup', '\n'),
(13, 'Hub', '\n'),
(14, 'Impresora', '\n'),
(15, 'LWC', 'Lan Wireless Controller'),
(16, 'Modem', '\n'),
(17, 'Monitor TFT', '\n'),
(18, 'Monitor CRT', '\n'),
(19, 'Notebook', '\n'),
(20, 'Pantalla-Proyector', '\n'),
(21, 'Pizarra Digital', '\n'),
(22, 'Portatil', '\n'),
(23, 'Privado', '\n'),
(24, 'Proyector', '\n'),
(25, 'Router', '\n'),
(26, 'Router Wifi', '\n'),
(27, 'Servidor', '\n'),
(28, 'Sobremesa', '\n'),
(29, 'Switch', '\n'),
(30, 'Switch Wifi', '\n'),
(31, 'Tablet', '\n'),
(32, 'Teléfono', '\n'),
(33, 'Televisión', '\n'),
(34, 'USB Wifi', '\n');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `incidencia`
--

DROP TABLE IF EXISTS `incidencia`;
CREATE TABLE IF NOT EXISTS `incidencia` (
  `numero_inc` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_apertura_inc` date DEFAULT NULL,
  `fecha_cierre_inc` date DEFAULT NULL,
  `solucion_inc` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `descripcion_inc` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `id_disp` int(11) NOT NULL,
  PRIMARY KEY (`numero_inc`),
  KEY `id_disp` (`id_disp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marca`
--

DROP TABLE IF EXISTS `marca`;
CREATE TABLE IF NOT EXISTS `marca` (
  `id_marca` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_marca` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion_marca` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`id_marca`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `marca`
--

INSERT INTO `marca` (`id_marca`, `nombre_marca`, `descripcion_marca`) VALUES
(1, 'Desconocido', ''),
(2, 'Dell', ''),
(3, 'HP', ''),
(4, 'Dlink', ''),
(5, 'Daewoo', ''),
(6, 'IBM', ''),
(7, 'Benq', ''),
(8, 'BQ', ''),
(9, 'Draytek', ''),
(10, 'Zyxel', ''),
(11, 'Cisco', ''),
(12, 'Cisco Meraki', ''),
(13, 'Western Digital', ''),
(14, 'Seagate', ''),
(15, 'Ubiquiti', ''),
(16, 'Level One', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `origen_equipo`
--

DROP TABLE IF EXISTS `origen_equipo`;
CREATE TABLE IF NOT EXISTS `origen_equipo` (
  `id_ori_eq` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_ori_eq` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion_ori_eq` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`id_ori_eq`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `origen_equipo`
--

INSERT INTO `origen_equipo` (`id_ori_eq`, `nombre_ori_eq`, `descripcion_ori_eq`) VALUES
(1, 'Nuevo', ''),
(2, 'Usado', ''),
(3, 'Donación', 'Provienen de donaciones de padres de alumnos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

DROP TABLE IF EXISTS `pedido`;
CREATE TABLE IF NOT EXISTS `pedido` (
  `id_ped` int(11) NOT NULL AUTO_INCREMENT,
  `codigo_ped` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `fecha_pet_ped` date DEFAULT NULL,
  `fecha_recepcion_ped` date DEFAULT NULL,
  `estado` tinyint(1) NOT NULL,
  `id_prov` int(11) NOT NULL,
  PRIMARY KEY (`id_ped`),
  KEY `id_prov` (`id_prov`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id_ped`, `codigo_ped`, `fecha_pet_ped`, `fecha_recepcion_ped`, `estado`, `id_prov`) VALUES
(1, 'Desconocido', '1900-01-01', '1900-01-01', 1, 1),
(2, 'Desconocido', '1900-01-01', '1900-01-01', 1, 17),
(3, 'Desconocido', '1900-01-01', '1900-01-01', 1, 4),
(4, 'Desconocido', '1900-01-01', '1900-01-01', 1, 16),
(5, 'Desconocido', '1900-01-01', '1900-01-01', 1, 32),
(6, 'Desconocido', '1900-01-01', '1900-01-01', 1, 27),
(7, 'Desconocido', '1900-01-01', '1900-01-01', 1, 13),
(8, 'Desconocido', '1900-01-01', '1900-01-01', 1, 10),
(9, 'Desconocido', '1900-01-01', '1900-01-01', 1, 30),
(10, 'Desconocido', '1900-01-01', '1900-01-01', 1, 39),
(11, 'Desconocido', '1900-01-01', '1900-01-01', 1, 8),
(12, 'Desconocido', '1900-01-01', '1900-01-01', 1, 26);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

DROP TABLE IF EXISTS `producto`;
CREATE TABLE IF NOT EXISTS `producto` (
  `id_prod` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_prod` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion_prod` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `id_marca` int(11) NOT NULL,
  `id_familia_prod` int(11) NOT NULL,
  `foto_prod` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`id_prod`),
  KEY `id_marca` (`id_marca`),
  KEY `id_familia_prod` (`id_familia_prod`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_prod`, `nombre_prod`, `descripcion_prod`, `id_marca`, `id_familia_prod`, `foto_prod`) VALUES
(1, 'PORTATIL DELL VOSTRO 1520', 'Intel Celeron 900 2.2 Ghz 2Gb RAM 160 Gb HD', 2, 22, 'img/PORTATIL DELL VOSTRO 1520.jpg'),
(2, 'DELL ULTRA SMALL GX620', '2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb', 2, 28, 'img/DELL ULTRA SMALL GX620.jpg'),
(3, 'HP DESKJET 6122', '', 3, 14, 'img/HP DESKJET 6122.jpg'),
(4, 'SWITCH DLINK 8 PUERTOS', '', 4, 29, 'img/SWITCH DLINK 8 PUERTOS.jpg'),
(5, 'Daewoo TFT 15', '', 5, 17, ''),
(6, 'LEVEL ONE OFFICE SWITCH FSW-1621', 'Switch de 16 puertos', 16, 29, 'img/LEVEL ONE OFFICE SWITCH FSW-1621.jpg'),
(7, 'IBM THINKCENTRE', '2.8 Ghz 1Gb RAM 80 Gb HD NIC 10/100Mb - (logo arriba)', 6, 28, 'img/IBM THINKCENTRE.jpg'),
(8, 'Proyector BENQ MX661 DLP', '', 7, 24, 'img/Proyector BENQ MX661 DLP.jpg'),
(9, 'Pantalla Desplegable', '', 1, 20, ''),
(10, 'Altavoces', '', 1, 2, ''),
(11, 'Bq Edison 2', 'Quad-Core Android 4.2.2', 8, 31, 'img/Bq Edison 2.jpg'),
(12, 'ROUTER DRAYTEK Vigor 3200', 'Router DRAYTEK VIGOR 3200 Balanceo de Carga 4 líneas VDSL', 9, 25, 'img/ROUTER DRAYTEK Vigor 3200.jpg'),
(13, 'Router Zyxel P-870HW ', 'Router ZYXEL VDSL P-870H-51QV2', 10, 25, 'img/Router Zyxel P-870HW.jpg'),
(14, 'Cisco AIR-ANT1260 ', 'ANTENA Exterior w/RP-TNC Connector 2.4 Ghz, 12 dBi', 11, 3, 'img/Cisco AIR-ANT1260.jpg'),
(15, 'DELL TFT 19', '', 2, 17, ''),
(16, 'HP TFT 19', '', 3, 17, ''),
(17, 'DELL OPTIPLEX 980 Small/DT', 'i5 3.2Ghz 8Gb RAM 250Gb HD DVDRW', 2, 28, 'img/DELL OPTIPLEX 980 SmallDT.jpg'),
(18, 'DELL TFT 22', '', 2, 17, ''),
(19, 'Switch CISCO Small Business SG200-50', 'Switch CISCO Small Business SG200-50 Port 10/100/1000 Smart Switch', 11, 29, 'img/CISCO Small Business SG200-50.jpg'),
(20, 'HP Deskjet 3637', 'Cartuchos 302', 3, 14, 'img/HP Deskjet 3637.jpg'),
(21, 'AP Meraki MR33 Cloud Managed AP', '', 12, 1, 'img/AP Meraki MR33 Cloud Managed AP.jpg'),
(22, 'ARMARIO PARA PORTÁTILES', '', 1, 6, ''),
(23, 'HD EXTERNO WD MY PASSPORT 1TB USB 3.0', '', 13, 12, 'img/WD MY PASSPORT 1TB USB 3.0.jpg'),
(24, 'HD EXTERNO SEAGATE EXPANSION 3TB USB 3.0', '', 14, 12, 'img/SEAGATE EXPANSION 3TB USB 3.0.jpg'),
(25, 'HP 8200SFF', 'i5 2400S  8Gb RAM 250Gb HD DVDRW', 3, 28, 'img/HP 8200SFF.jpg'),
(26, 'TFT 17 Generico', '', 1, 17, ''),
(27, 'Switch UBIQUITI 24', '', 15, 29, 'img/UBIQUITI 24.jpg'),
(28, 'Switch UBIQUITI 48 with SFP+', '', 15, 29, 'img/UBIQUITI 48 with SFP+.jpg'),
(29, 'TFT 19 Generico', '', 1, 17, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto_en_pedido`
--

DROP TABLE IF EXISTS `producto_en_pedido`;
CREATE TABLE IF NOT EXISTS `producto_en_pedido` (
  `id_ped` int(11) NOT NULL,
  `id_prod` int(11) NOT NULL,
  `nunidades_prod_ped` int(11) NOT NULL,
  `precio_por_unidad` int(11) NOT NULL,
  `id_ori_eq` int(11) NOT NULL,
  PRIMARY KEY (`id_ped`,`id_prod`,`id_ori_eq`),
  KEY `id_prod` (`id_prod`),
  KEY `id_ori_eq` (`id_ori_eq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE IF NOT EXISTS `proveedor` (
  `id_prov` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_prov` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `persona_contact_prov` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `telefono_prov` varchar(20) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `direccion_prov` varchar(100) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `email_prov` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `descripcion_prov` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`id_prov`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`id_prov`, `nombre_prov`, `persona_contact_prov`, `telefono_prov`, `direccion_prov`, `email_prov`, `descripcion_prov`) VALUES
(1, 'Desconocido', NULL, NULL, NULL, NULL, NULL),
(2, 'Acade', NULL, NULL, NULL, NULL, NULL),
(3, 'Acer', NULL, NULL, NULL, NULL, NULL),
(4, 'Advanced Computer Trading', NULL, NULL, NULL, NULL, NULL),
(5, 'Aldi', NULL, NULL, NULL, NULL, NULL),
(6, 'Amazon', NULL, NULL, NULL, NULL, NULL),
(7, 'Apple', NULL, NULL, NULL, NULL, NULL),
(8, 'Arca It', NULL, NULL, NULL, NULL, NULL),
(9, 'Blackberry', NULL, NULL, NULL, NULL, NULL),
(10, 'Bq', NULL, NULL, NULL, NULL, NULL),
(11, 'Cam', NULL, NULL, NULL, NULL, NULL),
(12, 'Carrefour', NULL, NULL, NULL, NULL, NULL),
(13, 'César-Electricista', NULL, NULL, NULL, NULL, NULL),
(14, 'Cibertech', NULL, NULL, NULL, NULL, NULL),
(15, 'Cisco', NULL, NULL, NULL, NULL, NULL),
(16, 'Cospa', NULL, NULL, NULL, NULL, NULL),
(17, 'Dell', NULL, NULL, NULL, NULL, NULL),
(18, 'Donación Empleado', NULL, NULL, NULL, NULL, NULL),
(19, 'nombre_prov', NULL, NULL, NULL, NULL, NULL),
(20, 'Editorial Libros', NULL, NULL, NULL, NULL, NULL),
(21, 'Excelia', NULL, NULL, NULL, NULL, 'timo-curso erp'),
(22, 'Hp', NULL, NULL, NULL, NULL, NULL),
(23, 'Htc', NULL, NULL, NULL, NULL, NULL),
(24, 'Ite', NULL, NULL, NULL, NULL, NULL),
(25, 'Lenovo', NULL, NULL, NULL, NULL, NULL),
(26, 'Media Markt', NULL, NULL, NULL, NULL, NULL),
(27, 'Micro Ocasión', NULL, NULL, NULL, NULL, NULL),
(28, 'Motorola', NULL, NULL, NULL, NULL, NULL),
(29, 'Nokia', NULL, NULL, NULL, NULL, NULL),
(30, 'Nova', NULL, NULL, NULL, NULL, NULL),
(31, 'Packard Bell (Atheros)', NULL, NULL, NULL, NULL, NULL),
(32, 'Posan', NULL, NULL, NULL, NULL, NULL),
(33, 'Privado-Profesor', NULL, NULL, NULL, NULL, NULL),
(34, 'Ricoh', NULL, NULL, NULL, NULL, NULL),
(35, 'Samsung (Atheros)', NULL, NULL, NULL, NULL, NULL),
(36, 'San Martín', NULL, NULL, NULL, NULL, NULL),
(37, 'Saturn', NULL, NULL, NULL, NULL, NULL),
(38, 'Snappet', NULL, NULL, NULL, NULL, NULL),
(39, 'Telecomun', NULL, NULL, NULL, NULL, NULL),
(40, 'Telefónica', NULL, NULL, NULL, NULL, NULL),
(41, 'Toshiba', NULL, NULL, NULL, NULL, NULL),
(42, 'Worten', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sala`
--

DROP TABLE IF EXISTS `sala`;
CREATE TABLE IF NOT EXISTS `sala` (
  `id_sala` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_sala` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `usuario_sala` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `curso_asignado_sala` varchar(20) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `descripcion_sala` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `id_edif` int(11) NOT NULL,
  `id_tipo_sala` int(11) NOT NULL,
  PRIMARY KEY (`id_sala`),
  KEY `id_edif` (`id_edif`),
  KEY `id_tipo_sala` (`id_tipo_sala`)
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `sala`
--

INSERT INTO `sala` (`id_sala`, `nombre_sala`, `usuario_sala`, `curso_asignado_sala`, `descripcion_sala`, `id_edif`, `id_tipo_sala`) VALUES
(1, 'Basura', NULL, NULL, 'No se permite la modificación y/o eliminación de esta sala puesto que es usado por el sistema.', 4, 1),
(2, 'Almacén', NULL, NULL, 'No se permite la modificación y/o eliminación de esta sala puesto que es usado por el sistema.', 3, 2),
(3, 'Desconocido', '', '', '', 4, 6),
(4, '101-A', '', '', '', 1, 3),
(5, '102-A', 'ELENA PASTOR', '', 'ELENA PASTOR', 1, 3),
(6, '103-A', '', '', '', 1, 3),
(7, '104-A', '', '', '', 1, 3),
(8, '105-A', '', '', '', 1, 3),
(9, '106-A', '', '', '', 1, 3),
(10, '107-A', '', '', '', 1, 3),
(11, '110-A', 'SRTA. SUSANA', '', 'SUSANA', 1, 3),
(12, '111-A', '', '', '', 1, 3),
(13, '112-A', '', '', '', 1, 3),
(14, '113-A', '', '', '', 1, 3),
(15, '114-A', '', '', '', 1, 3),
(16, '115-A', '', '', '', 1, 3),
(17, '116-A', '', '', '', 1, 3),
(18, '117-A', '', '', '', 1, 3),
(19, '118-A', '', '', '', 1, 3),
(20, '120-A', '', '', '', 1, 3),
(21, '121-A', '', '', '', 1, 3),
(22, '122-A', 'JESÚS', '', 'JESÚS', 1, 3),
(23, '123-A', '', '', '', 1, 3),
(24, '124-A', 'Begoña', '', '', 1, 3),
(25, '125-A', '', '', '', 1, 3),
(26, '126-A', '', '', '', 1, 3),
(27, '127-A', '', '', '', 1, 3),
(28, '128-A', '¿ANTIGUA 28-A?', '', 'AULA MULTITAREA', 1, 3),
(29, '130-A', '', '', '', 1, 3),
(30, '131-A', '', '', '', 1, 3),
(31, '132-A', '', '', '', 1, 3),
(32, '133-A', '', '', '', 1, 3),
(33, '134-A', '', '', '', 1, 3),
(34, '135-A', '', '', '', 1, 3),
(35, '136-A', '', '', '', 1, 3),
(36, '137-A', 'ALUMNOS INFANTIL-PRIMARIA', '', 'ROSA', 1, 3),
(37, '138-A-BIBLIOTECA', '', '', 'biblioteca --> REVISAR EQUIPAMIENTO', 1, 6),
(38, '139-A', '', '', 'antigua 31b', 1, 3),
(39, '140-A', '', '', '', 1, 3),
(40, '141-A', 'ALUMNOS INFANTIL-PRIMARIA', '', '', 1, 3),
(41, '142-A', '', '', '', 1, 3),
(42, '143-A', '', '', '', 1, 3),
(43, '144-A', '', '', '', 1, 3),
(44, '145-A', '', '', '', 1, 3),
(45, '146-A', '', '', '', 1, 3),
(46, '147-A', '', '', '', 1, 3),
(47, '148-A', '', '', 'aula musica d. alfonso', 1, 3),
(48, '149-A', '', '', '', 1, 3),
(49, '150-A', '', '', 'tambien 4a planta', 1, 3),
(50, '201-B', '', '', '', 2, 3),
(51, '202-B', '', '', 'clase susana', 2, 3),
(52, '211-B', 'SRTA. SUSANA', '', 'BALLENA', 2, 3),
(53, '212-B', 'MARIA JESÚS MARTÍNEZ', '', 'MARIA JESÚS MARTINEZ', 2, 3),
(54, '213-B', 'MARIVÍ', '', 'CEBRA', 2, 3),
(55, '214-B-AULA-MUSICA', '', '', '', 2, 3),
(56, '215-B-AULA-DUAL', '', '', '', 2, 3),
(57, '221-B', '', '', '', 2, 3),
(58, '222-B', '', '', '', 2, 3),
(59, '223-B', '', '', '', 2, 3),
(60, '224-B', '', '', '', 2, 3),
(61, '231-B', '', '', '', 2, 3),
(62, '232-B', 'ALUMNOS INFANTIL-PRIMARIA', '', '', 2, 3),
(63, '233-B', '', '', '', 2, 3),
(64, '234-B', '', '', '', 2, 3),
(65, '235-B', '', '', '', 2, 3),
(66, '241-B', '', '', '', 2, 3),
(67, '242-B', '', '', '', 2, 3),
(68, '243-B', '', '', '', 2, 3),
(69, '244-B', '', '', '', 2, 3),
(70, '311-C ------- SALA DE JUNTAS-C', '', '', '1ª PLANTA', 3, 6),
(71, '321-C', 'ALUMNOS ESO', '', 'JACINTO', 3, 3),
(72, '322-C', '', '', '6º ED. PRIMARIA', 3, 3),
(73, '323-C', '', '', '6º ED. PRIMARIA', 3, 3),
(74, '324-C', '', '', '6º ED. PRIMARIA', 3, 3),
(75, '325-C', 'ALUMNOS ESO', '', 'AULA 2ª PLANTA Srta. Clara', 3, 3),
(76, '331-C', 'ALUMNOS ESO', '', '', 3, 3),
(77, '332-C', 'ALUMNOS ESO', '', '', 3, 3),
(78, '333-C', '', '', '6º ED. PRIMARIA', 3, 3),
(79, '334-C', '', '', '6º ED. PRIMARIA', 3, 3),
(80, '335-C', '', '', 'AULA BIBLIOTECA 3ª PLANTA', 3, 3),
(81, '341-C', '', '', '', 3, 3),
(82, '342-C', '', '', '', 3, 3),
(83, '343-C', '', '', '', 3, 3),
(84, '344-C', '', '', '', 3, 3),
(85, '345-C', 'ALUMNOS ESO', '', '', 3, 3),
(86, '346-C', 'ALUMNOS CICLOS', '', '', 3, 3),
(87, '351-C', 'ALUMNOS CICLOS', '', '', 3, 3),
(88, '352-C', 'ALUMNOS CICLOS', '', '', 3, 3),
(89, 'ADMINISTRACIÓN - Mada & Isabel-C', 'MADALENA', '', '', 3, 4),
(90, 'ARMARIO 3ª PLANTA-C', 'ALUMNOS ESO', '', 'AULA PORTÁTILES ESO', 3, 6),
(91, 'AULA MAGNA-C', 'TODOS', '', '', 3, 6),
(92, 'BIBLIOTECA-A', '', '', 'BIBLIOTECA PRIMARIA PLANTA ????', 1, 6),
(93, 'CONTABILIDAD - Mari & Emilio-C', 'EMILIO', '', '', 3, 4),
(94, 'CUARTO LIMPIEZA 4ª planta - A', '', '', 'Cuarto Limpieza', 1, 6),
(95, 'DANIEL-C', '', '', '', 3, 6),
(96, 'DEPORTES-B', '', '', 'DEPORTES', 2, 6),
(97, 'DESPACHO ARANCHA -A', 'ARANCHA', '', 'ARANCHA', 1, 4),
(98, 'DESPACHO BELÉN-A', 'BELÉN', '', ' Antigüo dpcho. Escandón', 1, 4),
(99, 'DESPACHO CICLOS-C', 'PROFESORES CICLOS', '', '', 3, 4),
(100, 'DESPACHO JACINTO-A', 'JACINTO', '', '', 1, 4),
(101, 'DESPACHO JEFE EST. BACHILLERATO&ESO - Antonio-C', 'ANTONIO GARCIA', '', '', 3, 4),
(102, 'Despacho Jefe Est. Inf.Prim - Enrique Escandón -A', 'ENRIQUE ESCANDÓN', '', 'Antigüa aula 04-A', 1, 4),
(103, 'DESPACHO JEFE ORIENTACIÓN - Jaime-C', 'JAIME GUIJARRO', '', '', 3, 4),
(104, 'DESPACHO ORIENTADOR - Nuria&Lali-C', 'NURIA', '', '', 3, 4),
(105, 'DIRECCIÓN - Esther-C', 'ESTHER', '', '', 3, 4),
(106, 'DIRECCIÓN - Juanjo-C', 'JUAN JOSÉ GARCÍA', '', '', 3, 4),
(107, 'ENFERMERÍA-B', '', '', 'EVA', 2, 6),
(108, 'ENTREPLANTA infantil 1ª planta-A', '', '', '', 1, 5),
(109, 'LABORATORIO-C', '', '', 'laboratorio 5ª planta', 3, 6),
(110, 'MARÍA PASTOR-A', '', '', '', 1, 4),
(111, 'MARIA PASTOR-B', '', '', '', 2, 4),
(112, 'PASILLO 0-A', '', '', '', 1, 5),
(113, 'PASILLO 0-B', '', '', '', 2, 5),
(114, 'PASILLO 0-C', '', '', '', 3, 5),
(115, 'PASILLO -1-A', '', '', '', 1, 5),
(116, 'PASILLO 1-A', '', '', '', 1, 5),
(117, 'PASILLO -1-B', '', '', '', 2, 5),
(118, 'PASILLO 1-B', '', '', '', 2, 5),
(119, 'PASILLO 1-C', '', '', '', 3, 5),
(120, 'PASILLO 2-A', '', '', '', 1, 5),
(121, 'PASILLO 2-B', '', '', '', 2, 5),
(122, 'PASILLO 2-C', '', '', '', 3, 5),
(123, 'PASILLO 3-A', '', '', '', 1, 5),
(124, 'PASILLO 3-B', '', '', '', 2, 5),
(125, 'PASILLO 3-C', '', '', '', 3, 5),
(126, 'PASILLO 4-A', '', '', '', 1, 5),
(127, 'PASILLO 4-B', '', '', '', 2, 5),
(128, 'PASILLO 4-C', '', '', '', 3, 5),
(129, 'PASILLO BACHI 1ª PLANTA-B', 'ALUMNOS BACHILLERATO', '', 'ARMARIO DE PORTÁTILES', 2, 5),
(130, 'PASILLO INFANTIL 1ª PLANTA-A', 'ALUMNOS INFANTIL-PRIMARIA', '', 'ARMARIO NOTEBOOK', 1, 5),
(131, 'PRIVADO', 'PROFESORES', '', 'EQUIPO PRIVADO', 5, 6),
(132, 'RECIBIDOR-1-C', '', '', '', 3, 5),
(133, 'RECIBIDOR-2-C', '', '', '', 3, 5),
(134, 'RECIBIDOR-3-C', '', '', '', 3, 5),
(135, 'RECIBIDOR-4-C', '', '', '', 3, 5),
(136, 'SABINA', '', '', 'De clase en clase', 1, 4),
(137, 'SALA-PROFESORES-PRIMARIA', 'ALUMNOS INFANTIL-PRIMARIA', '', 'SÓTANO INFANTIL-PRIMARIA', 1, 6),
(138, 'SALÓN DE ACTOS-B', '', '', '', 2, 6),
(139, 'Sarah Dodman', 'Sara Doldman', '', 'De clase en clase', 1, 6),
(140, 'SECRETARIA EDIFICIO C-C', '', '', 'ISABEL,MARISA y TOÑI BAUDIL', 3, 6),
(141, 'SECRETARÍA INFANTIL-PRIMARIA-A', '', '', 'TOÑI y MARI CARMEN', 1, 6),
(142, 'SIN ASIGNAR', 'TERE&JOAKIN', '', 'VERIFICANDO EN CICLOS', 3, 6),
(143, 'SUSANA ROMERO-B', '', '', '', 2, 6),
(144, 'TIC-1A-viejaSalaProfes', '', '', '', 1, 6),
(145, 'TIC-2B-VIEJA-SALA-PROFESBACHI', '\"AULA \"\"PECERA\"\"\"', '', '', 2, 6),
(146, 'TIC-3C-BIBLIOTECA', '', '', 'AULA ENTREPLANTA BIBLIOTECA', 3, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_sala`
--

DROP TABLE IF EXISTS `tipo_sala`;
CREATE TABLE IF NOT EXISTS `tipo_sala` (
  `id_tipo_sala` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_tipo_sala` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion_tipo_sala` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`id_tipo_sala`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `tipo_sala`
--

INSERT INTO `tipo_sala` (`id_tipo_sala`, `nombre_tipo_sala`, `descripcion_tipo_sala`) VALUES
(1, 'Basura', 'No se permite la modificación y/o eliminación de este tipo de sala puesto que es usado por el sistema.'),
(2, 'Almacén', 'No se permite la modificación y/o eliminación de este tipo de sala puesto que es usado por el sistema.'),
(3, 'Aula', ''),
(4, 'Despacho', NULL),
(5, 'Pasillos', NULL),
(6, 'Otros', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ubicacion_dispositivo`
--

DROP TABLE IF EXISTS `ubicacion_dispositivo`;
CREATE TABLE IF NOT EXISTS `ubicacion_dispositivo` (
  `id_disp_sala` int(11) NOT NULL AUTO_INCREMENT,
  `id_disp` int(11) NOT NULL,
  `id_sala` int(11) NOT NULL,
  `fecha_entrada_disp_sala` date NOT NULL,
  `fecha_salida_disp_sala` date DEFAULT NULL,
  PRIMARY KEY (`id_disp_sala`),
  KEY `id_disp` (`id_disp`),
  KEY `id_sala` (`id_sala`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `ubicacion_dispositivo`
--

INSERT INTO `ubicacion_dispositivo` (`id_disp_sala`, `id_disp`, `id_sala`, `fecha_entrada_disp_sala`, `fecha_salida_disp_sala`) VALUES
(1, 1, 146, '2009-06-01', NULL),
(2, 2, 88, '2009-06-01', '2016-05-27'),
(3, 2, 1, '2016-05-27', NULL),
(4, 3, 88, '2009-09-01', '2016-10-24'),
(5, 3, 1, '2016-10-24', NULL),
(6, 4, 144, '2009-09-01', '2016-09-16'),
(7, 4, 1, '2016-09-16', NULL),
(8, 5, 88, '2005-09-01', NULL),
(9, 6, 89, '2005-09-01', '2016-05-16'),
(10, 6, 1, '2016-05-16', NULL),
(11, 7, 144, '2010-03-01', NULL),
(12, 8, 86, '2010-03-01', NULL),
(13, 9, 144, '2010-03-01', '2016-09-16'),
(14, 9, 1, '2016-09-16', NULL),
(15, 10, 86, '2010-03-01', NULL),
(16, 11, 1, '2010-03-01', NULL),
(17, 12, 1, '2010-03-01', NULL),
(18, 13, 1, '2010-03-01', NULL),
(19, 14, 1, '2010-03-01', NULL),
(20, 15, 106, '2010-03-01', NULL),
(21, 16, 86, '2008-09-01', NULL),
(22, 17, 86, '2008-09-01', NULL),
(23, 18, 86, '2008-09-01', NULL),
(24, 19, 86, '2008-09-01', NULL),
(25, 20, 86, '2008-09-01', NULL),
(26, 21, 86, '2008-09-01', NULL),
(27, 22, 86, '2008-09-01', NULL),
(28, 23, 86, '2008-09-01', NULL),
(29, 24, 86, '2008-09-01', NULL),
(30, 25, 137, '2008-09-01', NULL),
(31, 26, 86, '2008-09-01', NULL),
(32, 27, 86, '2008-09-01', NULL),
(33, 28, 88, '2008-09-01', NULL),
(34, 29, 86, '2008-09-01', NULL),
(35, 30, 86, '2008-09-01', NULL),
(36, 31, 88, '2008-09-01', NULL),
(37, 32, 86, '2008-09-01', NULL),
(38, 33, 88, '2008-09-01', NULL),
(39, 34, 1, '2008-09-01', NULL),
(40, 35, 86, '2008-09-01', NULL),
(41, 36, 88, '2008-09-01', NULL),
(42, 37, 86, '2007-09-01', NULL),
(43, 38, 1, '2007-09-01', NULL),
(44, 39, 86, '2008-09-01', NULL),
(45, 40, 86, '2008-09-01', NULL),
(46, 41, 86, '2007-09-01', NULL),
(47, 42, 1, '2007-09-01', NULL),
(48, 43, 86, '2007-09-01', NULL),
(49, 44, 86, '2007-09-01', NULL),
(50, 45, 86, '2007-09-01', NULL),
(51, 46, 1, '2007-09-01', NULL),
(52, 47, 86, '2007-09-01', NULL),
(53, 48, 86, '2008-09-01', NULL),
(54, 49, 86, '2007-09-01', NULL),
(55, 50, 86, '2007-09-01', NULL),
(56, 51, 86, '2007-09-01', NULL),
(57, 52, 1, '2008-09-01', NULL),
(58, 53, 84, '2013-10-05', NULL),
(59, 54, 76, '2013-10-05', NULL),
(60, 55, 84, '2013-10-05', NULL),
(61, 56, 143, '2014-01-08', NULL),
(62, 57, 143, '2014-01-08', NULL),
(63, 58, 143, '2014-01-08', NULL),
(64, 59, 143, '2014-01-08', NULL),
(65, 60, 143, '2014-01-08', NULL),
(66, 61, 144, '2014-01-15', NULL),
(67, 62, 144, '2014-01-15', NULL),
(68, 63, 144, '2014-01-15', NULL),
(69, 64, 129, '2010-09-01', NULL),
(70, 65, 86, '2016-09-09', NULL),
(71, 66, 86, '2016-09-09', NULL),
(72, 67, 86, '2016-09-09', NULL),
(73, 68, 56, '2016-11-17', NULL),
(74, 69, 56, '2016-11-17', NULL),
(75, 70, 88, '2016-09-30', NULL),
(76, 71, 88, '2016-09-30', NULL),
(77, 72, 28, '2015-09-23', NULL),
(78, 73, 141, '2016-09-01', NULL),
(79, 74, 107, '1900-01-01', NULL),
(80, 75, 88, '2017-09-11', NULL),
(81, 76, 88, '2017-09-11', NULL),
(82, 77, 88, '2017-09-11', NULL),
(83, 78, 88, '2017-09-11', NULL),
(84, 79, 112, '2017-06-15', NULL),
(85, 80, 126, '2017-11-02', NULL),
(86, 81, 99, '2017-10-16', NULL),
(87, 82, 99, '2017-10-16', NULL),
(88, 83, 144, '2018-01-11', NULL),
(89, 84, 146, '2018-03-05', NULL),
(90, 85, 146, '2018-03-05', NULL),
(91, 86, 146, '2018-03-05', NULL),
(92, 87, 146, '2018-03-05', NULL),
(93, 88, 146, '2018-03-05', NULL),
(94, 89, 3, '2017-09-01', NULL),
(95, 90, 3, '2017-09-01', NULL),
(96, 91, 3, '2017-09-01', NULL),
(97, 92, 146, '2018-04-10', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios_app`
--

DROP TABLE IF EXISTS `usuarios_app`;
CREATE TABLE IF NOT EXISTS `usuarios_app` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `pass_usuario` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `rol_usuario` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `usuarios_app`
--

INSERT INTO `usuarios_app` (`id_usuario`, `nombre_usuario`, `pass_usuario`, `rol_usuario`) VALUES
(1, 'admin', 'joyfe2018', 'administrador'),
(2, 'basic', '12345678', 'basico');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `dispositivo`
--
ALTER TABLE `dispositivo`
  ADD CONSTRAINT `dispositivo_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_prod`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `dispositivo_ibfk_2` FOREIGN KEY (`id_ped`) REFERENCES `pedido` (`id_ped`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `dispositivo_ibfk_3` FOREIGN KEY (`id_ori_eq`) REFERENCES `origen_equipo` (`id_ori_eq`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `estado_dispositivo`
--
ALTER TABLE `estado_dispositivo`
  ADD CONSTRAINT `estado_dispositivo_ibfk_1` FOREIGN KEY (`id_disp`) REFERENCES `dispositivo` (`id_disp`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `estado_dispositivo_ibfk_2` FOREIGN KEY (`id_estado`) REFERENCES `estado` (`id_estado`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `incidencia`
--
ALTER TABLE `incidencia`
  ADD CONSTRAINT `incidencia_ibfk_1` FOREIGN KEY (`id_disp`) REFERENCES `dispositivo` (`id_disp`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`id_prov`) REFERENCES `proveedor` (`id_prov`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`id_marca`) REFERENCES `marca` (`id_marca`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `producto_ibfk_2` FOREIGN KEY (`id_familia_prod`) REFERENCES `familia_producto` (`id_familia_prod`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `producto_en_pedido`
--
ALTER TABLE `producto_en_pedido`
  ADD CONSTRAINT `producto_en_pedido_ibfk_1` FOREIGN KEY (`id_ped`) REFERENCES `pedido` (`id_ped`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `producto_en_pedido_ibfk_2` FOREIGN KEY (`id_prod`) REFERENCES `producto` (`id_prod`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `producto_en_pedido_ibfk_3` FOREIGN KEY (`id_ori_eq`) REFERENCES `origen_equipo` (`id_ori_eq`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `sala`
--
ALTER TABLE `sala`
  ADD CONSTRAINT `sala_ibfk_1` FOREIGN KEY (`id_edif`) REFERENCES `edificio` (`id_edif`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `sala_ibfk_2` FOREIGN KEY (`id_tipo_sala`) REFERENCES `tipo_sala` (`id_tipo_sala`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `ubicacion_dispositivo`
--
ALTER TABLE `ubicacion_dispositivo`
  ADD CONSTRAINT `ubicacion_dispositivo_ibfk_1` FOREIGN KEY (`id_disp`) REFERENCES `dispositivo` (`id_disp`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `ubicacion_dispositivo_ibfk_2` FOREIGN KEY (`id_sala`) REFERENCES `sala` (`id_sala`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
