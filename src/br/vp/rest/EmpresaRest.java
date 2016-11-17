package br.vp.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.vp.dto.*;
import br.vp.dao.EmpresaDAO;;

@Path("empresa")
@Produces(MediaType.APPLICATION_JSON)
public class EmpresaRest {

	@POST
	@Path("cadastrarCampanha")
	public String cadastrarCampanha(ProdutoDTO produto) {
		
		EmpresaDAO empresaDAO = new EmpresaDAO();
		empresaDAO.cadastroCampanha(produto);
		
		return "200";
  }
}