package br.vp.test;

import br.vp.dao.*;
import br.vp.controller.*;
import br.vp.dto.*;
import br.vp.jdbc.*;

public class Teste {

	public static void main(String[] args) {
		
		EmpresaDTO edto = new EmpresaDTO(2, 96696969, 123456789, "Wally's viadagem", "Piroca", "Paraná", "punheta@gmail.com", "abcd1234");
		EmpresaDAO edao = new EmpresaDAO();
		
		edao.cadastroEmpresa(edto);
		

	}


}
