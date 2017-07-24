package br.com.irmaoscocati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.irmaoscocati.entidade.EmpresaOperadora;

public class EmpresaOperadoraRowMapper implements RowMapper<EmpresaOperadora> {

	@Override
	public EmpresaOperadora mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmpresaOperadora empresaOperadora = new EmpresaOperadora();
		empresaOperadora.setIdEmpresa(rs.getInt("idEmpresa"));
		empresaOperadora.setIdOperadora(rs.getInt("idOperadora"));
		empresaOperadora.setNumeroPV(rs.getString("numeroPV"));

		return empresaOperadora;
	}

}
