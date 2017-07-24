package br.com.irmaoscocati.exception;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.exception.ExceptionHandler;

import br.com.irmaoscocati.util.CaminhoArquivoEnum;

public class ErroGenericoException implements ExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(ErroGenericoException.class);
	private String mensagemErro;
	private static final Pattern EXPRESSAO_REGULAR = Pattern.compile("^.*\\[.*\\[.*\\/execucao\\/(.*)\\]\\].*$");

	public ErroGenericoException(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void handleException(RepeatContext arg0, Throwable arg1) throws Throwable {
		Matcher matchStr = EXPRESSAO_REGULAR.matcher(arg1.getMessage());
		if(matchStr.find()){
			String mensagem = matchStr.group(1);
			moveArquivoDiretorio(mensagem);
		}
	}

	public void moveArquivoDiretorio(final String nomeArquivo) {
		String caminhoSaida = CaminhoArquivoEnum.CAMINHO_ARQUIVO_ERRO.getCaminho();

        File dirSaida = new File(caminhoSaida);

    	File arquivos = new File("");

	    arquivos.renameTo(new File(dirSaida, nomeArquivo));

	    log.info("Arquivo foi movido com sucesso: " + arquivos.getName());
	}

}