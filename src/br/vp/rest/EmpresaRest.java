package br.vp.rest;

import javax.ws.rs.GET; 
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/helloworld")
public class EmpresaRest {

	@GET
	@Produces("text/plain")
	public String exibir(){
		return "Hello World";
	}
} 
