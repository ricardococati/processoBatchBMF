package br.com.irmaoscocati.dto;

public class BMFHeaderDTO {

	private String tipoRegistro;
	private String dataProcessamento;
	private String numeroSequencial;
	private String empresaAdquirente;
	private String opcaoDeExtrato;
	private String dadoDescarte;
	private String nomeArquivo;

	public BMFHeaderDTO() {
		super();
	}

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public String getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(String dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	public String getNumeroSequencial() {
		return numeroSequencial;
	}

	public void setNumeroSequencial(String numeroSequencial) {
		this.numeroSequencial = numeroSequencial;
	}

	public String getEmpresaAdquirente() {
		return empresaAdquirente;
	}

	public void setEmpresaAdquirente(String empresaAdquirente) {
		this.empresaAdquirente = empresaAdquirente;
	}

	public String getOpcaoDeExtrato() {
		return opcaoDeExtrato;
	}

	public void setOpcaoDeExtrato(String opcaoDeExtrato) {
		this.opcaoDeExtrato = opcaoDeExtrato;
	}

	public String getDadoDescarte() {
		return dadoDescarte;
	}

	public void setDadoDescarte(String dadoDescarte) {
		this.dadoDescarte = dadoDescarte;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataProcessamento == null) ? 0 : dataProcessamento.hashCode());
		result = prime * result + ((empresaAdquirente == null) ? 0 : empresaAdquirente.hashCode());
		result = prime * result + ((numeroSequencial == null) ? 0 : numeroSequencial.hashCode());
		result = prime * result + ((opcaoDeExtrato == null) ? 0 : opcaoDeExtrato.hashCode());
		result = prime * result + ((tipoRegistro == null) ? 0 : tipoRegistro.hashCode());
		result = prime * result + ((dadoDescarte == null) ? 0 : dadoDescarte.hashCode());
		result = prime * result + ((nomeArquivo == null) ? 0 : nomeArquivo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BMFHeaderDTO other = (BMFHeaderDTO) obj;
		if (dataProcessamento == null) {
			if (other.dataProcessamento != null)
				return false;
		} else if (!dataProcessamento.equals(other.dataProcessamento))
			return false;
		if (empresaAdquirente == null) {
			if (other.empresaAdquirente != null)
				return false;
		} else if (!empresaAdquirente.equals(other.empresaAdquirente))
			return false;
		if (numeroSequencial == null) {
			if (other.numeroSequencial != null)
				return false;
		} else if (!numeroSequencial.equals(other.numeroSequencial))
			return false;
		if (opcaoDeExtrato == null) {
			if (other.opcaoDeExtrato != null)
				return false;
		} else if (!opcaoDeExtrato.equals(other.opcaoDeExtrato))
			return false;
		if (tipoRegistro == null) {
			if (other.tipoRegistro != null)
				return false;
		} else if (!tipoRegistro.equals(other.tipoRegistro))
			return false;
		if (dadoDescarte == null) {
			if (other.dadoDescarte != null)
				return false;
		} else if (!dadoDescarte.equals(other.dadoDescarte))
			return false;
		if (nomeArquivo == null) {
			if (other.nomeArquivo != null)
				return false;
		} else if (!nomeArquivo.equals(other.nomeArquivo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BMFHeaderDTO ["+
				"tipoRegistro=" + tipoRegistro +
				", dataProcessamento=" + dataProcessamento +
				", numeroSequencial=" + numeroSequencial +
				", empresaAdquirente=" + empresaAdquirente +
				", opcaoDeExtrato=" + opcaoDeExtrato +
				", dadoDescarte=" + dadoDescarte +
				", nomeArquivo=" + nomeArquivo +
				"]";
	}

}