package br.vp.dto2;

import br.vp.model.Vendedor;

public class VendedorDTO {
	
	public Vendedor vendedor;

	public VendedorDTO() {
		this.vendedor = new Vendedor();
	}
	
	public VendedorDTO(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

}
