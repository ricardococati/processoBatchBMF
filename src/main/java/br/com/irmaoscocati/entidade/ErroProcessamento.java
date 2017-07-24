package br.com.irmaoscocati.entidade;

import java.io.Serializable;
import java.util.Date;

import br.com.irmaoscocati.util.CodigoErroProcessamentoEnum;

public class ErroProcessamento implements Serializable {

	private static final long serialVersionUID = -2502868472447017358L;

	private int id;

	private CodigoErroProcessamentoEnum codigoErroProcessamentoEnum;
	
	private TipoErroProcessamentoEnum tipoErroProcessamento;

	private String descricao;
	
	private String nomeArquivo;

	private Date dataHora;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public CodigoErroProcessamentoEnum getCodigoErroProcessamentoEnum() {
		return codigoErroProcessamentoEnum;
	}

	public void setCodigoErroProcessamentoEnum(CodigoErroProcessamentoEnum codigoErroProcessamentoEnum) {
		this.codigoErroProcessamentoEnum = codigoErroProcessamentoEnum;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoErroProcessamentoEnum getTipoErroProcessamento() {
		return tipoErroProcessamento;
	}

	public void setTipoErroProcessamento(TipoErroProcessamentoEnum tipoErroProcessamento) {
		this.tipoErroProcessamento = tipoErroProcessamento;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
}
