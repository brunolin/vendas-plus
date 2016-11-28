package br.vp.controller;

import br.vp.dao.EmpresaDAO;
import br.vp.dto.EmpresaDTO;

/**
 * 
 * @author Brunolin
 *	Classe de controlle que é a ponte entre a Bean e a DAO
 */
public class EmpresaController {
	
	public EmpresaController() {
		
	}
	
	public boolean cadastroEmpresa(EmpresaDTO empresa){
		EmpresaDAO empresaDAO = new EmpresaDAO();
		
		return empresaDAO.cadastroEmpresa(empresa);		 
	}
}
