package br.vp.dto2;

import br.vp.model.Empresa;

public class EmpresaDTO {

	public Empresa empresa;

	public EmpresaDTO() {
		this.empresa = new Empresa();
	}
	
	public EmpresaDTO(Empresa empresa) {
		this.empresa = empresa;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
}
