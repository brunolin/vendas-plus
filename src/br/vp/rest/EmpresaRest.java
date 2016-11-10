package br.vp.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.vp.dto.EmpresaDTO;

@Path("/empresa")
public class EmpresaRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getProdutos")
	public EmpresaDTO getProdutos() {
		EmpresaDTO e = new EmpresaDTO(9999, 4444444, 5555555, "Wally's Hetero", "São José", "Paraná", "brunolin@gmail.com", "SEnHA");		
		return e;
  }
}