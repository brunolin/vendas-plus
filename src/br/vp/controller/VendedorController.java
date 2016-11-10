package br.vp.controller;

import java.util.ArrayList;

import br.vp.dao.VendedorDAO;
import br.vp.dto.ProdutoDTO;
import br.vp.dto.VendedorDTO;

public class VendedorController {
	
 	VendedorDAO vendedorDAO;
 	
	public VendedorController() {
		vendedorDAO = new VendedorDAO();
	}

	public boolean cadastroVendedor(VendedorDTO vendedor){

		return vendedorDAO.cadastroVendedor(vendedor);
	}
	
	public ArrayList<ProdutoDTO> getCampanhas(){
		
		return vendedorDAO.getCampanhas();
	}
}
