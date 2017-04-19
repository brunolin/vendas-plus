package br.vp.rest2;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.vp.controller2.EmpresaController;
import br.vp.dao2.EmpresaDAO;
import br.vp.dto2.EmpresaDTO;
import br.vp.dto2.LogadoDTO;
import br.vp.dto2.ProdutoDTO;
import br.vp.dto2.VendasDTO;;
/**
 * Classe responsável por controlar as requisições na parte da empresa
 * @author Brunolin
 *
 */
@Path("empresa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpresaRest {
	
	EmpresaController controller = new EmpresaController();
	
	/**
	 * Responsável por cadastrar uma nova campanha
	 * @param produto
	 */
	@POST
	@Path("cadastrarCampanha")
	public void cadastrarCampanha(ProdutoDTO produto) {
		controller.cadastrarCampanha(produto);
    }
	
	/**
	 * Responsável por atualizar uma campanha já existente
	 * @param produto
	 */
	@POST
	@Path("atualizarCampanha")
	public void atualizarCampanha(ProdutoDTO produto) {
		controller.atualizarCampanha(produto);
    }
	
	/**
	 * Retorna dados de uma empresa em específico
	 * @param login
	 * @return
	 */
	@POST
	@Path("getInfoEmpresa")
	public EmpresaDTO getInfoEmpresa(LogadoDTO login) {
		EmpresaDTO empresa = controller.getInfoEmpresa(login);
		return empresa;
    }	
	
	/**
	 * Retorna notas pendentes de uma empresa
	 * @param id
	 * @return
	 */
	@GET
	@Path("notasPendentes/{id}")
	public ArrayList<VendasDTO> notasPendentes(@PathParam("id") int id) {
		return controller.notasPendentes(id);
	}
	
	/**
	 * Retorna as campanhas de uma empresa
	 * @param id
	 * @return
	 */
	@GET
	@Path("getCampanhas/{id}")
	public ArrayList<ProdutoDTO> getCampanhas(@PathParam("id") int id) {
		return controller.getCampanhasId(id);
	}
	
	/**
	 * Retorna todas as notas de uma empresa
	 * @param id
	 * @return
	 */
	@GET
	@Path("getNotasEmpresa/{id}")
	public ArrayList<VendasDTO> getNotasEmpresa(@PathParam("id") int id) {
		return controller.getNotasEmpresa(id);
	}
	
	/**
	 * Confirma uma venda por parte da empresa
	 * @param venda
	 * @return
	 */
	@POST
	@Path("confirmarNota")
	public boolean confirmarNota(VendasDTO venda) {
		return controller.confirmarNota(venda);
	}
	
	/**
	 * Reprova uma venda por parte da empresa
	 * @param venda
	 * @return
	 */
	@POST
	@Path("reprovarNota")
	public boolean reprovarNota(VendasDTO venda) {
		return controller.reprovarNota(venda);
	}
}