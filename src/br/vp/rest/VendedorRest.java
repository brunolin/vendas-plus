package br.vp.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.vp.controller.VendedorController;
import br.vp.dao.VendedorDAO;
import br.vp.dto.BonusDTO;
import br.vp.dto.LogadoDTO;
import br.vp.dto.ProdutoDTO;
import br.vp.dto.ValidateBonusDTO;
import br.vp.dto.VendasDTO;
import br.vp.dto.VendedorDTO;
/**
 * Classe responsável por controlar as requisições na parte de vendedor
 * @author Brunolin
 *
 */
@Path("vendedor")
@Produces(MediaType.APPLICATION_JSON)
public class VendedorRest {

	VendedorController controller = new VendedorController();
	
	/**
	 * Retorna as campanhas disponíveis
	 * @return
	 */
	@GET
	@Path("getCampanhas")
	public List<ProdutoDTO> getProdutos() {
		List<ProdutoDTO> produtos = controller.getCampanhas();
		return produtos;
	}
	
	/**
	 * Retorna dados de um vendedor
	 * @param login
	 * @return
	 */
	@POST
	@Path("getInfoVendedor")
	public VendedorDTO getInfoVendedor(LogadoDTO login) {		
		VendedorDTO vendedor = 	controller.getInfoVendedor(login);	
		return vendedor;
	}
	
	@SuppressWarnings("finally")
	@GET
	@Path("getInfoVendedorByEmail")
	public VendedorDTO getInfoVendedorByEmail(@QueryParam("email") String email) {
		
		System.out.println(email);
		
		VendedorDTO vendedor = new VendedorDTO();
		try {
			vendedor = controller.getInfoVendedorByEmail(URLDecoder.decode(email, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			return vendedor;			
		}
	}
	
	/**
	 * Cadastra uma venda feita por um vendedor
	 * @param venda
	 * @return
	 */
	@POST
	@Path("cadastrarNota")
	public String cadastrarNota(VendasDTO venda) {			
		return controller.cadastrarNota(venda);
	}
	
	/**
	 * Retorna as notas específicas de um vendedor
	 * @param id
	 * @return
	 */
	@GET
	@Path("getNotasVendedor/{id}")
	public List<VendasDTO> getNotas(@PathParam("id") int id){
		List<VendasDTO> vendas = controller.getNotas(id);
		return vendas;
	}
	
	/**
	 * Retorna os bonus disponíveis para resgate
	 * @return
	 */
	@GET
	@Path("getBonus")
	public ArrayList<BonusDTO> getBonus() {
		ArrayList<BonusDTO> produtos = controller.getBonus();
		return produtos;
	}
	
	/**
	 * Realiza o resgate de um bonus por parte do vendedor
	 * @param bonus
	 * @return
	 */
	@POST
	@Path("resgatarBonus")
	public boolean resgatarBonus(ValidateBonusDTO bonus){
		return controller.resgatarBonus(bonus);
	}
}