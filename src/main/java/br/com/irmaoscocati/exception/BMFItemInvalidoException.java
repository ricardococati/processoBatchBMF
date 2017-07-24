package br.com.irmaoscocati.exception;

import br.com.irmaoscocati.util.CodigoErroProcessamentoEnum;

public class BMFItemInvalidoException extends Exception {

	private CodigoErroProcessamentoEnum codigoErroProcessamentoEnum;

	private String descricaoErro;

	private static final long serialVersionUID = -3127644245933417138L;

	public BMFItemInvalidoException(CodigoErroProcessamentoEnum codigoErroProcessamentoEnum, String descricaoErro) {
		this.codigoErroProcessamentoEnum = codigoErroProcessamentoEnum;
		this.descricaoErro = descricaoErro;
	}

	public CodigoErroProcessamentoEnum getCodigoErroProcessamentoEnum() {
		return codigoErroProcessamentoEnum;
	}

	public String getDescricaoErro() {
		return descricaoErro;
	}



}
