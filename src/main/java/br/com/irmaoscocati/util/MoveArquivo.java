package br.com.irmaoscocati.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoveArquivo {

	private static final Logger log = LoggerFactory.getLogger(MoveArquivo.class);

	public void moveArquivoParaDiretorioDestino(final String caminhoOrigem, final String caminhoDestino) {
		File arquivosDiretorioOrigem = new File(caminhoOrigem);
        File diretorioDestino = new File(caminhoDestino);

    	File arrayArquivos[] = arquivosDiretorioOrigem.listFiles();

    	File arquivo;

    	int i = 0;

    	if(arrayArquivos.length > 0){
	    	for (int j = arrayArquivos.length; i < j; i++) {
	    		arquivo = arrayArquivos[i];
	    		moveArquivoPorArquivo(diretorioDestino, arquivo);
	    	}
    	} else{
    		log.info("Não foram encontrados arquivos no diretório: " + caminhoOrigem);
    	}
	}

	public void moveArquivoPorArquivo(File diretorioDestino, File arquivo) {
		arquivo.renameTo(new File(diretorioDestino, arquivo.getName()));
		log.info("Arquivo foi movido com sucesso: " + arquivo.getName());
	}

}
