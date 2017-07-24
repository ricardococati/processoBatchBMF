package br.com.irmaoscocati.config;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import br.com.irmaoscocati.dao.IErroProcessamentoDAO;
import br.com.irmaoscocati.decider.OrquestraFluxoProcessamentoDetalheDecider;
import br.com.irmaoscocati.decider.OrquestraFluxoProcessamentoHeaderDecider;
import br.com.irmaoscocati.dto.BMFHeaderDTO;
import br.com.irmaoscocati.dto.BMFResumoOperacoesDTO;
import br.com.irmaoscocati.entidade.ErroProcessamento;
import br.com.irmaoscocati.entidade.TipoErroProcessamentoEnum;
import br.com.irmaoscocati.listener.JobCompletionNotificationListener;
import br.com.irmaoscocati.processor.BMFItemDetalheProcessor;
import br.com.irmaoscocati.processor.BMFValidaEstruturaArquivoDetalheProcessor;
import br.com.irmaoscocati.processor.BMFValidaEstruturaArquivoHeaderProcessor;
import br.com.irmaoscocati.tasklet.ExecutaProcedureETLTasklet;
import br.com.irmaoscocati.tasklet.MoveArquivosTasklet;
import br.com.irmaoscocati.tokenizer.BMFFixedLengthTokenizer;
import br.com.irmaoscocati.util.CaminhoArquivoEnum;
import br.com.irmaoscocati.util.CodigoErroProcessamentoEnum;
import br.com.irmaoscocati.util.ScriptUtil;

@Configuration
@ComponentScan("br.com.irmaoscocati")
@EnableCaching
@EnableBatchProcessing
@PropertySource("file:/data/bmf-batch/bmf/config/application.properties")
public class ProcessoBatchBMF {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private IErroProcessamentoDAO erroProcessamentoDAO;

	@Autowired
	private DataSource datasource;

	@Autowired
	private ConfigBMFFixedLengthTokenizer configBMFFixedLengthTokenizer;

	@Autowired
	private ConfigTipoObjetoMapper configTipoObjetoMapper;

	@Autowired
	private ConfigArquivo configArquivo;

	@Autowired
	private ConfigMapperDePara configMapperDePara;

	@Autowired
	private ConfigLeituraUnicoArquivo configLeituraUnicoArquivo;

	@Autowired
	private ConfigLeituraMultiplosArquivos configLeituraMultiplosArquivos;

	@Autowired
	private ScriptUtil scriptUtil;

	private String nomeArquivo;

	@Bean
	public MultiResourceItemReader<BMFHeaderDTO> readerHeader() {
		MultiResourceItemReader<BMFHeaderDTO> multiResourceItemReader = null;

		try {

			/* Arquivos */
			Resource[] resources = configArquivo.obterArquivo();

			obterNomeDoArquivo(resources);

			/* Mapeamento */
			BMFFixedLengthTokenizer lineTokenizer = configBMFFixedLengthTokenizer.obterTokenizerTamanhoFixoHeader();

			/* Tipo de Objeto */
			BeanWrapperFieldSetMapper<BMFHeaderDTO> fieldSetMapper = configTipoObjetoMapper.obterTipoObjetoHeader();

			/* Line Mapper: De Para */
			DefaultLineMapper<BMFHeaderDTO> lineMapper = configMapperDePara.obterMapperDeParaHeader(lineTokenizer, fieldSetMapper);

			/* Lê arquivo individualmente */
			FlatFileItemReader<BMFHeaderDTO> flatFileItemReader = configLeituraUnicoArquivo.obterLeituraUnicoArquivoHeader(lineMapper);

			/* Lê múltiplos arquivos e delega pro FlatFile */
			multiResourceItemReader = configLeituraMultiplosArquivos.obterLeituraMultiplosArquivosHeader(resources, flatFileItemReader);

		} catch (Exception e) {
			insereErro(e);
		}

		return multiResourceItemReader;
	}

	@Bean
	public MultiResourceItemReader<BMFResumoOperacoesDTO> readerDetalhe() {
		MultiResourceItemReader<BMFResumoOperacoesDTO> multiResourceItemReader = null;

		try {

			/* Arquivos */
			Resource[] resources = configArquivo.obterArquivo();

			obterNomeDoArquivo(resources);

			/* Mapeamento */
			BMFFixedLengthTokenizer lineTokenizer = configBMFFixedLengthTokenizer.obterTokenizerTamanhoFixoDetalhe();

			/* Tipo de Objeto */
			BeanWrapperFieldSetMapper<BMFResumoOperacoesDTO> fieldSetMapper = configTipoObjetoMapper.obterTipoObjetoDetalhe();

			/* Line Mapper: De Para */
			DefaultLineMapper<BMFResumoOperacoesDTO> lineMapper = configMapperDePara.obterMapperDeParaDetalhe(lineTokenizer, fieldSetMapper);

			/* Lê arquivo individualmente */
			FlatFileItemReader<BMFResumoOperacoesDTO> flatFileItemReader = configLeituraUnicoArquivo.obterLeituraUnicoArquivoDetalhe(lineMapper);

			/* Lê múltiplos arquivos e delega pro FlatFile */
			multiResourceItemReader = configLeituraMultiplosArquivos.obterLeituraMultiplosArquivosDetalhe(resources, flatFileItemReader);

		} catch (Exception e) {
			insereErro(e);
		}

		return multiResourceItemReader;
	}

	@Bean
	public JdbcBatchItemWriter<BMFResumoOperacoesDTO> writer() {
		JdbcBatchItemWriter<BMFResumoOperacoesDTO> writer = new JdbcBatchItemWriter<BMFResumoOperacoesDTO>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<BMFResumoOperacoesDTO>());
		writer.setSql(scriptUtil.obterInsertTabelaBMFVendaPagto(this.nomeArquivo));
		writer.setDataSource(datasource);
		return writer;
	}

	@Bean
	public BMFItemDetalheProcessor processorDetalhe() {
		return new BMFItemDetalheProcessor();
	}

	@Bean
	public BMFValidaEstruturaArquivoHeaderProcessor processorValidaEstruturaArquivoHeader() {
		return new BMFValidaEstruturaArquivoHeaderProcessor();
	}

	@Bean
	public BMFValidaEstruturaArquivoDetalheProcessor processorValidaEstruturaArquivoDetalhe() {
		return new BMFValidaEstruturaArquivoDetalheProcessor();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionNotificationListener();
	}

	@Bean
	public Job jobBMF(@Qualifier("step0_ValidaEstruturaArquivoHeader") Step step0ValidaEstruturaArquivoHeader,
			@Qualifier("step1_ValidaEstruturaArquivoDetalhe") Step step1ValidaEstruturaArquivoDetalhe,
			@Qualifier("step2_LeituraProcessamentoEscritaArquivo") Step step2LeituraProcessamentoEscritaArquivo,
			@Qualifier("step3_MoveArquivosDiretorioExecucaoParaSaida") Step step3MoveArquivosDiretorioExecucaoParaSaida,
			@Qualifier("step4_ExcutaProcedureETL") Step step4ExcutaProcedureETL,
			OrquestraFluxoProcessamentoDetalheDecider orquestraFluxoProcessamentoDetalhe,
			OrquestraFluxoProcessamentoHeaderDecider orquestraFluxoProcessamentoHeader) {

		Date data = new Date();

		FlowBuilder<Flow> flowBuilder = new FlowBuilder<Flow>("flowJobBMF" + data);

		Flow flow = flowBuilder
					.start(step0ValidaEstruturaArquivoHeader)
					.next(orquestraFluxoProcessamentoHeader)
					.on(FlowExecutionStatus.COMPLETED.getName()).to(step1ValidaEstruturaArquivoDetalhe)
						.from(orquestraFluxoProcessamentoHeader)
					.on(FlowExecutionStatus.FAILED.getName()).to(step5_MoveArquivosDiretorioExecucaoParaErro())
						.next(step1ValidaEstruturaArquivoDetalhe)
						.next(orquestraFluxoProcessamentoDetalhe)
							.on(FlowExecutionStatus.COMPLETED.getName()).to(step2LeituraProcessamentoEscritaArquivo)
									.next(step3MoveArquivosDiretorioExecucaoParaSaida)
									.next(step4ExcutaProcedureETL)
								.from(orquestraFluxoProcessamentoDetalhe)
							.on(FlowExecutionStatus.FAILED.getName()).to(step5_MoveArquivosDiretorioExecucaoParaErro())
						.build();

		return jobBuilderFactory.get("flowJobBMF" + data)
		         .incrementer(new RunIdIncrementer())
		         .start(flow)
		         .end()
		         .build();
	}

	@Bean
	public Step step0_ValidaEstruturaArquivoHeader() {
		return stepBuilderFactory.get("step0_ValidaEstruturaArquivoHeader")
				   .<BMFHeaderDTO, BMFHeaderDTO> chunk(10)
				   .reader(readerHeader())
				   .processor(processorValidaEstruturaArquivoHeader())
				   .build();
	}

	@Bean
	public Step step1_ValidaEstruturaArquivoDetalhe() {
		return stepBuilderFactory.get("step1_ValidaEstruturaArquivoDetalhe")
				   .<BMFResumoOperacoesDTO, BMFResumoOperacoesDTO> chunk(10)
				   .reader(readerDetalhe())
				   .processor(processorValidaEstruturaArquivoDetalhe())
				   .build();
	}

	@Bean
	public Step step2_LeituraProcessamentoEscritaArquivo() {
		return stepBuilderFactory.get("step2_LeituraProcessamentoEscritaArquivo")
			   .<BMFResumoOperacoesDTO, BMFResumoOperacoesDTO> chunk(10)
			   .reader(readerDetalhe())
			   .processor(processorDetalhe())
			   .writer(writer())
			   .build();
	}

	@Bean
	public Step step3_MoveArquivosDiretorioExecucaoParaSaida() {
		return stepBuilderFactory.get("step3_MoveArquivosDiretorioExecucaoParaSaida")
		.tasklet(new MoveArquivosTasklet(CaminhoArquivoEnum.CAMINHO_ARQUIVO_EXECUCAO, CaminhoArquivoEnum.CAMINHO_ARQUIVO_SAIDA))
		.build();
	}

	@Bean
	public Step step4_ExcutaProcedureETL() {
		return stepBuilderFactory.get("step4_ExcutaProcedureETL")
		.tasklet(new ExecutaProcedureETLTasklet(datasource))
		.build();
	}

	@Bean
	public Step step5_MoveArquivosDiretorioExecucaoParaErro() {
		return stepBuilderFactory.get("step5_MoveArquivosDiretorioExecucaoParaErro")
		.tasklet(new MoveArquivosTasklet(CaminhoArquivoEnum.CAMINHO_ARQUIVO_EXECUCAO, CaminhoArquivoEnum.CAMINHO_ARQUIVO_ERRO))
		.build();
	}

	public void insereErro(Exception e) {
		ErroProcessamento erroProcessamento = obterObjetoErroProcessamento(e);
		erroProcessamentoDAO.incluirErroProcessamento(erroProcessamento);
	}

	public ErroProcessamento obterObjetoErroProcessamento(Exception e) {
		ErroProcessamento erroProcessamento = new ErroProcessamento();
		erroProcessamento.setCodigoErroProcessamentoEnum(CodigoErroProcessamentoEnum.CODIGO_ERRO_GENERICO);
		erroProcessamento.setDataHora(new Date());
		erroProcessamento.setDescricao(e.toString());
		erroProcessamento.setTipoErroProcessamento(TipoErroProcessamentoEnum.ESTRUTURAL);
		erroProcessamento.setNomeArquivo(getNomeArquivo());
		return erroProcessamento;
	}

	private void obterNomeDoArquivo(Resource[] resources) {
		this.nomeArquivo = resources[0].getFilename();
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

}