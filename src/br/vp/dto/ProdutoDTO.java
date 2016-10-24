package br.vp.dto;

import java.util.Date;

public class ProdutoDTO {
	
	public int idProduto, idEmpresa, pontosRecompensa;
	public String nomeProduto;
	public Date inicioCampanha, vigenciaCampanha;
	
	public ProdutoDTO() {
		
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public int getPontosRecompensa() {
		return pontosRecompensa;
	}

	public void setPontosRecompensa(int pontosRecompensa) {
		this.pontosRecompensa = pontosRecompensa;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Date getInicioCampanha() {
		return inicioCampanha;
	}

	public void setInicioCampanha(Date inicioCampanha) {
		this.inicioCampanha = inicioCampanha;
	}

	public Date getVigenciaCampanha() {
		return vigenciaCampanha;
	}

	public void setVigenciaCampanha(Date vigenciaCampanha) {
		this.vigenciaCampanha = vigenciaCampanha;
	}
	
	
}
