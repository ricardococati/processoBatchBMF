package br.com.irmaoscocati.tasklet;

import java.io.FileNotFoundException;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class IniciaProcessoTasklet implements Tasklet {

	public IniciaProcessoTasklet() {
		super();
	}

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws FileNotFoundException {
    	return RepeatStatus.FINISHED;
	}

}
