package br.com.irmaoscocati.util;

import org.springframework.stereotype.Component;

@Component
public class ScriptUtil {

	public String obterInsertTabelaBMFVendaPagto(String nomeArquivo) {
		final StringBuffer sql = new StringBuffer(50);
		sql.append(" INSERT INTO CIELO_VENDA_PAGTO( ");
		sql.append("                              TIPO_REGISTRO, ");
		sql.append("                              NUMERO_PV, ");
		sql.append("                              DTA_APRESENTACAO, ");
		sql.append("                              DTA_PREVISTA_PAGTO, ");
		sql.append("                              VLR_BRUTO, ");
		sql.append("                              VLR_LIQUIDO, ");
		sql.append("                              CODIGO_PRODUTO, ");
		sql.append("                              DTA_EXECUCAO, ");
		sql.append("                              STATUS_PROCESSAMENTO, ");
		sql.append("                              NOME_ARQUIVO ");
		sql.append(" ) VALUES( ");
		sql.append("                              :tipoRegistro, ");
		sql.append("                              :numeroPV, ");
		sql.append("                              :dtaApresentacao, ");
		sql.append("                              :dtaPrevistaPagto, ");
		sql.append("                              CAST(:vlrBruto AS DECIMAL(10,2)) / 100, ");
		sql.append("                              CAST(:vlrLiquido AS DECIMAL(10,2)) / 100, ");
		sql.append("                              :codigoProduto, ");
		sql.append("                              CURRENT_TIMESTAMP, ");
		sql.append("                              'N', ");
		sql.append("                              '" + nomeArquivo + "' ");
		sql.append("         ) 	");
		return sql.toString();
	}

}
