package br.vp.rest2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import br.vp.dto.LogadoDTO;

@Path("controller")
@Produces(MediaType.APPLICATION_JSON)
public class ControllerRest {
	
	/**
	 * Retorna os dados do usu�rios resgatados da sess�o
	 * @param request
	 * @return
	 */
	@GET
	@Path("user")
	public LogadoDTO user(@Context HttpServletRequest request){		
		
		try{
			HttpSession session = request.getSession();
			LogadoDTO user = new LogadoDTO();
			
			user.setType(session.getAttribute("type").toString());
			user.setUsername(session.getAttribute("username").toString());
			return user;
			
		} catch(NullPointerException ex) {
			System.out.println(ex);
			return null;
		}
	}
	
	/**
	 * Tira o usu�rio da sess�o
	 * @param request
	 * @return
	 */
	@GET
	@Path("logout")
	public boolean logout(@Context HttpServletRequest request){		
		
		try{
			HttpSession session = request.getSession();
			session.invalidate();
			
			return true;
			
		} catch(NullPointerException ex) {
			System.out.println(ex);
			return false;
		}
	}	
}