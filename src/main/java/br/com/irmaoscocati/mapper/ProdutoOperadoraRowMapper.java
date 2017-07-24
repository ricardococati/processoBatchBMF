package br.com.irmaoscocati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.irmaoscocati.entidade.ProdutoOperadora;

public class ProdutoOperadoraRowMapper implements RowMapper<ProdutoOperadora> {

	@Override
	public ProdutoOperadora mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProdutoOperadora produtoOperadora = new ProdutoOperadora();
		produtoOperadora.setIdProduto(rs.getInt("idProduto"));
		produtoOperadora.setIdOperadora(rs.getInt("idOperadora"));
		produtoOperadora.setCodProdOperadora(rs.getString("codProdOperadora"));

		return produtoOperadora;
	}

}
