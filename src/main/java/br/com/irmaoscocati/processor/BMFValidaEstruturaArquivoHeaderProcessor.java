package br.com.irmaoscocati.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.irmaoscocati.config.ProcessoBatchBMF;
import br.com.irmaoscocati.dto.BMFHeaderDTO;
import br.com.irmaoscocati.validator.IBMFItemValidacaoEstruturaHeader;

public class BMFValidaEstruturaArquivoHeaderProcessor implements ItemProcessor<BMFHeaderDTO, BMFHeaderDTO> {

	@Autowired
	IBMFItemValidacaoEstruturaHeader bmfItemValidacaoEstrutura;

	@Autowired
	private ProcessoBatchBMF processoBatchBMF;

	@Override
	public BMFHeaderDTO process(BMFHeaderDTO bmf) throws Exception {
		bmf.setNomeArquivo(processoBatchBMF.getNomeArquivo());
		bmfItemValidacaoEstrutura.validarDominioHeader(bmf);
		bmfItemValidacaoEstrutura.validarTipagemHeader(bmf);
		return null;
	}

}
