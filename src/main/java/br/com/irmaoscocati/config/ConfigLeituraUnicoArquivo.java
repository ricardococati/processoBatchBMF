package br.com.irmaoscocati.config;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.stereotype.Component;

import br.com.irmaoscocati.dto.BMFHeaderDTO;
import br.com.irmaoscocati.dto.BMFResumoOperacoesDTO;

@Component
public class ConfigLeituraUnicoArquivo {

	public FlatFileItemReader<BMFResumoOperacoesDTO> obterLeituraUnicoArquivoDetalhe(DefaultLineMapper<BMFResumoOperacoesDTO> lineMapper) throws Exception {
		FlatFileItemReader<BMFResumoOperacoesDTO> flatFileItemReader = new FlatFileItemReader<BMFResumoOperacoesDTO>();
		flatFileItemReader.setLineMapper(lineMapper);
		flatFileItemReader.afterPropertiesSet();
		flatFileItemReader.setStrict(true);
		return flatFileItemReader;
	}

	public FlatFileItemReader<BMFHeaderDTO> obterLeituraUnicoArquivoHeader(DefaultLineMapper<BMFHeaderDTO> lineMapper) throws Exception {
		FlatFileItemReader<BMFHeaderDTO> flatFileItemReader = new FlatFileItemReader<BMFHeaderDTO>();
		flatFileItemReader.setLineMapper(lineMapper);
		flatFileItemReader.afterPropertiesSet();
		flatFileItemReader.setStrict(true);
		return flatFileItemReader;
	}

}
