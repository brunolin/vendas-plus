package br.vp.controller;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.vp.dao.VendedorDAO;
import br.vp.dao.hibernate.VendedorDaoHibernate;
import br.vp.dto.*;
import br.vp.model.*;


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

	public boolean cadastroVendedor(VendedorDTO vendedorDTO){
		Vendedor vendedor = new Vendedor();
		
		vendedor.setNome(vendedorDTO.getNome());
		vendedor.setCidade(vendedorDTO.getCidade());	
		vendedor.setCpf(vendedorDTO.getCpf());
		vendedor.setEmail(vendedorDTO.getCpf());
		vendedor.setEstado(vendedorDTO.getEstado());
		vendedor.setPontos(0);
		vendedor.setSenha(vendedorDTO.getSenha());
		vendedor.setTelefone(vendedorDTO.getTelefone());
		
		return vendedorHibernate.cadastroVendedor(vendedor);
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
			produtoDTO.setInicioCampanha(produto.getInicioCampanha());
			
			produtosDTO.add(produtoDTO);
		}
		return produtosDTO;
	}
	
	public VendedorDTO getInfoVendedor(LogadoDTO login) {
		try {		
			VendedorDTO vendedorDTO = new VendedorDTO();
			Vendedor vendedor = vendedorHibernate.getInfoVendedor(login.getUsername());
			
			vendedorDTO.setNome(vendedor.getNome());
			vendedorDTO.setPontos(vendedor.getPontos());
			vendedorDTO.setIdVendedor(vendedor.getIdVendedor());
			
			return vendedorDTO;
		} catch (NullPointerException e) {
			System.out.println("Salve");
		}
		
		return null;
	}
	
	public VendedorDTO getInfoVendedorByEmail(String email) {
		try {		
			VendedorDTO vendedorDTO = new VendedorDTO();
			Vendedor vendedor = vendedorHibernate.getInfoVendedorByEmail(email);
			
			vendedorDTO.setNome(vendedor.getNome());
			vendedorDTO.setPontos(vendedor.getPontos());
			vendedorDTO.setCidade(vendedor.getCidade());
			vendedorDTO.setEstado(vendedor.getEstado());
			vendedorDTO.setEmail(vendedor.getEmail());
			vendedorDTO.setTelefone(vendedor.getTelefone());
			
			return vendedorDTO;
		} catch (NullPointerException e) {
			System.out.println("Salve");
		}
		
		return null;
	}
	
	public String cadastrarNota(VendasDTO vendaDTO) {
		Vendas venda = new Vendas();
		
		venda.setNomeProduto(vendaDTO.getNomeProduto());
		venda.setAprovada("F");
		venda.setData(vendaDTO.getData());
		venda.setIdEmpresa(vendaDTO.getIdEmpresa());
		venda.setIdProduto(vendaDTO.getIdProduto());
		venda.setIdVenda(vendaDTO.getIdVenda());
		venda.setIdVendedor(vendaDTO.getIdVendedor());
		
		return vendedorHibernate.cadastroVenda(venda) ? "200" : null;
	}
	
	public List<VendasDTO> getNotas(int id) {
		VendasDTO vendaDTO;
		List<VendasDTO> vendasDTO = new ArrayList<VendasDTO>();
		
		List<Vendas> vendas = vendedorHibernate.getNotasVendedor(id);
		
		try{
			for(Vendas venda : vendas) {
				
				vendaDTO = new VendasDTO(); 
	
				vendaDTO.setNomeProduto(venda.getNomeProduto());
				vendaDTO.setData(venda.getData());
				vendaDTO.setIdProduto(venda.getIdProduto());
				vendaDTO.setIdVenda(venda.getIdVenda());
				vendaDTO.setAprovada(venda.getAprovada());
				
				vendasDTO.add(vendaDTO);
			}
			
			return vendasDTO;
		} catch(NullPointerException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<BonusDTO> getBonus() {
		BonusDTO bonusDTO;
		List<BonusDTO> bonusDTOList = new ArrayList<BonusDTO>();
		List<Bonus> bonusList = vendedorHibernate.getBonus();
		
		for(Bonus bonus : bonusList) {
			bonusDTO = new BonusDTO();
			
			bonusDTO.setDescricao(bonus.getDescricao());
			bonusDTO.setIdBonus(bonus.getIdBonus());
			bonusDTO.setNome(bonus.getNome());
			bonusDTO.setPontosNecessarios(bonus.getPontosNecessarios());
			
			bonusDTOList.add(bonusDTO);
		}
		
		return bonusDTOList;
	}
	
	public boolean resgatarBonus(ValidateBonusDTO bonus) {
		return vendedorHibernate.resgatarBonus(bonus.getPontos(), bonus.getCpf());
	}
}
