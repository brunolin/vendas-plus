package br.vp.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import br.vp.controller.EmpresaController;
import br.vp.dto.EmpresaDTO;

@Path("/empresa")
public class EmpresaRest {

	@GET
	@Path("/getProdutos")
	public String getProdutos() {
		return "Deu ruim";
  }
