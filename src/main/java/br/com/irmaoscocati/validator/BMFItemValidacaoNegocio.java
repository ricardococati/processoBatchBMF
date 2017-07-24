package br.com.irmaoscocati.validator;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.irmaoscocati.dao.IEmpresaOperadoraDAO;
import br.com.irmaoscocati.dao.IErroProcessamentoDAO;
import br.com.irmaoscocati.dao.IProdutoOperadoraDAO;
import br.com.irmaoscocati.dto.BMFResumoOperacoesDTO;
import br.com.irmaoscocati.entidade.EmpresaOperadora;
import br.com.irmaoscocati.entidade.ErroProcessamento;
import br.com.irmaoscocati.entidade.ProdutoOperadora;
import br.com.irmaoscocati.entidade.TipoErroProcessamentoEnum;
import br.com.irmaoscocati.exception.BMFItemInvalidoException;
import br.com.irmaoscocati.util.CodigoErroProcessamentoEnum;

@Service
public class BMFItemValidacaoNegocio implements IBMFItemValidacaoNegocio {

	private static final String STR_FILLER = "";

	private static final String TIPO_REGISTRO_DET_RESUMO_OPERAC = "1";

	private static final String TIPO_TRANSACAO_VENDA = "01";

	private static final String VENDA_A_VISTA = "";

	@Autowired
	private IErroProcessamentoDAO erroProcessamentoDAO;

	@Autowired
	private IEmpresaOperadoraDAO empresaOperadoraDAO;

	@Autowired
	private IProdutoOperadoraDAO produtoOperadoraDAO;

	public void validaRegrasNegocioArquivo(BMFResumoOperacoesDTO bmf) throws BMFItemInvalidoException, Exception {
		boolean logarSomenteSeRegistroForTipoUm = TIPO_REGISTRO_DET_RESUMO_OPERAC.equals(bmf.getTipoRegistro());
		try {
			validarTipoRegistroValido(bmf);
			validarFiller(bmf);
			validarTipoTransacaoValido(bmf);
			validarVendaValida(bmf);
			validarEmpresaCadastradaPorNumeroPV(bmf);
			validarCodigoProduto(bmf);
		} catch (BMFItemInvalidoException erro) {
			if(logarSomenteSeRegistroForTipoUm) {
				ErroProcessamento erroProcessamento = new ErroProcessamento();
				erroProcessamento.setCodigoErroProcessamentoEnum(erro.getCodigoErroProcessamentoEnum());
				erroProcessamento.setDataHora(new Date());
				erroProcessamento.setDescricao(erro.getDescricaoErro());
				erroProcessamento.setTipoErroProcessamento(TipoErroProcessamentoEnum.NEGOCIO);
				erroProcessamento.setNomeArquivo(bmf.getNomeArquivo());
				erroProcessamentoDAO.incluirErroProcessamento(erroProcessamento);
			}
			throw erro;
		} catch (Exception e) {
			ErroProcessamento erroProcessamento = new ErroProcessamento();
			erroProcessamento.setCodigoErroProcessamentoEnum(CodigoErroProcessamentoEnum.CODIGO_ERRO_GENERICO);
			erroProcessamento.setDataHora(new Date());
			erroProcessamento.setDescricao(e.toString());
			erroProcessamento.setTipoErroProcessamento(TipoErroProcessamentoEnum.NEGOCIO);
			erroProcessamento.setNomeArquivo(bmf.getNomeArquivo());
			erroProcessamentoDAO.incluirErroProcessamento(erroProcessamento);
			throw e;
		}
	}

	private void validarTipoRegistroValido(BMFResumoOperacoesDTO bmf) throws BMFItemInvalidoException, Exception {
		boolean naoEhTipoRegistroValido = !TIPO_REGISTRO_DET_RESUMO_OPERAC.equals(bmf.getTipoRegistro());
		if(naoEhTipoRegistroValido) {
			throw new BMFItemInvalidoException(CodigoErroProcessamentoEnum.TIPO_REGISTRO_INVALIDO, "Campo tipo de registro inválido. Tipo de registro igual a(" + bmf.getTipoRegistro() + ")");
		}
	}

	private void validarFiller(BMFResumoOperacoesDTO bmf) throws BMFItemInvalidoException, Exception {
		boolean ehTipoRegistroValido = TIPO_REGISTRO_DET_RESUMO_OPERAC.equals(bmf.getTipoRegistro());
		boolean fillerNaoEstaVazio = !STR_FILLER.equals(bmf.getFiller());
		if(ehTipoRegistroValido && fillerNaoEstaVazio) {
			throw new BMFItemInvalidoException(CodigoErroProcessamentoEnum.FILLER_INVALIDO, "Campo Filler inválido. Filler igual a(" + bmf.getFiller() + ")");
		}
	}

	private void validarTipoTransacaoValido(BMFResumoOperacoesDTO bmf) throws BMFItemInvalidoException, Exception {
		boolean ehTipoRegistroValido = TIPO_REGISTRO_DET_RESUMO_OPERAC.equals(bmf.getTipoRegistro());
		boolean naoEhTipoTransacaoVenda = !TIPO_TRANSACAO_VENDA.equals(bmf.getTipoDeTransacao());
		if(ehTipoRegistroValido && naoEhTipoTransacaoVenda) {
			throw new BMFItemInvalidoException(CodigoErroProcessamentoEnum.TIPO_TRANSACAO_INVALIDA, "Campo tipo de transação inválida. Tipo de transação igual a(" + bmf.getTipoDeTransacao() + ")");
		}
	}

	private void validarVendaValida(BMFResumoOperacoesDTO bmf) throws BMFItemInvalidoException, Exception {
		boolean ehTipoRegistroValido = TIPO_REGISTRO_DET_RESUMO_OPERAC.equals(bmf.getTipoRegistro());
		boolean naoEhVendaAVista = !VENDA_A_VISTA.equals(bmf.getParcela());
		if(ehTipoRegistroValido && naoEhVendaAVista) {
			throw new BMFItemInvalidoException(CodigoErroProcessamentoEnum.VENDA_INVALIDA, "Campo venda inválida. Venda igual a(" + bmf.getTipoRegistro() + ")");
		}
	}

	private void validarEmpresaCadastradaPorNumeroPV(BMFResumoOperacoesDTO bmf) throws BMFItemInvalidoException, Exception {
		EmpresaOperadora empresaOperadora = empresaOperadoraDAO.obterEmpresaOperadoraPorPV(bmf.getNumeroPV());
		if(empresaOperadora == null){
			throw new BMFItemInvalidoException(CodigoErroProcessamentoEnum.NUMERO_PV_INVALIDO, "Número do PV(" + bmf.getNumeroPV() + ") não está cadastrado na tabela EmpresaOperadora");
		}
	}

	private void validarCodigoProduto(BMFResumoOperacoesDTO bmf) throws BMFItemInvalidoException, Exception {
		ProdutoOperadora produtoOperadora = produtoOperadoraDAO.obterProdutoOperadoraPorCodOperadora(bmf.getCodigoProduto());
		if(produtoOperadora == null){
			throw new BMFItemInvalidoException(CodigoErroProcessamentoEnum.CODIGO_PRODUTO_INVALIDO, "Código da operadora(" + bmf.getCodigoProduto() + ") não está cadastrado na tabela ProdutoOperadora");
		}
	}

}
