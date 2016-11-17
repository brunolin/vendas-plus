package br.vp.dto;

import java.util.Date;

public class ProdutoDTO {
	
	public int idProduto, idEmpresa, pontosRecompensa;
	public String nomeProduto, img, inicioCampanha, vigenciaCampanha;
	
	public ProdutoDTO() {
		
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public String getInicioCampanha() {
		return inicioCampanha;
	}

	public void setInicioCampanha(String inicioCampanha) {
		this.inicioCampanha = inicioCampanha;
	}

	public String getVigenciaCampanha() {
		return vigenciaCampanha;
	}

	public void setVigenciaCampanha(String vigenciaCampanha) {
		this.vigenciaCampanha = vigenciaCampanha;
	}
	
	
}
