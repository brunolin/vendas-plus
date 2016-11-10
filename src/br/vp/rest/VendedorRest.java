package br.vp.rest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.vp.dao.VendedorDAO;
import br.vp.dto.ProdutoDTO;

@Path("/vendedor")
public class VendedorRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getCampanhas")
	public ArrayList<ProdutoDTO> getProdutos() {
		
		VendedorDAO vendedorDAO = new VendedorDAO();
		
		ArrayList<ProdutoDTO> produtos = vendedorDAO.getCampanhas();
		
		return produtos;
  }
}