package br.vp.rest;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("controller")
@Produces(MediaType.APPLICATION_JSON)
public class ControllerRest {
	
	@GET
	@Path("user")
	public String user(){		
		
		try{
			return "BOA";
			
		} catch(NullPointerException ex) {
			System.out.println(ex);
			return "Deu Ruim";
		}
		

	}
}