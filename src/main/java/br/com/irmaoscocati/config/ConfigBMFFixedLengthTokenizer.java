package br.com.irmaoscocati.config;

import org.springframework.stereotype.Component;

import br.com.irmaoscocati.layout.BMFLayOutDetalhe;
import br.com.irmaoscocati.layout.BMFLayOutHeader;
import br.com.irmaoscocati.tokenizer.BMFFixedLengthTokenizer;

@Component
public class ConfigBMFFixedLengthTokenizer {

	public BMFFixedLengthTokenizer obterTokenizerTamanhoFixoDetalhe() {
		BMFFixedLengthTokenizer lineTokenizer = new BMFFixedLengthTokenizer();
		BMFLayOutDetalhe layout = new BMFLayOutDetalhe();
		lineTokenizer.setNames(layout.getStrArrCampos());
		lineTokenizer.setColumns(layout.getNumArrRanges());
		return lineTokenizer;
	}

	public BMFFixedLengthTokenizer obterTokenizerTamanhoFixoHeader() {
		BMFFixedLengthTokenizer lineTokenizer = new BMFFixedLengthTokenizer();
		BMFLayOutHeader layout = new BMFLayOutHeader();
		lineTokenizer.setNames(layout.getStrArrCampos());
		lineTokenizer.setColumns(layout.getNumArrRanges());
		return lineTokenizer;
	}

}
