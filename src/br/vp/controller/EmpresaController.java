package br.vp.controller;

import br.vp.dao.EmpresaDAO;
import br.vp.dto.EmpresaDTO;

public class EmpresaController {
	
	public EmpresaController() {
		
	}
	
	public boolean cadastroEmpresa(EmpresaDTO empresa){
		EmpresaDAO empresaDAO = new EmpresaDAO();
		
		return empresaDAO.cadastroEmpresa(empresa);		 
	}
}
