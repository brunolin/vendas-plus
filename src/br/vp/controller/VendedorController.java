package br.vp.controller;

import br.vp.dao.VendedorDAO;
import br.vp.dto.VendedorDTO;

public class VendedorController {

	public VendedorController() {

	}

	public boolean cadastroVendedor(VendedorDTO vendedor){
	 	VendedorDAO vendedorDAO = new VendedorDAO();

		return vendedorDAO.cadastroVendedor(vendedor);
	}
}
