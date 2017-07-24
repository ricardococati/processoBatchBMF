package br.com.irmaoscocati.entidade;

public enum TipoErroProcessamentoEnum {
	
	ESTRUTURAL(1, "Estrutural"),//
	NEGOCIO(2, "Negocio");

	private Integer codigo;
	private String descricaoErro;

	private TipoErroProcessamentoEnum(int codigo, String descricaoErro) {
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
