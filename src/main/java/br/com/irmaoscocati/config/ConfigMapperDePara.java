package br.com.irmaoscocati.config;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.stereotype.Component;

import br.com.irmaoscocati.dto.BMFHeaderDTO;
import br.com.irmaoscocati.dto.BMFResumoOperacoesDTO;
import br.com.irmaoscocati.tokenizer.BMFFixedLengthTokenizer;

@Component
public class ConfigMapperDePara {

	public DefaultLineMapper<BMFResumoOperacoesDTO> obterMapperDeParaDetalhe(BMFFixedLengthTokenizer lineTokenizer, BeanWrapperFieldSetMapper<BMFResumoOperacoesDTO> fieldSetMapper) {
		DefaultLineMapper<BMFResumoOperacoesDTO> lineMapper = new DefaultLineMapper<BMFResumoOperacoesDTO>();
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		lineMapper.afterPropertiesSet();
		return lineMapper;
	}

	public DefaultLineMapper<BMFHeaderDTO> obterMapperDeParaHeader(BMFFixedLengthTokenizer lineTokenizer, BeanWrapperFieldSetMapper<BMFHeaderDTO> fieldSetMapper) {
		DefaultLineMapper<BMFHeaderDTO> lineMapper = new DefaultLineMapper<BMFHeaderDTO>();
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		lineMapper.afterPropertiesSet();
		return lineMapper;
	}

}
