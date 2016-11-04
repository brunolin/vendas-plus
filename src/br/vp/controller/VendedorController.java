package br.vp.controller;

import br.vp.dto.*;

public class VendedorController {

	public VendedorController() {

	}

	public boolean cadastroEmpresa(VendedorDTO vendedor){
	 	VendedorDAO vendedorDAO = new VendedorDAO();

		return vendedorDAO.cadastroVendedor(vendedor);
	}
}
