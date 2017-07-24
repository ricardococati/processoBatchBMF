package br.com.irmaoscocati.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReaderException;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemWriterException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.irmaoscocati.config.ProcessoBatchBMF;
import br.com.irmaoscocati.dto.BMFResumoOperacoesDTO;
import br.com.irmaoscocati.exception.BMFItemInvalidoException;
import br.com.irmaoscocati.validator.IBMFItemValidacaoNegocio;

@Service
public class BMFItemDetalheProcessor implements ItemProcessor<BMFResumoOperacoesDTO, BMFResumoOperacoesDTO> {

	private static final Logger log = LoggerFactory.getLogger(BMFItemDetalheProcessor.class);

	@Autowired
	private ProcessoBatchBMF processoBatchBMF;

	@Autowired
	IBMFItemValidacaoNegocio bmfItemValidacaoNegocio;

	public BMFResumoOperacoesDTO process(BMFResumoOperacoesDTO bmf) throws Exception, ParseException, ItemStreamException, ItemReaderException, ItemWriterException {
		BMFResumoOperacoesDTO bmfReturn = null;

		try{
			bmf.setNomeArquivo(processoBatchBMF.getNomeArquivo());

			bmfItemValidacaoNegocio.validaRegrasNegocioArquivo(bmf);

			final BMFResumoOperacoesDTO bmfRODTO = new BMFResumoOperacoesDTO();
			bmfRODTO.setTipoRegistro(bmf.getTipoRegistro());
			bmfRODTO.setNumeroPV(bmf.getNumeroPV());
			bmfRODTO.setFiller(bmf.getFiller());
			bmfRODTO.setDtaApresentacao(bmf.getDtaApresentacao());
			bmfRODTO.setDtaPrevistaPagto(bmf.getDtaPrevistaPagto());
			bmfRODTO.setVlrLiquido(bmf.getVlrLiquido());
			bmfRODTO.setVlrBruto(bmf.getVlrBruto());
			bmfRODTO.setCodigoProduto(bmf.getCodigoProduto());
			bmfRODTO.setDadoDescarte(bmf.getDadoDescarte());
			bmfReturn = bmfRODTO;

			log.info("Converting (" + bmf + ") into (" + bmfReturn + ")");
		} catch (BMFItemInvalidoException ex){
			log.info("Aconteceu uma Excessão por BMFItemInvalidoException - BMFResumoOperacoesDTO(" + bmf.toString() + ") Mensagem("+ex.getDescricaoErro()+")");
		} catch (FlatFileParseException ex){
			log.info("Aconteceu uma Excessão por FlatFileParseException - BMFResumoOperacoesDTO(" + bmf.toString() + ") Mensagem("+ex.getMessage()+")");
		} catch (Exception ex){
			log.info("Aconteceu uma Excessão por Exception - BMFResumoOperacoesDTO(" + bmf.toString() + ") Mensagem("+ex.getMessage()+")");
		}
		return bmfReturn;
	}

}
