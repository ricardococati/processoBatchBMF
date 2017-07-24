package br.com.irmaoscocati.decider;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.irmaoscocati.writer.GerenciadorArquivo;

@Component
public class OrquestraFluxoProcessamentoHeaderDecider implements JobExecutionDecider{

	@Autowired
	private GerenciadorArquivo gerenciadorArquivo;

	@Override
	public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
		if(gerenciadorArquivo.isArquivoValido()){
			return FlowExecutionStatus.COMPLETED;
		} else {
			return FlowExecutionStatus.FAILED;
		}
	}

}
