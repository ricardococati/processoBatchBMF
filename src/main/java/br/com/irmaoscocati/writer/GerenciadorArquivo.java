package br.com.irmaoscocati.writer;

import org.springframework.stereotype.Service;

@Service
public class GerenciadorArquivo {

	private boolean arquivoValido = true;

	public boolean isArquivoValido() {
		return arquivoValido;
	}

	public void setArquivoValido(boolean arquivoValido) {
		this.arquivoValido = arquivoValido;
	}


}
