-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 30/03/2019 às 04:18
-- Versão do servidor: 10.1.38-MariaDB
-- Versão do PHP: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `vocealuga`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `cliente`
--

CREATE TABLE `cliente` (
  `Id` int(11) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  `CPF` varchar(15) NOT NULL,
  `Passaporte` varchar(15) NOT NULL,
  `DataNascimento` date NOT NULL,
  `Nacionalidade` varchar(30) NOT NULL,
  `Telefone` bigint(20) NOT NULL,
  `CNH` bigint(20) NOT NULL,
  `DataCNH` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Despejando dados para a tabela `cliente`
--

INSERT INTO `cliente` (`Id`, `Nome`, `CPF`, `Passaporte`, `DataNascimento`, `Nacionalidade`, `Telefone`, `CNH`, `DataCNH`) VALUES
(1, 'Cliente Teste', '12345678910', '', '2019-03-29', 'mundo', 987651234, 12345678901, '2019-03-30'),
(2, 'Cliente Teste Passaporte', '', 'AB123456', '2019-03-29', 'brasileiro', 912345678, 12345678911, '2019-03-29'),
(3, 'Cliente Teste Passaporte 2', '', 'AC123456', '2019-03-29', 'brasileiro', 987654321, 12345678911, '2019-03-29'),
(4, 'Teste de Cadastro do Cliente', '12345678905', '', '2019-03-30', 'mundo', 987654322, 12345678903, '2019-03-30');

-- --------------------------------------------------------

--
-- Estrutura para tabela `filial`
--

CREATE TABLE `filial` (
  `id` int(11) NOT NULL,
  `nomeFilial` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Despejando dados para a tabela `filial`
--

INSERT INTO `filial` (`id`, `nomeFilial`) VALUES
(1, 'Rio de Janeiro');

-- --------------------------------------------------------

--
-- Estrutura para tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `Id` int(11) NOT NULL,
  `User` varchar(30) NOT NULL,
  `Senha` varchar(30) NOT NULL,
  `IdFilial` int(11) NOT NULL,
  `IdTipo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Despejando dados para a tabela `funcionario`
--

INSERT INTO `funcionario` (`Id`, `User`, `Senha`, `IdFilial`, `IdTipo`) VALUES
(1, 'Admin', 'Admin', 1, 1),
(2, 'Test', 'Test', 1, 2);

-- --------------------------------------------------------

--
-- Estrutura para tabela `tipofuncionario`
--

CREATE TABLE `tipofuncionario` (
  `Id` int(11) NOT NULL,
  `Nombre` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Despejando dados para a tabela `tipofuncionario`
--

INSERT INTO `tipofuncionario` (`Id`, `Nombre`) VALUES
(1, 'Administrador'),
(2, 'Funcionario');

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`Id`);

--
-- Índices de tabela `filial`
--
ALTER TABLE `filial`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `FK_TipoFuncionario` (`IdTipo`),
  ADD KEY `FK_Filial` (`IdFilial`);

--
-- Índices de tabela `tipofuncionario`
--
ALTER TABLE `tipofuncionario`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `filial`
--
ALTER TABLE `filial`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `tipofuncionario`
--
ALTER TABLE `tipofuncionario`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restrições para dumps de tabelas
--

--
-- Restrições para tabelas `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `FK_Filial` FOREIGN KEY (`IdFilial`) REFERENCES `filial` (`id`),
  ADD CONSTRAINT `FK_TipoFuncionario` FOREIGN KEY (`IdTipo`) REFERENCES `tipofuncionario` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
