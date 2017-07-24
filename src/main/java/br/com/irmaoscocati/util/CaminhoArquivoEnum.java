package br.com.irmaoscocati.util;

public enum CaminhoArquivoEnum {

	CAMINHO_ARQUIVO_PROCESSO("file:/data/bmf-batch/bmf/execucao/*"),//
	CAMINHO_ARQUIVO_ENTRADA("/data/bmf-batch/bmf/entrada/"), //
	CAMINHO_ARQUIVO_ERRO("/data/bmf-batch/bmf/erro/"),//
	CAMINHO_ARQUIVO_EXECUCAO("/data/bmf-batch/bmf/execucao/"),//
	CAMINHO_ARQUIVO_SAIDA("/data/bmf-batch/bmf/saida/");

	private String caminho;

	private CaminhoArquivoEnum(String caminho) {
		this.caminho = caminho;
	}

	public String getCaminho() {
		return caminho;
	}

}
