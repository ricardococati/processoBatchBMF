package br.com.irmaoscocati.config;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceArrayPropertyEditor;
import org.springframework.stereotype.Component;

import br.com.irmaoscocati.util.CaminhoArquivoEnum;

@Component
public class ConfigArquivo {

	public Resource[] obterArquivo() {
		ResourceArrayPropertyEditor resourceLoader = new ResourceArrayPropertyEditor();
		resourceLoader.setAsText(CaminhoArquivoEnum.CAMINHO_ARQUIVO_PROCESSO.getCaminho());
		Resource[] resources = (Resource[]) resourceLoader.getValue();
		return resources;
	}

}
