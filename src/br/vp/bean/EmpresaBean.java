package br.vp.bean;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.vp.dto.*;
import br.vp.controller.EmpresaController;
/**
 * 
 * @author Brunolin
 *	Classe Bean responsável pela tela de cadastro de empresa
 *	com metodo de incluir que faz ligação com a controller da Empresa
 */
@Named("empresaBean")
@SessionScoped
public class EmpresaBean implements Serializable {

	private static final long serialVersionUID = -2647767011104840956L;

	private EmpresaDTO empresa;
	private EmpresaController empresaCtrl;

	public EmpresaBean() {
		empresa = new EmpresaDTO();
		empresaCtrl = new EmpresaController();	
	}

	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setMinhaEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}

	/**
	 * Envia uma EmpresaDTO para a controller, caso esteja tudo ok, o usuário será direcionado para a tela de login
	 * @return
	 * @throws IOException
	 */
	public String incluir() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		
		System.out.println(empresa.toString());

		boolean empresaDTO = empresaCtrl.cadastroEmpresa(empresa);

		if (empresaDTO) {

			empresa = new EmpresaDTO();
			
			System.out.println("Empresa inserida");
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Empresa inserida", ""));
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.html");
		} else {
			
			System.out.println("Empresa não inserida");		
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Empresa não adiciona", ""));
		}
		
		return "index";
	}
}
