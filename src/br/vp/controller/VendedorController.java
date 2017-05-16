package br.vp.controller;

import java.util.ArrayList;

import br.vp.dao.VendedorDAO;
import br.vp.dto.BonusDTO;
import br.vp.dto.LogadoDTO;
import br.vp.dto.ProdutoDTO;
import br.vp.dto.ValidateBonusDTO;
import br.vp.dto.VendasDTO;
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
	
	public VendedorDTO getInfoVendedor(LogadoDTO login) {
		return vendedorDAO.getInfoVendedor(login.getUsername());
	}
	
	public String cadastrarNota(VendasDTO venda) {
		if(vendedorDAO.cadastroVenda(venda)) {
			return "200";
		} else {
			return null;
		}
	}
	
	public ArrayList<VendasDTO> getNotas(int id) {
		return vendedorDAO.getNotasVendedor(id);
	}
	
	public ArrayList<BonusDTO> getBonus() {
		return vendedorDAO.getBonus();
	}
	
	public boolean resgatarBonus(ValidateBonusDTO bonus) {
		boolean validacao;
		validacao = vendedorDAO.resgatarBonus(bonus.getPontos(), bonus.getCpf());
		return validacao;
	}
	
	
}
