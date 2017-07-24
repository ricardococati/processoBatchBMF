package br.com.irmaoscocati.config;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.stereotype.Component;

import br.com.irmaoscocati.dto.BMFHeaderDTO;
import br.com.irmaoscocati.dto.BMFResumoOperacoesDTO;

@Component
public class ConfigTipoObjetoMapper {

	public BeanWrapperFieldSetMapper<BMFResumoOperacoesDTO> obterTipoObjetoDetalhe() {
		BeanWrapperFieldSetMapper<BMFResumoOperacoesDTO> fieldSetMapper = new BeanWrapperFieldSetMapper<BMFResumoOperacoesDTO>();
		fieldSetMapper.setTargetType(BMFResumoOperacoesDTO.class);
		return fieldSetMapper;
	}

	public BeanWrapperFieldSetMapper<BMFHeaderDTO> obterTipoObjetoHeader() {
		BeanWrapperFieldSetMapper<BMFHeaderDTO> fieldSetMapper = new BeanWrapperFieldSetMapper<BMFHeaderDTO>();
		fieldSetMapper.setTargetType(BMFHeaderDTO.class);
		return fieldSetMapper;
	}

}
