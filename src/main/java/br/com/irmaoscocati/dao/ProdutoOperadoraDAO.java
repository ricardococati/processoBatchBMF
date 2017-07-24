package br.com.irmaoscocati.dao;

import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.irmaoscocati.entidade.ProdutoOperadora;
import br.com.irmaoscocati.mapper.ProdutoOperadoraRowMapper;

@Repository
public class ProdutoOperadoraDAO implements IProdutoOperadoraDAO, Serializable {

	private static final long serialVersionUID = -2038146146207194866L;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	private DataSource dataSource;

	private static final int ID_OPERADORA_CIELO = 1;

	@Override
	@Cacheable("produtoOperadora")
	public ProdutoOperadora obterProdutoOperadoraPorCodOperadora(String codProdutoOperadora) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		StringBuilder query = new StringBuilder();

		query.append("SELECT ");
		query.append("  	po.idProduto,  ");
		query.append("  	po.idOperadora, ");
		query.append("  	po.codProdOperadora ");
		query.append("FROM ");
		query.append("  bmf.ProdutoOperadora po ");
		query.append("WHERE");
		query.append("  po.codProdOperadora = ? ");
		query.append("  AND po.idOperadora = " + ID_OPERADORA_CIELO);
		//Apenas para garantir que não terá problema com dados repetidos
		query.append(" LIMIT 1 ");

		Object[] parametros = new Object[] { codProdutoOperadora };

		try {
			ProdutoOperadora produtoOperadora = (ProdutoOperadora) jdbcTemplate.queryForObject(query.toString(), parametros,  new ProdutoOperadoraRowMapper());
			return produtoOperadora;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

}
