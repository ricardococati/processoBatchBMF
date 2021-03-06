/*DROP TABLE processo_batch_cielo.CIELO_ERRO_PROCESSAMENTO;*/

/*Criação da tabela de erros para a operadora Cielo*/
CREATE TABLE  `processo_batch_cielo`.`CIELO_ERRO_PROCESSAMENTO` (
  `ID_ERRO_PROCESSAMENTO` int(11) NOT NULL AUTO_INCREMENT,
  `CODIGO_ERRO_PROCESSAMENTO` int(2) NOT NULL,
  `TIPO_ERRO_PROCESSAMENTO` int(2) NOT NULL,
  `DESCRICAO_ERRO_PROCESSAMENTO` varchar(500) NOT NULL,
  `DATA_ERRO_PROCESSAMENTO` date NOT NULL,
  `NOME_ARQUIVO` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_ERRO_PROCESSAMENTO`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;

ALTER TABLE `processo_batch_cielo`.`CIELO_VENDA_PAGTO` ADD `NOME_ARQUIVO` VARCHAR(100);