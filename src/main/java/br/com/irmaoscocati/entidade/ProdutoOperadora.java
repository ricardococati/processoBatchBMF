package br.com.irmaoscocati.entidade;

import java.io.Serializable;

public class ProdutoOperadora implements Serializable {

	private static final long serialVersionUID = 8203066812484154213L;

	private int idProduto;
	private int idOperadora;
	private String codProdOperadora;

	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public int getIdOperadora() {
		return idOperadora;
	}
	public void setIdOperadora(int idOperadora) {
		this.idOperadora = idOperadora;
	}
	public String getCodProdOperadora() {
		return codProdOperadora;
	}
	public void setCodProdOperadora(String codProdOperadora) {
		this.codProdOperadora = codProdOperadora;
	}

}
