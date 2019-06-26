-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-06-2019 a las 14:10:29
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
-- Estructura de tabla para la tabla `carro`
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
-- Volcado de datos para la tabla `carro`
--

INSERT INTO `carro` (`id`, `placa`, `dataManutencao`, `dataCompra`, `quilometragem`, `idModelo`, `idFilial`, `idEstado`) VALUES
(1, 'ABC1234', '2019-04-21', '2019-04-21', 0, 2, 1, 3),
(2, 'ABD1234', '2019-04-15', '2019-05-10', 123, 3, 1, 1),
(3, 'ABE1234', '2019-07-07', '2019-06-06', 200, 5, 1, 3),
(4, 'ABF1234', '2019-06-25', '2019-06-25', 300, 1, 2, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
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
-- Volcado de datos para la tabla `cliente`
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
-- Estructura de tabla para la tabla `estadocarro`
--

CREATE TABLE `estadocarro` (
  `id` int(11) NOT NULL,
  `tipo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `estadocarro`
--

INSERT INTO `estadocarro` (`id`, `tipo`) VALUES
(1, 'Alugado'),
(2, 'Em manutenção'),
(3, 'Disponível'),
(4, 'Vendido');

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
(1, 'Rio de Janeiro'),
(2, 'São Paulo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `funcionario`
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
-- Volcado de datos para la tabla `funcionario`
--

INSERT INTO `funcionario` (`id`, `nome`, `usuario`, `senha`, `idFilial`, `idTipo`, `ativo`) VALUES
(1, 'Fulano Adm da Silva', 'Admin', 'Admin', 1, 1, 1),
(2, 'Beltrano Testador da Silva', 'Test', 'Test', 1, 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupocarro`
--

CREATE TABLE `grupocarro` (
  `id` int(11) NOT NULL,
  `grupo` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `grupocarro`
--

INSERT INTO `grupocarro` (`id`, `grupo`) VALUES
(1, 'A'),
(2, 'B'),
(3, 'C');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `locacoes`
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
-- Estructura de tabla para la tabla `manutencao`
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
-- Estructura de tabla para la tabla `modelocarro`
--

CREATE TABLE `modelocarro` (
  `id` int(11) NOT NULL,
  `marca` varchar(20) NOT NULL,
  `modelo` varchar(20) NOT NULL,
  `idGrupo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `modelocarro`
--

INSERT INTO `modelocarro` (`id`, `marca`, `modelo`, `idGrupo`) VALUES
(1, 'Hyundai', 'HB20', 2),
(2, 'Volkswagen', 'Gol', 3),
(3, 'Fiat', 'Uno', 2),
(4, 'Volkswagen', 'Fox', 2),
(5, 'Ford', 'Fiesta', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
  `id` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_grupo` int(11) NOT NULL,
  `id_modelo` int(11) NOT NULL,
  `dataLocacao` date NOT NULL,
  `dataDevolucao` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipofuncionario`
--

CREATE TABLE `tipofuncionario` (
  `id` int(11) NOT NULL,
  `nome` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipofuncionario`
--

INSERT INTO `tipofuncionario` (`id`, `nome`) VALUES
(1, 'Administrador'),
(2, 'Funcionario');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carro`
--
ALTER TABLE `carro`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `placa` (`placa`),
  ADD KEY `FK_modelo` (`idModelo`),
  ADD KEY `FK_estado` (`idEstado`),
  ADD KEY `FK_filial` (`idFilial`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `CPF` (`CPF`),
  ADD UNIQUE KEY `passaporte` (`passaporte`);

--
-- Indices de la tabla `estadocarro`
--
ALTER TABLE `estadocarro`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `filial`
--
ALTER TABLE `filial`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_tipoFuncionario` (`idTipo`),
  ADD KEY `FK_filial` (`idFilial`);

--
-- Indices de la tabla `grupocarro`
--
ALTER TABLE `grupocarro`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `locacoes`
--
ALTER TABLE `locacoes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Id_Carro` (`id_cliente`);

--
-- Indices de la tabla `manutencao`
--
ALTER TABLE `manutencao`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pk_Id_Carro` (`id_carro`);

--
-- Indices de la tabla `modelocarro`
--
ALTER TABLE `modelocarro`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_grupo` (`idGrupo`);

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Id_Cliente_Reserva` (`id_cliente`),
  ADD KEY `fk_Id_Grupo` (`id_grupo`),
  ADD KEY `fk_Id_Modelo` (`id_modelo`);

--
-- Indices de la tabla `tipofuncionario`
--
ALTER TABLE `tipofuncionario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `carro`
--
ALTER TABLE `carro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `estadocarro`
--
ALTER TABLE `estadocarro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `filial`
--
ALTER TABLE `filial`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `grupocarro`
--
ALTER TABLE `grupocarro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `locacoes`
--
ALTER TABLE `locacoes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `manutencao`
--
ALTER TABLE `manutencao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `modelocarro`
--
ALTER TABLE `modelocarro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `reservas`
--
ALTER TABLE `reservas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipofuncionario`
--
ALTER TABLE `tipofuncionario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `locacoes`
--
ALTER TABLE `locacoes`
  ADD CONSTRAINT `fk_Id_Carro` FOREIGN KEY (`id_cliente`) REFERENCES `carro` (`id`),
  ADD CONSTRAINT `fk_Id_Cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`);

--
-- Filtros para la tabla `manutencao`
--
ALTER TABLE `manutencao`
  ADD CONSTRAINT `pk_Id_Carro` FOREIGN KEY (`id_carro`) REFERENCES `carro` (`id`);

--
-- Filtros para la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `fk_Id_Cliente_Reserva` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `fk_Id_Grupo` FOREIGN KEY (`id_grupo`) REFERENCES `grupocarro` (`id`),
  ADD CONSTRAINT `fk_Id_Modelo` FOREIGN KEY (`id_modelo`) REFERENCES `modelocarro` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
