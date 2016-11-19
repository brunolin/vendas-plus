package br.vp.rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.vp.dao.EmpresaDAO;
import br.vp.dto.ProdutoDTO;
import br.vp.dto.VendasDTO;;

@Path("empresa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpresaRest {

	@POST
	@Path("cadastrarCampanha")
	public void cadastrarCampanha(ProdutoDTO produto) {
		
		EmpresaDAO empresaDAO = new EmpresaDAO();
		empresaDAO.cadastroCampanha(produto);
  }
	
	@GET
	@Path("notasPendentes/{id}")
	public ArrayList<VendasDTO> notasPendentes(@PathParam("id") int id) {
		
		EmpresaDAO empresaDAO = new EmpresaDAO();
		ArrayList<VendasDTO> vendas = empresaDAO.notasPendentes(id);
		
		return vendas;
	}
	
	@POST
	@Path("confirmarNota")
	public boolean confirmarNota(VendasDTO venda) {
		
		EmpresaDAO empresaDAO = new EmpresaDAO();
		
		return empresaDAO.confirmarVenda(venda);
	}
	
	@POST
	@Path("reprovarNota")
	public boolean reprovarNota(VendasDTO venda) {
		
		EmpresaDAO empresaDAO = new EmpresaDAO();
		
		return empresaDAO.confirmarVenda(venda);
	}
}