package br.com.irmaoscocati.util;

public enum CodigoErroProcessamentoEnum {

	CODIGO_ERRO_GENERICO(0, "Código de Erro Generico"),
	TIPO_REGISTRO_INVALIDO(1, "Tipo de registro inválido"),//
	FILLER_INVALIDO(2, "Filler inválido"),//
	TIPO_TRANSACAO_INVALIDA(1, "Tipo de transação inválida"),//
	VENDA_INVALIDA(1, "Venda inválida"),//
	USUARIO_ACCESSTAGE_OBRIGATORIO(2, "Campo Usuário Accesstage é obrigatório."),//
	NUMERO_PV_INVALIDO(3, "Numero do PV inválido"), //
	CODIGO_PRODUTO_INVALIDO(4, "Código do produto inexistente"),//
	CAMPO_FORMATO_INVALIDO(5, "Formato do campo inválido"),//
	CAMPO_DOMINIO_VALORES_INVALIDO(6, "Dominio de valores do campo inválido");


	private Integer codigo;
	private String descricaoErro;

	private CodigoErroProcessamentoEnum(int codigo, String descricaoErro) {
		this.codigo = codigo;
		this.descricaoErro = descricaoErro;
	}

	public String getDescricaoErro() {
		return descricaoErro;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public void setDescricaoErro(String descricaoErro) {
		this.descricaoErro = descricaoErro;
	}

}
