package br.vp.controller;

import br.vp.dto.*;

public class VendedorController {

	public VendedorController() {

	}

	public boolean cadastroVendedor(VendedorDTO vendedor){
	 	VendedorDAO vendedorDAO = new VendedorDAO();

		return vendedorDAO.cadastroVendedor(vendedor);
	}
}
