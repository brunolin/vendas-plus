package br.vp.bean;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.vp.dto.*;
import br.vp.controller.EmpresaController;

@Named("empresaBean")
@SessionScoped
public class EmpresaBean implements Serializable {

	private static final long serialVersionUID = -2647767011104840956L;

	private EmpresaDTO empresa;
	private EmpresaController empresaCtrl;

	public EmpresaBean() {
		empresa = new EmpresaDTO();
		empresaCtrl = new EmpresaController();
		try{
			empresa.setIdEmpresa(empresaCtrl.getIdEmpresa());
		} catch (NullPointerException e){
			e.printStackTrace();
			System.out.println("Deu ruim na Id novo da Empresa");
		}
		
	}

	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setMinhaEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}

	public String incluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		System.out.println(empresa.toString());

		boolean empresaDTO = empresaCtrl.cadastroEmpresa(empresa);

		if (empresaDTO) {

			empresa = new EmpresaDTO();
			
			System.out.println("Empresa inserida");
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Empresa inserida", ""));

		} else {
			
			System.out.println("Empresa não inserida");		
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Empresa não adiciona", ""));
		}
		return "cadastroEmpresa";
	}
}
