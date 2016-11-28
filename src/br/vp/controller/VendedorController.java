package br.vp.controller;

import java.util.ArrayList;

import br.vp.dao.VendedorDAO;
import br.vp.dto.ProdutoDTO;
import br.vp.dto.VendedorDTO;

/**
 * 
 * @author Brunolin
 *	Classe de controlle que é a ponte entre a Bean e a DAO
 */
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
