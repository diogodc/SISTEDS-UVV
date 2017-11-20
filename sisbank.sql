-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 26-Out-2016 às 20:46
-- Versão do servidor: 10.1.10-MariaDB
-- PHP Version: 7.0.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sisbank`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_historico`
--

CREATE TABLE `tb_historico` (
  `id` int(11) NOT NULL,
  `data` date NOT NULL,
  `hora` time NOT NULL,
  `operacao` varchar(15) NOT NULL,
  `valor` float NOT NULL,
  `saldo` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tb_historico`
--

INSERT INTO `tb_historico` (`id`, `data`, `hora`, `operacao`, `valor`, `saldo`) VALUES
(10, '2016-09-24', '10:33:00', '1', 2000, 12000);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_poupanca`
--

CREATE TABLE `tb_poupanca` (
  `id` int(11) NOT NULL,
  `jurosMensal` float NOT NULL,
  `agencia` varchar(10) NOT NULL,
  `numPoup` int(11) NOT NULL,
  `saldo` float NOT NULL,
  `nomeCliente` varchar(30) NOT NULL,
  `taxaAdm` float NOT NULL,
  `nomeBanco` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tb_poupanca`
--

INSERT INTO `tb_poupanca` (`id`, `jurosMensal`, `agencia`, `numPoup`, `saldo`, `nomeCliente`, `taxaAdm`, `nomeBanco`) VALUES
(1, 1, '1234', 20161234, 12000, 'Felipe Vogel', 10, 'Caixa Econômica'),
(2, 2, '4321', 20164320, 200000, 'Diogo Diniz', 20, 'Banco do Brasil'),
(3, 3, '5555x', 20165556, 300000, 'Hudson Ramos', 3, 'Itaú');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_historico`
--
ALTER TABLE `tb_historico`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_poupanca`
--
ALTER TABLE `tb_poupanca`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `numPoup` (`numPoup`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_historico`
--
ALTER TABLE `tb_historico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `tb_poupanca`
--
ALTER TABLE `tb_poupanca`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
