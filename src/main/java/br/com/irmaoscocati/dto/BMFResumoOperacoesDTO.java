package br.com.irmaoscocati.dto;

public class BMFResumoOperacoesDTO {

	private String tipoRegistro;
	private String numeroPV;
	private String filler;
	private String parcela;
	private String tipoDeTransacao;
	private String dtaApresentacao;
	private String dtaPrevistaPagto;
	private String vlrLiquido;
	private String vlrBruto;
	private String codigoProduto;
	private String dadoDescarte;
	private String nomeArquivo;

	/**
	 * @param tipoRegistro : Constante “1” - Identifica o tipo de registro detalhe do RO.
	 *
	 * @param numeroPV : NÚMERO DO PONTO DE VENDA na BMF chega como o NÚMERO DO ESTABELECIMENTO,
	 * cadastrado na VAN para o recebimento do arquivo.
	 *
	 * @param filler : “/” = para vendas parceladas, “a” = aceleração das parcelas, “ “ = demais situações.
	 *
	 * @param parcela : No caso de venda parcelada, será formatado com o número da parcela que está
	 * sendo liberado na data do envio do arquivo.
     * No caso de venda à vista, será formatado com brancos.
	 *
	 * @param tipoDeTransacao : Código que identifica a transação - vide Tabela II.
	 *
	 * @param dtaApresentacao : AAMMDD - Data em que o RO/Lote de vendas foi transmitido para a BMF.
	 *
	 * @param dtaPrevistaPagto : AAMMDD - Data prevista de pagamento.
	 * Na recuperação, pode ser atualizada após o processamento da transação ou ajuste.
	 *
	 * @param vlrLiquido : Valor das vendas deduzido o valor da comissão.
	 *
	 * @param vlrBruto : Somatória dos valores de venda para EC/lote.
	 *
	 * @param codigoProduto : Código da bandeira do cartão.
	 *
	 * @param dadoDescarte : Dados de descarte, essa informação é importante para saber se a linha foi finalizada.
	 *           No entanto essa informação não é carregada no banco de dados.
	 */
	public BMFResumoOperacoesDTO() {
		super();
	}

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public String getNumeroPV() {
		return numeroPV;
	}

	public void setNumeroPV(String numeroPV) {
		this.numeroPV = numeroPV;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public String getParcela() {
		return parcela;
	}

	public void setParcela(String parcela) {
		this.parcela = parcela;
	}

	public String getTipoDeTransacao() {
		return tipoDeTransacao;
	}

	public void setTipoDeTransacao(String tipoDeTransacao) {
		this.tipoDeTransacao = tipoDeTransacao;
	}

	public String getDtaApresentacao() {
		return dtaApresentacao;
	}

	public void setDtaApresentacao(String dtaApresentacao) {
		this.dtaApresentacao = dtaApresentacao;
	}

	public String getDtaPrevistaPagto() {
		return dtaPrevistaPagto;
	}

	public void setDtaPrevistaPagto(String dtaPrevistaPagto) {
		this.dtaPrevistaPagto = dtaPrevistaPagto;
	}

	public String getVlrLiquido() {
		return vlrLiquido;
	}

	public void setVlrLiquido(String vlrLiquido) {
		this.vlrLiquido = vlrLiquido;
	}

	public String getVlrBruto() {
		return vlrBruto;
	}

	public void setVlrBruto(String vlrBruto) {
		this.vlrBruto = vlrBruto;
	}

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
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
		result = prime * result + ((codigoProduto == null) ? 0 : codigoProduto.hashCode());
		result = prime * result + ((dadoDescarte == null) ? 0 : dadoDescarte.hashCode());
		result = prime * result + ((dtaApresentacao == null) ? 0 : dtaApresentacao.hashCode());
		result = prime * result + ((dtaPrevistaPagto == null) ? 0 : dtaPrevistaPagto.hashCode());
		result = prime * result + ((filler == null) ? 0 : filler.hashCode());
		result = prime * result + ((parcela == null) ? 0 : parcela.hashCode());
		result = prime * result + ((tipoDeTransacao == null) ? 0 : tipoDeTransacao.hashCode());
		result = prime * result + ((nomeArquivo == null) ? 0 : nomeArquivo.hashCode());
		result = prime * result + ((numeroPV == null) ? 0 : numeroPV.hashCode());
		result = prime * result + ((tipoRegistro == null) ? 0 : tipoRegistro.hashCode());
		result = prime * result + ((vlrBruto == null) ? 0 : vlrBruto.hashCode());
		result = prime * result + ((vlrLiquido == null) ? 0 : vlrLiquido.hashCode());
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
		BMFResumoOperacoesDTO other = (BMFResumoOperacoesDTO) obj;
		if (codigoProduto == null) {
			if (other.codigoProduto != null)
				return false;
		} else if (!codigoProduto.equals(other.codigoProduto))
			return false;
		if (getDadoDescarte() == null) {
			if (other.getDadoDescarte() != null)
				return false;
		} else if (!getDadoDescarte().equals(other.getDadoDescarte()))
			return false;
		if (dtaApresentacao == null) {
			if (other.dtaApresentacao != null)
				return false;
		} else if (!dtaApresentacao.equals(other.dtaApresentacao))
			return false;
		if (dtaPrevistaPagto == null) {
			if (other.dtaPrevistaPagto != null)
				return false;
		} else if (!dtaPrevistaPagto.equals(other.dtaPrevistaPagto))
			return false;
		if (filler == null) {
			if (other.filler != null)
				return false;
		} else if (!filler.equals(other.filler))
			return false;
		if (parcela == null) {
			if (other.parcela != null)
				return false;
		} else if (!parcela.equals(other.parcela))
			return false;
		if (tipoDeTransacao == null) {
			if (other.tipoDeTransacao != null)
				return false;
		} else if (!tipoDeTransacao.equals(other.tipoDeTransacao))
			return false;
		if (nomeArquivo == null) {
			if (other.nomeArquivo != null)
				return false;
		} else if (!nomeArquivo.equals(other.nomeArquivo))
			return false;
		if (numeroPV == null) {
			if (other.numeroPV != null)
				return false;
		} else if (!numeroPV.equals(other.numeroPV))
			return false;
		if (tipoRegistro == null) {
			if (other.tipoRegistro != null)
				return false;
		} else if (!tipoRegistro.equals(other.tipoRegistro))
			return false;
		if (vlrBruto == null) {
			if (other.vlrBruto != null)
				return false;
		} else if (!vlrBruto.equals(other.vlrBruto))
			return false;
		if (vlrLiquido == null) {
			if (other.vlrLiquido != null)
				return false;
		} else if (!vlrLiquido.equals(other.vlrLiquido))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BMFResumoOperacoesDTO [" +
				"tipoRegistro=" + tipoRegistro +
				", numeroPV=" + numeroPV +
				", filler=" + filler +
				", parcela=" + parcela +
				", tipoDeTransacao=" + tipoDeTransacao +
				", dtaApresentacao=" + dtaApresentacao +
				", dtaPrevistaPagto=" + dtaPrevistaPagto +
				", vlrLiquido=" + vlrLiquido +
				", vlrBruto=" + vlrBruto +
				", codigoProduto=" + codigoProduto +
				", dadoDescarte=" + dadoDescarte +
				", nomeArquivo=" + nomeArquivo + "]";
	}

}
