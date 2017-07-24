package br.com.irmaoscocati.layout;

import java.util.Arrays;

import org.springframework.batch.item.file.transform.Range;

public class BMFLayOutDetalhe {

	private String[] strArrCampos;
	private Range[] numArrRanges;
	private static final String TIPO_REGISTRO = "tipoRegistro";
	private static final Range RANGE_TIPO_REGISTRO = new Range(1,1);
	private static final String NUMERO_PV = "numeroPV";
	private static final Range RANGE_NUMERO_PV = new Range(2,11);
	private static final String PARCELA = "parcela";
	private static final Range RANGE_PARCELA = new Range(19,20);
	private static final String FILLER = "filler";
	private static final Range RANGE_FILLER = new Range(21,21);
	private static final String TIPO_DE_TRANSACAO = "tipoDeTransacao";
	private static final Range RANGE_TIPO_DE_TRANSACAO = new Range(24,25);
	private static final String DTA_APRESENTACAO = "dtaApresentacao";
	private static final Range RANGE_DTA_APRESENTACAO = new Range(26,31);
	private static final String DTA_PREVISTA_PAGTO = "dtaPrevistaPagto";
	private static final Range RANGE_DTA_PREVISTA_PAGTO = new Range(32,37);
	private static final String VLR_BRUTO = "vlrBruto";
	private static final Range RANGE_VLR_BRUTO = new Range(44,57);
	private static final String VLR_LIQUIDO = "vlrLiquido";
	private static final Range RANGE_VLR_LIQUIDO = new Range(86,99);
	private static final String CODIGO_PRODUTO = "codigoProduto";
	private static final Range RANGE_CODIGO_PRODUTO = new Range(233,235);
	private static final String DADO_DESCARTE = "dadoDescarte";
	private static final Range RANGE_DADO_DESCARTE = new Range(249,250);

	public BMFLayOutDetalhe() {
		super();
		this.strArrCampos = new String[] { //
				TIPO_REGISTRO, //
				NUMERO_PV, //
				PARCELA,//
				FILLER, //
				TIPO_DE_TRANSACAO,//
				DTA_APRESENTACAO, //
				DTA_PREVISTA_PAGTO, //
				VLR_BRUTO, //
				VLR_LIQUIDO, //
				CODIGO_PRODUTO, //
				DADO_DESCARTE//
				};
		this.numArrRanges = new Range[] { //
				RANGE_TIPO_REGISTRO, //
				RANGE_NUMERO_PV, //
				RANGE_PARCELA, //
				RANGE_FILLER, //
				RANGE_TIPO_DE_TRANSACAO, //
				RANGE_DTA_APRESENTACAO, //
				RANGE_DTA_PREVISTA_PAGTO, //
				RANGE_VLR_BRUTO, //
				RANGE_VLR_LIQUIDO, //
				RANGE_CODIGO_PRODUTO, //
				RANGE_DADO_DESCARTE//
				};
	}

	public String[] getStrArrCampos() {
		return strArrCampos;
	}

	public Range[] getNumArrRanges() {
		return numArrRanges;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(numArrRanges);
		result = prime * result + Arrays.hashCode(strArrCampos);
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
		BMFLayOutDetalhe other = (BMFLayOutDetalhe) obj;
		if (!Arrays.equals(numArrRanges, other.numArrRanges))
			return false;
		if (!Arrays.equals(strArrCampos, other.strArrCampos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LayOutSDXResumoVendas [strArrCampos=" + Arrays.toString(strArrCampos) + ", numArrRanges=" + Arrays.toString(numArrRanges) + "]";
	}

}
