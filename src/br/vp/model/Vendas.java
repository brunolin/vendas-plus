package br.vp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity()
@Table(name="TB_VENDAS", schema="vendasplus")

public class Vendas {
	
    @Id()
    @Column(name="ID_VENDA")
    public String idVenda;
    
    @Column(name="DATA_VENDA")
	public String data;
    
    @Column(name="NOME_PRODUTO")
    public String nomeProduto;
	
    @Column(name="APROVADA")
	public String aprovada;
    
    @Column(name="IMG")
	public String img;
    
    @Column(name="ID_PRODUTO")
	public int idProduto;
    
    @Column(name="ID_VENDEDOR")
	public int idVendedor;
    
    @Column(name="ID_EMPRESA")
	public int idEmpresa;
	
	public Vendas() {
		
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getAprovada() {
		return aprovada;
	}

	public void setAprovada(String aprovada) {
		this.aprovada = aprovada;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(String idVenda) {
		this.idVenda = idVenda;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public int getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}
