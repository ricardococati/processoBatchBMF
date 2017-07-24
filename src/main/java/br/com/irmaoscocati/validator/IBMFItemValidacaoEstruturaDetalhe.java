package br.com.irmaoscocati.validator;

import br.com.irmaoscocati.dto.BMFResumoOperacoesDTO;
import br.com.irmaoscocati.exception.BMFItemInvalidoException;

public interface IBMFItemValidacaoEstruturaDetalhe {

	public void validarDominioDetalhe(BMFResumoOperacoesDTO bmf) throws BMFItemInvalidoException, Exception;
	public void validarTipagemDetalhe(BMFResumoOperacoesDTO bmf) throws BMFItemInvalidoException, Exception;

}
