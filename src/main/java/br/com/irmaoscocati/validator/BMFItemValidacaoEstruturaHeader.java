package br.com.irmaoscocati.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.irmaoscocati.config.ProcessoBatchBMF;
import br.com.irmaoscocati.dao.IErroProcessamentoDAO;
import br.com.irmaoscocati.dto.BMFHeaderDTO;
import br.com.irmaoscocati.entidade.ErroProcessamento;
import br.com.irmaoscocati.entidade.TipoErroProcessamentoEnum;
import br.com.irmaoscocati.exception.BMFItemInvalidoException;
import br.com.irmaoscocati.util.CodigoErroProcessamentoEnum;
import br.com.irmaoscocati.writer.GerenciadorArquivo;

@Service
public class BMFItemValidacaoEstruturaHeader implements IBMFItemValidacaoEstruturaHeader {

	private static final String OPERADORA_CIELO = "CIELO";

	private static final String TIPO_ARQUIVO_VENDA_COM_CV_PARCELADO_FUTURO = "03";

	private static final Logger log = LoggerFactory.getLogger(BMFItemValidacaoEstruturaHeader.class);

	@Autowired
	private IErroProcessamentoDAO erroProcessamentoDAO;

	@Autowired
	private ProcessoBatchBMF processoBatchBMF;

	@Autowired
	private GerenciadorArquivo gerenciadorArquivo;

	@Override
	public void validarDominioHeader(BMFHeaderDTO bmf) throws BMFItemInvalidoException, Exception {
		validaDominioPorTipoRegistro(bmf);
		validaEmpresaAdquirente(bmf);
		validaOpcaoDeExtrato(bmf);
	}

	public void validaDominioPorTipoRegistro(BMFHeaderDTO bmf) {
		try{
			boolean tipoIgualAZero = "0".equals(bmf.getTipoRegistro());
			boolean tipoIgualAUm = "1".equals(bmf.getTipoRegistro());
			boolean tipoIgualADois = "2".equals(bmf.getTipoRegistro());
			boolean tipoIgualANove = "9".equals(bmf.getTipoRegistro());
			if(!(tipoIgualAZero || tipoIgualAUm || tipoIgualADois || tipoIgualANove)) {
				log.info("O tipo de registro é inválido para arquivos de Resumo de Vendas - tipoRegistro("+bmf.getTipoRegistro()+")");
			}
		} catch(Exception e) {
			ErroProcessamento erroProcessamento = new ErroProcessamento();
			erroProcessamento.setCodigoErroProcessamentoEnum(CodigoErroProcessamentoEnum.CODIGO_ERRO_GENERICO);
			erroProcessamento.setDataHora(new Date());
			erroProcessamento.setDescricao(e.toString());
			erroProcessamento.setTipoErroProcessamento(TipoErroProcessamentoEnum.NEGOCIO);
			erroProcessamento.setNomeArquivo(bmf.getNomeArquivo());
			erroProcessamentoDAO.incluirErroProcessamento(erroProcessamento);
			//processoBatchBMF.setArquivoValido(false);
			gerenciadorArquivo.setArquivoValido(false);
		}
	}

	@Override
	public void validarTipagemHeader(BMFHeaderDTO bmf) throws BMFItemInvalidoException, Exception {
		try{
			validaTipoRegistro(bmf.getTipoRegistro());
			validaDataProcessamento(bmf);
			validaNumeroSequencial(bmf);
		} catch(Exception e) {
			String descricao = montaMensagem("ErroGenerico", e.getMessage());
			insereErro(descricao);
		}
	}

	@SuppressWarnings("unused")
	private void validaNumeroSequencial(BMFHeaderDTO item) {
		try {
			boolean ehTipoRegistroZero = "0".equals(item.getTipoRegistro());
			if(ehTipoRegistroZero) {
				Integer numeroSequencial = new Integer(item.getNumeroSequencial());
			}
		} catch(NumberFormatException ex) {
			String descricao = montaMensagem("numeroSequencial", item.getNumeroSequencial());
			insereErro(descricao);
		}
	}

	private void validaEmpresaAdquirente(BMFHeaderDTO bmf) {
		try {
			boolean ehTipoRegistroZero = "0".equals(bmf.getTipoRegistro());
			if(ehTipoRegistroZero) {
				if(OPERADORA_CIELO.equals(bmf.getEmpresaAdquirente())){
					log.info("A empresa adquirente é válida - ("+bmf.getEmpresaAdquirente()+")");
				} else{
					String descricao = "A empresa adquirente é inválida. Campo (" + bmf.getEmpresaAdquirente() + ")";
					ErroProcessamento erroProcessamento = incluirErroEstruturalDoArquivo(descricao, CodigoErroProcessamentoEnum.CAMPO_DOMINIO_VALORES_INVALIDO);
					erroProcessamentoDAO.incluirErroProcessamento(erroProcessamento);
					log.info("A empresa adquirente é inválida - ("+bmf.getEmpresaAdquirente()+")");
					//processoBatchBMF.setArquivoValido(false);
					gerenciadorArquivo.setArquivoValido(false);

				}
			}
		} catch(Exception ex) {
			String descricao = montaMensagem("empresaAdquirente", bmf.getEmpresaAdquirente());
			insereErro(descricao);
		}
	}

	private void validaOpcaoDeExtrato(BMFHeaderDTO bmf) {
		try {
			boolean ehTipoRegistroZero = "0".equals(bmf.getTipoRegistro());
			if(ehTipoRegistroZero) {
				if(TIPO_ARQUIVO_VENDA_COM_CV_PARCELADO_FUTURO.equals(bmf.getOpcaoDeExtrato())){
					log.info("A opção de extrato é válida - ("+bmf.getOpcaoDeExtrato()+")");
				} else{
					String descricao = "A opção de extrato é inválida. Campo (" + bmf.getOpcaoDeExtrato() + ")";
					ErroProcessamento erroProcessamento = incluirErroEstruturalDoArquivo(descricao, CodigoErroProcessamentoEnum.CAMPO_DOMINIO_VALORES_INVALIDO);
					erroProcessamentoDAO.incluirErroProcessamento(erroProcessamento);
					log.info("A opção de extrato é inválida - ("+bmf.getOpcaoDeExtrato()+")");
					//processoBatchBMF.setArquivoValido(false);
					gerenciadorArquivo.setArquivoValido(false);
				}
			}
		} catch(Exception ex) {
			String descricao = montaMensagem("OpcaoDeExtrato", bmf.getOpcaoDeExtrato());
			insereErro(descricao);
		}
	}

	@SuppressWarnings("unused")
	private void validaDataProcessamento(BMFHeaderDTO bmf) {
		SimpleDateFormat formato = new SimpleDateFormat("YYYYMMDD");
		try {
			boolean ehTipoRegistroZero = "0".equals(bmf.getTipoRegistro());
			if(ehTipoRegistroZero) {
				Date dataProcessamento = formato.parse(bmf.getDataProcessamento());
			}
		} catch(ParseException ex) {
			String descricao = montaMensagem("dataProcessamento", bmf.getDataProcessamento());
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
		//processoBatchBMF.setArquivoValido(false);
		gerenciadorArquivo.setArquivoValido(false);
	}

	public String montaMensagem(String nomeCampo, String valor) {
		return "Campo " + nomeCampo + " não é um tipo valido(" + valor + ")";
	}

}
