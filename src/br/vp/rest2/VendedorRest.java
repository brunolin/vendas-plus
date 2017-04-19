package br.vp.rest2;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.vp.controller2.VendedorController;
import br.vp.dao2.VendedorDAO;
import br.vp.dto2.BonusDTO;
import br.vp.dto2.LogadoDTO;
import br.vp.dto2.ProdutoDTO;
import br.vp.dto2.ValidateBonusDTO;
import br.vp.dto2.VendasDTO;
import br.vp.dto2.VendedorDTO;
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
	public ArrayList<ProdutoDTO> getProdutos() {
		ArrayList<ProdutoDTO> produtos = controller.getCampanhas();
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
	public ArrayList<VendasDTO> getNotas(@PathParam("id") int id){
		ArrayList<VendasDTO> vendas = controller.getNotas(id);
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