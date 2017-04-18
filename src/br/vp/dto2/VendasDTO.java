package br.vp.dto2;

import br.vp.model.Vendas;

public class VendasDTO {
	
	public Vendas vendas;

	public VendasDTO() {
		this.vendas = new Vendas();
	}
	
	public VendasDTO(Vendas vendas) {
		this.vendas = vendas;
	}

	public Vendas getVendas() {
		return vendas;
	}

	public void setVendas(Vendas vendas) {
		this.vendas = vendas;
	}
	
	
}
