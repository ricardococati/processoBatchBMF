package br.com.irmaoscocati.validator;

import br.com.irmaoscocati.dto.BMFResumoOperacoesDTO;
import br.com.irmaoscocati.exception.BMFItemInvalidoException;

public interface IBMFItemValidacaoNegocio {
	
	public void validaRegrasNegocioArquivo(BMFResumoOperacoesDTO bmf) throws BMFItemInvalidoException, Exception;
	
}
