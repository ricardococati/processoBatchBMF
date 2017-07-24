USE tiquei;

DROP PROCEDURE IF EXISTS tiquei.prcProcessCielo;

DELIMITER $$
CREATE PROCEDURE prcProcessCielo(OUT ptsProcesso INT)
LANGUAGE SQL
DETERMINISTIC
READS SQL DATA
SQL SECURITY INVOKER
COMMENT ''

BEGIN
   DECLARE vstsProcess       INT DEFAULT 0;
   DECLARE vidOperadora      BIGINT(16) DEFAULT 0;
   DECLARE vidProduto        INT(6) DEFAULT 0;
   DECLARE vcnpj             VARCHAR(14);
   DECLARE vdataOperacao     DATE;
   DECLARE vdtaProcess       DATE;
   DECLARE vvalor            DECIMAL(12,2);
   DECLARE vcodproduto       VARCHAR(3);
   DECLARE vFound            INT DEFAULT 0;
   DECLARE vContaVda         INT DEFAULT 0;
   DECLARE vContaPrev        INT DEFAULT 0;
   DECLARE vInsVda           INT DEFAULT 0;
   DECLARE vInsPrev          INT DEFAULT 0;
   DECLARE vUpdVda           INT DEFAULT 0;
   DECLARE vUpdPrev          INT DEFAULT 0;
   DECLARE vLoopVda          INT DEFAULT 0;
   DECLARE vLoopPrev         INT DEFAULT 0;
   DECLARE vUpdCarga         INT DEFAULT 0;
   DECLARE vcodDia           INT(2) DEFAULT 0;
   DECLARE vsemanaISO        INT(2) DEFAULT 0;
   DECLARE vcodMes           INT(6) DEFAULT 0;
   DECLARE vcodAno           INT(4) DEFAULT 0;

   /* Declaracao do cursor de vendas                                   */
   /* Obtem: cnpj                                                      */
   /*        codigo da operadora (parametro)                           */
   /*        codigo do produto (duvida)                                */
   /*        data da operacao (DTA_APRESENTACAO)                       */
   /*        data de processamento (CURRENT_DATE())                    */
   /*        somatorio das vendas (SUM(VLR_BRUTO))                     */
   /* Condicao: Nao ha condicao de filtro na tabela origem             */
   DECLARE curVdas CURSOR FOR SELECT cielo_vda_pgto.cnpj,
                                     cielo_vda_pgto.dataOperacao,
                                     cielo_vda_pgto.CODIGO_PRODUTO,
                                     cielo_vda_pgto.idOperadora,
                                     cielo_vda_pgto.codDia,
                                     cielo_vda_pgto.semana,
                                     cielo_vda_pgto.codMes,
                                     cielo_vda_pgto.ano,
                                     cielo_vda_pgto.dataProcessamento,
                                     cielo_vda_pgto.valor
                                FROM (SELECT DISTINCT
                                             c.cnpj,
                                             DATE_FORMAT(a.DTA_APRESENTACAO,'%Y-%m-%d') AS dataOperacao,
                                             a.CODIGO_PRODUTO,
                                             b.idOperadora,
                                             DATE_FORMAT(a.DTA_APRESENTACAO, '%Y%m%d') AS codDia,
                                             WEEK(a.DTA_APRESENTACAO, 2) AS semana,
                                             DATE_FORMAT(a.DTA_APRESENTACAO, '%Y%m') AS codMes,
                                             YEAR(a.DTA_APRESENTACAO) AS ano,
                                             DATE_FORMAT(CURRENT_DATE(),'%Y-%m-%d') AS dataProcessamento,
                                             a.VLR_BRUTO AS valor
                                        FROM processo_batch_cielo.CIELO_VENDA_PAGTO a
                                       INNER JOIN corporativo.EmpresaOperadora b
                                          ON (a.NUMERO_PV = b.numeroPV)
                                       INNER JOIN corporativo.Operadora d
                                          ON (b.idOperadora = d.id 
                                         AND UPPER(d.nome) LIKE 'CIELO')
                                       INNER JOIN corporativo.Empresa c
                                          ON (b.idEmpresa = c.id)
                                       WHERE a.STATUS_PROCESSAMENTO = 'N') cielo_vda_pgto
                               GROUP BY 
                                     cielo_vda_pgto.cnpj,
                                     cielo_vda_pgto.dataOperacao,
                                     cielo_vda_pgto.CODIGO_PRODUTO,
                                     cielo_vda_pgto.idOperadora,
                                     cielo_vda_pgto.codDia,
                                     cielo_vda_pgto.semana,
                                     cielo_vda_pgto.codMes,
                                     cielo_vda_pgto.ano,
                                     cielo_vda_pgto.dataProcessamento;

   /* Declaracao do cursor de previsao de recebimento                  */
   /* Obtem: cnpj                                                      */
   /*        codigo da operadora (parametro)                           */
   /*        codigo do produto (duvida)                                */
   /*        data da operacao (DTA_APRESENTACAO)                       */
   /*        data de processamento (CURRENT_DATE())                    */
   /*        somatorio das vendas (SUM(VLR_LIQUIDO))                   */
   /* Condicao: Nao ha condicao de filtro na tabela origem             */
   DECLARE curPrev CURSOR FOR SELECT cielo_vda_pgto.cnpj,
                                     cielo_vda_pgto.dataOperacao,
                                     cielo_vda_pgto.CODIGO_PRODUTO,
                                     cielo_vda_pgto.idOperadora,
                                     cielo_vda_pgto.codDia,
                                     cielo_vda_pgto.semana,
                                     cielo_vda_pgto.codMes,
                                     cielo_vda_pgto.ano,
                                     cielo_vda_pgto.dataProcessamento,
                                     cielo_vda_pgto.valor
                                FROM (SELECT DISTINCT
                                             c.cnpj,
                                             DATE_FORMAT(a.DTA_PREVISTA_PAGTO,'%Y-%m-%d') AS dataOperacao,
                                             a.CODIGO_PRODUTO,
                                             b.idOperadora,
                                             DATE_FORMAT(a.DTA_PREVISTA_PAGTO, '%Y%m%d') AS codDia,
                                             WEEK(a.DTA_PREVISTA_PAGTO, 2) AS semana,
                                             DATE_FORMAT(a.DTA_PREVISTA_PAGTO, '%Y%m') AS codMes,
                                             YEAR(a.DTA_PREVISTA_PAGTO) AS ano,
                                             DATE_FORMAT(CURRENT_DATE(),'%Y-%m-%d') AS dataProcessamento,
                                             a.VLR_LIQUIDO AS valor
                                        FROM processo_batch_cielo.CIELO_VENDA_PAGTO a
                                       INNER JOIN corporativo.EmpresaOperadora b
                                          ON (a.NUMERO_PV = b.numeroPV)
                                       INNER JOIN corporativo.Operadora d
                                          ON (b.idOperadora = d.id 
                                         AND UPPER(d.nome) LIKE 'CIELO')
                                       INNER JOIN corporativo.Empresa c
                                          ON (b.idEmpresa = c.id)
                                       WHERE a.STATUS_PROCESSAMENTO = 'N') cielo_vda_pgto
                               GROUP BY 
                                     cielo_vda_pgto.cnpj,
                                     cielo_vda_pgto.dataOperacao,
                                     cielo_vda_pgto.CODIGO_PRODUTO,
                                     cielo_vda_pgto.idOperadora,
                                     cielo_vda_pgto.codDia,
                                     cielo_vda_pgto.semana,
                                     cielo_vda_pgto.codMes,
                                     cielo_vda_pgto.ano,
                                     cielo_vda_pgto.dataProcessamento;
   
   /*                     tratamento de erro                    */
   DECLARE EXIT HANDLER FOR SQLEXCEPTION SET vstsProcess = 1; 

   BEGIN
      START TRANSACTION;
      /*              Obtendo o código da Operadora              */
      BEGIN
         /*              Abrindo o cursor de vendas              */
         OPEN curVdas;

         SET vLoopVda = (Select FOUND_ROWS());

         /*               executando o loop vendas               */
         WHILE vContaVda<vLoopVda DO
            FETCH curVdas INTO vcnpj, 
                               vdataOperacao,
                               vcodproduto,
                               vidOperadora,
                               vcodDia,
                               vsemanaISO,
                               vcodMes,
                               vcodAno,
                               vdtaProcess,
                               vvalor;

            SET vFound = 0;

            /*            obtendo o codigo do produto           */
            BEGIN
               SELECT idProduto
                 INTO vidProduto
                 FROM tiquei.ProdutoOperadora
                WHERE idOperadora      = vidOperadora
                  AND codProdOperadora = vcodproduto;
            END;

            /*        verificando se o registro ja existe        */
            BEGIN
               SELECT 1
                 INTO vFound
                 FROM tiquei.VendasRealizadas
                WHERE cnpj              = vcnpj
                  AND idOperadora       = vidOperadora
                  AND produto           = vidProduto
                  AND dataOperacao      = vdataOperacao
                  AND codDia            = vcodDia
                  AND semanaISO         = vsemanaISO
                  AND codMes            = vcodMes
                  AND codAno            = vcodAno;
            END;

            /*                executando o insert                */
            IF vFound = 1 THEN
               SET SQL_SAFE_UPDATES=0;
               UPDATE tiquei.VendasRealizadas
                  SET valor             = vvalor
                    , dataProcessamento = vdtaProcess
                WHERE cnpj              = vcnpj
                  AND idOperadora       = vidOperadora
                  AND produto           = vidProduto
                  AND dataOperacao      = vdataOperacao
                  AND codDia            = vcodDia
                  AND semanaISO         = vsemanaISO
                  AND codMes            = vcodMes
                  AND codAno            = vcodAno;
               SET vUpdVda = vUpdVda+1;
               SET SQL_SAFE_UPDATES=1;
            ELSE
               SET SQL_SAFE_UPDATES=0;
               INSERT INTO tiquei.VendasRealizadas (cnpj, 
                                                    idOperadora, 
                                                    produto,
                                                    dataOperacao,
                                                    codDia,
                                                    semanaISO,
                                                    codMes,
                                                    codAno,
                                                    valor,
                                                    dataProcessamento)
                                            VALUES (vcnpj, 
                                                    vidOperadora, 
                                                    vidProduto,
                                                    vdataOperacao,
                                                    vcodDia,
                                                    vsemanaISO,
                                                    vcodMes,
                                                    vcodAno,
                                                    vvalor,
                                                    vdtaProcess);
               SET SQL_SAFE_UPDATES=1;
            END IF;
            SET vContaVda = vContaVda+1;
         END while;

         /*               Fechando o cursor curVdas              */
         CLOSE curVdas;
      END;

      BEGIN
         /*              Abrindo o cursor de vendas              */
         OPEN curPrev;

         SET vLoopPrev = (Select FOUND_ROWS());

         /*               executando o loop vendas               */
         WHILE vContaPrev<vLoopPrev DO
            FETCH curPrev INTO vcnpj, 
                               vdataOperacao,
                               vcodproduto,
                               vidOperadora,
                               vcodDia,
                               vsemanaISO,
                               vcodMes,
                               vcodAno,
                               vdtaProcess,
                               vvalor;

            SET vFound = 0;

            /*            obtendo o codigo do produto           */
            BEGIN
               SELECT idProduto
                 INTO vidProduto
                 FROM tiquei.ProdutoOperadora
                WHERE idOperadora      = vidOperadora
                  AND codProdOperadora = vcodproduto;
            END;

            /*        verificando se o registro ja existe        */
            BEGIN
               SELECT 1
                 INTO vFound
                 FROM tiquei.PrevisaoRecebimento
                WHERE cnpj              = vcnpj
                  AND idOperadora       = vidOperadora
                  AND produto           = vidProduto
                  AND dataOperacao      = vdataOperacao
                  AND codDia            = vcodDia
                  AND semanaISO         = vsemanaISO
                  AND codMes            = vcodMes
                  AND codAno            = vcodAno;
            END;

            /*                executando o insert                */
            IF vFound = 1 THEN
               SET SQL_SAFE_UPDATES=0;
               UPDATE tiquei.PrevisaoRecebimento
                  SET valor             = vvalor
                    , dataProcessamento = vdtaProcess
                WHERE cnpj              = vcnpj
                  AND idOperadora       = vidOperadora
                  AND produto           = vidProduto
                  AND dataOperacao      = vdataOperacao
                  AND codDia            = vcodDia
                  AND semanaISO         = vsemanaISO
                  AND codMes            = vcodMes
                  AND codAno            = vcodAno;
               SET vUpdPrev = vUpdPrev+1;
               SET SQL_SAFE_UPDATES=1;
            ELSE
               SET SQL_SAFE_UPDATES=0;
               INSERT INTO tiquei.PrevisaoRecebimento (cnpj, 
                                                       idOperadora, 
                                                       produto,
                                                       dataOperacao,
                                                       codDia,
                                                       semanaISO,
                                                       codMes,
                                                       codAno,
                                                       valor,
                                                       dataProcessamento)
                                               VALUES (vcnpj, 
                                                       vidOperadora, 
                                                       vidProduto,
                                                       vdataOperacao,
                                                       vcodDia,
                                                       vsemanaISO,
                                                       vcodMes,
                                                       vcodAno,
                                                       vvalor,
                                                       vdtaProcess);
               SET vInsPrev = vInsPrev+1;
               SET SQL_SAFE_UPDATES=1;
            END IF;
            SET vContaPrev = vContaPrev+1;
         END while;

         /*               Fechando o cursor curPrev              */
         CLOSE curPrev;
      END;

      IF vstsProcess = 0 THEN
         SET SQL_SAFE_UPDATES=0;
         UPDATE processo_batch_cielo.CIELO_VENDA_PAGTO
            SET STATUS_PROCESSAMENTO = 'S'
          WHERE STATUS_PROCESSAMENTO = 'N';
         COMMIT;
         SET SQL_SAFE_UPDATES=1;
         SET ptsProcesso = 0;
      ELSE
         SET SQL_SAFE_UPDATES=0;
         ROLLBACK;
         SET SQL_SAFE_UPDATES=1;
         SET ptsProcesso = 1;
      END IF;
   END;
END$$
DELIMITER ;
