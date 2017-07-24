package br.com.irmaoscocati.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.irmaoscocati.config.ProcessoBatchBMF;
import br.com.irmaoscocati.dao.IErroProcessamentoDAO;
import br.com.irmaoscocati.dto.BMFHeaderDTO;
import br.com.irmaoscocati.dto.BMFResumoOperacoesDTO;
import br.com.irmaoscocati.entidade.ErroProcessamento;
import br.com.irmaoscocati.entidade.TipoErroProcessamentoEnum;
import br.com.irmaoscocati.exception.BMFItemInvalidoException;
import br.com.irmaoscocati.util.CodigoErroProcessamentoEnum;
import br.com.irmaoscocati.writer.GerenciadorArquivo;

@Service
public class BMFItemValidacaoEstruturaDetalhe implements IBMFItemValidacaoEstruturaDetalhe {

	@Autowired
	private IErroProcessamentoDAO erroProcessamentoDAO;

	@Autowired
	private ProcessoBatchBMF processoBatchBMF;

	@Autowired
	private GerenciadorArquivo gerenciadorArquivo;


	@Override
	public void validarDominioDetalhe(BMFResumoOperacoesDTO bmf)  throws BMFItemInvalidoException, Exception {
		try{
			boolean tipoIgualAZero = "0".equals(bmf.getTipoRegistro());
			boolean tipoIgualAUm = "1".equals(bmf.getTipoRegistro());
			boolean tipoIgualADois = "2".equals(bmf.getTipoRegistro());
			boolean tipoIgualANove = "9".equals(bmf.getTipoRegistro());
			boolean ehTipoCompativelComOperacao = tipoIgualAZero || tipoIgualAUm || tipoIgualADois || tipoIgualANove;
			if(!ehTipoCompativelComOperacao) {
				String descricao = "Campo tipoRegistro deve possuir somente os seguintes valores(0-1-2-9). Campo (" + bmf.getTipoRegistro() + ")";
				ErroProcessamento erroProcessamento = incluirErroEstruturalDoArquivo(descricao, CodigoErroProcessamentoEnum.CAMPO_DOMINIO_VALORES_INVALIDO);
				erroProcessamentoDAO.incluirErroProcessamento(erroProcessamento);
				//processoBatchBMF.setArquivoValido(false);
				gerenciadorArquivo.setArquivoValido(false);
			}
		} catch(Exception e) {
			ErroProcessamento erroProcessamento = new ErroProcessamento();
			erroProcessamento.setCodigoErroProcessamentoEnum(CodigoErroProcessamentoEnum.CODIGO_ERRO_GENERICO);
			erroProcessamento.setDataHora(new Date());
			erroProcessamento.setDescricao(e.toString());
			erroProcessamento.setTipoErroProcessamento(TipoErroProcessamentoEnum.NEGOCIO);
			erroProcessamento.setNomeArquivo(bmf.getNomeArquivo());
			erroProcessamentoDAO.incluirErroProcessamento(erroProcessamento);
			gerenciadorArquivo.setArquivoValido(false);
		}
	}

	@Override
	public void validarTipagemDetalhe(BMFResumoOperacoesDTO item)  throws BMFItemInvalidoException, Exception {
		try{
			validaTipoRegistro(item.getTipoRegistro());
			validaNumeroPV(item);
			validaDataApresentacao(item);
			validaDataPrevistaPagto(item);
			validaValorBruto(item);
			validaValorLiquido(item);
			validaCodigoProduto(item);
		} catch(Exception e) {
			String descricao = montaMensagem("ErroGenerico", e.getMessage());
			insereErro(descricao);
		}
	}

	@SuppressWarnings("unused")
	private void validaCodigoProduto(BMFResumoOperacoesDTO item) {
		try {
			boolean ehTipoRegistroUm = "1".equals(item.getTipoRegistro());
			if(ehTipoRegistroUm) {
				Integer codigoProduto = new Integer(item.getCodigoProduto());
			}
		} catch(NumberFormatException ex) {
			String descricao = montaMensagem("codigoProduto", item.getCodigoProduto());
			insereErro(descricao);
		}
	}

	@SuppressWarnings("unused")
	private void validaValorBruto(BMFResumoOperacoesDTO item) {
		try {
			boolean verificaTipoRegistroUm = "1".equals(item.getTipoRegistro());
			if(verificaTipoRegistroUm) {
				Integer vlrBruto = new Integer(item.getVlrBruto());
			}
		} catch(NumberFormatException ex) {
			String descricao = montaMensagem("vlrBruto", item.getVlrBruto());
			insereErro(descricao);
		}
	}

	@SuppressWarnings("unused")
	private void validaValorLiquido(BMFResumoOperacoesDTO item) {
		try {
			boolean verificaTipoRegistroUm = "1".equals(item.getTipoRegistro());
			if(verificaTipoRegistroUm) {
				Integer vlrLiquido = new Integer(item.getVlrLiquido());
			}
		} catch(NumberFormatException ex) {
			String descricao = montaMensagem("vlrLiquido", item.getVlrLiquido());
			insereErro(descricao);
		}
	}

	@SuppressWarnings("unused")
	private void validaDataApresentacao(BMFResumoOperacoesDTO item) {
		SimpleDateFormat formato = new SimpleDateFormat("YYMMDD");
		try {
			boolean verificaTipoRegistroUm = "1".equals(item.getTipoRegistro());
			if(verificaTipoRegistroUm) {
				Date dtaApresentacao = formato.parse(item.getDtaApresentacao());
			}
		} catch(ParseException ex) {
			String descricao = montaMensagem("dtaApresentacao", item.getDtaApresentacao());
			insereErro(descricao);
		}
	}

	@SuppressWarnings("unused")
	private void validaDataProcessamento(BMFHeaderDTO bmf) {
		SimpleDateFormat formato = new SimpleDateFormat("YYYYMMDD");
		try {
			boolean verificaTipoRegistroZero = "0".equals(bmf.getTipoRegistro());
			if(verificaTipoRegistroZero) {
				Date dataProcessamento = formato.parse(bmf.getDataProcessamento());
			}
		} catch(ParseException ex) {
			String descricao = montaMensagem("dataProcessamento", bmf.getDataProcessamento());
			insereErro(descricao);
		}
	}

	@SuppressWarnings("unused")
	private void validaDataPrevistaPagto(BMFResumoOperacoesDTO item) {
		SimpleDateFormat formato = new SimpleDateFormat("YYMMDD");
		try {
			boolean verificaTipoRegistroUm = "1".equals(item.getTipoRegistro());
			if(verificaTipoRegistroUm) {
				Date dtaPrevistaPagt = formato.parse(item.getDtaPrevistaPagto());
			}
		} catch(ParseException ex) {
			String descricao = montaMensagem("dtaPrevistaPagt", item.getDtaPrevistaPagto());
			insereErro(descricao);
		}
	}


	@SuppressWarnings("unused")
	public void validaNumeroPV(BMFResumoOperacoesDTO item) {
		try {
			boolean verificaTipoRegistroUm = "1".equals(item.getTipoRegistro());
			if(verificaTipoRegistroUm) {
				Integer numeroPv = new Integer(item.getNumeroPV());
			}
		} catch(NumberFormatException ex) {
			String descricao = montaMensagem("numeroPv", item.getNumeroPV());
			insereErro(descricao);
		}
	}

	@SuppressWarnings("unused")
	public void validaTipoRegistro(String strTipoRegistro) throws Exception{
		try {
			Integer tipoRegistro = new Integer(strTipoRegistro);
		} catch(NumberFormatException ex) {
			String descricao = montaMensagem("tipoRegistro", strTipoRegistro);
			insereErro(descricao);
		} catch(Exception ex) {
			throw ex;
		}
	}

	private ErroProcessamento incluirErroEstruturalDoArquivo(String descricao, CodigoErroProcessamentoEnum codigoErroProcessamentoEnum) {
		ErroProcessamento erroProcessamento = new ErroProcessamento();
		erroProcessamento.setCodigoErroProcessamentoEnum(codigoErroProcessamentoEnum);
		erroProcessamento.setDataHora(new Date());
		erroProcessamento.setDescricao(descricao);
		erroProcessamento.setTipoErroProcessamento(TipoErroProcessamentoEnum.ESTRUTURAL);
		erroProcessamento.setNomeArquivo(processoBatchBMF.getNomeArquivo());
		return erroProcessamento;
	}

	public void insereErro(String descricao) {
		ErroProcessamento erroProcessamento = incluirErroEstruturalDoArquivo(descricao, CodigoErroProcessamentoEnum.CAMPO_FORMATO_INVALIDO);
		erroProcessamentoDAO.incluirErroProcessamento(erroProcessamento);
		gerenciadorArquivo.setArquivoValido(false);
	}

	public String montaMensagem(String nomeCampo, String valor) {
		return "Campo " + nomeCampo + " não é um tipo valido(" + valor + ")";
	}

}
