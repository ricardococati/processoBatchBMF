package br.com.irmaoscocati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.data.mapping.model.MappingException;
import org.springframework.jdbc.core.RowMapper;

import br.com.irmaoscocati.dto.BMFResumoOperacoesDTO;

public class BMFMapper implements RowMapper<BMFResumoOperacoesDTO> {

	public BMFResumoOperacoesDTO mapRow(ResultSet rs, int rowNum) throws SQLException, MappingException {
		BMFResumoOperacoesDTO bmf = new BMFResumoOperacoesDTO();
		try{
			bmf.setTipoRegistro(rs.getString("tipoRegistro"));
			bmf.setNumeroPV(rs.getString("numeroPV"));
			bmf.setParcela(rs.getString("parcela"));
			bmf.setFiller(rs.getString("filler"));
			bmf.setTipoDeTransacao(rs.getString("tipoDeTransacao"));
			bmf.setDtaApresentacao(rs.getString("dtaProcesArquivo"));
			bmf.setDtaPrevistaPagto(rs.getString("dtaPrevistaPagto"));
			bmf.setVlrLiquido(rs.getString("vlrLiquido"));
			bmf.setVlrLiquido(rs.getString("vlrBruto"));
			bmf.setCodigoProduto(rs.getString("codigoProduto"));
			bmf.setDadoDescarte(rs.getString("dadoDescarte"));
		} catch (MappingException mapEx){
			mapEx.printStackTrace();
		} catch (Exception ex){
			ex.printStackTrace();
		}
		return bmf;
	}

}
