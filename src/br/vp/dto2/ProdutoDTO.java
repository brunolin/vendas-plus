package br.vp.dto2;

import br.vp.model.Produto;

public class ProdutoDTO {

	public Produto produto;
	
	public ProdutoDTO() {
		this.produto = new Produto();
	}
	
	public ProdutoDTO(Produto produto) {
		this.produto = produto;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}
