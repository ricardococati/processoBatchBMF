package br.com.irmaoscocati.validator;

import br.com.irmaoscocati.dto.BMFHeaderDTO;
import br.com.irmaoscocati.exception.BMFItemInvalidoException;

public interface IBMFItemValidacaoEstruturaHeader {

	public void validarDominioHeader(BMFHeaderDTO bmf) throws BMFItemInvalidoException, Exception;
	public void validarTipagemHeader(BMFHeaderDTO bmf) throws BMFItemInvalidoException, Exception;

}
