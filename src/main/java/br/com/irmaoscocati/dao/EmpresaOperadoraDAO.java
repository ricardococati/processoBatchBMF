package br.com.irmaoscocati.dao;

import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.irmaoscocati.entidade.EmpresaOperadora;
import br.com.irmaoscocati.mapper.EmpresaOperadoraRowMapper;

@Repository
public class EmpresaOperadoraDAO implements IEmpresaOperadoraDAO, Serializable {

	private static final long serialVersionUID = 209431743387545436L;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	private DataSource dataSource;

	@Override
	@Cacheable("empresasOperadoras")
	public EmpresaOperadora obterEmpresaOperadoraPorPV(String numeroPV) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		StringBuilder query = new StringBuilder();

		query.append("SELECT ");
		query.append("  	eo.idEmpresa,  ");
		query.append("  	eo.idOperadora, ");
		query.append("  	eo.numeroPV ");
		query.append("FROM ");
		query.append("  corporativo.EmpresaOperadora eo ");
		query.append("WHERE");
		query.append("  eo.numeroPV = ? ");
		//Caso exista mais de uma empresa com o mesmo PV o sistema vai limitar sempre em 1 registro de retorno
		//A função LIMIT traz sempre o número de linhas estipulado, nesse caso 1
		//Pois de acordo com essa regra é necessário apenas que se saiba se o PV existe na base.
		query.append(" LIMIT 1 ");

		Object[] parametros = new Object[] { numeroPV };

		try {
			EmpresaOperadora empresaOperadora = (EmpresaOperadora) jdbcTemplate.queryForObject(query.toString(), parametros,  new EmpresaOperadoraRowMapper());
			return empresaOperadora;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

}
