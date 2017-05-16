package br.vp.controller;

import java.util.ArrayList;

import br.vp.dao.EmpresaDAO;
import br.vp.dto.EmpresaDTO;
import br.vp.dto.LogadoDTO;
import br.vp.dto.ProdutoDTO;
import br.vp.dto.VendasDTO;

/**
 * 
 * @author Brunolin
 *	Classe de controlle que é a ponte entre a Bean e a DAO
 */
public class EmpresaController {
	
	EmpresaDAO empresaDAO;
	
	public EmpresaController() {
		empresaDAO = new EmpresaDAO();
	}
	
	public boolean cadastroEmpresa(EmpresaDTO empresa){
		return empresaDAO.cadastroEmpresa(empresa);		 
	}
	
	public void cadastrarCampanha(ProdutoDTO produto) {
		empresaDAO.cadastroCampanha(produto);
	}
	
	public void atualizarCampanha(ProdutoDTO produto) {
		empresaDAO.atualizarCampanha(produto);
	}
	
	public EmpresaDTO getInfoEmpresa(LogadoDTO login) {
		return empresaDAO.getInfoEmpresa(login.getUsername().toString());
	}
	
	public ArrayList<VendasDTO> notasPendentes(int id) {
		return empresaDAO.notasPendentes(id);
	}
	
	public ArrayList<ProdutoDTO> getCampanhasId(int id) {
		return empresaDAO.getCampanhas(id);
	}
	
	public ArrayList<VendasDTO> getNotasEmpresa(int id) {
		return empresaDAO.getNotasEmpresa(id);
	}
	
	public boolean confirmarNota(VendasDTO venda) {
		return empresaDAO.confirmarVenda(venda);
	}
	
	public boolean reprovarNota(VendasDTO venda) {
		return empresaDAO.reprovarVenda(venda);
	}
	
}
