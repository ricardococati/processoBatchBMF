package br.com.irmaoscocati.config;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.transform.FlatFileFormatException;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import br.com.irmaoscocati.dto.BMFHeaderDTO;
import br.com.irmaoscocati.dto.BMFResumoOperacoesDTO;

@Component
public class ConfigLeituraMultiplosArquivos {

	public MultiResourceItemReader<BMFResumoOperacoesDTO> obterLeituraMultiplosArquivosDetalhe(Resource[] resources, FlatFileItemReader<BMFResumoOperacoesDTO> flatFileItemReader) throws FlatFileParseException, FlatFileFormatException{
		MultiResourceItemReader<BMFResumoOperacoesDTO> multiResourceItemReader = new MultiResourceItemReader<BMFResumoOperacoesDTO>();
		multiResourceItemReader.setDelegate(flatFileItemReader);
		multiResourceItemReader.setResources(resources);
		return multiResourceItemReader;
	}

	public MultiResourceItemReader<BMFHeaderDTO> obterLeituraMultiplosArquivosHeader(Resource[] resources, FlatFileItemReader<BMFHeaderDTO> flatFileItemReader) throws FlatFileParseException, FlatFileFormatException{
		MultiResourceItemReader<BMFHeaderDTO> multiResourceItemReader = new MultiResourceItemReader<BMFHeaderDTO>();
		multiResourceItemReader.setDelegate(flatFileItemReader);
		multiResourceItemReader.setResources(resources);
		return multiResourceItemReader;
	}

}
