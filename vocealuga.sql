-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-04-2019 a las 04:53:22
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `vocealuga`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `Id` int(11) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  `CPF` varchar(15) NOT NULL,
  `Passaporte` varchar(15) NOT NULL,
  `DataNascimento` date NOT NULL,
  `Nacionalidade` varchar(30) NOT NULL,
  `Telefone` varchar(20) NOT NULL,
  `CNH` bigint(20) NOT NULL,
  `DataCNH` date NOT NULL,
  `Ativo` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`Id`, `Nome`, `CPF`, `Passaporte`, `DataNascimento`, `Nacionalidade`, `Telefone`, `CNH`, `DataCNH`, `Ativo`) VALUES
(1, 'Cliente Teste', '12345678910', '', '2019-03-29', 'mundo', '987651234', 12345678901, '2019-03-30', 1),
(2, 'Cliente Teste Passaporte', '', 'AB123456', '2019-03-29', 'brasileiro', '912345678', 12345678911, '2019-03-29', 1),
(3, 'Cliente Teste Passaporte 2', '', 'AC123456', '2019-03-29', 'brasileiro', '987654321', 12345678911, '2019-03-29', 1),
(4, 'Teste de Cadastro do Cliente', '12345678905', '', '2019-03-30', 'mundo', '987654322', 12345678903, '2019-03-30', 1),
(6, 'Daniel Jimenez', '08254888183', '', '1993-11-29', 'Colombiano', '21988657473', 12312312312, '2019-03-31', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `filial`
--

CREATE TABLE `filial` (
  `id` int(11) NOT NULL,
  `nomeFilial` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `filial`
--

INSERT INTO `filial` (`id`, `nomeFilial`) VALUES
(1, 'Rio de Janeiro');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `funcionario`
--

CREATE TABLE `funcionario` (
  `Id` int(11) NOT NULL,
  `User` varchar(30) NOT NULL,
  `Senha` varchar(30) NOT NULL,
  `IdFilial` int(11) NOT NULL,
  `IdTipo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `funcionario`
--

INSERT INTO `funcionario` (`Id`, `User`, `Senha`, `IdFilial`, `IdTipo`) VALUES
(1, 'Admin', 'Admin', 1, 1),
(2, 'Test', 'Test', 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipofuncionario`
--

CREATE TABLE `tipofuncionario` (
  `Id` int(11) NOT NULL,
  `Nombre` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipofuncionario`
--

INSERT INTO `tipofuncionario` (`Id`, `Nombre`) VALUES
(1, 'Administrador'),
(2, 'Funcionario');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`Id`);

--
-- Indices de la tabla `filial`
--
ALTER TABLE `filial`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `FK_TipoFuncionario` (`IdTipo`),
  ADD KEY `FK_Filial` (`IdFilial`);

--
-- Indices de la tabla `tipofuncionario`
--
ALTER TABLE `tipofuncionario`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `filial`
--
ALTER TABLE `filial`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipofuncionario`
--
ALTER TABLE `tipofuncionario`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `FK_Filial` FOREIGN KEY (`IdFilial`) REFERENCES `filial` (`id`),
  ADD CONSTRAINT `FK_TipoFuncionario` FOREIGN KEY (`IdTipo`) REFERENCES `tipofuncionario` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
