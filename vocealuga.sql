-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 10, 2019 at 01:12 AM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 5.6.38

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
(1, 'ABC1234', '2019-04-01', '2019-04-01', 0, 2, 1, 3),
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
-- Table structure for table `estadocarro`
--

CREATE TABLE `estadocarro` (
  `id` int(11) NOT NULL,
  `tipo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `estadocarro`
--

INSERT INTO `estadocarro` (`id`, `tipo`) VALUES
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
-- Table structure for table `grupocarro`
--

CREATE TABLE `grupocarro` (
  `id` int(11) NOT NULL,
  `grupo` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `grupocarro`
--

INSERT INTO `grupocarro` (`id`, `grupo`) VALUES
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
  `dataInicial` datetime NOT NULL,
  `dataFinal` datetime NOT NULL,
  `devolvido` tinyint(1) NOT NULL DEFAULT '0',
  `nota` int(11) NOT NULL,
  `custo` int(11) NOT NULL,
  `fidelidade` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `locacoes`
--

INSERT INTO `locacoes` (`id`, `id_cliente`, `id_carro`, `dataInicial`, `dataFinal`, `devolvido`, `nota`, `custo`, `fidelidade`) VALUES
(1, 1, 1, '2019-07-09 18:01:00', '2019-07-09 18:05:59', 1, 0, 0, 0),
(2, 1, 1, '2019-07-09 18:06:00', '2019-07-09 18:10:25', 1, 0, 0, 0),
(3, 1, 1, '2019-07-09 18:10:00', '2019-07-09 18:11:10', 1, 0, 0, 0),
(4, 1, 1, '2019-07-09 18:15:00', '2019-07-09 18:15:53', 1, 0, 0, 0),
(5, 6, 1, '2019-07-09 19:14:00', '2019-07-09 19:15:47', 1, 0, 0, 0),
(11, 6, 1, '2019-07-09 19:35:00', '2019-07-09 19:43:12', 1, 0, 0, 0),
(12, 6, 1, '2019-07-09 19:51:00', '2019-07-09 20:00:43', 1, 0, 0, 0),
(13, 6, 1, '2019-07-09 20:07:00', '2019-07-09 20:09:02', 1, 0, 1409, 0),
(14, 6, 1, '2019-07-09 20:10:00', '2019-07-09 20:10:17', 1, 1, 3016, 0),
(15, 6, 1, '2019-07-09 20:11:00', '2019-07-09 20:11:26', 1, 4, 3316, 0);

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
-- Table structure for table `modelocarro`
--

CREATE TABLE `modelocarro` (
  `id` int(11) NOT NULL,
  `marca` varchar(20) NOT NULL,
  `modelo` varchar(20) NOT NULL,
  `idGrupo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `modelocarro`
--

INSERT INTO `modelocarro` (`id`, `marca`, `modelo`, `idGrupo`) VALUES
(1, 'General Motors', 'Cadillac', 1),
(2, 'Hyundai', 'HB20', 2),
(3, 'Volkswagen', 'Gol', 3),
(4, 'Fiat', 'Uno', 2),
(5, 'Volkswagen', 'Fox', 2),
(6, 'Ford', 'Fiesta', 3);

-- --------------------------------------------------------

--
-- Table structure for table `precos`
--

CREATE TABLE `precos` (
  `id` int(11) NOT NULL,
  `id_grupo` int(11) NOT NULL,
  `valor` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `precos`
--

INSERT INTO `precos` (`id`, `id_grupo`, `valor`) VALUES
(1, 1, 100.99),
(2, 2, 200.98),
(3, 3, 300.97);

-- --------------------------------------------------------

--
-- Table structure for table `reservas`
--

CREATE TABLE `reservas` (
  `id` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_grupo` int(11) NOT NULL,
  `id_modelo` int(11) NOT NULL,
  `dataLocacao` datetime NOT NULL,
  `dataDevolucao` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tipofuncionario`
--

CREATE TABLE `tipofuncionario` (
  `id` int(11) NOT NULL,
  `nome` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tipofuncionario`
--

INSERT INTO `tipofuncionario` (`id`, `nome`) VALUES
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
-- Indexes for table `estadocarro`
--
ALTER TABLE `estadocarro`
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
-- Indexes for table `grupocarro`
--
ALTER TABLE `grupocarro`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `locacoes`
--
ALTER TABLE `locacoes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Id_Carro` (`id_cliente`),
  ADD KEY `fk_Id_Carro_Locacao` (`id_carro`);

--
-- Indexes for table `manutencao`
--
ALTER TABLE `manutencao`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pk_Id_Carro` (`id_carro`);

--
-- Indexes for table `modelocarro`
--
ALTER TABLE `modelocarro`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_grupo` (`idGrupo`);

--
-- Indexes for table `precos`
--
ALTER TABLE `precos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_grupo_preco` (`id_grupo`);

--
-- Indexes for table `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Id_Cliente_Reserva` (`id_cliente`),
  ADD KEY `fk_Id_Grupo` (`id_grupo`),
  ADD KEY `fk_Id_Modelo` (`id_modelo`);

--
-- Indexes for table `tipofuncionario`
--
ALTER TABLE `tipofuncionario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `carro`
--
ALTER TABLE `carro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `estadocarro`
--
ALTER TABLE `estadocarro`
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
-- AUTO_INCREMENT for table `grupocarro`
--
ALTER TABLE `grupocarro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `locacoes`
--
ALTER TABLE `locacoes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `manutencao`
--
ALTER TABLE `manutencao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `modelocarro`
--
ALTER TABLE `modelocarro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `precos`
--
ALTER TABLE `precos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `reservas`
--
ALTER TABLE `reservas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tipofuncionario`
--
ALTER TABLE `tipofuncionario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `locacoes`
--
ALTER TABLE `locacoes`
  ADD CONSTRAINT `fk_Id_Carro_Locacao` FOREIGN KEY (`id_carro`) REFERENCES `carro` (`id`),
  ADD CONSTRAINT `fk_Id_Cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`);

--
-- Constraints for table `manutencao`
--
ALTER TABLE `manutencao`
  ADD CONSTRAINT `pk_Id_Carro` FOREIGN KEY (`id_carro`) REFERENCES `carro` (`id`);

--
-- Constraints for table `precos`
--
ALTER TABLE `precos`
  ADD CONSTRAINT `id_grupo_preco` FOREIGN KEY (`id_grupo`) REFERENCES `grupocarro` (`id`);

--
-- Constraints for table `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `fk_Id_Cliente_Reserva` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `fk_Id_Grupo` FOREIGN KEY (`id_grupo`) REFERENCES `grupocarro` (`id`),
  ADD CONSTRAINT `fk_Id_Modelo` FOREIGN KEY (`id_modelo`) REFERENCES `modelocarro` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
