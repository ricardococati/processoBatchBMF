package br.com.irmaoscocati.layout;

import java.util.Arrays;

import org.springframework.batch.item.file.transform.Range;

public class BMFLayOutHeader {

	private String[] strArrCampos;
	private Range[] numArrRanges;
	private static final String TIPO_REGISTRO = "tipoRegistro";
	private static final Range RANGE_TIPO_REGISTRO = new Range(1,2);
	private static final String DATA_PROCESSAMENTO = "dataProcessamento";
	private static final Range RANGE_DATA_PROCESSAMENTO = new Range(12,19);
	private static final String NUMERO_SEQUENCIAL = "numeroSequencial";
	private static final Range RANGE_NUMERO_SEQUENCIAL = new Range(36,42);
	private static final String EMPRESA_ADQUIRENTE = "empresaAdquirente";
	private static final Range RANGE_EMPRESA_ADQUIRENTE = new Range(43,47);
	private static final String OPCAO_DE_EXTRATO = "opcaoDeExtrato";
	private static final Range RANGE_OPCAO_DE_EXTRATO = new Range(48, 49);
	private static final String DADO_DESCARTE = "dadoDescarte";
	private static final Range RANGE_DADO_DESCARTE = new Range(50,250);

	public BMFLayOutHeader() {
		super();
		this.strArrCampos = new String[] { //
				TIPO_REGISTRO, //
				DATA_PROCESSAMENTO, //
				NUMERO_SEQUENCIAL, //
				EMPRESA_ADQUIRENTE, //
				OPCAO_DE_EXTRATO,//
				DADO_DESCARTE//
				};
		this.numArrRanges = new Range[] { //
				RANGE_TIPO_REGISTRO, //
				RANGE_DATA_PROCESSAMENTO, //
				RANGE_NUMERO_SEQUENCIAL, //
				RANGE_EMPRESA_ADQUIRENTE, //
				RANGE_OPCAO_DE_EXTRATO,//
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
		BMFLayOutHeader other = (BMFLayOutHeader) obj;
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
