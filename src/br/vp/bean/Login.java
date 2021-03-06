package br.vp.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.vp.controller.SessionController;
import br.vp.dao.LoginDAO;
/**
 * 
 * @author Brunolin
 *	Classe Bean respons�vel pela tela de login
 *	com um m�todo de validar login que adiciona o usu�rio na sess�o
 */
@ManagedBean
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	
	private String pwd;
	private String msg;
	private String user;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Caso os dados de login informados pelo usu�rio estiverem ok
	 * seus dados s�o jogados na sess�o e ele � redirecionado para a tela inicial do sistema
	 * @return
	 * @throws IOException
	 */
	public String validateLogin() throws IOException {
		boolean valid = LoginDAO.validate(user, pwd);
		if (valid) {
			HttpSession session = SessionController.getSession();
			session.setAttribute("username", user);
			if(user.length() == 11){
				session.setAttribute("type", "vendedor");	
			} else {
				session.setAttribute("type", "empresa");
			}
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.html");
			
			return "admin";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"CNPJ/CPF e/ou senha incorretos",
							"Por favor, entre com dados v�lidos"));
			return "login";
		}
	}
}

