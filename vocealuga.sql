-- phpMyAdmin SQL Dump
-- version 5.0.0-dev
-- https://www.phpmyadmin.net/
--
-- Host: 192.168.30.23
-- Generation Time: Jun 26, 2019 at 01:31 PM
-- Server version: 8.0.3-rc-log
-- PHP Version: 7.2.18-1+0~20190503103213.21+stretch~1.gbp101320

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vocealuga`
--

-- --------------------------------------------------------

--
-- Table structure for table `carro`
--

CREATE TABLE `carro` (
  `id` int(11) NOT NULL,
  `placa` varchar(8) NOT NULL,
  `dataManutencao` date NOT NULL,
  `dataCompra` date NOT NULL,
  `quilometragem` int(11) NOT NULL,
  `idModelo` int(11) NOT NULL,
  `idFilial` int(11) NOT NULL,
  `idEstado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `carro`
--

INSERT INTO `carro` (`id`, `placa`, `dataManutencao`, `dataCompra`, `quilometragem`, `idModelo`, `idFilial`, `idEstado`) VALUES
(1, 'ABC1234', '2019-04-21', '2019-04-21', 0, 2, 1, 3),
(2, 'ABD1234', '2019-04-15', '2019-05-10', 123, 3, 1, 1),
(3, 'ABE1234', '2019-07-07', '2019-06-06', 200, 5, 1, 3),
(4, 'ABF1234', '2019-06-25', '2019-06-25', 300, 1, 2, 3);

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `CPF` varchar(15) DEFAULT NULL,
  `passaporte` varchar(15) DEFAULT NULL,
  `dataNascimento` date NOT NULL,
  `nacionalidade` varchar(30) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `CNH` bigint(20) NOT NULL,
  `dataCNH` date NOT NULL,
  `ativo` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`id`, `nome`, `CPF`, `passaporte`, `dataNascimento`, `nacionalidade`, `telefone`, `CNH`, `dataCNH`, `ativo`) VALUES
(1, 'Cliente Teste', '12345678910', NULL, '2019-03-29', 'mundo', '987651234', 12345678901, '2019-03-30', 1),
(2, 'Cliente Teste Passaporte', NULL, 'AB123456', '2019-03-29', 'brasileiro', '912345678', 12345678911, '2019-03-29', 1),
(3, 'Cliente Teste Passaporte 2', NULL, 'AC123457', '2019-03-29', 'brasileiro', '987654321', 12345678911, '2019-03-29', 1),
(4, 'Teste de Cadastro do Cliente', '12345678905', NULL, '2019-03-30', 'mundo', '987654322', 12345678903, '2019-03-30', 1),
(6, 'Daniel Jimenez', '08254888183', NULL, '1993-11-29', 'Colombiano', '21988657473', 12312312312, '2019-03-31', 0),
(9, 'danie', NULL, '1asd123a', '1993-11-29', 'colombiano', '21988657473', 12312312312, '2020-12-12', 1),
(11, 'daniel', NULL, 'asdasdas2', '2000-11-11', 'colombia', '12312312312', 12312312312, '2019-04-17', 1),
(12, 'matheustrollinho', NULL, 'asdasdas', '2019-04-04', 'brasil', '123123123', 12312312312, '2019-04-02', 1),
(13, '12435687901', '12435687901', NULL, '2019-04-24', 'Brasileiro', '987654321', 12435687901, '2019-04-24', 1);

-- --------------------------------------------------------

--
-- Table structure for table `estadoCarro`
--

CREATE TABLE `estadoCarro` (
  `id` int(11) NOT NULL,
  `tipo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `estadoCarro`
--

INSERT INTO `estadoCarro` (`id`, `tipo`) VALUES
(1, 'Alugado'),
(2, 'Em manutenção'),
(3, 'Disponível'),
(4, 'Vendido');

-- --------------------------------------------------------

--
-- Table structure for table `filial`
--

CREATE TABLE `filial` (
  `id` int(11) NOT NULL,
  `nomeFilial` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `filial`
--

INSERT INTO `filial` (`id`, `nomeFilial`) VALUES
(1, 'Rio de Janeiro'),
(2, 'São Paulo');

-- --------------------------------------------------------

--
-- Table structure for table `funcionario`
--

CREATE TABLE `funcionario` (
  `id` int(11) NOT NULL,
  `nome` char(100) NOT NULL,
  `usuario` varchar(30) NOT NULL,
  `senha` varchar(30) NOT NULL,
  `idFilial` int(11) NOT NULL,
  `idTipo` int(11) NOT NULL,
  `ativo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `funcionario`
--

INSERT INTO `funcionario` (`id`, `nome`, `usuario`, `senha`, `idFilial`, `idTipo`, `ativo`) VALUES
(1, 'Fulano Adm da Silva', 'Admin', 'Admin', 1, 1, 1),
(2, 'Beltrano Testador da Silva', 'Test', 'Test', 1, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `grupoCarro`
--

CREATE TABLE `grupoCarro` (
  `id` int(11) NOT NULL,
  `grupo` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `grupoCarro`
--

INSERT INTO `grupoCarro` (`id`, `grupo`) VALUES
(1, 'A'),
(2, 'B'),
(3, 'C');

-- --------------------------------------------------------

--
-- Table structure for table `locacoes`
--

CREATE TABLE `locacoes` (
  `id` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_carro` int(11) NOT NULL,
  `dataInicial` date NOT NULL,
  `dataFinal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `manutencao`
--

CREATE TABLE `manutencao` (
  `id` int(11) NOT NULL,
  `id_carro` int(11) NOT NULL,
  `dataInicio` date NOT NULL,
  `dataFim` date NOT NULL,
  `orcamento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `modeloCarro`
--

CREATE TABLE `modeloCarro` (
  `id` int(11) NOT NULL,
  `marca` varchar(20) NOT NULL,
  `modelo` varchar(20) NOT NULL,
  `idGrupo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `modeloCarro`
--

INSERT INTO `modeloCarro` (`id`, `marca`, `modelo`, `idGrupo`) VALUES
(1, 'Hyundai', 'HB20', 2),
(2, 'Volkswagen', 'Gol', 3),
(3, 'Fiat', 'Uno', 2),
(4, 'Volkswagen', 'Fox', 2),
(5, 'Ford', 'Fiesta', 3);

-- --------------------------------------------------------

--
-- Table structure for table `reservas`
--

CREATE TABLE `reservas` (
  `id` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_grupo` int(11) NOT NULL,
  `id_modelo` int(11) NOT NULL,
  `dataLocacao` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tipoFuncionario`
--

CREATE TABLE `tipoFuncionario` (
  `id` int(11) NOT NULL,
  `nome` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tipoFuncionario`
--

INSERT INTO `tipoFuncionario` (`id`, `nome`) VALUES
(1, 'Administrador'),
(2, 'Funcionario');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `carro`
--
ALTER TABLE `carro`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `placa` (`placa`),
  ADD KEY `FK_modelo` (`idModelo`),
  ADD KEY `FK_estado` (`idEstado`),
  ADD KEY `FK_filial` (`idFilial`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `CPF` (`CPF`),
  ADD UNIQUE KEY `passaporte` (`passaporte`);

--
-- Indexes for table `estadoCarro`
--
ALTER TABLE `estadoCarro`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `filial`
--
ALTER TABLE `filial`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_tipoFuncionario` (`idTipo`),
  ADD KEY `FK_filial` (`idFilial`);

--
-- Indexes for table `grupoCarro`
--
ALTER TABLE `grupoCarro`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `locacoes`
--
ALTER TABLE `locacoes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Id_Carro` (`id_cliente`);

--
-- Indexes for table `manutencao`
--
ALTER TABLE `manutencao`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pk_Id_Carro` (`id_carro`);

--
-- Indexes for table `modeloCarro`
--
ALTER TABLE `modeloCarro`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_grupo` (`idGrupo`);

--
-- Indexes for table `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Id_Cliente_Reserva` (`id_cliente`),
  ADD KEY `fk_Id_Grupo` (`id_grupo`),
  ADD KEY `fk_Id_Modelo` (`id_modelo`);

--
-- Indexes for table `tipoFuncionario`
--
ALTER TABLE `tipoFuncionario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `carro`
--
ALTER TABLE `carro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `estadoCarro`
--
ALTER TABLE `estadoCarro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `filial`
--
ALTER TABLE `filial`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `grupoCarro`
--
ALTER TABLE `grupoCarro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `locacoes`
--
ALTER TABLE `locacoes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `manutencao`
--
ALTER TABLE `manutencao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `modeloCarro`
--
ALTER TABLE `modeloCarro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `reservas`
--
ALTER TABLE `reservas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tipoFuncionario`
--
ALTER TABLE `tipoFuncionario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `locacoes`
--
ALTER TABLE `locacoes`
  ADD CONSTRAINT `fk_Id_Carro` FOREIGN KEY (`id_cliente`) REFERENCES `carro` (`id`),
  ADD CONSTRAINT `fk_Id_Cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`);

--
-- Constraints for table `manutencao`
--
ALTER TABLE `manutencao`
  ADD CONSTRAINT `pk_Id_Carro` FOREIGN KEY (`id_carro`) REFERENCES `carro` (`id`);

--
-- Constraints for table `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `fk_Id_Cliente_Reserva` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `fk_Id_Grupo` FOREIGN KEY (`id_grupo`) REFERENCES `grupoCarro` (`id`),
  ADD CONSTRAINT `fk_Id_Modelo` FOREIGN KEY (`id_modelo`) REFERENCES `modeloCarro` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

