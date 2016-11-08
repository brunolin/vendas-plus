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
		EmpresaDTO e = new EmpresaDTO(0, 9945, 1234, "JAX-RS", "CWB", "PR", "BRUNOLIN@EMAIL", "asdasd");
		EmpresaController ec = new EmpresaController();		
		if(ec.cadastroEmpresa(e)){
			return "Deu boa";			
		}
		return "Deu ruim";
	}
 
}
