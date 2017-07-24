package br.com.irmaoscocati.entidade;

import java.io.Serializable;

public class EmpresaOperadora implements Serializable {

	private static final long serialVersionUID = 1568131409727491579L;

	private int idEmpresa;

	private int idOperadora;

	private String numeroPV;

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public int getIdOperadora() {
		return idOperadora;
	}

	public void setIdOperadora(int idOperadora) {
		this.idOperadora = idOperadora;
	}

	public String getNumeroPV() {
		return numeroPV;
	}

	public void setNumeroPV(String numeroPV) {
		this.numeroPV = numeroPV;
	}

}
