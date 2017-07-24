package br.com.irmaoscocati;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import br.com.irmaoscocati.config.ProcessoBatchBMF;
import br.com.irmaoscocati.util.CaminhoArquivoEnum;
import br.com.irmaoscocati.util.MoveArquivo;

@SpringBootApplication
public class ApplicationBMF {

	private static final Logger log = LoggerFactory.getLogger(ApplicationBMF.class);

	//private boolean arquivoValido = true;

	public static void main(String args[]) {
		try {
			final MoveArquivo move = new MoveArquivo();
			File arquivosDiretorioOrigem = new File(CaminhoArquivoEnum.CAMINHO_ARQUIVO_ENTRADA.getCaminho());
			File diretorioDestino = new File(CaminhoArquivoEnum.CAMINHO_ARQUIVO_EXECUCAO.getCaminho());

			File arrayArquivos[] = arquivosDiretorioOrigem.listFiles();

			File arquivo;

			int i = 0;

			if (arrayArquivos.length > 0) {
				for (int j = arrayArquivos.length; i < j; i++) {
					SpringApplication springApp = new SpringApplication(ProcessoBatchBMF.class);

					arquivo = arrayArquivos[i];
					move.moveArquivoPorArquivo(diretorioDestino, arquivo);
					springApp.setWebEnvironment(false);
					ConfigurableApplicationContext ctx = springApp.run(args);

					ctx.close();
				}
			} else {
				log.info("Não foram encontrados arquivos no diretório: " + CaminhoArquivoEnum.CAMINHO_ARQUIVO_ENTRADA.getCaminho());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
