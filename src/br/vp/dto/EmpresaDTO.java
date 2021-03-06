package br.vp.dto;

import br.vp.model.Empresa;

public class EmpresaDTO {

	public Empresa empresa;

	public int idEmpresa;
	public Long telefone, cnpj;
	public String nomeEmpresa, cidade, estado, email, senha;
	
	public EmpresaDTO(){
		
	}
	
	public EmpresaDTO(int id,  long telefone, long cnpj, String nome, String cidade, String estado, String email, String senha){
		this.idEmpresa = id;
		this.telefone = telefone;
		this.cnpj = cnpj;
		this.nomeEmpresa = nome;
		this.cidade = cidade;
		this.estado = estado;
		this.email = email;
		this.senha = senha;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
