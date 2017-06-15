package br.vp.controller;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.vp.dao.VendedorDAO;
import br.vp.dao.hibernate.VendedorDaoHibernate;
import br.vp.dto.BonusDTO;
import br.vp.dto.LogadoDTO;
import br.vp.dto.ProdutoDTO;
import br.vp.dto.ValidateBonusDTO;
import br.vp.dto.VendasDTO;
import br.vp.dto.VendedorDTO;
import br.vp.model.Produto;


/**
 * 
 * @author Brunolin
 *	Classe de controlle que é a ponte entre a Bean e a DAO
 */
public class VendedorController {
	
 	VendedorDAO vendedorDAO;
 	VendedorDaoHibernate vendedorHibernate;
 	
	public VendedorController() {
		vendedorDAO = new VendedorDAO();
		vendedorHibernate = new VendedorDaoHibernate();
	}

	public boolean cadastroVendedor(VendedorDTO vendedor){

		return vendedorDAO.cadastroVendedor(vendedor);
	}
	
	public List<ProdutoDTO> getCampanhas(){		
		ProdutoDTO produtoDTO;
		List<ProdutoDTO> produtosDTO = new ArrayList<ProdutoDTO>();
		
		List<Produto> produtos = vendedorHibernate.getCampanhas();
		
		for(Produto produto : produtos) {
			
			produtoDTO = new ProdutoDTO();
			produtoDTO.setNomeProduto(produto.getNomeProduto());
			produtoDTO.setPontosRecompensa(produto.getPontosRecompensa());
			produtoDTO.setImg(produto.getImg());
			
			produtosDTO.add(produtoDTO);
		}
		return produtosDTO;
	}
	
	public VendedorDTO getInfoVendedor(LogadoDTO login) {
		return vendedorDAO.getInfoVendedor(login.getUsername());
	}
	
	public VendedorDTO getInfoVendedorByEmail(String email) {
		return vendedorDAO.getInfoVendedorByEmail(email);
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
