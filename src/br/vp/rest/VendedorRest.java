package br.vp.rest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.vp.dao.VendedorDAO;
import br.vp.dto.BonusDTO;
import br.vp.dto.LogadoDTO;
import br.vp.dto.ProdutoDTO;
import br.vp.dto.ValidateBonusDTO;
import br.vp.dto.VendasDTO;
import br.vp.dto.VendedorDTO;
/**
 * Classe respons�vel por controlar as requisi��es na parte de vendedor
 * @author Brunolin
 *
 */
@Path("vendedor")
@Produces(MediaType.APPLICATION_JSON)
public class VendedorRest {

	/**
	 * Retorna as campanhas dispon�veis
	 * @return
	 */
	@GET
	@Path("getCampanhas")
	public ArrayList<ProdutoDTO> getProdutos() {
		
		VendedorDAO vendedorDAO = new VendedorDAO();
		
		ArrayList<ProdutoDTO> produtos = vendedorDAO.getCampanhas();
		
		return produtos;
	}
	
	/**
	 * Retorna dados de um vendedor
	 * @param login
	 * @return
	 */
	@POST
	@Path("getInfoVendedor")
	public VendedorDTO getInfoVendedor(LogadoDTO login) {
		
		VendedorDAO vendedorDAO = new VendedorDAO();
		
		VendedorDTO vendedor = vendedorDAO.getInfoVendedor(login.getUsername());
		
		return vendedor;
	}
	
	/**
	 * Cadastra uma venda feita por um vendedor
	 * @param venda
	 * @return
	 */
	@POST
	@Path("cadastrarNota")
	public String cadastrarNota(VendasDTO venda) {
		
		VendedorDAO vendedorDAO = new VendedorDAO();
		vendedorDAO.cadastroVenda(venda);			
		
		return "200";
	}
	
	/**
	 * Retorna as notas espec�ficas de um vendedor
	 * @param id
	 * @return
	 */
	@GET
	@Path("getNotasVendedor/{id}")
	public ArrayList<VendasDTO> getNotas(@PathParam("id") int id){
		VendedorDAO vendedorDAO = new VendedorDAO();
		
		ArrayList<VendasDTO> vendas = vendedorDAO.getNotasVendedor(id);
		
		return vendas;
	}
	
	/**
	 * Retorna os bonus dispon�veis para resgate
	 * @return
	 */
	@GET
	@Path("getBonus")
	public ArrayList<BonusDTO> getBonus() {
		
		VendedorDAO vendedorDAO = new VendedorDAO();
		ArrayList<BonusDTO> produtos = vendedorDAO.getBonus();
		
		return produtos;
	}
	
	/**
	 * Realiza o resgate de um bonus por parte do vendedor
	 * @param bonus
	 * @return
	 */
	@POST
	@Path("resgatarBonus")
	public boolean resgatarBonus(ValidateBonusDTO bonus){
		
		VendedorDAO vendedorDAO = new VendedorDAO();
		return vendedorDAO.resgatarBonus(bonus.getPontos(), bonus.getCpf());
	}
}