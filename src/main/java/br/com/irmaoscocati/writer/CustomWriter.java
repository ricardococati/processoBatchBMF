package br.com.irmaoscocati.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import br.com.irmaoscocati.dto.BMFResumoOperacoesDTO;

public class CustomWriter implements ItemWriter<BMFResumoOperacoesDTO> {

	public void write(List<? extends BMFResumoOperacoesDTO> items) throws Exception {
		//Não implementado
	}

}
