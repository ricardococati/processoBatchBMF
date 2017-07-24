package br.com.irmaoscocati.tasklet;

import java.io.FileNotFoundException;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import br.com.irmaoscocati.util.CaminhoArquivoEnum;
import br.com.irmaoscocati.util.MoveArquivo;

public class MoveArquivosTasklet implements Tasklet {

	private CaminhoArquivoEnum caminhoEntrada;
	private CaminhoArquivoEnum caminhoSaida;

	public MoveArquivosTasklet(CaminhoArquivoEnum caminhoEntrada, CaminhoArquivoEnum caminhoSaida) {
		super();
		this.caminhoEntrada = caminhoEntrada;
		this.caminhoSaida = caminhoSaida;
	}

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws FileNotFoundException {
		final MoveArquivo moveArquivo = new MoveArquivo();
		moveArquivo.moveArquivoParaDiretorioDestino(getCaminhoEntrada().getCaminho(), getCaminhoSaida().getCaminho());
    	return RepeatStatus.FINISHED;
	}

	public CaminhoArquivoEnum getCaminhoEntrada() {
		return caminhoEntrada;
	}

	public void setCaminhoEntrada(CaminhoArquivoEnum caminhoEntrada) {
		this.caminhoEntrada = caminhoEntrada;
	}

	public CaminhoArquivoEnum getCaminhoSaida() {
		return caminhoSaida;
	}

	public void setCaminhoSaida(CaminhoArquivoEnum caminhoSaida) {
		this.caminhoSaida = caminhoSaida;
	}

}
