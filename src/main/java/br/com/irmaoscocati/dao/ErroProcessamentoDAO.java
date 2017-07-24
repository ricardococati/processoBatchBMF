package br.com.irmaoscocati.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.irmaoscocati.entidade.ErroProcessamento;

@Repository
public class ErroProcessamentoDAO implements IErroProcessamentoDAO, Serializable {

	private static final long serialVersionUID = 8591374303668900373L;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	private DataSource dataSource;

	@Override
	public void incluirErroProcessamento(ErroProcessamento erroProcessamento) {
		Date date= new Date();
		final StringBuffer sql = new StringBuffer(50);
		sql.append(" INSERT INTO CIELO_ERRO_PROCESSAMENTO( ");
		sql.append("                              CODIGO_ERRO_PROCESSAMENTO, ");
		sql.append("                              TIPO_ERRO_PROCESSAMENTO, ");
		sql.append("                              DESCRICAO_ERRO_PROCESSAMENTO, ");
		sql.append("                              DATA_ERRO_PROCESSAMENTO, ");
		sql.append("                              NOME_ARQUIVO ");
		sql.append(" ) VALUES( ");
		sql.append("                              ?, ");
		sql.append("                              ?, ");
		sql.append("                              ?, ");
		sql.append("                              ?, ");
		sql.append("                              ?  ");
		sql.append("         ) 	");

		jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql.toString(), new Object[] { //
														erroProcessamento.getCodigoErroProcessamentoEnum().getCodigo(),//
														erroProcessamento.getTipoErroProcessamento().getCodigo(), //
														erroProcessamento.getDescricao(),//
														new Timestamp(date.getTime()), //
														erroProcessamento.getNomeArquivo()
														});
	}

}
