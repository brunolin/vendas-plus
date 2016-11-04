package br.vp.bean;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.vp.dto.*;
import br.vp.controller.VendedorController;

@Named("vendedorBean")
@SessionScoped
public class VendedorBean implements Serializable {

	private static final long serialVersionUID = -2647767011104840956L;

	private VendedorDTO vendedor;
	private VendedorController vendedorCtrl;

	public VendedorBean() {
		vendedor = new VendedorDTO();
		vendedorCtrl = new VendedorController();
	}

	public VendedorDTO getVendedor() {
		return vendedor;
	}

	public void setVendedor(VendedorDTO vendedor) {
		this.vendedor = vendedor;
	}

	public String incluir() {
		FacesContext context = FacesContext.getCurrentInstance();

		System.out.println(vendedor.toString());

		boolean vendedorDTO = vendedorCtrl.cadastroVendedor(vendedor);

		if (vendedorDTO) {

			vendedor = new VendedorDTO();

			System.out.println("vendedor inserido");
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "vendedor inserido", ""));

		} else {

			System.out.println("vendedor n�o inserido");
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "vendedor n�o adicionado", ""));
		}
		return "cadastroVendedor";
	}
}