--
-- Estrutura para tabela `CIELO_CODIGO_PRODUTO`
--
CREATE TABLE CIELO_CODIGO_PRODUTO (
  CODIGO_PRODUTO int(3) NOT NULL,
  DESCRICAO_PRODUTO varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Código que identifi ca o produto';

--
-- Índices de tabela CIELO_CODIGO_PRODUTO
--
ALTER TABLE CIELO_CODIGO_PRODUTO ADD PRIMARY KEY (CODIGO_PRODUTO);