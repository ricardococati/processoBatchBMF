package br.com.irmaoscocati.tasklet;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.core.task.TaskTimeoutException;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class ExecutaProcedureETLTasklet implements Tasklet {

	private static final String ESQUEMA_EXECUCAO_PROC = "bmf";
	private static final String NOME_PROC = "prcProcessBMF";
	private static final String NOME_ARGUMENTO_PROC = "ptsProcesso";
	private static final Logger log = LoggerFactory.getLogger(ExecutaProcedureETLTasklet.class);

	private DataSource datasource;

	private SimpleJdbcCall simpleJdbcCall;

	public ExecutaProcedureETLTasklet(DataSource datasource) {
		this.datasource = datasource;
	}

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception, TaskRejectedException, TaskTimeoutException {

		log.info("Inicia execução da procedure");
		simpleJdbcCall = new SimpleJdbcCall(getDataSource());

		log.info("Esquema de execução da PROC: " + ESQUEMA_EXECUCAO_PROC);
		simpleJdbcCall.withSchemaName(ESQUEMA_EXECUCAO_PROC);

		log.info("Nome da PROC: " + NOME_PROC);
		simpleJdbcCall.withProcedureName(NOME_PROC);

		log.info("Nome argumento da PROC: " + NOME_ARGUMENTO_PROC);
		simpleJdbcCall.addDeclaredParameter(new SqlOutParameter(NOME_ARGUMENTO_PROC, java.sql.Types.INTEGER));

		simpleJdbcCall.withoutProcedureColumnMetaDataAccess();

		log.info("Executa procedure");
		Map<String, Object> out =  new HashMap<String, Object>();

		try {
			out = simpleJdbcCall.execute();
		} catch (Exception except) {
			except.printStackTrace();
			throw except;
		}

		log.info("FIM!!!! Procedure executada!!" + out);

		return RepeatStatus.FINISHED;
	}

	public DataSource getDataSource() {
		return datasource;
	}

}
