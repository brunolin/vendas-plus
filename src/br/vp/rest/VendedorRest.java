package br.vp.rest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.vp.dao.VendedorDAO;
import br.vp.dto.ProdutoDTO;
import br.vp.dto.VendasDTO;

@Path("vendedor")
@Produces(MediaType.APPLICATION_JSON)
public class VendedorRest {

	@GET
	@Path("getCampanhas")
	public ArrayList<ProdutoDTO> getProdutos() {
		
		VendedorDAO vendedorDAO = new VendedorDAO();
		
		ArrayList<ProdutoDTO> produtos = vendedorDAO.getCampanhas();
		
		return produtos;
  }
	
	@POST
	@Path("cadastrarNota")
	public String cadastrarNota(VendasDTO venda) {
		
		VendedorDAO vendedorDAO = new VendedorDAO();
		vendedorDAO.cadastroVenda(venda);			
		
		return "200";
  }
}