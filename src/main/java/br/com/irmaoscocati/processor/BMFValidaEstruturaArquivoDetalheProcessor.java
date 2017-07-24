package br.com.irmaoscocati.processor;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.irmaoscocati.dto.BMFResumoOperacoesDTO;
import br.com.irmaoscocati.exception.BMFItemInvalidoException;
import br.com.irmaoscocati.validator.IBMFItemValidacaoEstruturaDetalhe;

@Service
@JobScope
public class BMFValidaEstruturaArquivoDetalheProcessor implements ItemProcessor<BMFResumoOperacoesDTO, BMFResumoOperacoesDTO> {

	@Autowired
	IBMFItemValidacaoEstruturaDetalhe bmfItemValidacaoEstrutura;

	public BMFResumoOperacoesDTO process(BMFResumoOperacoesDTO bmf) throws BMFItemInvalidoException, Exception {
		bmfItemValidacaoEstrutura.validarTipagemDetalhe(bmf);
		bmfItemValidacaoEstrutura.validarDominioDetalhe(bmf);
		return null;
	}

}
