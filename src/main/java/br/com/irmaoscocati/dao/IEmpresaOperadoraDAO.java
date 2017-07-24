package br.com.irmaoscocati.dao;

import br.com.irmaoscocati.entidade.EmpresaOperadora;

public interface IEmpresaOperadoraDAO {

	public EmpresaOperadora obterEmpresaOperadoraPorPV(String numeroPV);
	
}
