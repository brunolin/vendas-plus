package br.vp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity()
@Table(name="TB_EMPRESA", schema="system")
public class Empresa {

    @Id()
    @Column(name="ID_EMPRESA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
	@SequenceGenerator(name = "id_seq", sequenceName = "id_seq", allocationSize = 1)
	public int idEmpresa;
	
    @Column(name="TELEFONE")
	public Long telefone;
    
    @Column(name="CNPJ")
	public Long cnpj;
    
    @Column(name="NOME_EMPRESA")
	public String nomeEmpresa;
    
    @Column(name="CIDADE")
	public String cidade;
    
    @Column(name="ESTADO")
	public String estado;
    
    @Column(name="EMAIL")
	public String email;
    
    @Column(name="SENHA")
	public String senha;
	
	public Empresa(){
		
	}
	
	public Empresa(int id,  long telefone, long cnpj, String nome, String cidade, String estado, String email, String senha){
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

	@Override
	public String toString() {
		return "EmpresaDTO [idEmpresa=" + idEmpresa + ", telefone=" + telefone + ", cnpj=" + cnpj + ", nomeEmpresa="
				+ nomeEmpresa + ", cidade=" + cidade + ", estado=" + estado + ", email=" + email + ", senha=" + senha + "]";
	}
	
	
	
}
