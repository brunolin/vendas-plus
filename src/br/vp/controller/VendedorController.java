package br.vp.controller;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.vp.dao.VendedorDAO;
import br.vp.dao.hibernate.VendedorDaoHibernate;
import br.vp.dto.*;
import br.vp.model.*;
import br.vp.controller.*;


/**
 * 
 * @author Brunolin
 *	Classe de controlle que é a ponte entre a Bean e a DAO
 */
public class VendedorController {
	
 	VendedorDAO vendedorDAO;
 	VendedorDaoHibernate vendedorHibernate;
 	MailController mail;
 	
	public VendedorController() {
		vendedorDAO = new VendedorDAO();
		vendedorHibernate = new VendedorDaoHibernate();
		mail = new MailController();
	}

	public boolean cadastroVendedor(VendedorDTO vendedorDTO){
		Vendedor vendedor = new Vendedor();
		
		vendedor.setNome(vendedorDTO.getNome());
		vendedor.setCidade(vendedorDTO.getCidade());	
		vendedor.setCpf(vendedorDTO.getCpf());
		vendedor.setEmail(vendedorDTO.getEmail());
		vendedor.setEstado(vendedorDTO.getEstado());
		vendedor.setPontos(0);
		vendedor.setSenha(vendedorDTO.getSenha());
		vendedor.setTelefone(vendedorDTO.getTelefone());
		
		boolean retorno = vendedorDAO.cadastroVendedor(vendedor);
		
		if(retorno) {
			String mensagem = "<h1>Olá " + vendedor.getNome() + ", seja bem vindo ao Vendas Plus!</h1><br><br><h2>Aproveite nossas campanhas e boas vendas.</h2>";
			return mail.sendMail(vendedor.getEmail(), mensagem);	
		}
		
		return false;
	}
	
	public List<ProdutoDTO> getCampanhas(){		
		ProdutoDTO produtoDTO;
		List<ProdutoDTO> produtosDTO = new ArrayList<ProdutoDTO>();
		
		List<Produto> produtos = vendedorHibernate.getCampanhas();
		
		for(Produto produto : produtos) {
			
			produtoDTO = new ProdutoDTO();
			produtoDTO.setNomeProduto(produto.getNomeProduto());
			produtoDTO.setPontosRecompensa(produto.getPontosRecompensa());
			produtoDTO.setInicioCampanha(produto.getInicioCampanha());
			produtoDTO.setIdEmpresa(produto.getIdEmpresa());
			produtoDTO.setIdProduto(produto.getIdProduto());
			
			String image64 = ImageController.getBase64FromResource(produto.getImg());
			produtoDTO.setImg(image64);
			
			produtosDTO.add(produtoDTO);
		}
		
		return produtosDTO;
	}
	
	public VendedorDTO getInfoVendedor(LogadoDTO login) {
		try {		
			VendedorDTO vendedorDTO = new VendedorDTO();
			Vendedor vendedor = vendedorDAO.getInfoVendedor(login.getUsername());
			
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
			Vendedor vendedor = vendedorDAO.getInfoVendedorByEmail(email);
			
			vendedorDTO.setIdVendedor(vendedor.getIdVendedor());
			vendedorDTO.setNome(vendedor.getNome());
			vendedorDTO.setPontos(vendedor.getPontos());
			vendedorDTO.setCidade(vendedor.getCidade());
			vendedorDTO.setEstado(vendedor.getEstado());
			vendedorDTO.setEmail(vendedor.getEmail());
			vendedorDTO.setTelefone(vendedor.getTelefone());
			
			return vendedorDTO;
		} catch (NullPointerException e) {
			System.out.println("Erro no retorno de dados do vendedor por email");
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
		venda.setImg(vendaDTO.getImg());
		
		String path = "c:\\git\\vendas-plus\\WebContent\\view\\img\\produtos\\";
		String newFileName = venda.getIdVendedor() + "-nota-" + venda.getNomeProduto() + "-" + venda.getImg();
		
		File oldfile = new File(path + venda.getImg());
		File newfile = new File(path + newFileName);

		if(oldfile.renameTo(newfile)){
			System.out.println("Rename succesful");
			venda.setImg(newFileName);
		}else{
			System.out.println("Rename failed");
		}
		
		return vendedorDAO.cadastroVenda(venda) ? "200" : null;
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
			
			String image64 = ImageController.getBase64FromResource(bonus.getImg());
			bonusDTO.setImg(image64);
			
			bonusDTOList.add(bonusDTO);
		}
		
		return bonusDTOList;
	}
	
	public boolean resgatarBonus(ValidateBonusDTO bonus) {
		return vendedorDAO.resgatarBonus(bonus.getPontos(), bonus.getCpf());
	}
}
